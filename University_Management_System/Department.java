import java.util.Scanner;

public class Department {
	
	// Class members -------------------------------------
	private String DepName;
	private String DepAddress;
	private String DepSecrPhoneNum;
	private int NumOfStudents;
	private int NumOfEmployees;
	
	// Class constructors --------------------------------
	public Department() {
		this.DepAddress = "";
		this.DepName = "";
		this.DepSecrPhoneNum = "";
		NumOfStudents = 0;
		NumOfEmployees = 0;
	}

	public Department(String depName, String depAddress, String depSecrPhoneNum, int numOfStudents, int numOfEmployees) {
		this.DepName = depName;
		this.DepAddress = depAddress;
		this.DepSecrPhoneNum = depSecrPhoneNum;
		NumOfStudents = numOfStudents;
		NumOfEmployees = numOfEmployees;
	}

	
	// Class methods --------------------------------------
	@Override
	public String toString() {
		return "Department \n\tDepartment Name = " + DepName + "\n\n\tDepartment Address = " + DepAddress + "\n\n\n\tDepartment Secratery Phone Number = " + DepSecrPhoneNum + 
				"\n\tNumber of students = " + NumOfStudents + "\n\tNumber of employees = " + NumOfEmployees;
	}

	public String getDepName() {
		return DepName;
	}

	public void setDepName(String depName) {
		this.DepName = depName;
	}

	public String getDepAddress() {
		return DepAddress;
	}

	public void setDepAddress(String depAddress) {
		this.DepAddress = depAddress;
	}

	public String getDepSecrPhoneNum() {
		return DepSecrPhoneNum;
	}

	public void setDepSecrPhoneNum(String depSecrPhoneNum) {
		this.DepSecrPhoneNum = depSecrPhoneNum;
	}

	public int getNumOfStudents() {
		return NumOfStudents;
	}

	public void setNumOfStudents(int numOfStudents) {
		this.NumOfStudents = numOfStudents;
	}

	public int getNumOfEmployees() {
		return NumOfEmployees;
	}

	public void setNumOfEmployees(int numOfEmployees) {
		this.NumOfEmployees = numOfEmployees;
	}
	
	public void readInfo(Scanner scan) {
		
		System.out.println("Enter the department name : ");
		DepName = scan.nextLine();
		System.out.println("Enter the department address : ");
		DepAddress = scan.nextLine();
		System.out.println("Enter the department secratery phone number : ");
		DepSecrPhoneNum = scan.nextLine();
		System.out.println("Enter the number of students : ");
		NumOfStudents = Integer.parseInt(scan.nextLine());
		System.out.println("Enter the number of employees : ");
		NumOfEmployees = Integer.parseInt(scan.nextLine());
		
	}
	
	
}
