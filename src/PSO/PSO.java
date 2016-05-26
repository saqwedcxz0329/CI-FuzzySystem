package PSO;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;

public class PSO implements ActionListener{
	
	public ArrayList<ArrayList<Double>> TrainingData = new ArrayList<ArrayList<Double>>();
	
	public static JLabel jlb_itrNum, jlb_groupNum,jlb_En, jlb_error;
	public static JButton btn_selectedFile, btn_run, btn_exit;
	public static JTextField jtf_groupNum, jtf_itrNum, jtf_error;
	public static JFrame frame;
	
	public static double [] bestLocation;
	
	public PSO(){
		frame = new JFrame("PSO");
		jlb_groupNum = new JLabel("粒子數");
		jlb_itrNum = new JLabel("移動次數");
		jlb_error = new JLabel("誤差值");
		jlb_En = new JLabel("Error(n) = ");
		jtf_groupNum = new JTextField("100");
		jtf_itrNum = new JTextField("256");
		jtf_error = new JTextField("2.5");
		btn_selectedFile = new JButton("Select File");
		btn_run = new JButton("Run");
		btn_exit = new JButton("Exit");
		
		jlb_itrNum.setBounds(10, 0, 60, 30);
		jtf_itrNum.setBounds(10, 30, 60, 30);
		jlb_groupNum.setBounds(80, 0, 60, 30);
		jtf_groupNum.setBounds(80, 30, 60, 30);
		jlb_En.setBounds(10, 60, 250, 30);
		jlb_error.setBounds(150, 0, 60, 30);
		jtf_error.setBounds(150, 30, 60, 30);
		btn_selectedFile.setBounds(10, 100, 100, 35);
		btn_run.setBounds(200, 100, 100, 35);
		btn_exit.setBounds(310, 100, 100, 35);
		
		frame.add(jlb_itrNum);
		frame.add(jtf_itrNum);
		frame.add(jlb_error);
		frame.add(jtf_error);
		frame.add(jlb_groupNum);
		frame.add(jtf_groupNum);
		frame.add(jlb_En);
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
			PSOon();
		}
		if (e.getSource().equals(btn_exit)) {
			frame.dispose();
		}
	}
	
	public void PSOon(){
		int itrNum = Integer.valueOf(jtf_itrNum.getText());
		double error = Double.valueOf(jtf_error.getText());
		int groupNum = Integer.valueOf(jtf_groupNum.getText());
		PSOEngine psoEngine = new PSOEngine(itrNum, error, groupNum, TrainingData, jlb_En);
		psoEngine.start();
	}
}
