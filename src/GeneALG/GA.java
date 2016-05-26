
package GeneALG;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class GA implements ActionListener {

	public ArrayList<ArrayList<Double>> TrainingData = new ArrayList<ArrayList<Double>>();
	public static double[] bestDNA;

	public static JLabel jlb_itrNum, jlb_groupNum, jlb_crossoverPro, jlb_mutationPro, jlb_error, jlb_En;
	public static JTextField jtf_itrNum, jtf_groupNum, jtf_crossoverPro, jtf_mutationPro, jtf_error;
	public static JButton btn_selectedFile, btn_run, btn_exit;
	public static JFrame frame;

	public GA() {
		// TODO Auto-generated constructor stub
		frame = new JFrame("GA");
		jlb_itrNum = new JLabel("迭代次數");
		jlb_groupNum = new JLabel("族群大小");
		jlb_crossoverPro = new JLabel("交配機率");
		jlb_mutationPro = new JLabel("突變機率");
		jlb_error = new JLabel("誤差值");
		jlb_En = new JLabel("Error(n) = ");
		jtf_itrNum = new JTextField("256");
		jtf_groupNum = new JTextField("100");
		jtf_crossoverPro = new JTextField("0.5");
		jtf_mutationPro = new JTextField("0.5");
		jtf_error = new JTextField("2.5");
		btn_selectedFile = new JButton("Select File");
		btn_run = new JButton("Run");
		btn_exit = new JButton("Exit");

		jlb_itrNum.setBounds(10, 0, 60, 30);
		jtf_itrNum.setBounds(10, 30, 60, 30);
		jlb_groupNum.setBounds(80, 0, 60, 30);
		jtf_groupNum.setBounds(80, 30, 60, 30);
		jlb_crossoverPro.setBounds(150, 0, 60, 30);
		jtf_crossoverPro.setBounds(150, 30, 60, 30);
		jlb_mutationPro.setBounds(220, 0, 60, 30);
		jtf_mutationPro.setBounds(220, 30, 60, 30);
		jlb_error.setBounds(290, 0, 60, 30);
		jtf_error.setBounds(290, 30, 60, 30);
		jlb_En.setBounds(10, 60, 250, 30);
		btn_selectedFile.setBounds(10, 100, 100, 35);
		btn_run.setBounds(200, 100, 100, 35);
		btn_exit.setBounds(310, 100, 100, 35);

		frame.add(jlb_itrNum);
		frame.add(jlb_groupNum);
		frame.add(jlb_crossoverPro);
		frame.add(jlb_mutationPro);
		frame.add(jlb_error);
		frame.add(jlb_En);
		frame.add(jtf_itrNum);
		frame.add(jtf_groupNum);
		frame.add(jtf_crossoverPro);
		frame.add(jtf_mutationPro);
		frame.add(jtf_error);
		frame.add(btn_selectedFile);
		frame.add(btn_run);
		frame.add(btn_exit);
		
		btn_selectedFile.addActionListener(this);
		btn_run.addActionListener(this);
		btn_exit.addActionListener(this);
		
		frame.setFocusable(true);
		frame.setLayout(null);
		frame.setSize(500, 200);
		frame.setLocation(400, 100);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource().equals(btn_selectedFile)) {
			String userDir = System.getProperty("user.home");
			JFileChooser fileChooser = new JFileChooser(userDir + "/Desktop");
			int returnValue = fileChooser.showOpenDialog(null);// 叫出filechooser
			if (returnValue == JFileChooser.APPROVE_OPTION) // 判斷是否選擇檔案
			{
				File selectedFile = fileChooser.getSelectedFile();// 指派給File
				String FilePath = selectedFile.getAbsolutePath();
				try {
					BufferedReader br = new BufferedReader(new FileReader(FilePath));
					String str;

					/***** record TrainingData *****/
					while ((str = br.readLine()) != null) {
						ArrayList<Double> OneTrainingData = new ArrayList<>();
						String[] tmpstr = str.split(" ");
						for (int i = 0; i < tmpstr.length; i++) {
							OneTrainingData.add(Double.valueOf(tmpstr[i]));
						}
						TrainingData.add(OneTrainingData);
					}
					br.close();

					/***** print TrainingData *****/
					// for (int i = 0; i < TrainingData.size(); i++) {
					// for (int j = 0; j < TrainingData.get(i).size(); j++) {
					// System.out.print(TrainingData.get(i).get(j) + " ");
					// }
					// System.out.println();
					// }

				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		if (e.getSource().equals(btn_run)) {
			GAon();
		}
		if (e.getSource().equals(btn_exit)) {
			frame.dispose();
		}
	}

	public void GAon() {
		int itrNum = Integer.valueOf(jtf_itrNum.getText());
		int groupNum = Integer.valueOf(jtf_groupNum.getText());
		double crossoverPro = Double.valueOf(jtf_crossoverPro.getText());
		double mutationPro = Double.valueOf(jtf_mutationPro.getText());
		double error = Double.valueOf(jtf_error.getText());
		GAEngine gaEngine = new GAEngine(itrNum, groupNum, crossoverPro, mutationPro, 
				error, TrainingData, jlb_En);
		gaEngine.start();
	}
	
}