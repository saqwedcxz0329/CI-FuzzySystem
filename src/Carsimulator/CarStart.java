package Carsimulator;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

import GeneALG.*;

public class CarStart extends Thread {
	private Car car;
	private Engine engine;
	private GA ga;
	private  double[] bestDNA;
	private int sysFlag;// 0 for Fuzzy 1 for GA
	public static Point carT, carL, carR, min_InterPointT, min_InterPointL, min_InterPointR;
	public static Point CarLocate, CarT_Locate, CarL_Locate, CarR_Locate;

	private boolean isTerminated = false;

	public CarStart(Car car, Engine engine, int sysFlag) {
		// TODO Auto-generated constructor stub
		this.car = car;
		this.engine = engine;
		this.sysFlag = sysFlag;
		bestDNA = GA.bestDNA;
		CountInterPoint_TRL();
//		System.out.println("===================");
//		for(int i =0; i< bestDNA.length; i++){
//			System.out.print(bestDNA[i] + " ");
//		}
//		System.out.println();
	}


	public void terminate() {
		isTerminated = true;
		interrupt();
	}

	public Point CountMinInterPoint(Point o, Point p, Point Sensor) {// 求出和賽道距離最近之交點
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

	public double CountDist(Point p, Point q) {// 計算兩點之距離
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

			if (sysFlag == 0) {
				FuzzySystem fSystem = new FuzzySystem();
				fSystem.Right_Left(distR, distL);
				fSystem.Front(distT);

				/***** write TrainingData *****/
				try {
					BufferedWriter bw = new BufferedWriter(new FileWriter("TrainingData.txt", true));
					bw.write(distT + " " + distR + " " + distL + " " + theta);
					bw.newLine();
					bw.close();

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out.println(distT + " " + distR + " " + distL + " " + theta);

				car.setTheta(fSystem.Defuzzification());

			} else if (sysFlag == 1) {
				ArrayList<Double> Xn = new ArrayList<>();
				Xn.add(distT);
				Xn.add(distR);
				Xn.add(distL);
				car.setTheta(RBFN.computeTheta(bestDNA, Xn));
				System.out.println(car.getTheta());
			}

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
