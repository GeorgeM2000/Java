package arraylists;

import java.util.ArrayList;

class Student {
	private String aem;
	private String name;
	private ArrayList<Double> grades;
	
	public Student() { }

	public Student(String aem, String name, ArrayList<Double> grades) {
		super();
		this.aem = aem;
		this.name = name;
		this.grades = grades;
	}

	public String getAem() {
		return aem;
	}

	public void setAem(String aem) {
		this.aem = aem;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public ArrayList<Double> getGrades() {
		return grades;
	}

	public void setGrades(ArrayList<Double> grades) {
		this.grades = grades;
	}

	public double getMinGrade() {
		double lowGrade = grades.get(0);
		for (double grade : grades) {
			if (grade < lowGrade)
				lowGrade = grade;
		} 
		return lowGrade;
	}

	public double getMaxGrade() {
		double maxGrade = grades.get(0);
		for (int i=0; i < grades.size(); i++) {
			if (grades.get(i) > maxGrade)
				maxGrade = grades.get(i);
		} 
		return maxGrade;
	}
}

public class ArrayListTest3 {
	
	public static void main(String[] args) {
		ArrayList<Double> student1Grades = new ArrayList<Double>(); 
		student1Grades.add(Double.valueOf(6.5));
		student1Grades.add(Double.valueOf(5.5));
		student1Grades.add(Double.valueOf(5));
		ArrayList<Double> student2Grades = new ArrayList<Double>(); 
		student2Grades.add(Double.valueOf(8));
		student2Grades.add(Double.valueOf(7));
		student2Grades.add(Double.valueOf(6));

		Student student1 = new Student("2101","John",student1Grades);
		System.out.println("The maximum grade of the first student is : "+student1.getMaxGrade());
		Student student2 = new Student("1897","George",student2Grades);
		System.out.println("The maximum grade of the second student is "+student2.getMaxGrade());

		if (student1.getMinGrade() < student2.getMinGrade()) {
			System.out.println("The second student has higher grade that the first one.");
		}
		else {
			System.out.println("The second student has a lower grade that the first one.");
		}
	}
}
