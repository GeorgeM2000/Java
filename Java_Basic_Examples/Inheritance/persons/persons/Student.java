package inheritance.persons;

public class Student extends Person {

	private int aem;

	
	public Student() { aem=19; }

	public Student(int aem) {
		this.aem = aem;
	}

	public Student(int aem, String name) {
		super(name);  
		this.aem = aem;
	}

	public int getAem() {
		return aem;
	}

	public void setAem(int aem) {
		this.aem = aem;
	}
	

	public void print() {
		super.print(); 
		System.out.println("Aem="+aem+"\n");
	}
	
	public void print(int a) {
		System.out.println("Aem="+aem+"\n");
	}

}
