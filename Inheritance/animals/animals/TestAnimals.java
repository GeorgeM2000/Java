package inheritance.animals;

public class TestAnimals {

	public static void main(String[] args) {
		Mammal m = new Mammal();
		m.setWeight(4.5);
	            
		Dog d1 = new Dog("black");
		d1.bark();
		d1.setWeight(6.7);
	            
		Sheep s1 = new Sheep();
		s1.setMilk(2.4);
		s1.setWeight(23.8);

		System.out.println(d1.toString());
		System.out.println(s1.toString());
	}

}
