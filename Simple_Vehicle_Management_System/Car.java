
public class Car extends Vehicle{
	private int numberOfPorts;
	
	public Car(int nOP, String ow, String rN, int yOC) {
		super(ow, rN, yOC);
		numberOfPorts = nOP;
	}
	
	public void drive() {
		System.out.println("You need a car driving licence dear "+ Car.super.owner);
	}
	public int getNumberOfPorts() {
		return numberOfPorts;
	}
	public void setNumberOfPorts(int numberOfPorts) {
		this.numberOfPorts = numberOfPorts;
	}
	
	
	@Override
	public void print() {
		super.print();
		System.out.println("The Car has " + numberOfPorts + " number of doors. ");
	}
		
}
