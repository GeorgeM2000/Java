import java.util.Arrays;

import java.util.Scanner;

public class Student extends Person implements Comparable<Student> {
	// Class members --------------------------------
	private int AM;
	private Department dep;
	private int YearOfImport;
	private double[] Grades;
	private double Average;
	
	// Class constructors ---------------------------
	public Student() {
		super();
		AM = 0;
		dep = new Department();
		YearOfImport = 0;
		Grades = new double[3];
		Average = 0.0;
	}

	public Student(int aM, Department dep, int yearOfImport, double[] grades, double average, String firstName, String lastName, int ag, String address) {
		super(firstName, lastName, ag, address);
		AM = aM;
		this.dep = dep;
		YearOfImport = yearOfImport;
		Grades = grades;
		Average = average;
	}

	// Class methods --------------------------------
	public int getAM() {
		return AM;
	}

	public void setAM(int aM) {
		AM = aM;
	}

	public Department getDep() {
		return dep;
	}

	public void setDep(Department dep) {
		this.dep = dep;
	}

	public int getYearOfImport() {
		return YearOfImport;
	}

	public void setYearOfImport(int yearOfImport) {
		YearOfImport = yearOfImport;
	}

	public double[] getGrades() {
		return Grades;
	}

	public void setGrades(double[] grades) {
		Grades = grades;
	}

	public double getAverage() {
		return Average;
	}

	public void setAverage(double average) {
		Average = average;
	}

	@Override
	public String toString() {
		return "Student \n\tAM = " + AM + "\n\tDepartment = " + dep.getDepName() + "\n\tYear of import = " + YearOfImport + "\n\tGrades = "
				+ Arrays.toString(Grades) + "\n\tAverage = " + Average + "\n\tFirst name = " + Student.super.getPeFirstName() + "\n\tLast name = " + Student.super.getPeLastName() + ""
						+ "\n\tAge = " + Student.super.getAge() + "\n\tAddress = " + Student.super.getPeAddress() + "\n";
	}
	
	public void readStudInfo(Scanner scan, Department Dep) {
		
		System.out.println("Enter the first name of the student : ");
		Student.super.setPeFirstName(scan.nextLine());
		
		System.out.println("Enter the last name of the student : ");
		Student.super.setPeLastName(scan.nextLine());
		
		System.out.println("Enter the address of the student : ");
		Student.super.setPeAddress(scan.nextLine());
		
		System.out.println("Enter the age of the student : ");
		Student.super.setAge(Integer.parseInt(scan.nextLine()));
		
		System.out.println("Enter the AM of the student : ");
		AM = Integer.parseInt(scan.nextLine());
		
		System.out.println("Enter the year of import of the student : ");
		YearOfImport = Integer.parseInt(scan.nextLine());
		
		for(int i = 0; i < 3; i++) {
			System.out.println("Enter the " + (i+1) + " grade of the student : ");
			Grades[i] = Double.parseDouble(scan.nextLine());
			Average += Grades[i];
		}
		
		Average = Average / 3.0;
		dep = Dep;
		
	}
	
	@Override
	public int compareTo(Student s) {
		if(this.YearOfImport > s.YearOfImport) {
			return 1;
		}
		else if(this.YearOfImport == s.YearOfImport) {
			return 0;
		}
		else {
			return -1;
		}
	}
	
	
	
	
}
