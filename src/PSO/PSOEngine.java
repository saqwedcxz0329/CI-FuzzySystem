package PSO;

import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.JLabel;
import javax.xml.crypto.dsig.keyinfo.PGPData;

import GeneALG.GenePair;
import GeneALG.RBFN;

public class PSOEngine extends Thread {

	private int groupNum;
	private ArrayList<ArrayList<Double>> TrainingData;
	private JLabel jlb_En;

	private static final int J = 3;
	private static final int p = 3;
	private static final int Dimension = (p + 2) * J + 1;

	public PSOEngine(int groupNum, ArrayList<ArrayList<Double>> TrainingData, JLabel jlb_En) {
		// TODO Auto-generated constructor stub
		this.groupNum = groupNum;
		this.TrainingData = TrainingData;
		this.jlb_En = jlb_En;
	}

	@Override
	public void run() {
		double[][] locations;
		double[][] velocities = new double[groupNum][Dimension];
		ArrayList<Particle> particles = new ArrayList<>();
		double gFitness = 1000000;
		double[] gBest = new double[Dimension];

		/***** Generate random group *****/
		RBFN rbfn = new RBFN(groupNum, TrainingData);
		locations = rbfn.generateRanGroup();
		
		for(int i = 0; i < groupNum; i++){
			for(int j = 0; j < Dimension; j++){
				velocities[i][j] = 0;
			}
		}
		//velocities = rbfn.generateRanGroup();

		/***** Initial Particle *****/
		for (int i = 0; i < groupNum; i++) {
			Particle p = new Particle(locations[i], velocities[i]);
			particles.add(p);
		}

		for (int m = 0; m < 256; m++) {

//			double[] p1Locate = particles.get(0).getLocation();
//			for (int i = 0; i < p1Locate.length; i++) {
//				System.out.print(p1Locate[i] + " ");
//			}
//			System.out.println("****************");
			
			/***** Calculate fitness *****/
			for (Particle p : particles) {
				double fitness = rbfn.computeEofN(p.getLocation());
				double BestFitness = p.getBestFitness();
				if (fitness < BestFitness) {
					p.setBestFitness(fitness);
					p.setPBest(p.getLocation());
					//System.out.println("Z");
				}
			}

			/***** Choose the best fitness of all the particles *****/
			for (Particle p : particles) {
				double pFitness = p.getBestFitness();
				if (pFitness < gFitness) {
					gFitness = pFitness;
					//System.out.println("Z");
					for(int i = 0; i < Dimension; i++){
						gBest[i] = p.getPBest()[i];
						
					}
				}
			}
//			System.out.println("gFitness: " + gFitness);
//			System.out.println("calGfitness: " + rbfn.computeEofN(gBest));
			
			/***** Update the location and velocity *****/
			double weight = 0.08;
			for (Particle p : particles) {
				double[] velocity = p.getVelocity();
				double[] location = p.getLocation();
				double[] pBest = p.getPBest();
				double phi_1 = (Math.random() - 0.5) * 2;
				double phi_2 = (Math.random() - 0.5) * 2;
				for (int i = 0; i < Dimension; i++) {
					velocity[i] = velocity[i] + weight * phi_1 * (pBest[i] - location[i])
							+ weight * phi_2 * (gBest[i] - location[i]);
					location[i] = location[i] + velocity[i];
					//System.out.print(velocity[i] + " ");
				}
			    //System.out.println();
				p.setVelocity(velocity);
				p.setLocation(location);
			}
//			p1Locate = particles.get(0).getLocation();
//			for (int i = 0; i < p1Locate.length; i++) {
//				System.out.print(p1Locate[i] + " ");
//			}
//			System.out.println();
		}

		/***** Error *****/
		System.out.println("====================");
//		System.out.println("calGfitness: " + rbfn.computeEofN(gBest));
		double Error = rbfn.computeError(gBest);
		System.out.println(Error + "\n");

		PSO.bestLocation = gBest;
		// for (int i = 0; i < gBest.length; i++) {
		// System.out.print(gBest[i] + " ");
		// }
	}
}
