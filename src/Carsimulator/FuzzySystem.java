package Carsimulator;

import java.util.ArrayList;

public class FuzzySystem {
	private double[] d1 = new double[3];
	private double[] d2 = new double[3];
	private double alpha_d1, alpha_d2;

	public FuzzySystem() {
		// TODO Auto-generated constructor stub
		for (int i = 0; i < 3; i++) {
			d1[i] = 0;
			d2[i] = 0;
		}
		alpha_d1 = 0;
		alpha_d2 = 0;
	}

	public void Right_Left(double Right, double Left) {
		double dist = Right - Left;
		if (dist <= -10) {
			if (dist <= -20) {
				alpha_d1 = 1;
			} else {
				alpha_d1 = dist * (-1 / 10) - 1;
			}
			d1[0] = 1;
		}
		if (dist >= -12.5 && dist <= 12.5) {
			if (dist < 0) {
				alpha_d1 = dist * (1 / 12.5) + 1;
			} else if (dist > 0) {
				alpha_d1 = dist * (-1 / 12.5) + 1;
			} else {
				alpha_d1 = 1;
			}
			d1[1] = 1;
		}
		if (dist >= 10) {
			if (dist >= 20) {
				alpha_d1 = 1;
			} else {
				alpha_d1 = dist * (1 / 10) - 1;
			}
			d1[2] = 1;
		}
	}

	public void Front(double dist) {
		if (dist <= 20) {
			if (dist <= 15) {
				alpha_d2 = 1;
			} else {
				alpha_d2 = dist * (-1 / 5) + 4;
			}
			d2[0] = 1;
		}
		if (dist > 20 && dist < 50) {
			alpha_d2 = 1;
			d2[1] = 1;
		}
		if (dist >= 50) {
			if (dist >= 60) {
				alpha_d2 = 1;
			} else {
				alpha_d2 = dist * (1 / 10) - 5;
			}
			d2[2] = 1;
		}
	}

	public double Defuzzification() {
		double alpha_R1 = 0;
		double alpha_R2 = 0;
		double alpha_R3 = 0;
		double alpha_R4 = 0;
		double alpha_R5 = 0;
		double alpha_R6 = 0;
		double alpha_R7 = 0;
		double num_a, num_b;
		double theta = 0;
		if (d1[2] == 1 && d2[0] == 1) {
			alpha_R1 = Math.min(alpha_d1, alpha_d2);
		}
		if (d1[0] == 1 && d2[0] == 1) {
			alpha_R2 = Math.min(alpha_d1, alpha_d2);
		}
		if (d1[1] == 1 && d2[0] == 1) {
			alpha_R3 = Math.min(alpha_d1, alpha_d2);
		}
		if (d1[2] == 1 && d2[1] == 1) {
			alpha_R4 = Math.min(alpha_d1, alpha_d2);
		}
		if (d1[0] == 1 && d2[1] == 1) {
			alpha_R5 = Math.min(alpha_d1, alpha_d2);
		}
		if (d1[2] == 1 && d2[2] == 1) {
			alpha_R6 = Math.min(alpha_d1, alpha_d2);
		}
		if (d1[0] == 1 && d2[2] == 1) {
			alpha_R7 = Math.min(alpha_d1, alpha_d2);
		}
		num_a = -40 * alpha_R1 + 40 * alpha_R2 + -40 * alpha_R3 + -20 * alpha_R4 + 20 * alpha_R5 + -12.5 * alpha_R6 + 12.5 * alpha_R7;
		num_b = alpha_R1 + alpha_R2 + alpha_R3 + alpha_R4 + alpha_R5 + alpha_R6 + alpha_R7;
		if (num_b != 0) {
			theta = num_a / num_b;
		}
		return theta;
	}

}
