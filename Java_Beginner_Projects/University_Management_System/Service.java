import java.util.Scanner;

public class Service {
	// Class members -------------------------------------
	private String SeName;
	private Department dep;
	private String SePhone;
	private int numOfEmployees;
	private double average;
	
	
	// Class constructors --------------------------------
	public Service() {
		SeName = "";
		dep = new Department();
		SePhone = "";
		average = 0.0;
		numOfEmployees = 0;
	}
	public Service(String seName, Department Dep, String sePhone, double Average, int NumOfEmployees) {
		SeName = seName;
		dep = Dep;
		SePhone = sePhone;
		average = Average;
		numOfEmployees = NumOfEmployees;
	}
	
	// Class methods --------------------------------------
	
	public String getSeName() {
		return SeName;
	}
	public void setSeName(String seName) {
		SeName = seName;
	}
	public Department getDep() {
		return dep;
	}
	public void setDep(Department dep) {
		this.dep = dep;
	}
	public String getSePhone() {
		return SePhone;
	}
	public void setSePhone(String sePhone) {
		SePhone = sePhone;
	}
	public int getNumOfEmployees() {
		return numOfEmployees;
	}
	public void setNumOfEmployees(int NumOfEmployees) {
		numOfEmployees = NumOfEmployees;
	}
	public double getAverage() {
		return average;
	}
	public void setAverage(double average) {
		this.average = average;
	}
	
	@Override
	public String toString() {
		return "Service \n\tService Name = " + SeName + "\n\tDepartment = " + dep.getDepName() + "\n\tService Phone = " + SePhone + "\n\tAverage = " + average + "\n";
	}
	
	public void readSerInfo(Scanner scan,Department Dep) {
		
		System.out.println("Enter the service name : ");
		SeName = scan.nextLine();
		System.out.println("Enter the service phone number : ");
		SePhone = scan.nextLine();
		System.out.println("Enter the service number of employees : ");
		numOfEmployees = Integer.parseInt(scan.nextLine());
		dep = Dep;
		
	}
	
}
