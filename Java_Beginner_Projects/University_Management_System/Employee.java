import java.util.Scanner;

public class Employee extends Person{
	// Class members ------------------------------
	private String AFM;
	private double salary;
	private String speciality;
	private int YearsOfService;
	private Department dep;
	
	
	// Class constructors -------------------------
	public Employee(String aFM, double salary, String speciality, int yearsOfService, Department dep, String firstName, String lastName, int ag, String address) {
		super(firstName, lastName, ag, address);
		AFM = aFM;
		this.salary = salary;
		this.speciality = speciality;
		YearsOfService = yearsOfService;
		this.dep = dep;
	}
	
	public Employee() {
		AFM = "";
		this.salary = 0.0;
		this.speciality = "";
		YearsOfService = 0;
		this.dep = new Department();
	}

	// Class methods -------------------------------
	public String getAFM() {
		return AFM;
	}

	public void setAFM(String aFM) {
		AFM = aFM;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	public int getYearsOfService() {
		return YearsOfService;
	}

	public void setYearsOfService(int yearsOfService) {
		YearsOfService = yearsOfService;
	}

	public Department getDep() {
		return dep;
	}

	public void setDep(Department dep) {
		this.dep = dep;
	}

	@Override
	public String toString() {
		return "Employee \n\tAFM = " + AFM + "\n\tSalary = " + salary + "\n\tSpeciality = " + speciality + "\n\tXronia prouphresias = "
				+ YearsOfService + "\n\tDepartment = " + dep.getDepName() + "\n\tFirst name = " + getPeFirstName() + "\n\tLast name = "
				+ getPeLastName() + "\n\tAge = " + getAge() + "\n\tAddress = " + getPeAddress() + "\n";
	}
	
	public void readEmInfo(Scanner scan, Department Dep) {
		System.out.println("Enter the first name of the employee : ");
		Employee.super.setPeFirstName(scan.nextLine());
		
		System.out.println("Enter the last name of the employee : ");
		Employee.super.setPeLastName(scan.nextLine());
		
		System.out.println("Enter the address of the employee : ");
		Employee.super.setPeAddress(scan.nextLine());
		
		System.out.println("Enter the AFM : ");
		AFM = scan.nextLine();
		
		System.out.println("Enter the speciality : ");
		speciality = scan.nextLine();
		
		System.out.println("Enter the age of the employee : ");
		Employee.super.setAge(Integer.parseInt(scan.nextLine()));
		
		System.out.println("Enter the years of service : ");
		YearsOfService = Integer.parseInt(scan.nextLine());
		
		System.out.println("Enter the salary : ");
		salary = Double.parseDouble(scan.nextLine());
		
		dep = Dep;
		
	}
}
