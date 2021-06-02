
public abstract class Vehicle {
	protected String owner;
	protected String registrationNumber;
	protected int yearOfConstruction;
	
	public Vehicle() {}
	
	public Vehicle(String ow, String rN, int yOC) {
		owner = ow;
		registrationNumber = rN;
		yearOfConstruction = yOC;
	}
	public void transferOwnership(String newOwner) {
		owner = newOwner;
		System.out.println("Ownership is transferd to "+ owner);
	}
	
	public void print() {
		System.out.println("The Vehicle's owner is " + owner + ", the registration number is " + registrationNumber + " and the year of construction is " + yearOfConstruction);
	}
	public abstract void drive();
	
}
