package inheritance.persons;

public class TestPerson {
	public static void main(String[] args) {
		Student s1 = new Student();
		s1.print();
		s1.setAem(2021);
		s1.print();
		s1.setName("George");
		s1.print(5);
		s1.print();
		
		Student s2 = new Student(1500);
		s2.print();

		Student s3 = new Student(2500, "Kostas");
		s3.print();
		
		Person p1 = new Person("Yannis");
		p1 = s3;
		System.out.println("-----");
		p1.print();  
		System.out.println("-----");
		System.out.println(p1.getName());
		if (p1 instanceof Student) {
			Student st = (Student)p1;
			System.out.println(st.getAem());
			st.setName("Dimitris");
			System.out.println(p1.getName());
		}
	}
}
