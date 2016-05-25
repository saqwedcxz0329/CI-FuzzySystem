package Carsimulator;

public class Engine {
	Car car;
	private double x, y, phi;
	private double radphi, radtheta;

	public Engine(Car car) {
		// TODO Auto-generated constructor stub
		this.car = car;
	}

	public void newlocation(double theta) {
		radphi = Math.toRadians(car.getPhi());
		radtheta = Math.toRadians(theta);

		x = car.getX() + Math.cos(radphi + radtheta)
				+ Math.sin(radtheta) * Math.sin(radphi);
		y = car.getY() + Math.sin(radphi + radtheta)
				- Math.sin(radtheta) * Math.cos(radphi);
		phi = car.getPhi() - (Math.asin((2 * Math.sin(radtheta)) / (2 * car.getRadius()))) * 180 / Math.PI;

		car.setPhi(phi);
		car.setX(x);
		car.setY(y);
	}
}
