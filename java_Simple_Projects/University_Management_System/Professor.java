import java.util.*;


public class Professor extends Employee {
	// Class members ------------------------
	private String Step; // Βαθμίδα
	private int PhdDuration;
	private int NumberOfPublications;
	private int SupervisorOfPostgraduateStudies;
	private int NumOfSubjects;
	
	
	// Class constructors -------------------
	public Professor() {
		super();
		
	}
	public Professor(String step, int phdDuration, int numberOfPublications, int supervisorOfPostgraduateStudies, int numOfSubjects, String aFM, double salary, String speciality, int yearsOfService, Department dep, String firstName,
			String lastName, int ag, String address) {
		super(aFM, salary, speciality, yearsOfService, dep, firstName, lastName, ag, address);
		Step = step;
		PhdDuration = phdDuration;
		NumberOfPublications = numberOfPublications;
		SupervisorOfPostgraduateStudies = supervisorOfPostgraduateStudies;
		NumOfSubjects = numOfSubjects;
	}
	
	// Class methods ------------------------
	public String getStep() {
		return Step;
	}
	public void setStep(String step) {
		Step = step;
	}
	public int getPhdDuration() {
		return PhdDuration;
	}
	public void setPhdDuration(int phdDuration) {
		PhdDuration = phdDuration;
	}
	public int getNumberOfPublications() {
		return NumberOfPublications;
	}
	public void setNumberOfPublications(int numberOfPublications) {
		NumberOfPublications = numberOfPublications;
	}
	public int getSupervisorOfPostgraduateStudies() {
		return SupervisorOfPostgraduateStudies;
	}
	public void setSupervisorOfPostgraduateStudies(int supervisorOfPostgraduateStudies) {
		SupervisorOfPostgraduateStudies = supervisorOfPostgraduateStudies;
	}
	public int getNumOfSubjects() {
		return NumOfSubjects;
	}
	public void setNumOfSubjects(int numOfSubjects) {
		NumOfSubjects = numOfSubjects;
	}
	@Override
	public String toString() {
		return "Professor \n\tStep = " + Step + "\n\tPhdDuration = " + PhdDuration + "\n\tNumber of publications = "
				+ NumberOfPublications + "\n\tSupervisor of postgraduateStudies = " + SupervisorOfPostgraduateStudies
				+ "\n\tTeaches " + NumOfSubjects + " number of subjects" + "\n\tAFM = " + getAFM() + "\n\tSalary = " + getSalary()
				+ "\n\tSpeciality = " + getSpeciality() + "\n\tXronia prouphresias = " + getYearsOfService()
				+ "\n\tDepartment = " + getDep().getDepName() + "\n\tFirst name = "
				+ getPeFirstName() + "\n\tLast name = " + getPeLastName() + "\n\tAge = " + getAge()
				+ "\n\tAddress = " + getPeAddress() + "\n";
	}
	
	public void readProInfo(Scanner scan,Department Dep) {
		System.out.println("Enter your first name : ");
		Professor.super.setPeFirstName(scan.nextLine());
		
		System.out.println("Enter your last name : ");
		Professor.super.setPeLastName(scan.nextLine());
		
		System.out.println("Enter your address : ");
		Professor.super.setPeAddress(scan.nextLine());
		
		System.out.println("Enter the AFM of the professor : ");
		Professor.super.setAFM(scan.nextLine());
		
		System.out.println("Enter the speciality of the professor : ");
		Professor.super.setSpeciality(scan.nextLine());
		
		System.out.println("Enter the professor's grade(vathmida) : ");
		Step = scan.nextLine();
		
		System.out.println("Enter the years of experience of the professor : ");
		Professor.super.setYearsOfService(Integer.parseInt(scan.nextLine()));
		
		System.out.println("Enter the number of subjects that the professor teaches : ");
		NumOfSubjects = Integer.parseInt(scan.nextLine());
		
		System.out.println("Enter the years it took for professor to complete the Phd : ");
		PhdDuration = Integer.parseInt(scan.nextLine());
		
		System.out.println("Enter the salary of the professor : ");
		Professor.super.setSalary(Double.parseDouble(scan.nextLine()));
		
		System.out.println("Enter your age : ");
		Professor.super.setAge(Integer.parseInt(scan.nextLine()));
		
		System.out.println("Enter the number of publications : ");
		NumberOfPublications = Integer.parseInt(scan.nextLine());
		
		System.out.println("Enter the number of postgraduate programs in which the professor is the supervisor : ");
		SupervisorOfPostgraduateStudies = Integer.parseInt(scan.nextLine());
		
		Professor.super.setDep(Dep);
		
		
	}
	
}
