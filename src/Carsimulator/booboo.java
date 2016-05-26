package Carsimulator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import GeneALG.*;
import PSO.PSO;

public class booboo extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static JButton start;
	public static JButton stop;
	public static JButton set;
	public static JButton reset;
	public static JButton jb_GABuild, jb_runGA;
	public static JButton jb_PSOBuild, jb_runPSO;
	public static JTextField angle;
	public static JTextField xaxis;
	public static JTextField yaxis;
	public static JTextField iteration_num;
	public static JLabel angleInfor;
	public static JLabel xaxisInfor;
	public static JLabel yaxisInfor;
	public static RenewPanel RP;
	public static Engine engine;
	public static Car car;
	public static CarStart thread;
	public static booboo frame = new booboo();

	public static int[] x = { 70, 70, 190, 190, 130, 130, 250, 250, 70, 190, 130, 250 };
	public static int[] y = { 275, 165, 165, 90, 275, 225, 225, 90, 325, 30, 325, 30 };
	public static double distT, distL, distR;
	public static ArrayList<Point> BoundPoint = new ArrayList<>();

	public booboo() {
		start = new JButton("Start");
		stop = new JButton("Stop");
		set = new JButton("Set");
		reset = new JButton("Reset");
		jb_GABuild = new JButton("GA_Build");
		jb_runGA = new JButton("RunGA");
		jb_PSOBuild = new JButton("PSO_Build");
		jb_runPSO = new JButton("RunPSO");
		angle = new JTextField(20);
		xaxis = new JTextField(20);
		yaxis = new JTextField(20);
		angleInfor = new JLabel("идл╫: ");
		xaxisInfor = new JLabel("X: ");
		yaxisInfor = new JLabel("Y: ");
		car = new Car();
		engine = new Engine(car);
		RP = new RenewPanel(car);

		RP.setLayout(null);
		angle.setText("-90");
		xaxis.setText("0");
		yaxis.setText("0");
		start.setBounds(275, 250, 75, 30);
		stop.setBounds(275, 280, 75, 30);
		jb_GABuild.setBounds(380, 250, 100, 30);
		jb_runGA.setBounds(380, 280, 100, 30);
		jb_PSOBuild.setBounds(380, 350, 100, 30);
		jb_runPSO.setBounds(380, 380, 100, 30);
		set.setBounds(275, 160, 75, 30);
		reset.setBounds(275, 190, 75, 30);
		angle.setBounds(310, 70, 50, 20);
		xaxis.setBounds(300, 100, 50, 20);
		yaxis.setBounds(300, 130, 50, 20);
		angleInfor.setBounds(275, 70, 50, 20);
		xaxisInfor.setBounds(275, 100, 20, 20);
		yaxisInfor.setBounds(275, 130, 20, 20);
		RP.add(start);
		RP.add(stop);
		RP.add(jb_GABuild);
		RP.add(jb_runGA);
		RP.add(jb_PSOBuild);
		RP.add(jb_runPSO);
		RP.add(set);
		RP.add(reset);
		RP.add(angle);
		RP.add(xaxis);
		RP.add(yaxis);
		RP.add(angleInfor);
		RP.add(xaxisInfor);
		RP.add(yaxisInfor);
		add(RP);

		start.addActionListener(this);
		stop.addActionListener(this);
		jb_GABuild.addActionListener(this);
		jb_runGA.addActionListener(this);
		jb_PSOBuild.addActionListener(this);
		jb_runPSO.addActionListener(this);
		set.addActionListener(this);
		reset.addActionListener(this);
		setFocusable(true);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(550, 550);
		setLocation(400, 100);
		setVisible(true);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(550, 550);
		frame.setLocation(400, 100);
		frame.setVisible(true);

		BoundPoint.add(new Point(70, 325));
		BoundPoint.add(new Point(70, 165));
		BoundPoint.add(new Point(190, 165));
		BoundPoint.add(new Point(190, 30));

		BoundPoint.add(new Point(130, 325));
		BoundPoint.add(new Point(130, 225));
		BoundPoint.add(new Point(250, 225));
		BoundPoint.add(new Point(250, 30));

		BoundPoint.add(new Point(70, 325));
		BoundPoint.add(new Point(130, 325));
		BoundPoint.add(new Point(190, 30));
		BoundPoint.add(new Point(250, 30));

		thread = new CarStart(car, engine, 0);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource().equals(start)) {
			thread = new CarStart(car, engine, 0);
			thread.start();
		}
		if (e.getSource() == stop) {
			thread.terminate();
		}
		if (e.getSource().equals(set)) {
			double x, y, phi;
			x = Double.valueOf(xaxis.getText()) + 100;
			y = 275 - Double.valueOf(yaxis.getText());
			phi = Double.valueOf(angle.getText());
			car.setX(x);
			car.setY(y);
			car.setPhi(phi);
			thread.CountInterPoint_TRL();
			frame.revalidate();
			frame.repaint();
		}
		if (e.getSource().equals(reset)) {
			car.setX(100);
			car.setY(275);
			car.setPhi(-90);
			thread.CountInterPoint_TRL();
			frame.revalidate();
			frame.repaint();
		}

		if (e.getSource().equals(jb_GABuild)) {
			new GA();
		}
		if (e.getSource().equals(jb_runGA)) {
			thread = new CarStart(car, engine, 1);
			thread.start();
		}
		if(e.getSource().equals(jb_PSOBuild)){
			new PSO();
		}
		if(e.getSource().equals(jb_runPSO)){
			thread = new CarStart(car, engine, 2);
			thread.start();
		}
	}
}