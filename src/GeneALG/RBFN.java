package GeneALG;

import java.nio.DoubleBuffer;
import java.util.ArrayList;

public class RBFN {

	private int groupNum;
	public ArrayList<ArrayList<Double>> TrainingData;

	private static final int J = 3;
	private static final int p = 3;
	private static final int DNALenth = (p + 2) * J + 1;

	public RBFN(int groupNum, ArrayList<ArrayList<Double>> TrainingData) {
		// TODO Auto-generated constructor stub
		this.groupNum = groupNum;
		this.TrainingData = TrainingData;
	}

	public double[][] generateRanGroup() {
		double[][] group = new double[groupNum][DNALenth];
		for (int m = 0; m < groupNum; m++) {

			group[m][0] = Math.random();// generate £c

			/***** generate W (-40~40) *****/
			for (int n = 1; n < J + 1; n++) {
				double ran = Math.random();
				if (ran < 0.5) {
					group[m][n] = (-40) * Math.random();
				} else {
					group[m][n] = 40 * Math.random();
				}
			}

			/***** generate M (0~190) *****/
			for (int n = J + 1; n < J + 1 + p * J; n++) {
				group[m][n] = 190 * Math.random();
			}
			/***** generate £m (0~65) *****/
			for (int n = J + 1 + p * J; n < DNALenth; n++) {
				group[m][n] = 65 * Math.random();
			}

			// /***** show group *****/
			// for (int n = 0; n < DNALenth; n++) {
			// System.out.print(group[m][n] + " ");
			// }
			// System.out.println();
		}
		return group;
	}

	public double computeEofN(double[] DNA) {
		double idealTheta;
		double Theta;
		double En = 0;
		for (int i = 0; i < TrainingData.size(); i++) {
			idealTheta = TrainingData.get(i).get(3);
			Theta = computeTheta(DNA, TrainingData.get(i));
			// System.out.println("Ideal: " + idealTheta + " Theta: " + Theta);
			En += Math.pow(idealTheta - Theta, 2);
		}
		En = En / 2;
		return En;

	}

	public static double computeTheta(double[] DNA, ArrayList<Double> Xn) {
		double[] W = new double[J];
		double[][] M = new double[J][p];
		double[] X = new double[p];
		double[] sigma = new double[J];

		/***** generate W *****/
		// System.out.println("======");
		for (int m = 0; m < J; m++) {
			W[m] = DNA[m + 1];
			// System.out.print(W[m] + " ");
		}
		// System.out.println();

		/***** generate M *****/
		// System.out.println("#####");
		for (int m = 0; m < J; m++) {
			for (int n = 0; n < p; n++) {
				M[m][n] = DNA[m * p + n + J + 1];
				// System.out.print(M[m][n] + " ");
			}
			// System.out.println();
		}

		/**** generate £m *****/
		// System.out.println("*****");
		for (int m = 0; m < J; m++) {
			sigma[m] = DNA[m + J + 1 + p * J];
			// System.out.print(sigma[m] + " ");
		}
		// System.out.println();

		/**** generate X *****/
		for (int m = 0; m < p; m++) {
			X[m] = Xn.get(m);
		}

		double theta = 0;
		double val;
		for (int i = 0; i < J; i++) {
			val = W[i] * Math.exp(-dist(X, M[i]) / (2 * sigma[i] * sigma[i]));
			theta = theta + val;
		}
		theta = theta + DNA[0];
		return theta;
	}

	public static double dist(double[] Xn, double[] Mj) {
		double dist = 0;
		for (int i = 0; i < Xn.length; i++) {
			dist = dist + Math.pow(Xn[i] - Mj[i], 2);
		}
		return dist;
	}

	public double computeError(double[] DNA) {
		double ret = 0;
		double idealTheta;
		double Theta;
		for (int i = 0; i < TrainingData.size(); i++) {
			idealTheta = TrainingData.get(i).get(3);
			Theta = computeTheta(DNA, TrainingData.get(i));
			ret = ret + Math.abs(idealTheta - Theta);
		}
		ret = ret / TrainingData.size();
		return ret;
	}

	public void getTheta(double[] DNA) {
		double Theta, idealTheta;
		for (int i = 0; i < TrainingData.size(); i++) {
			idealTheta = TrainingData.get(i).get(3);
			Theta = computeTheta(DNA, TrainingData.get(i));
			System.out.println(idealTheta + " " + Theta);
		}
	}
}
