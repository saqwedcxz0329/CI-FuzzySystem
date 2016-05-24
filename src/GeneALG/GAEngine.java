package GeneALG;

import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.JLabel;

public class GAEngine extends Thread {

	private int itrNum, groupNum;
	private double crossoverPro, mutationPro, error;
	private ArrayList<ArrayList<Double>> TrainingData;	
	private JLabel jlb_En;

	public GAEngine(int itrNum, int groupNum, double crossoverPro, double mutationPro, 
			double error, ArrayList<ArrayList<Double>> TrainingData, JLabel jlb_En) {
		// TODO Auto-generated constructor stub
		this.itrNum = itrNum;
		this.groupNum = groupNum;
		this.crossoverPro = crossoverPro;
		this.mutationPro = mutationPro;
		this.error = error;
		this.TrainingData = TrainingData;
		this.jlb_En = jlb_En;
	}

	public void run() {
		double[][] group;
		LinkedList<GenePair> gPairs = null;
		
		/***** Generate random group *****/
		RBFN rbfn = new RBFN(groupNum, TrainingData);
		group = rbfn.generateRanGroup();
		
		/***** GeneALG *****/
		for (int k = 0; k < itrNum; k++) {
			double[] En = new double[groupNum];
			gPairs = new LinkedList<>();

			for (int i = 0; i < groupNum; i++) {
				En[i] = 1 / rbfn.computeEofN(group[i]);
				gPairs.add(new GenePair(group[i]));
			}

			GeneMachine GM = new GeneMachine(groupNum, gPairs);
			GM.reproduction(En);
			GM.crossover(crossoverPro);
			GM.mutation(mutationPro);

			gPairs = GM.getRestGene();
			for (int m = 0; m < gPairs.size(); m++) {
				double[] DNA = gPairs.get(m).getDNA();
				for (int n = 0; n < DNA.length; n++) {
					group[m][n] = DNA[n];
				}
			}
		}
		
		/***** Error *****/
		System.out.println("====================");
		double[] Error = new double[groupNum];
		double min = 10000;
		int best_index = 0;
		for (int i = 0; i < groupNum; i++) {
			Error[i] = rbfn.computeError(gPairs.get(i).getDNA());
			// System.out.println(Error[i]);
			if (Error[i] < min) {
				min = Error[i];
				best_index = i;
			}
		}
		jlb_En.setText("Error(n) = " + min);
		System.out.println("*********" + min);
		if (min > error) {
			GAEngine gaEngine = new GAEngine(itrNum, groupNum, crossoverPro, mutationPro, 
					error, TrainingData, jlb_En);
			gaEngine.start();
		}
		if( min < error){
			jlb_En.setText("Error(n) = " + min + " ºt¤Æ§¹²¦!");
		}

		GA.bestDNA = gPairs.get(best_index).getDNA();
	}
}
