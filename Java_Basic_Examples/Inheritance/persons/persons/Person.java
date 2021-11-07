package inheritance.persons;

public class Person {

	private String name;

	public Person() { name = "..."; System.out.println("default constructor of Person called"); }
	
	public Person(String name) {
		this.name = name;
		System.out.println("constructor with 1 param of Person called");
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void print() {
		System.out.println("Name="+name);
	}
}
