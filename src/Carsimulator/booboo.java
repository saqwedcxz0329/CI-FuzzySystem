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

public class booboo extends JFrame implements KeyListener {

	public static booboo frame = new booboo();
	public static JButton start;
	public static RenewPanel RP;
	public static Engine engine;
	public static Car car;

	public static int[] x = { 70, 70, 190, 190, 130, 130, 250, 250, 70, 190, 130, 250 };
	public static int[] y = { 275, 165, 165, 90, 275, 225, 225, 90, 325, 30, 325, 30 };
	public static double distT, distL, distR;
	public static ArrayList<Point> BoundPoint = new ArrayList<>();

	public static Point carT, carL, carR, min_InterPointT, min_InterPointL, min_InterPointR;
	public static Point CarLocate, CarT_Locate, CarL_Locate, CarR_Locate;

	booboo() {
		car = new Car();
		engine = new Engine();
		RP = new RenewPanel();
		min_InterPointR = new Point(0, 0);

		add(RP);
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
		
		Thread t = new FuzzySystem(car, engine);
		t.start();
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

	public static Point CountMinInterPoint(Point o, Point p, Point Sensor) {
		double min_dist = 1000;
		double dist;
		Point min_InterPoint = new Point(0, 0);

		for (int i = 0; i < 11; i++) {
			Point InterPoint;
			if (i == 3 || i == 7 || i == 9) {
				i++;
			}
			InterPoint = CountIntersec.Intersec(o, p, BoundPoint.get(i), BoundPoint.get(i + 1));
			dist = CountDist(Sensor, InterPoint);
			if (dist < min_dist) {
				min_dist = dist;
				min_InterPoint = InterPoint;
			}
		}
		return min_InterPoint;
	}

	public static double CountDist(Point p, Point q) {
		double distX = p.getX() - q.getX();
		double distY = p.getY() - q.getY();
		return Math.sqrt(Math.pow(distX, 2) + Math.pow(distY, 2));
	}

}