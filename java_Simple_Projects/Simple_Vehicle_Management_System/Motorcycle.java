
public class Motorcycle extends Vehicle {
	private boolean hasBasket;
	
	public Motorcycle(boolean hB, String ow, String rN, int yOC) {
		super(ow, rN, yOC);
		hasBasket = hB;
	}
	public void drive() {
		System.out.println("Your car driving licence is not enough. You need a special licence dear " + Motorcycle.super.owner);
	}
	public boolean isHasBasket() {
		return hasBasket;
	}
	public void setHasBasket(boolean hasBasket) {
		this.hasBasket = hasBasket;
	}
	
	@Override
	public void print() {
		super.print();
		if(hasBasket) System.out.println("The motorcycle has a basket.");
		else System.out.println("The motorcycle doesn't have a basket.");
	}
	
	
}
