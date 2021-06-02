package inheritance.animals;

public class Sheep extends Mammal {

	private double milkperday;

	public Sheep () { 
		milkperday = 0.0; 
	}

	public void setMilk (double m) {
		milkperday = m;
	}

	public String toString() {
		return "Sheep " + super.toString() + " milkperday " + milkperday;
	}
}
