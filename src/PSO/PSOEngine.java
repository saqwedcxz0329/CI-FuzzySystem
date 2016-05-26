package PSO;

import java.util.ArrayList;
import javax.swing.JLabel;

import GeneALG.GAEngine;
import GeneALG.RBFN;

public class PSOEngine extends Thread {

	private int itrNum, groupNum;
	private double error;
	private ArrayList<ArrayList<Double>> TrainingData;
	private JLabel jlb_En;

	private static final int J = 3;
	private static final int p = 3;
	private static final int Dimension = (p + 2) * J + 1;

	public PSOEngine(int itrNum, double error, int groupNum, ArrayList<ArrayList<Double>> TrainingData, JLabel jlb_En) {
		// TODO Auto-generated constructor stub
		this.itrNum = itrNum;
		this.error = error;
		this.groupNum = groupNum;
		this.TrainingData = TrainingData;
		this.jlb_En = jlb_En;
	}

	@Override
	public void run() {
		double[][] locations;
		ArrayList<Particle> particles = new ArrayList<>();
		double gFitness = 1000000;
		double[] gBest = new double[Dimension];

		/***** Generate random group *****/
		RBFN rbfn = new RBFN(groupNum, TrainingData);
		locations = rbfn.generateRanGroup();

		/***** Initial Particle *****/
		for (int i = 0; i < groupNum; i++) {
			double[] velocities = new double[Dimension];
			Particle p = new Particle(locations[i], velocities);
			particles.add(p);
		}

		for (int m = 0; m < itrNum; m++) {

			/***** Calculate fitness *****/
			for (Particle p : particles) {
				double fitness = rbfn.computeEofN(p.getLocation());
				double BestFitness = p.getBestFitness();
				if (fitness < BestFitness) {
					p.setBestFitness(fitness);
					p.setPBest(p.getLocation());
				}
			}

			/***** Choose the best fitness of all the particles *****/
			for (Particle p : particles) {
				double pFitness = p.getBestFitness();
				if (pFitness < gFitness) {
					gFitness = pFitness;
					for (int i = 0; i < Dimension; i++) {
						gBest[i] = p.getPBest()[i];

					}
				}
			}

			/***** Update the location and velocity *****/
			double weight = 0.1;
			for (Particle p : particles) {
				double[] velocity = p.getVelocity();
				double[] location = p.getLocation();
				double[] pBest = p.getPBest();
				double phi_1 = Math.random();
				double phi_2 = Math.random();
				for (int i = 0; i < Dimension; i++) {
					velocity[i] = velocity[i] + weight * phi_1 * (pBest[i] - location[i])
							+ weight * phi_2 * (gBest[i] - location[i]);
					location[i] = location[i] + velocity[i];
					// System.out.print(location[i] + " ");
				}
				// System.out.println();
				p.setVelocity(velocity);
				p.setLocation(location);
			}
		}

		/***** Error *****/
		System.out.println("====================");
		double Error = rbfn.computeError(gBest);
		System.out.println(Error + "\n");
		
		jlb_En.setText("Error(n) = " + Error);
		if (Error > error) {
			PSOEngine psoEngine = new PSOEngine(itrNum, error, groupNum, TrainingData, jlb_En);
			psoEngine.start();
		}
		if( Error < error){
			jlb_En.setText("Error(n) = " + Error + " งนฆจ!");
		}
		
		PSO.bestLocation = gBest;
		// for (int i = 0; i < gBest.length; i++) {
		// System.out.print(gBest[i] + " ");
		// }
		// System.out.println();
	}
}
