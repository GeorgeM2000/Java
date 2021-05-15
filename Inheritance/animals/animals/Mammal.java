package inheritance.animals;

public class Mammal {

	private double weight;

    public Mammal() { 
    	weight = 0.0; 
    }

    public void setWeight (double w) {
		weight = w;
    }
    
	public String toString() {
		return "weight " + weight;
	}
}
