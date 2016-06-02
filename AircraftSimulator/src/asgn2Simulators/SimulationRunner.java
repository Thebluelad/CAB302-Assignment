/**
 * 
 * This file is part of the AircraftSimulator Project, written as 
 * part of the assessment for CAB302, semester 1, 2016. 
 * 
 */
package asgn2Simulators;

import java.awt.Color;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import asgn2Aircraft.Aircraft;
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
	XYSeriesCollection data;
	
	public static void main(String[] args) {
		
		//Think I'm going to need to edit this heaps
		
		final int NUM_ARGS = 9; 
		//Simulator s = null; 
		//Log l = null; 
		
		//try {
			switch (args.length) {
				case NUM_ARGS: {
					//s = createSimulatorUsingArgs(args);
					new GUISimulator("Aircraft Simulator");
					break;
				}
				case 0: {
					//s = new Simulator();
					new GUISimulator("Aircraft Simulator");
					break;
				}
				//Case 10 was added by me but not sure if it should stay.
				case 10: {
					if (args[9].compareTo("true") == 0){
						//run the gui
						//GUISimulator gui = new GUISimulator("Aircraft Simulator");
					} else {
						//run the simulator only
					}
					break;	
				}
				default: {
					printErrorAndExit(); 
				}
			}
			//l = new Log();
//		} catch (SimulationException | IOException e1) {
//			e1.printStackTrace();
//			System.exit(-1);
		}
	
		//Run the simulation 
//		SimulationRunner sr = new SimulationRunner(s,l);
//		try {
//			sr.runSimulation();
//		} catch (Exception e) {
//			e.printStackTrace();
//			System.exit(-1);
//		} 
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
		
		//Not sure if the following line actually does anything
		//JFrame.setDefaultLookAndFeelDecorated(true);
		
		//GUISimulator gui = new GUISimulator("Aircraft Simulator");
		//SwingUtilities.invokeLater(new GUISimulator("Aircraft Simulator"));
		//gui.setBackground(new Color(238, 30, 238));
		//XYSeriesCollection data = new XYSeriesCollection();
		this.data = new XYSeriesCollection();
		XYSeries first = new XYSeries("First");
		XYSeries business = new XYSeries("Business");
		XYSeries premium = new XYSeries("Premium");
		XYSeries economy = new XYSeries("Economy");
		XYSeries empty = new XYSeries("Empty");
		XYSeries total = new XYSeries("Total Bookings");
		Flights flightsDaily;
		Bookings daily;
		
		//Main simulation loop 
		for (int time=0; time<=Constants.DURATION; time++) {
			this.sim.resetStatus(time); 
			this.sim.rebookCancelledPassengers(time); 
			this.sim.generateAndHandleBookings(time);
			this.sim.processNewCancellations(time);
			if (time >= Constants.FIRST_FLIGHT) {
				//GUI stuff
				flightsDaily = this.sim.getFlights(time);
				daily = flightsDaily.getCurrentCounts();
				first.add(time, daily.getNumFirst());
				business.add(time, daily.getNumBusiness());
				premium.add(time, daily.getNumPremium());
				economy.add(time, daily.getNumEconomy());
				empty.add(time, daily.getAvailable());
				total.add(time, daily.getTotal());
				
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
		this.data.addSeries(first);
		this.data.addSeries(economy);
		this.data.addSeries(business);
		this.data.addSeries(premium);
		this.data.addSeries(empty);
		this.data.addSeries(total);
		//gui.data = (XYDataset)data;
		this.sim.finaliseQueuedAndCancelledPassengers(Constants.DURATION); 
		this.log.logQREntries(Constants.DURATION, sim);
		this.log.finalise(this.sim);
	}
	
	XYDataset returnData() {
		return (XYDataset)this.data;
	}
}
