package Carsimulator;

import java.io.ObjectInputStream.GetField;

public class Function {
	private double alpha;
	private int xaxis;

	public Function(double alpha, int xaxis) {
		// TODO Auto-generated constructor stub
		this.alpha = alpha;
		this.xaxis = xaxis;
	}

	public double getAlpha(){
		return alpha;
	}
	public int getXaxis(){
		return xaxis;
	}
}
