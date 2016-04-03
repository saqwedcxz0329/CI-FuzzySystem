package Carsimulator;

import java.time.format.FormatStyle;

public class CarStart extends Thread {
	private Car car;
	private Engine engine;
	public static Point carT, carL, carR, min_InterPointT, min_InterPointL, min_InterPointR;
	public static Point CarLocate, CarT_Locate, CarL_Locate, CarR_Locate;

	private boolean isTerminated = false;

	public CarStart(Car car, Engine engine) {
		// TODO Auto-generated constructor stub
		this.car = car;
		this.engine = engine;
		CountInterPoint_TRL();
	}

	public void terminate() {
		isTerminated = true;
		interrupt();
	}

	public Point CountMinInterPoint(Point o, Point p, Point Sensor) {
		double min_dist = 1000;
		double dist;
		Point min_InterPoint = new Point(0, 0);

		for (int i = 0; i < 11; i++) {
			Point InterPoint;
			if (i == 3 || i == 7 || i == 9) {
				i++;
			}
			InterPoint = CountIntersec.Intersec(o, p, booboo.BoundPoint.get(i), booboo.BoundPoint.get(i + 1));
			dist = CountDist(Sensor, InterPoint);
			if (dist < min_dist) {
				min_dist = dist;
				min_InterPoint = InterPoint;
			}
		}
		return min_InterPoint;
	}

	public double CountDist(Point p, Point q) {
		double distX = p.getX() - q.getX();
		double distY = p.getY() - q.getY();
		return Math.sqrt(Math.pow(distX, 2) + Math.pow(distY, 2));
	}

	public void CountInterPoint_TRL() {
		CarLocate = new Point(car.getX(), car.getY());
		carT = new Point(car.getX() + car.getRadius() * Math.cos(Math.toRadians(car.getPhi())),
				car.getY() + car.getRadius() * Math.sin(Math.toRadians(car.getPhi())));

		carR = new Point(car.getX() + car.getRadius() * Math.cos(Math.toRadians(car.getPhi() + 45)),
				car.getY() + car.getRadius() * Math.sin(Math.toRadians(car.getPhi() + 45)));

		carL = new Point(car.getX() + car.getRadius() * Math.cos(Math.toRadians(car.getPhi() - 45)),
				car.getY() + car.getRadius() * Math.sin(Math.toRadians(car.getPhi() - 45)));

		CarT_Locate = new Point(car.getX() + 300 * Math.cos(Math.toRadians(car.getPhi())),
				car.getY() + 300 * Math.sin(Math.toRadians(car.getPhi())));
		min_InterPointT = CountMinInterPoint(CarLocate, CarT_Locate, carT);

		CarR_Locate = new Point(car.getX() + 300 * Math.cos(Math.toRadians(car.getPhi() + 45)),
				car.getY() + 300 * Math.sin(Math.toRadians(car.getPhi() + 45)));
		min_InterPointR = CountMinInterPoint(CarLocate, CarR_Locate, carR);

		CarL_Locate = new Point(car.getX() + 300 * Math.cos(Math.toRadians(car.getPhi() - 45)),
				car.getY() + 300 * Math.sin(Math.toRadians(car.getPhi() - 45)));
		min_InterPointL = CountMinInterPoint(CarLocate, CarL_Locate, carL);
	}

	public void run() {
		while (!isTerminated) {

			double distT, distL, distR;
			double theta = car.getTheta();
			engine.newlocation(theta);

			CountInterPoint_TRL();
			distR = CountDist(carR, min_InterPointR);
			distL = CountDist(carL, min_InterPointL);
			distT = CountDist(carT, min_InterPointT);

			System.out.println("distT: " + distT);
			System.out.println("distR: " + distR);
			System.out.println("distL: " + distL);

			FuzzySystem fSystem = new FuzzySystem();
			fSystem.Right_Left(distR, distL);
			fSystem.Front(distT);
			System.out.println("theta: " + fSystem.Defuzzification());
			// System.out.println("X : " + car.getX());
			// System.out.println("Y : " + car.getY());
			// System.out.println("phi: " + car.getPhi());
			car.setTheta(fSystem.Defuzzification());

			booboo.frame.revalidate();
			booboo.frame.repaint();

			if (car.getY() <= 90) {
				terminate();
			}

			try {
				sleep(50);
			} catch (InterruptedException e) {
			}
		}
	}
}
