package Carsimulator;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.regex.Matcher;

import javax.print.attribute.HashPrintJobAttributeSet;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.omg.CORBA.PolicyErrorCodeHelper;

public class Car{
	private static double x;
	private static double y;
	private static double phi;
	private  double theta;
	private static int r;
	
	Car() {
		x = 100;
		y = 275;
		theta = 0;
		phi = -90;
		r = 15;
	}

	public static  double getX() {
		return x;
	}

	public static  double getY() {
		return y;
	}

	public static  double getPhi() {
		return phi;
	}
	
	public double getTheta() {
		return theta;
	}
	public static int getRadius(){
		return r;
	}
	
	public static void setX(double x) {
		Car.x = x;
	}

	public static void setY(double y) {
		Car.y = y;
	}

	public static void setPhi(double phi) {
		if(phi >= 270){			
			Car.phi = phi -360;
		}
		else if(phi >= -90){
			Car.phi = phi;
		}
		else			{
			Car.phi = 360 + phi;
		}
	}
	
	public void setTheta(double theta){
		this.theta = theta;
	}
}
