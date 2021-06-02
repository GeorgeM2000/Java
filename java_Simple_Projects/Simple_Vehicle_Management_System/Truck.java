
public class Truck extends Vehicle{
	private int numberOfAxels;
	
	public Truck(int nOA, String ow, String rN, int yOC) {
		super(ow, rN, yOC);
		numberOfAxels = nOA;
	}
	
	public void drive() {
		System.out.println("You must be a professional driver. This is a "+ numberOfAxels + " axles truck Mr. "+ Truck.super.owner);
	}
	public int getNumberOfAxels() {
		return numberOfAxels;
	}
	public void setNumberOfAxels(int numberOfAxels) {
		this.numberOfAxels = numberOfAxels;
	}
	
	@Override
	public void print() {
		super.print();
		System.out.println("The truck has " + numberOfAxels + " number of axels. ");
	}
	
}
