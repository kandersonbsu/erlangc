package erlangc;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.text.DecimalFormat;

import javax.swing.*;


/**
 * @author kyanderson
 * The GUI framework for the Calculator. Compute listener takes the 
 * input data and runs it through the methods from the erlangc class. 
 *
 */
public class GUI {
	private JFrame frame;
	private JPanel baseBorder, mainGrid, topLeft, bottomLeft, bottomBorder, leftOne, leftTwo, leftThree, leftFour,
	rightOne, rightTwo, rightThree, rightFour, rightFive, rightSix,
	leftLeftOne, leftLeftTwo, leftLeftThree, leftLeftFour,
	leftRightOne, leftRightTwo, leftRightThree, leftRightFour,
	rightLeftOne, rightLeftTwo, rightLeftThree, rightLeftFour, rightLeftFive, rightLeftSix,
	rightRightOne, rightRightTwo, rightRightThree, rightRightFour, rightRightFive, rightRightSix, rightBorder,
	rbLeftOne, rbLeftTwo, rbLeftThree, rbLeftFour, rbLeftFive, rbLeftSix, rbLeftSeven, rbLeftEight, rbLeftNine, rbLeftTen,
	rbMidOne, rbMidTwo, rbMidThree, rbMidFour, rbMidFive, rbMidSix, rbMidSeven, rbMidEight, rbMidNine, rbMidTen,
	rbRightOne, rbRightTwo, rbRightThree, rbRightFour, rbRightFive, rbRightSix, rbRightSeven, rbRightEight, rbRightNine, rbRightTen;
	
	private JButton calculate, cancel;
	private JTextField arrivalRateField, ahtField, agentsField, serviceLevelField, trafficIntensityField, occupancyField, erlangCField, queueProbField, asaField, forecastedSLField;
	private JLabel arrivalRate, aht, agents, serviceLevel, trafficIntensity, occupancy, erlangC, queueProb, asa, forecastedSL, sl, occ, agent;
	private Dimension textDimension = new Dimension(60,21);
	private Dimension outputDimension = new Dimension(100, 21);
	private erlangc erlang = new erlangc();
	private int agentArray [] = new int[9];
	private JPanel panelArray [] = {rbLeftOne, rbLeftTwo, rbLeftThree, rbLeftFour, rbLeftFive, rbLeftSix, rbLeftSeven, rbLeftEight, rbLeftNine, rbLeftTen,
	                                       	rbMidOne, rbMidTwo, rbMidThree, rbMidFour, rbMidFive, rbMidSix, rbMidSeven, rbMidEight, rbMidNine, rbMidTen,
	                                    	rbRightOne, rbRightTwo, rbRightThree, rbRightFour, rbRightFive, rbRightSix, rbRightSeven, rbRightEight, rbRightNine, rbRightTen};
	private JLabel labelArray[] = {sl, occ, agent,sl, sl, sl,sl, sl, sl,sl, sl, sl,sl, sl, sl,sl, sl, sl,sl, sl, sl,sl, sl, sl,sl, sl, sl,sl, sl, sl};
	
	private double arrivalRateNum;
	private double ahtNum;
	private double agentsNum;
	private double trafficIntensityNum;
	private double occupancyNum;
	private double slSecondsNum;
	BigDecimal serviceLevelNum;
	BigDecimal erlangcNum;
	BigDecimal oneHundredNum = BigDecimal.valueOf(100);
	BigDecimal asaNum;
	DecimalFormat df = new DecimalFormat("#.00");
	DecimalFormat df2 = new DecimalFormat("#.000");
	
	
	
	/**
	 * Constructor. 
	 */
	public GUI() {
		//BaseBorder
		baseBorder = new JPanel();
		baseBorder.setLayout(new BorderLayout(0,0));
		baseBorder.setPreferredSize(new Dimension (700,350));
		baseBorder.setBackground(Color.black);
		CancelListener c = new CancelListener();
		ComputeListener l = new ComputeListener();
		
		//Right Border Instantiation
		rightBorder = new JPanel();
		rightBorder.setLayout(new GridLayout(10,3,1,1));
		rightBorder.setPreferredSize(new Dimension(0,0));
		rightBorder.setBackground(Color.black);
		
		//rightBorder.setBackground(Color.green);
		
		//Buttons
		calculate = new JButton("Calculate");
		cancel = new JButton("Cancel");
		
		//Text Fields
		arrivalRateField = new JTextField("");
		arrivalRateField.setPreferredSize(textDimension);
		
		ahtField = new JTextField("");
		ahtField.setPreferredSize(textDimension);
		
		agentsField = new JTextField("");
		agentsField.setPreferredSize(textDimension);
		
		serviceLevelField = new JTextField("");
		serviceLevelField.setPreferredSize(textDimension);
		
		trafficIntensityField = new JTextField("");
		trafficIntensityField.setPreferredSize(outputDimension);
		trafficIntensityField.setEditable(false);
		
		occupancyField = new JTextField("");
		occupancyField.setPreferredSize(outputDimension);
		occupancyField.setEditable(false);
		
		erlangCField = new JTextField("");
		erlangCField.setPreferredSize(outputDimension);
		erlangCField.setEditable(false);
		
		queueProbField = new JTextField("");
		queueProbField.setPreferredSize(outputDimension);
		queueProbField.setEditable(false);
		
		asaField = new JTextField("");
		asaField.setPreferredSize(outputDimension);
		asaField.setEditable(false);
		
		forecastedSLField = new JTextField("");
		forecastedSLField.setPreferredSize(outputDimension);
		forecastedSLField.setEditable(false);
		
		//Labels
		arrivalRate = new JLabel ("Enter the calls per half hour: ", SwingConstants.LEFT);
		aht = new JLabel ("Enter the AHT (in seconds): ", SwingConstants.LEFT);
		agents = new JLabel ("Enter the available FTE: ", SwingConstants.LEFT);
		serviceLevel = new JLabel ("Enter the target Service Level: ", SwingConstants.LEFT);
		trafficIntensity = new JLabel ("Traffic Intensity: ", SwingConstants.LEFT);
		occupancy = new JLabel ("Occupancy: ", SwingConstants.LEFT);
		erlangC = new JLabel ("ErlangC: ", SwingConstants.LEFT);
		queueProb = new JLabel ("Probablity a call will queue: ", SwingConstants.LEFT);
		asa = new JLabel ("Average Speed of Answer: ", SwingConstants.LEFT);
		forecastedSL = new JLabel ("Forecasted Service Level: ", SwingConstants.LEFT);
		sl = new JLabel ("Service Level");
		occ = new JLabel ("Occupancy");
		agent = new JLabel ("Agents");
		
		
		arrivalRate.setVerticalAlignment(SwingConstants.CENTER);
		
		mainGrid = new JPanel();
		mainGrid.setLayout(new GridLayout(1,2,1,1));
		mainGrid.setBackground(Color.black);
		
		topLeft = new JPanel();
		bottomLeft = new JPanel();
		topLeft.setLayout(new GridLayout(4,0));
		bottomLeft.setLayout(new GridLayout(6,0));
		
		//Adding Components to the left panel
		leftOne = new JPanel(new GridLayout(0,2));
		leftTwo = new JPanel(new GridLayout(0,2));
		leftThree = new JPanel(new GridLayout(0,2));
		leftFour = new JPanel(new GridLayout(0,2));
		
		
		leftLeftOne = new JPanel(new FlowLayout(FlowLayout.LEFT,0,30));
		leftLeftTwo = new JPanel(new FlowLayout(FlowLayout.LEFT,0,30));
		leftLeftThree = new JPanel(new FlowLayout(FlowLayout.LEFT,0,30));
		leftLeftFour = new JPanel(new FlowLayout(FlowLayout.LEFT,0,30));
		
		leftRightOne = new JPanel(new FlowLayout(FlowLayout.RIGHT,0,30));
		leftRightTwo = new JPanel(new FlowLayout(FlowLayout.RIGHT,0,30));
		leftRightThree = new JPanel(new FlowLayout(FlowLayout.RIGHT,0,30));
		leftRightFour = new JPanel(new FlowLayout(FlowLayout.RIGHT,0,30));
		
		
		
		
		leftOne.setBorder(BorderFactory.createLineBorder(Color.black));
		leftTwo.setBorder(BorderFactory.createLineBorder(Color.black));
		leftThree.setBorder(BorderFactory.createLineBorder(Color.black));
		leftFour.setBorder(BorderFactory.createLineBorder(Color.black));
		
		
		leftLeftOne.add(arrivalRate);
		leftRightOne.add(arrivalRateField);
		
		leftLeftTwo.add(aht);
		leftRightTwo.add(ahtField);
		
		leftLeftThree.add(agents);
		leftRightThree.add(agentsField);
		
		leftLeftFour.add(serviceLevel);
		leftRightFour.add(serviceLevelField);
		
		leftOne.add(leftLeftOne);
		leftOne.add(leftRightOne);
		
		leftTwo.add(leftLeftTwo);
		leftTwo.add(leftRightTwo);
		
		leftThree.add(leftLeftThree);
		leftThree.add(leftRightThree);
		
		leftFour.add(leftLeftFour);
		leftFour.add(leftRightFour);
		
		topLeft.add(leftOne);
		topLeft.add(leftTwo);
		topLeft.add(leftThree);
		topLeft.add(leftFour);
		
		//Adding Components to the Right Panel
		rightOne = new JPanel(new GridLayout(0,2));
		rightTwo = new JPanel(new GridLayout(0,2));
		rightThree = new JPanel(new GridLayout(0,2));
		rightFour = new JPanel(new GridLayout(0,2));
		rightFive = new JPanel(new GridLayout(0,2));
		rightSix = new JPanel(new GridLayout(0,2));
		
		rightRightOne = new JPanel (new FlowLayout(FlowLayout.RIGHT,0,14));
		rightRightTwo = new JPanel (new FlowLayout(FlowLayout.RIGHT,0,14));
		rightRightThree = new JPanel (new FlowLayout(FlowLayout.RIGHT,0,14));
		rightRightFour = new JPanel (new FlowLayout(FlowLayout.RIGHT,0,14));
		rightRightFive = new JPanel (new FlowLayout(FlowLayout.RIGHT,0,14));
		rightRightSix = new JPanel (new FlowLayout(FlowLayout.RIGHT,0,14));
		
		rightLeftOne = new JPanel (new FlowLayout(FlowLayout.LEFT,0,14));
		rightLeftTwo = new JPanel (new FlowLayout(FlowLayout.LEFT,0,14));
		rightLeftThree = new JPanel (new FlowLayout(FlowLayout.LEFT,0,14));
		rightLeftFour = new JPanel (new FlowLayout(FlowLayout.LEFT,0,14));
		rightLeftFive = new JPanel (new FlowLayout(FlowLayout.LEFT,0,14));
		rightLeftSix = new JPanel (new FlowLayout(FlowLayout.LEFT,0,14));
		
		
		
		
		rightOne.setBorder(BorderFactory.createLineBorder(Color.black));
		rightTwo.setBorder(BorderFactory.createLineBorder(Color.black));
		rightThree.setBorder(BorderFactory.createLineBorder(Color.black));
		rightFour.setBorder(BorderFactory.createLineBorder(Color.black));
		rightFive.setBorder(BorderFactory.createLineBorder(Color.black));
		rightSix.setBorder(BorderFactory.createLineBorder(Color.black));
		
		rightLeftOne.add(trafficIntensity);
		rightRightOne.add(trafficIntensityField);
		
		rightLeftTwo.add(occupancy);
		rightRightTwo.add(occupancyField);
		
		rightLeftThree.add(erlangC);
		rightRightThree.add(erlangCField);
		
		rightLeftFour.add(queueProb);
		rightRightFour.add(queueProbField);
		
		rightLeftFive.add(asa);
		rightRightFive.add(asaField);
		
		rightLeftSix.add(forecastedSL);
		rightRightSix.add(forecastedSLField);

		rightOne.add(rightLeftOne);
		rightOne.add(rightRightOne);
		
		rightTwo.add(rightLeftTwo);
		rightTwo.add(rightRightTwo);
		
		rightThree.add(rightLeftThree);
		rightThree.add(rightRightThree);
		
		rightFour.add(rightLeftFour);
		rightFour.add(rightRightFour);
		
		rightFive.add(rightLeftFive);
		rightFive.add(rightRightFive);
		
		rightSix.add(rightLeftSix);
		rightSix.add(rightRightSix);
		
		bottomLeft.add(rightOne);
		bottomLeft.add(rightTwo);
		bottomLeft.add(rightThree);
		bottomLeft.add(rightFour);
		bottomLeft.add(rightFive);
		bottomLeft.add(rightSix);
		bottomLeft.setBackground(Color.black);
		
		mainGrid.add(topLeft);
		mainGrid.add(bottomLeft);
		
		baseBorder.add(mainGrid,BorderLayout.CENTER);
		bottomBorder = new JPanel();
		bottomBorder.setPreferredSize(new Dimension(0,40));
		baseBorder.add(bottomBorder, BorderLayout.PAGE_END);
		baseBorder.add(rightBorder, BorderLayout.LINE_END);
		bottomBorder.setBorder(BorderFactory.createLineBorder(Color.black));
		bottomBorder.add(calculate);
		bottomBorder.add(cancel);
		
		calculate.addActionListener(l);
		cancel.addActionListener(c);
		
		frame = new JFrame("ErlangC Calculator");
		frame.setLocation(200, 200);
		frame.getContentPane().add(baseBorder);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setBackground(Color.black);
		frame.pack();
								
	}
	/**
	 * Returns the Base JPanel with its contents added. 
	 * @return
	 */
	public JPanel getPanel() {
		return baseBorder;
	}
	/**
	 * Adds the loaded base JPanel to the frame. 
	 * @return
	 */
	public JFrame frameConstructor() {
		frame.getContentPane().add(baseBorder);
		return frame;
	}
	
	/**
	 * @author kyanderson
	 * Closes the program when the Cancel button is clicked. 
	 */
	private class CancelListener implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
			
		}
		
	}
	/**
	 * @author kyanderson
	 * Takes the input data from the fields and runs it through the 
	 * methods in the erlangc class. Still needs to catch any empty 
	 * fields as it will crash if fields are left blank or the wrong 
	 * data type is entered. 
	 */
	private class ComputeListener implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			arrivalRateNum = Double.parseDouble(arrivalRateField.getText());
			ahtNum = Double.parseDouble(ahtField.getText());
			agentsNum = Double.parseDouble(agentsField.getText());
			slSecondsNum = Double.parseDouble(serviceLevelField.getText());
			
			arrivalRateNum = erlang.convertToSec(arrivalRateNum);
			
			trafficIntensityNum = erlang.trafficIntensity(arrivalRateNum, ahtNum);
			occupancyNum = erlang.occupancy(trafficIntensityNum, agentsNum);
			erlangcNum = erlang.erlangc(trafficIntensityNum, agentsNum, occupancyNum);
			asaNum = erlang.asa(erlangcNum, ahtNum, agentsNum, occupancyNum);
			serviceLevelNum = erlang.serviceLevel(erlangcNum, slSecondsNum, ahtNum);
			
			trafficIntensityField.setText("" + df.format(trafficIntensityNum));
			occupancyField.setText("" + df.format(occupancyNum * 100) + "%");
			erlangCField.setText("" + df2.format(erlangcNum));
			queueProbField.setText("" + df.format(erlangcNum.multiply(oneHundredNum)) + "%");
			asaField.setText("" + df.format(asaNum) + " seconds");
			forecastedSLField.setText("" + df.format(serviceLevelNum.multiply(oneHundredNum)) + "%");
			
			int agents = Integer.parseInt(agentsField.getText());
			int i = (agents - 4);
			int agentMax = (agents + 4);
			int j = 0;
			while (i <= agentMax) {
				agentArray[j] = i;
				i++;
				j++;
			}
			frame.setSize(new Dimension(1000,389));
			rightBorder.setPreferredSize(new Dimension(300,0));
			
			for(int k = 0; k < 30; k++) {
				panelArray[k] = new JPanel();
				labelArray[k] = new JLabel();
				panelArray[k].add(labelArray[k]);
				panelArray[k].setBorder(BorderFactory.createLineBorder(Color.BLACK));
				rightBorder.add(panelArray[k]);
			}

			
			
		}
		
	}
}
