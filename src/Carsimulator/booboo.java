package Carsimulator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.imageio.spi.RegisterableService;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class booboo extends JFrame implements ActionListener, KeyListener {

	public static booboo frame = new booboo();
	public static JButton start;
	public static JButton stop;
	public static JButton set;
	public static JButton reset;
	public static JTextField angle;
	public static JTextField xaxis;
	public static JTextField yaxis;
	public static JLabel angleinfor;
	public static JLabel xaxisinfor;
	public static JLabel yaxisinfor;
	public static RenewPanel RP;
	public static Engine engine;
	public static Car car;
	public static CarStart thread;

	public static int[] x = { 70, 70, 190, 190, 130, 130, 250, 250, 70, 190, 130, 250 };
	public static int[] y = { 275, 165, 165, 90, 275, 225, 225, 90, 325, 30, 325, 30 };
	public static double distT, distL, distR;
	public static ArrayList<Point> BoundPoint = new ArrayList<>();

	public booboo() {
		start = new JButton("Start");
		stop = new JButton("Stop");
		set = new JButton("Set");
		reset = new JButton("Reset");
		angle = new JTextField(20);
		xaxis = new JTextField(20);
		yaxis = new JTextField(20);
		angleinfor = new JLabel("идл╫: ");
		xaxisinfor = new JLabel("X: ");
		yaxisinfor = new JLabel("Y: ");
		car = new Car();
		engine = new Engine(car);
		RP = new RenewPanel(car);

		RP.setLayout(null);
		angle.setText("-90");
		xaxis.setText("0");
		yaxis.setText("0");
		start.setBounds(275, 250, 75, 30);
		stop.setBounds(275, 280, 75, 30);
		set.setBounds(275, 160, 75, 30);
		reset.setBounds(275, 190, 75, 30);
		angle.setBounds(310, 70, 50, 20);
		xaxis.setBounds(300, 100, 50, 20);
		yaxis.setBounds(300, 130, 50, 20);
		angleinfor.setBounds(275, 70, 50, 20);
		xaxisinfor.setBounds(275, 100, 20, 20);
		yaxisinfor.setBounds(275, 130, 20, 20);
		RP.add(start);
		RP.add(stop);
		RP.add(set);
		RP.add(reset);
		RP.add(angle);
		RP.add(xaxis);
		RP.add(yaxis);
		RP.add(angleinfor);
		RP.add(xaxisinfor);
		RP.add(yaxisinfor);
		add(RP);

		start.addActionListener(this);
		stop.addActionListener(this);
		set.addActionListener(this);
		reset.addActionListener(this);
		addKeyListener(this);
		setFocusable(true);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 400);
		frame.setLocation(400, 0);
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

		thread = new CarStart(car, engine);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

		double theta = car.getTheta();
		int keyCode = e.getKeyCode();

		switch (keyCode) {
		case KeyEvent.VK_UP:
			break;

		case KeyEvent.VK_LEFT:
			// handle left
			if (theta < 0) {
				theta = 0;
			}
			theta++;
			car.setTheta(theta);
			System.out.println("theta: " + car.getTheta());
			break;

		case KeyEvent.VK_RIGHT:
			// handle right
			if (theta > 0) {
				theta = 0;
			}
			theta--;
			car.setTheta(theta);
			System.out.println("theta: " + car.getTheta());
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == start) {
			thread = new CarStart(car, engine);
			thread.start();
		}
		if (e.getSource() == stop) {
			thread.terminate();
		}
		if (e.getSource() == set) {
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
		if (e.getSource() == reset) {
			car.setX(100);
			car.setY(275);
			car.setPhi(-90);
			thread.CountInterPoint_TRL();
			frame.revalidate();
			frame.repaint();
		}
	}
}