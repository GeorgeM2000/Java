package inheritance.animals;

public class Dog extends Mammal {

	private String color;

	public Dog(String c) { 
		color = c; 
	}

	public void bark() {
		System.out.println("I am a dog and I can bark");
	}
	
	public String toString() {
		return "Dog " + super.toString() + " color " + color;
	}
}
