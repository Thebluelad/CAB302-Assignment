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
	JTextField dailyMeanTxt = new JTextField(Double.toString(Constants.DEFAULT_DAILY_BOOKING_MEAN));
	JTextField queueSizeTxt = new JTextField(Integer.toString(Constants.DEFAULT_MAX_QUEUE_SIZE));
	JTextField cancellationTxt = new JTextField(Integer.toString(Constants.CANCELLATION_PERIOD));
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
	
	public GUISimulator(String arg0) throws HeadlessException {
		super(arg0);      
		
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
	

	private XYDataset createDataset() {
		//Creates the dataset
		final XYSeries series1 = new XYSeries("First");
        series1.add(1.0, 1.0);
        series1.add(2.0, 4.0);
        series1.add(3.0, 3.0);
        series1.add(4.0, 5.0);
        series1.add(5.0, 5.0);
        series1.add(6.0, 7.0);
        series1.add(7.0, 7.0);
        series1.add(8.0, 8.0);
        
        final XYSeries series2 = new XYSeries("Second");
        series2.add(1.0, 5.0);
        series2.add(2.0, 7.0);
        series2.add(3.0, 6.0);
        series2.add(4.0, 8.0);
        series2.add(5.0, 4.0);
        series2.add(6.0, 4.0);
        series2.add(7.0, 2.0);
        series2.add(8.0, 1.0);

        final XYSeries series3 = new XYSeries("Third");
        series3.add(3.0, 4.0);
        series3.add(4.0, 3.0);
        series3.add(5.0, 2.0);
        series3.add(6.0, 3.0);
        series3.add(7.0, 6.0);
        series3.add(8.0, 3.0);
        series3.add(9.0, 4.0);
        series3.add(10.0, 3.0);
        
        final XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series1);
        dataset.addSeries(series2);
        dataset.addSeries(series3);
		return dataset;
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
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//JFrame.setDefaultLookAndFeelDecorated(true);
        //SwingUtilities.invokeLater(new GUISimulator("Aircraft Simulator"));
	}

}
