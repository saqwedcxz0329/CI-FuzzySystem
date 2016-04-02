package Carsimulator;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Locale.Category;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class booboo extends JFrame implements ActionListener, KeyListener {

	public static booboo frame = new booboo();
	public static JButton start;
	public static JButton stop;
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
		car = new Car();
		engine = new Engine(car);
		RP = new RenewPanel(car);

		RP.setLayout(null);
		start.setBounds(275, 200, 75, 50);
		stop.setBounds(275, 275, 75, 50);
		RP.add(start);
		RP.add(stop);
		add(RP);

		start.addActionListener(this);
		stop.addActionListener(this);
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
	}
}