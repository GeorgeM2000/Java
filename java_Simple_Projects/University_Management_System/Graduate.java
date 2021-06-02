import java.util.Arrays;
import java.util.Scanner;

public class Graduate extends Student{
	// Class members --------------------------------------
	private String DegreeName;
	private	double DegreeGrade;
	private String ProjectTitle;
	private	Professor pro;
	
	
	// Class constructors ---------------------------------
	public Graduate(String degreeName, double degreeGrade, String projectTitle, Professor pro, int aM, Department dep, int yearOfImport, double[] grades, double average, String firstName, String lastName, int ag, String address) {
		super(aM, dep, yearOfImport, grades, average, firstName, lastName, ag, address);
		DegreeName = degreeName;
		DegreeGrade = degreeGrade;
		ProjectTitle = projectTitle;
		this.pro = pro;
	}
	
	public Graduate() {
		DegreeName = "";
		DegreeGrade = 0.0;
		ProjectTitle = "";
		this.pro = new Professor();
	}

	// Class methods --------------------------------------
	public String getDegreeName() {
		return DegreeName;
	}

	public void setDegreeName(String degreeName) {
		DegreeName = degreeName;
	}

	public double getDegreeGrade() {
		return DegreeGrade;
	}

	public void setDegreeGrade(double degreeGrade) {
		DegreeGrade = degreeGrade;
	}

	public String getProjectTitle() {
		return ProjectTitle;
	}

	public void setProjectTitle(String projectTitle) {
		ProjectTitle = projectTitle;
	}

	public Professor getPro() {
		return pro;
	}

	public void setPro(Professor pro) {
		this.pro = pro;
	}

	@Override
	public String toString() {
		return "Graduate \n\tDegree Name = " + DegreeName + "\n\tDegree Grade = " + DegreeGrade + "\n\tProject Title = " + ProjectTitle
				+ "\n\tProfessor = " + pro.getPeFirstName() + " " + pro.getPeLastName() + "\n\tAM = " + getAM() + "\n\tDepartment = " + getDep().getDepName() + "\n\tYear of import = "
				+ getYearOfImport() + "\n\tGrades = " + Arrays.toString(getGrades()) + "\n\tAverage = " + getAverage()
				+ "\n\tFirst name = " + getPeFirstName() + "\n\tLast name = "
				+ getPeLastName() + "\n\tAge = " + getAge() + "\n\tAddress = " + getPeAddress() + "\n";
	}
	
	public void readGradInfo(Scanner scan,Department Dep) {
		double[] grades = new double[3];
		double average = 0.0;
		System.out.println("Enter the first name of the student : ");
		Graduate.super.setPeFirstName(scan.nextLine());
		
		System.out.println("Enter the last name of the student : ");
		Graduate.super.setPeLastName(scan.nextLine());
		
		System.out.println("Enter the address of the student : ");
		Graduate.super.setPeAddress(scan.nextLine());
		
		System.out.println("Enter the degree name of the graduate student : ");
		DegreeName = scan.nextLine();
		
		System.out.println("Enter the project title of the graduate student : ");
		ProjectTitle = scan.nextLine();
		
		System.out.println("Enter the age of the student : ");
		Graduate.super.setAge(Integer.parseInt(scan.nextLine()));
		
		System.out.println("Enter the AM of the student : ");
		Graduate.super.setAM(Integer.parseInt(scan.nextLine()));
		
		System.out.println("Enter the year of import of the student : ");
		Graduate.super.setYearOfImport(Integer.parseInt(scan.nextLine()));
		
		for(int i = 0; i < 3; i++) {
			System.out.println("Enter the grade of the " + (i+1) + " subject of the graduate student : ");
			grades[i] = Double.parseDouble(scan.nextLine());
			average += grades[i];
		}
		
		System.out.println("Enter the degree grade of the graduate student : ");
		DegreeGrade = Double.parseDouble(scan.nextLine());
		
		Graduate.super.setGrades(grades);
		Graduate.super.setAverage(average/3.0);
		Graduate.super.setDep(Dep);
	}
	
}
