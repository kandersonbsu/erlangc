package erlangc;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.TextField;
import java.math.*;
import java.text.DecimalFormat;
import java.util.Scanner;
import javax.swing.*;

/**
 * @author kyanderson
 * All of the methods used to calculate the Call Center stats for 
 * any given half hour. Main method calls the GUI class. 
 *
 */
public class erlangc{
	
	
	/**
	 * Takes an integer input and returns its factorial.
	 * @param number
	 * @return
	 */
	public BigDecimal factorial (int number)
	{
		BigDecimal factorial = BigDecimal.ONE;
		
		for(int i = number; i > 0; i--)
		{
			factorial = factorial.multiply(BigDecimal.valueOf(i));
		}
		return factorial;
	}
	/**
	 * Converts the number of calls in a half hour to the number of calls
	 * per second. 
	 * @param numOne
	 * @return
	 */
	public double convertToSec(double numOne)
	{
		numOne = numOne/1800;
		return numOne;
	}
	
	
	/**
	 * Takes the Arrival Rate and Average Handle Time and computes
	 * the traffic intensity. 
	 * @param a
	 * @param b
	 * @return
	 */
	public double trafficIntensity(double a, double b)
	{
		double trafficInten;
		trafficInten = a * b;
		return trafficInten;
	}
	
	
	/**
	 * Takes the Traffic Intensity and Number of Agents to 
	 * calculate the Occupacncy. 
	 * @param a
	 * @param b
	 * @return
	 */
	public double occupancy (double a, double b)
	{
		double occupancy;
		occupancy = a/b;
		return occupancy;

	}
	
	/**
	 * Takes the Traffic Intensity, Number of Agents, and Occupancy
	 * and computes the Erlangs. 
	 * @param a
	 * @param b
	 * @param c
	 * @return
	 */
	public BigDecimal erlangc(double a, double b, double c)
	{
		BigDecimal erlangc;
		BigDecimal bottom;
		int bInt = (int)b;
		BigDecimal bigOcc = BigDecimal.valueOf(occupancy(a,b));
		BigDecimal bigOne = BigDecimal.valueOf(1);
		BigDecimal bottomOcc = bigOne.subtract(bigOcc);
		
		double power = Math.pow(a, b);
		BigDecimal powerB = BigDecimal.valueOf(power);
		BigDecimal fact = factorial(bInt);
		BigDecimal top = powerB.divide(fact, 3, 0);
		
		bottom = bottomOcc.multiply(sigma(a,b));
		bottom = bottom.add(top);
		
		erlangc = top.divide(bottom, 3, 0);
		
		return erlangc;
		
	}
	
	/**
	 * A method taking Traffic Intensity and Number of Agents and 
	 * computing the summation of the Traffic Intensity to the power
	 * of the iterator divided by the factorial of the iterator. 
	 * @param u
	 * @param m
	 * @return
	 */
	public BigDecimal sigma(double u, double m)
	{
		double doubleTotal = 0.0;
		BigDecimal total = BigDecimal.valueOf(doubleTotal);
		for(int k = 0; k <= (m-1);k++)
		{
			
			double top = Math.pow(u, k);
			BigDecimal topB = BigDecimal.valueOf(top);
			BigDecimal bottom = factorial(k);
			BigDecimal divide = topB.divide(bottom,3,0);

			
			
			total = total.add(divide);
		}
		return total;
	}

	/**
	 * 
	 * Takes the ErlangC, Average Call Duration, Number of Agents, and
	 * the Occupancy and produces the Average Speed of Answer. 
	 * @param a
	 * @param b
	 * @param c
	 * @param d
	 * @return
	 */
	public BigDecimal asa (BigDecimal a, double b, double c, double d)
	{
		BigDecimal top = BigDecimal.valueOf(0);
		BigDecimal bottom = BigDecimal.valueOf(0);
		BigDecimal bigB = BigDecimal.valueOf(b);
		BigDecimal bigC = BigDecimal.valueOf(c);
		BigDecimal bigD = BigDecimal.valueOf(d);
		BigDecimal asa = BigDecimal.valueOf(0);
		
		BigDecimal bigOne = BigDecimal.valueOf(1);
		
		top = a.multiply(bigB);
		bottom = bigC.multiply((bigOne.subtract(bigD)));
		
		asa = top.divide(bottom, 1, 0);
		
		return asa;
		
	}

	/**
	 * Takes the ErlangC, Target Service Level, and Average
	 * Call Duration to produce an estimated Service Level. 
	 * @param a
	 * @param b
	 * @param c
	 * @return
	 */
	public BigDecimal serviceLevel(BigDecimal a, double b, double c)
	{
		BigDecimal bigB = BigDecimal.valueOf(b);
		BigDecimal bigC = BigDecimal.valueOf(c);
		BigDecimal bigOne = BigDecimal.valueOf(1);
		BigDecimal bigNeg = BigDecimal.valueOf(-1);
		BigDecimal bigPow = bigNeg.multiply(a).multiply(bigB).divide(bigC, 5, 0);
		double pow = bigPow.doubleValue();
		double aa = a.doubleValue();
		BigDecimal total;
		
		BigDecimal bigSub;
		
		bigSub = BigDecimal.valueOf(aa * Math.pow(Math.E, pow));
		
		total = bigOne.subtract(bigSub);
		
		return total;
		
		
	}

	
	public static void main(String[] args) 
	{
		
		GUI gui = new GUI();
		gui.frameConstructor();
	}

}