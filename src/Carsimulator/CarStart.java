package Carsimulator;

public class CarStart extends Thread {
	Car car;
	Engine engine;
	public static Point carT, carL, carR, min_InterPointT, min_InterPointL, min_InterPointR;
	public static Point CarLocate, CarT_Locate, CarL_Locate, CarR_Locate;
	//public static double distT, distL, distR;
	
	public CarStart(Car car, Engine engine) {
		// TODO Auto-generated constructor stub
		this.car = car;
		this.engine = engine;
	}
	public void run() {
		while(true){
			
			double distT, distL, distR;
			double theta = car.getTheta();
			//System.out.println("theta: " + theta);
			engine.newlocation(theta);
			
			CarLocate = new Point(car.getX(), car.getY());
			carT = new Point(car.getX() + car.getRadius() * Math.cos(Math.toRadians(car.getPhi())),
					car.getY() + car.getRadius() * Math.sin(Math.toRadians(car.getPhi())));
			
			carR = new Point(car.getX() + car.getRadius() * Math.cos(Math.toRadians(car.getPhi() + 45)),
					car.getY() + car.getRadius() * Math.sin(Math.toRadians(car.getPhi() + 45)));
			
			carL = new Point(car.getX() + car.getRadius() * Math.cos(Math.toRadians(car.getPhi() - 45)),
					car.getY() + car.getRadius() * Math.sin(Math.toRadians(car.getPhi() - 45)));
			
			CarR_Locate = new Point(car.getX() + 300 * Math.cos(Math.toRadians(car.getPhi() + 45)),
					car.getY() + 300 * Math.sin(Math.toRadians(car.getPhi() + 45)));
			min_InterPointR = booboo.CountMinInterPoint(CarLocate, CarR_Locate, carR);
			distR = booboo.CountDist(carR, min_InterPointR);
			
			CarL_Locate = new Point(car.getX() + 300 * Math.cos(Math.toRadians(car.getPhi() - 45)),
					car.getY() + 300 * Math.sin(Math.toRadians(car.getPhi() - 45)));
			min_InterPointL = booboo.CountMinInterPoint(CarLocate, CarL_Locate, carL);
			distL = booboo.CountDist(carL, min_InterPointL);
			
			CarT_Locate = new Point(car.getX() + 300 * Math.cos(Math.toRadians(car.getPhi())),
					car.getY() + 300 * Math.sin(Math.toRadians(car.getPhi())));
			min_InterPointT = booboo.CountMinInterPoint(CarLocate, CarT_Locate, carT);
			distT = booboo.CountDist(carT, min_InterPointT);
			
			System.out.println("distT: " + distT);
			System.out.println("distR: " + distR);
			System.out.println("distL: " + distL);
			
			FuzzySystem fSystem = new FuzzySystem();
			fSystem.Right_Left(distR, distL);
			fSystem.Front(distT);
			System.out.println("theta: " + fSystem.Defuzzification());
			car.setTheta(fSystem.Defuzzification());
			
			booboo.frame.revalidate();
			booboo.frame.repaint();
			try {
				this.sleep(100);
			} catch (InterruptedException e) {
			}
		}
	}
}
