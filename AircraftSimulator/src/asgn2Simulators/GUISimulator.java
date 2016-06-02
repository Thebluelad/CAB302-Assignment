/**
 * 
 * This file is part of the AircraftSimulator Project, written as 
 * part of the assessment for CAB302, semester 1, 2016. 
 * 
 */
package asgn2Simulators;

import java.awt.*;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import asgn2Aircraft.AircraftException;
import asgn2Aircraft.Bookings;
import asgn2Passengers.PassengerException;

/**
 * @author hogan
 *
 */
@SuppressWarnings("serial")
public class GUISimulator extends JFrame implements Runnable {

	/**
	 * @param arg0
	 * @throws HeadlessException
	 */
	JPanel mainPanel = new JPanel();
	XYDataset data;// = createDataset();
	JFreeChart chart;// = createChart(data);
	ChartPanel chartPanel;// = new ChartPanel(chart);
	JPanel titlePanel = new JPanel();
	JPanel bottomTextPanel = new JPanel();
	JPanel bottomButtonPanel = new JPanel();
	
	Simulator s = null;
	Log l = null;
	SimulationRunner sr;
	
	
	JLabel title = new JLabel("GUI For Aircraft Simulator");
	JLabel seedLbl = new JLabel("Seed:");
	JLabel dailyMeanLbl = new JLabel("Daily Mean:");
	JLabel queueSizeLbl = new JLabel("Queue Size:");
	JLabel cancellationLbl = new JLabel("Cancellation:");
	JLabel firstLbl = new JLabel("First:");
	JLabel businessLbl = new JLabel("Business:");
	JLabel premiumLbl = new JLabel("Premium:");
	JLabel economyLbl = new JLabel("Economy:");
	
	JTextField seedTxt = new JTextField(Integer.toString(Constants.DEFAULT_SEED));
	JTextField queueSizeTxt = new JTextField(Integer.toString(Constants.DEFAULT_MAX_QUEUE_SIZE));
	JTextField dailyMeanTxt = new JTextField(Double.toString(Constants.DEFAULT_DAILY_BOOKING_MEAN));
	JTextField cancellationTxt = new JTextField(Double.toString(Constants.DEFAULT_CANCELLATION_PROB));
	JTextField firstTxt = new JTextField(Double.toString(Constants.DEFAULT_FIRST_PROB));
	JTextField businessTxt = new JTextField(Double.toString(Constants.DEFAULT_BUSINESS_PROB));
	JTextField premiumTxt = new JTextField(Double.toString(Constants.DEFAULT_PREMIUM_PROB));
	JTextField economyTxt = new JTextField(Double.toString(Constants.DEFAULT_ECONOMY_PROB));
	
	JButton btnTest = new JButton("Test");
	JButton btnRunSimulation = new JButton("Run Simulation");
	
	//Frame used for the JDialog
	protected Frame frame;
	
	//Some potentially useful code
//	JOptionPane.showConfirmDialog(null, "Press one of these");
//	JOptionPane.showInputDialog(null, "Add some input");
//	JOptionPane.showMessageDialog(null, "This is the message");
	
	
	
	
	//Notes for the incredibly stupid.
	/*
	 * Need to make all the running happen here by creating a simulator from
	 * either the Constants, or the values in the boxes. Then when the button
	 * is pressed, the simulation should run and the results should be graphed.
	 * The Simulation runner the main in this function needs to be called in
	 * the SimulationRunner main (I think). This should all happen here with
	 * the exception of the graphing results which should probably still
	 * happen inside the Simulation Runner.
	 * 
	 * This function needs to set up it's own Simulation, Log, and Simulation
	 * Runner classes though and be able to run independently from Simulation
	 * Runner so it can be called whenever it needs to be used in Simulation
	 * Runner.
	 */
	
	
	
	
	public GUISimulator(String arg0) throws HeadlessException {
		super(arg0);      
		
		try {
			s = new Simulator();
			l = new Log();
		} catch (SimulationException | IOException e2) {
			e2.printStackTrace();
		}
		
		
		btnTest.addActionListener(new ActionListener() {
			  public void actionPerformed(ActionEvent e) {
			    JDialog d = new JDialog(frame, "Hello", true);
			    //d.setLocation(200, 200);
			    d.setLocationRelativeTo(mainPanel);
			    d.setSize(500, 500);
			    d.setVisible(true);
			    
			    //Create the new simulator using arguments from the various text fields
			  }
		});
		
		btnRunSimulation.addActionListener(new ActionListener() {
			  public void actionPerformed(ActionEvent e) {
				  
					try {
						s = createSimulatorUsingValues();
						l = new Log();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				 
				  sr = new SimulationRunner(s, l);
				  try {
					sr.runSimulation();
				} catch (AircraftException | PassengerException | SimulationException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				  data = sr.returnData();
				  drawGraph();
			  }
		});
		
		seedLbl.setVisible(true);
		seedLbl.setSize(305, 20);
		seedLbl.setLocation(20, 700);
		
		//btnTest's setup code
		btnTest.setBounds(100, 100, 100, 25);
		
		//mainPanel's setup code
		mainPanel.setBounds(0, 25, 700, 600);
		mainPanel.setBackground(new Color(17, 55, 97));
		//mainPanel.setLayout(null);
		mainPanel.add(btnTest);
		
		//TitlePanel's setup code
		titlePanel.setBounds(0, 0, 700, 25);
		titlePanel.setBackground(new Color(255, 255, 255));
		titlePanel.add(title);
		
		//bottomTextPanel's setup code
		bottomTextPanel.setBounds(0, 625, 700, 100);
		bottomTextPanel.setBackground(new Color(200, 230, 255));
		
		//bottomButtonPanel's setup code
		bottomButtonPanel.setBounds(0, 725, 700, 75);
		bottomButtonPanel.setBackground(new Color(17, 55, 97));
		bottomButtonPanel.setLayout(null);
		btnRunSimulation.setBounds(0, 725, 233, 25);
		btnRunSimulation.setLocation(233, 725);
		bottomButtonPanel.add(btnRunSimulation);
		
		//Adds for text areas and labels
		bottomTextPanel.add(seedLbl);			bottomTextPanel.add(seedTxt);
		bottomTextPanel.add(firstLbl);			bottomTextPanel.add(firstTxt);
		bottomTextPanel.add(dailyMeanLbl);		bottomTextPanel.add(dailyMeanTxt);
		bottomTextPanel.add(businessLbl);		bottomTextPanel.add(businessTxt);
		bottomTextPanel.add(queueSizeLbl);		bottomTextPanel.add(queueSizeTxt);
		bottomTextPanel.add(premiumLbl);		bottomTextPanel.add(premiumTxt);
		bottomTextPanel.add(cancellationLbl);	bottomTextPanel.add(cancellationTxt);
		bottomTextPanel.add(economyLbl);		bottomTextPanel.add(economyTxt);
		
		GridLayout bottom = new GridLayout(4, 4, 0, 0);
		bottomTextPanel.setLayout(bottom);
		
		this.getContentPane().add(titlePanel);
		this.getContentPane().add(bottomTextPanel);
		this.getContentPane().add(mainPanel);
		this.getContentPane().add(bottomButtonPanel);
		
		this.setResizable(false);
		this.setSize(700, 800);
		this.setLocation(100, 200);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private JFreeChart createChart(XYDataset data) {
		// create the chart...
        final JFreeChart chart = ChartFactory.createXYLineChart(
            "Aircraft Simulator",      // chart title
            "Time",                      // x axis label
            "Passengers",                      // y axis label
            data,                  // data
            PlotOrientation.VERTICAL,
            true,                     // include legend
            true,                     // tooltips
            false                     // urls
        );
        return chart;
	}
	
	private void drawGraph() {
		chart = createChart(data);
		chartPanel = new ChartPanel(chart);
		mainPanel.remove(chartPanel);
		mainPanel.add(chartPanel);
		chartPanel.setLocation(25, 50);
		chartPanel.setSize(650, 500);
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

	/**
	 * @param args
	 */
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		//JFrame.setDefaultLookAndFeelDecorated(true);
//        SwingUtilities.invokeLater(new GUISimulator("Aircraft Simulator"));
//	}
	
	//Method to create a simulator using the values given in the gui
		private Simulator createSimulatorUsingValues() throws Exception {
			int seed, maxQueueSize;
			double meanBookings, sdBookings, firstProb, businessProb, premiumProb, economyProb, cancelProb;
			try {
				seed = Integer.parseInt(this.seedTxt.getText());
				maxQueueSize = Integer.parseInt(this.queueSizeTxt.getText());
				meanBookings = Double.parseDouble(this.dailyMeanTxt.getText());
				sdBookings = 0.33 * meanBookings;
				firstProb = Double.parseDouble(this.firstTxt.getText());
				businessProb = Double.parseDouble(this.businessTxt.getText());
				premiumProb = Double.parseDouble(this.premiumTxt.getText());
				economyProb = Double.parseDouble(this.economyTxt.getText());
				cancelProb = Double.parseDouble(this.cancellationTxt.getText());
			} catch(Exception e) {
				throw new Exception("The values entered are not the correct format");
			}
				
			return new Simulator(seed,maxQueueSize,meanBookings,sdBookings,firstProb,businessProb,
							  premiumProb,economyProb,cancelProb);	
		}

}
