package PSO;

public class Particle {
	private double[] location;
	private double[] velocity;
	private double[] pBest;
	private double BestFitness;
	
	public Particle(double[] location, double[] velocity){
		this.location = location;
		this.velocity = velocity;
		setBestFitness(1000000);
	}
	
	public double[] getLocation(){
		return location;
	}
	
	public double[] getVelocity(){
		return velocity;
	}
	
	public double[] getPBest(){
		return pBest;
	}
	
	public double getBestFitness(){
		return BestFitness;
	}
	
	public void setLocation(double[] location){
		this.location = location;
	}
	
	public void setVelocity(double[] velocity){
		this.velocity = velocity;
	}
	
	public void setPBest(double[] pBest){
		this.pBest = pBest;
	}
	
	public void setBestFitness(double BestFitness){
		this.BestFitness = BestFitness;
	}
}
