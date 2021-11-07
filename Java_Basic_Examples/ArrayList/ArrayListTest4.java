package arraylists;

import java.util.ArrayList;
import java.util.Scanner;

class Foititis {
	private String name;
	private ArrayList<Double> grades;
	
	public Foititis() { }

	public Foititis(String name, ArrayList<Double> grades) {
		super();
		this.name = name;
		this.grades = grades;
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
	
	public double getAvgGrade() {
		double sum = 0.0;
		for (double grade : grades) {
			sum += grade;
		} 
		return sum/grades.size();
	}
}

public class ArrayListTest4 {
	
	public static void main(String[] args) {
		ArrayList<Double> student1Grades = new ArrayList<Double>(); 
		student1Grades.add(Double.valueOf(6));
		student1Grades.add(Double.valueOf(5.5));
		student1Grades.add(Double.valueOf(5));
		ArrayList<Double> student2Grades = new ArrayList<Double>(); 
		student2Grades.add(Double.valueOf(8));
		student2Grades.add(Double.valueOf(7));
		student2Grades.add(Double.valueOf(6));

		
		
		ArrayList<Foititis> al = new ArrayList<Foititis>();
		
		al.add(new Foititis("John", student1Grades));
		al.add(new Foititis("Jack", student2Grades));
		
		for (int i=0; i<al.size(); i++) {
			System.out.println("Foititis "+i);
			Foititis f = al.get(i);
			ArrayList fGrades = f.getGrades();
			for (int j=0; j<fGrades.size(); j++) {
				System.out.println(fGrades.get(j));
			}
		}
		
		if (al.get(0).getAvgGrade() > al.get(1).getAvgGrade()) {
			System.out.println("Student 1 has an average grade of "+al.get(0).getAvgGrade());
		}
		else {
			System.out.println("Student 2 has an average grade of "+al.get(1).getAvgGrade());
		}
		
	}
}
