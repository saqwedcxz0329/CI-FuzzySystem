package Carsimulator;

public class Engine {
	private double x, y, phi;
	private double radphi, radtheta;
	public void newlocation(double theta){
		radphi = Math.toRadians(Car.getPhi());
		radtheta = Math.toRadians(theta);
		
		x = Car.getX() + Math.cos(radphi + radtheta) 
				+ Math.sin(radtheta) * Math.sin(radphi);
		y = Car.getY() + Math.sin(radphi + radtheta) 
				- Math.sin(radtheta) * Math.cos(radphi);
		phi = Car.getPhi() - (Math.asin((2*Math.sin(radtheta)) / (2*Car.getRadius()))) * 180 / Math.PI;
		
		Car.setPhi(phi);
		Car.setX(x);
		Car.setY(y);
	}
}
