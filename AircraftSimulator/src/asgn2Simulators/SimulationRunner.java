/**
 * 
 * This file is part of the AircraftSimulator Project, written as 
 * part of the assessment for CAB302, semester 1, 2016. 
 * 
 */
package asgn2Simulators;

import java.io.IOException;

import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import asgn2Aircraft.AircraftException;
import asgn2Aircraft.Bookings;
import asgn2Passengers.PassengerException;

/**
 * Class to operate the simulation, taking parameters and utility methods from the Simulator
 * to control the available resources, and using Log to provide a record of operation. 
 * 
 * @author hogan
 *
 */ 
public class SimulationRunner {
	/**
	 * Main program for the simulation 
	 * 
	 * @param args Arguments to the simulation - 
	 * see {@link asgn2Simulators.SimulationRunner#printErrorAndExit()}
	 */
	XYSeriesCollection data1;
	XYSeriesCollection data2;
	String textOutput;
	
	public static void main(String[] args) {
		
		//Think I'm going to need to edit this heaps
		
		final int NUM_ARGS = 9; 
		Simulator s = null; 
		Log l = null; 
		
		try {
			switch (args.length) {
				case NUM_ARGS: {
					new GUISimulator("Aircraft Simulator");
					break;
				}
				case 0: {
					new GUISimulator("Aircraft Simulator");
					break;
				}
				//Case 10: False triggers the command line
				case 10: {
					if (args[9].compareTo("false") == 0) {
						s = createSimulatorUsingArgs(args);
						l = new Log();
						
						SimulationRunner sr = new SimulationRunner(s,l);
						try {
							sr.runSimulation();
						} catch (Exception e) {
							e.printStackTrace();
							System.exit(-1);
						} 
					} else {
						new GUISimulator("Aircraft Simulator");
					}
					break;	
				}
				default: {
					printErrorAndExit(); 
				}
			}
			
		} catch (SimulationException | IOException e1) {
			e1.printStackTrace();
			System.exit(-1);
		}
	}
	
	/**
	 * Helper to process args for Simulator  
	 * 
	 * @param args Command line arguments (see usage message) 
	 * @return new <code>Simulator</code> from the arguments 
	 * @throws SimulationException if invalid arguments. 
	 * See {@link asgn2Simulators.Simulator#Simulator(int, int, double, double, double, double, double, double, double)}
	 */
	private static Simulator createSimulatorUsingArgs(String[] args) throws SimulationException {
		int seed = Integer.parseInt(args[0]);
		int maxQueueSize = Integer.parseInt(args[1]);
		double meanBookings = Double.parseDouble(args[2]);
		double sdBookings = Double.parseDouble(args[3]);
		double firstProb = Double.parseDouble(args[4]);
		double businessProb = Double.parseDouble(args[5]);
		double premiumProb = Double.parseDouble(args[6]);
		double economyProb = Double.parseDouble(args[7]);
		double cancelProb = Double.parseDouble(args[8]);
		return new Simulator(seed,maxQueueSize,meanBookings,sdBookings,firstProb,businessProb,
						  premiumProb,economyProb,cancelProb);	
	}
	
	/**
	 *  Helper to generate usage message 
	 */
	private static void printErrorAndExit() {
		String str = "Usage: java asgn2Simulators.SimulationRunner [SIM Args]\n";
		str += "SIM Args: seed maxQueueSize meanBookings sdBookings "; 
		str += "firstProb businessProb premiumProb economyProb cancelProb\n";
		str += "If no arguments, default values are used\n";
		System.err.println(str);
		System.exit(-1);
	}
	
	
	private Simulator sim;
	private Log log; 

	/**
	 * Constructor just does initialisation 
	 * 
	 * @param sim <code>Simulator</code> containing simulation parameters
	 * @param log <code>Log</code> object supporting record of operation 
	 */
	public SimulationRunner(Simulator sim, Log log) {
		this.sim = sim;
		this.log = log;
	}

	/**
	 * Method to run the simulation from start to finish. 
	 * Exceptions are propagated upwards as necessary 
	 * 
	 * @throws AircraftException See methods from {@link asgn2Simulators.Simulator} 
	 * @throws PassengerException See methods from {@link asgn2Simulators.Simulator} 
	 * @throws SimulationException See methods from {@link asgn2Simulators.Simulator} 
	 * @throws IOException on logging failures See methods from {@link asgn2Simulators.Log} 

	 */
	public void runSimulation() throws AircraftException, PassengerException, SimulationException, IOException {
		this.sim.createSchedule();
		this.log.initialEntry(this.sim);
		
		//Initialising the datasets and series objects
		this.data1 = new XYSeriesCollection();
		this.data2 = new XYSeriesCollection();
		XYSeries first = new XYSeries("First");
		XYSeries business = new XYSeries("Business");
		XYSeries premium = new XYSeries("Premium");
		XYSeries economy = new XYSeries("Economy");
		XYSeries empty = new XYSeries("Empty");
		XYSeries total = new XYSeries("Total Bookings");
		XYSeries queueSize = new XYSeries("Total Queued");
		XYSeries refusedSize = new XYSeries("Total Refused");
		
		//Objects to contain daily flights and bookings
		Flights flightsDaily;
		Bookings daily;
		
		//Header for the text output string
		this.textOutput = "The values for each class on a given day: \n\n";
		
		//Main simulation loop 
		for (int time=0; time<=Constants.DURATION; time++) {
			this.sim.resetStatus(time); 
			this.sim.rebookCancelledPassengers(time); 
			this.sim.generateAndHandleBookings(time);
			this.sim.processNewCancellations(time);
			
			//Adding to the queue and refused series
			queueSize.add(time, this.sim.numInQueue());
			refusedSize.add(time, this.sim.numRefused());
			
			if (time >= Constants.FIRST_FLIGHT) {
				//Collecting data and adding to the series
				flightsDaily = this.sim.getFlights(time);
				daily = flightsDaily.getCurrentCounts();
				first.add(time, daily.getNumFirst());
				business.add(time, daily.getNumBusiness());
				premium.add(time, daily.getNumPremium());
				economy.add(time, daily.getNumEconomy());
				empty.add(time, daily.getAvailable());
				total.add(time, daily.getTotal());
				
				//Collecting data and adding to the text output string
				this.textOutput += ("The current day is: " + time + "\n");
				this.textOutput += ("First: " + daily.getNumFirst() + "\n");
				this.textOutput += ("Business: " + daily.getNumBusiness() + "\n");
				this.textOutput += ("Premium: " + daily.getNumPremium() + "\n");
				this.textOutput += ("Economy: " + daily.getNumEconomy() + "\n");
				this.textOutput += ("Queued: " + this.sim.numInQueue() + "\n");
				this.textOutput += ("Refused: " + this.sim.numRefused() + "\n");
				this.textOutput += ("End of day: " + time + "\n\n");
				
				this.sim.getStatus(time);
				this.sim.processUpgrades(time);
				this.sim.processQueue(time);
				this.sim.flyPassengers(time);
				this.sim.updateTotalCounts(time); 
				this.log.logFlightEntries(time, sim);
				
			} else {
				this.sim.processQueue(time);
			}
			//Log progress 
			this.log.logQREntries(time, this.sim);
			this.log.logEntry(time,this.sim);
		}
		//Adding the series to the dataset
		this.data1.addSeries(first);
		this.data1.addSeries(economy);
		this.data1.addSeries(business);
		this.data1.addSeries(premium);
		this.data1.addSeries(empty);
		this.data1.addSeries(total);
		this.data2.addSeries(queueSize);
		this.data2.addSeries(refusedSize);
		
		this.sim.finaliseQueuedAndCancelledPassengers(Constants.DURATION); 
		this.log.logQREntries(Constants.DURATION, sim);
		this.log.finalise(this.sim);
	}
	
	/**
	 * Method to get the data1's dataset
	 * 
	 * @return XYDataset containing data1's data
	 */
	public XYDataset returnData1() {
		return (XYDataset)this.data1;
	}
	
	/**
	 * Method to get data2's dataset
	 * 
	 * @return XYDataset containing data2's data
	 */
	public XYDataset returnData2() {
		return (XYDataset)this.data2;
	}
	
	/**
	 * Method to get the textOutput
	 * 
	 * @return String containing the text output
	 */
	public String returnTextOutput() {
		return this.textOutput;
	}
}
