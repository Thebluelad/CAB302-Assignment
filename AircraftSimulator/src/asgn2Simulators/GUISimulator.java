/**
 * 
 * This file is part of the AircraftSimulator Project, written as 
 * part of the assessment for CAB302, semester 1, 2016. 
 * 
 */
package asgn2Simulators;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;

import asgn2Aircraft.AircraftException;
import asgn2Passengers.PassengerException;

/**
 * @author hogan and Matthew West and Lucy Webster
 *
 */
@SuppressWarnings("serial")
public class GUISimulator extends JFrame implements Runnable {
	
	//The panel in the middle of the screen which contains the graphic mode
	//switches and the graphs/text output
	JPanel mainPanel = new JPanel();
	
	//Objects used for displaying a scrollable text area for the text output
	JPanel summary = new JPanel();
	JTextArea summaryTxt = new JTextArea("");
	JScrollPane scroll = new JScrollPane(summaryTxt);
	
	//Dataset to store the data gathered in SimulationRunner
	XYDataset data1;
	XYDataset data2;
	
	JFreeChart chart;
	ChartPanel chartPanel;
	
	JPanel titlePanel = new JPanel();
	
	//Contains all text inputs
	JPanel bottomTextPanel = new JPanel();
	
	//Contains only the Run Simulation button
	JPanel bottomButtonPanel = new JPanel();
	
	//Objects to correctly run the simulation
	Simulator s = null;
	Log l = null;
	SimulationRunner sr;
	
	//Labels including title and text field labels
	JLabel title = new JLabel("GUI For Aircraft Simulator");
	JLabel seedLbl = new JLabel("Seed:");
	JLabel dailyMeanLbl = new JLabel("Daily Mean:");
	JLabel queueSizeLbl = new JLabel("Queue Size Max:");
	JLabel cancellationLbl = new JLabel("Cancellation Prob:");
	JLabel firstLbl = new JLabel("First Prob:");
	JLabel businessLbl = new JLabel("Business Prob:");
	JLabel premiumLbl = new JLabel("Premium Prob:");
	JLabel economyLbl = new JLabel("Economy Prob:");
	
	//Label to display instructions at startup
	JTextArea textAreaLbl = new JTextArea("The simulation output will appear here.\n\n"
			+ 							  "To start:\n"
			+ 							  "• Choose values\n"
			+ 							  "• Run the simulation\n"
			+ 							  "• Select a data viewing option\n\n"
			+ 							  "You can also rerun the simulation with\n"
			+ 							  "different values by repeating the above steps.\n");
	
	//Setting the default values in the text fields at startup
	JTextField seedTxt = new JTextField(Integer.toString(Constants.DEFAULT_SEED));
	JTextField queueSizeTxt = new JTextField(Integer.toString(Constants.DEFAULT_MAX_QUEUE_SIZE));
	JTextField dailyMeanTxt = new JTextField(Double.toString(Constants.DEFAULT_DAILY_BOOKING_MEAN));
	JTextField cancellationTxt = new JTextField(Double.toString(Constants.DEFAULT_CANCELLATION_PROB));
	JTextField firstTxt = new JTextField(Double.toString(Constants.DEFAULT_FIRST_PROB));
	JTextField businessTxt = new JTextField(Double.toString(Constants.DEFAULT_BUSINESS_PROB));
	JTextField premiumTxt = new JTextField(Double.toString(Constants.DEFAULT_PREMIUM_PROB));
	JTextField economyTxt = new JTextField(Double.toString(Constants.DEFAULT_ECONOMY_PROB));
	
	//Buttons for graphic control and running the simulation
	JButton btnGraph1 = new JButton("Graph 1");
	JButton btnGraph2 = new JButton("Graph 2");
	JButton btnRunSimulation = new JButton("Run Simulation");
	JButton btnTextOutput = new JButton("Text Output");
	
	protected Frame frame;
	
	/**
	 * @param arg0
	 * @throws HeadlessException
	 */
	public GUISimulator(String arg0) throws HeadlessException {
		super(arg0);      
		
		initialiseSimLog();
		
		//The listener for the graph 1 button press
		btnGraph1.addActionListener(new ActionListener() {
			  public void actionPerformed(ActionEvent e) {
				  
				  //The following if statements clear the panel if something is there in preparation for graphing
				  if(isComponentInPanel(chartPanel)) {
					  mainPanel.remove(chartPanel);
				  }
				  if(isComponentInPanel(summary)) {
					  mainPanel.remove(summary);
				  }
				  drawGraph1();
			  }
		});
		
		//The listener for the graph 2 button press
		btnGraph2.addActionListener(new ActionListener() {
			  public void actionPerformed(ActionEvent e) {
				  
				  if(isComponentInPanel(chartPanel)) {
					  mainPanel.remove(chartPanel);
				  }
				  if(isComponentInPanel(summary)) {
					  mainPanel.remove(summary);
				  }
				  drawGraph2();
			  }
		});
		
		//The listener for the text output button press
		btnTextOutput.addActionListener(new ActionListener() {
			  public void actionPerformed(ActionEvent e) {
				  scroll.setVisible(true);
				  summary.setVisible(true);
				  if(isComponentInPanel(chartPanel)) {
					  mainPanel.remove(chartPanel);
				  }
				  if(isComponentInPanel(summary)) {
					  mainPanel.remove(summary);
				  }
				  mainPanel.add(scroll);
				  mainPanel.repaint();
			  }
		});
		
		//The listener for the Run Simulation button press
		btnRunSimulation.addActionListener(new ActionListener() {
			  public void actionPerformed(ActionEvent e) {
				  
				  if (isComponentInPanel(chartPanel)) {
					  mainPanel.remove(chartPanel);
					  mainPanel.repaint();
				  }
				  
				  mainPanel.add(scroll);
				  
				  //If the values are correct, run the simulation
				  if (valuesCorrect()) {
					  try {
							s = createSimulatorUsingValues();
							l = new Log();
					  } catch (Exception e1) {
						  e1.printStackTrace();
					  }
					  sr = new SimulationRunner(s, l);
					  try {
						sr.runSimulation();
					  } catch (AircraftException | PassengerException | SimulationException | IOException e1) {
						e1.printStackTrace();
					  }
					  data1 = sr.returnData1();
					  data2 = sr.returnData2();
					  summaryTxt.setText(sr.returnTextOutput());
					  btnGraph1.setVisible(true);
					  btnGraph2.setVisible(true);
					  btnTextOutput.setVisible(true);
				  }
			  }
		});
		
		//seedLbl's setup
		seedLbl.setVisible(true);
		seedLbl.setSize(305, 20);
		seedLbl.setLocation(20, 700);
		
		//textAreaLbl(Startup message)'s setup
		textAreaLbl.setVisible(true);
		textAreaLbl.setLocation(100, 200);
		textAreaLbl.setSize(700, 200);
		textAreaLbl.setFont(new Font("Serif", Font.BOLD, 20));
		textAreaLbl.setForeground(Color.WHITE);
		textAreaLbl.setBackground(new Color(17, 55, 97));
		
		//View option buttons setup
		btnGraph1.setBounds(100, 20, 100, 25);
		btnGraph1.setVisible(false);
		btnGraph2.setBounds(475, 20, 100, 25);
		btnGraph2.setVisible(false);
		btnTextOutput.setBounds(300, 20, 100, 25);
		btnTextOutput.setVisible(false);
		
		//summary's setup
		summary.add(scroll);
		summary.add(textAreaLbl);
		summary.setLayout(null);
		summary.setBounds(0, 25, 700, 600);
		summary.setLocation(25, 50);
		summary.setSize(650, 500);
		summary.setBackground(new Color(17, 55, 97));
		
		//summaryTxt's setup
		summaryTxt.setLocation(25, 50);
		summaryTxt.setSize(650, 500);
		summaryTxt.setEditable(false);
		summaryTxt.setVisible(true);
		
		//scroll's setup
		scroll.setLocation(25, 50);
		scroll.setSize(650, 500);
		scroll.setVisible(false);
		
		//mainPanel's setup
		mainPanel.setBounds(0, 25, 700, 600);
		mainPanel.setBackground(new Color(17, 55, 97));
		mainPanel.setLayout(null);
		mainPanel.add(btnGraph1);
		mainPanel.add(btnGraph2);
		mainPanel.add(btnTextOutput);
		mainPanel.add(summary);
		
		//TitlePanel's setup
		titlePanel.setBounds(0, 0, 700, 25);
		titlePanel.setBackground(new Color(255, 255, 255));
		titlePanel.add(title);
		
		//bottomTextPanel's setup
		bottomTextPanel.setBounds(0, 625, 700, 100);
		bottomTextPanel.setBackground(new Color(140, 180, 255));
		
		//bottomButtonPanel's setup
		bottomButtonPanel.setBounds(0, 725, 700, 75);
		bottomButtonPanel.setBackground(new Color(17, 55, 97));
		bottomButtonPanel.setLayout(null);
		bottomButtonPanel.add(btnRunSimulation);
		
		//btnRunSimulation's setup
		btnRunSimulation.setBounds(0, 725, 233, 25);
		btnRunSimulation.setLocation(233, 735);
		
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
		
		//Adding all panel's to the frame
		this.getContentPane().add(titlePanel);
		this.getContentPane().add(bottomTextPanel);
		this.getContentPane().add(mainPanel);
		this.getContentPane().add(bottomButtonPanel);
		
		//Frame customisation
		this.setResizable(false);
		this.setSize(700, 800);
		this.setLocation(100, 200);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * Creates a chart from the given data
	 * @param data to be graphed
	 * @return JFreeChart of data
	 */
	private JFreeChart createChart(XYDataset data) {
        final JFreeChart chart = ChartFactory.createXYLineChart(
            "Aircraft Simulator",      	// Chart Title
            "Time",                     // X Axis Label
            "Passengers",               // Y Axis Label
            data,               	    // Data
            PlotOrientation.VERTICAL,
            true,                       // Include Legend
            true,                       // Tooltips
            false                       // URLs
        );
        return chart;
	}
	
	/**
	 * Handles drawing graph1
	 */
	private void drawGraph1() {
		chart = createChart(data1);
		chartPanel = new ChartPanel(chart);
		mainPanel.remove(chartPanel);
		mainPanel.add(chartPanel);
		chartPanel.setLocation(25, 50);
		chartPanel.setSize(650, 500);
	}
	
	/**
	 * Handles drawing graph2
	 */
	private void drawGraph2() {
		chart = createChart(data2);
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
	public void run() {}
	
	/**
	 * Method to check if a component is within the mainPanel
	 * @param component the component to check
	 * @return boolean true if the component exists in the panel
	 */
	private boolean isComponentInPanel(Component component) {
	    return
	        java.util.Arrays.asList(mainPanel.getComponents())
	            .contains(component);
	}
	
	/**
	 * Initialises the Simulator and Log
	 */
	private void initialiseSimLog() {
		try {
			s = new Simulator();
			l = new Log();
		} catch (SimulationException | IOException e2) {
			e2.printStackTrace();
		}
	}
	
	/**
	 * Checks to see if the values given in the GUI are
	 * correct and within the guidelines.
	 * 
	 * Also show's message dialogs to communicate the error
	 * to the users.
	 * 
	 * @return boolean true if values are correct
	 */
	private boolean valuesCorrect() {
		@SuppressWarnings("unused")
		int seed, maxQueueSize;
		
		@SuppressWarnings("unused")
		double meanBookings, firstProb, businessProb, premiumProb, economyProb, cancelProb;
		//Attempts to parse all the values to the correct type
		try {
			seed = Integer.parseInt(this.seedTxt.getText());
			maxQueueSize = Integer.parseInt(this.queueSizeTxt.getText());
			meanBookings = Double.parseDouble(this.dailyMeanTxt.getText());
			firstProb = Double.parseDouble(this.firstTxt.getText());
			businessProb = Double.parseDouble(this.businessTxt.getText());
			premiumProb = Double.parseDouble(this.premiumTxt.getText());
			economyProb = Double.parseDouble(this.economyTxt.getText());
			cancelProb = Double.parseDouble(this.cancellationTxt.getText());
		} catch(Exception e) {
			//The value was not the correct type. Prompt the user.
			JOptionPane.showMessageDialog(null, "All values entered must be numbers");
			return false;
		}
		if (firstProb > 1 || businessProb > 1 || premiumProb > 1 || economyProb > 1 || cancelProb > 1) {
			JOptionPane.showMessageDialog(null, "No individual probability can be greater than 1");
			return false;
		}
		//Probabilities must add to 1
		else if (firstProb + businessProb + premiumProb + economyProb != 1) {
			JOptionPane.showMessageDialog(null, "The Probabilities need to total to 1");
			return false;
		}
		
		//If we get here, the probability values must have been correct.
		return true;
	}
	
	/**
	 * Method to create a simulator using the values from the GUI
	 * @return	Simulator
	 * @throws Exception if values are still in the wrong format
	 */
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
