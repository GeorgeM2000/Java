/*

This script implements a simple university management system. User has to enter 
information for 2 departments, 6 employees (2 employees for each service),
6 undergraduate students and 2 graduate students. It prints the average employee
earnings for each service, the average number of publications for the professors
and the average grade for each student according to the year of import.
 
 Note : 
 	Because the number of loops and the number of registrations in each for loop is quite big,
 	you might want to decrease the number of loops in each for loop. For the best results in 
 	this project, put 1 as the max iteration in the first for loop, 1 for the second for loop,
 	3 for the third and 3 for the fourth.
 */

import java.util.*;


public class University {
	
	public static double averageDEP(LinkedList<Professor> professors) {
		double average = 0.0;
		for(int i = 0; i < professors.size(); i++) {
			average += (double) professors.get(i).getNumberOfPublications();
		}
		average = average / professors.size();
		return average;
	}
	
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		Department Department;
		Service Service;
		Student Student;
		Employee Employee;
		Graduate Graduate;
		Professor Professor;
		LinkedList<Student> students = new LinkedList<>();
		LinkedList<Professor> professors;
		LinkedList<Employee> employees;
		LinkedList<Graduate> graduates = new LinkedList<>();
		LinkedList<Service> services;
		LinkedList<Student> tempStudents;
		LinkedList<Graduate> tempGraduates;
		int postGraduates;
		float averageEarnings;
		
		
		
		// for loop for 2 departments
		for(int a = 0; a < 2; a++) {
			
			tempStudents = new LinkedList<>();
			tempGraduates = new LinkedList<>();
			services = new LinkedList<>();
			professors = new LinkedList<>();
			employees = new LinkedList<>();
			
			// Read department information
			Department = new Department();
			Department.readInfo(scan);
			
			
			
			
			// for loop for a total of 6 employees. Each department has 6 employees.
			for(int b = 0; b < 3; b++) {
				averageEarnings = (float) 0.0;
				
				// Read Service information
				Service = new Service();
				Service.readSerInfo(scan,Department);
				services.add(Service);
				
				// Each Service has 2 employees
				for(int c = 0; c < 2; c++) {
					
					System.out.println("If you are a professor press 1.\nIf your speciality is something other than a professor press 2.");
					int choice = Integer.parseInt(scan.nextLine());
					switch(choice) {
					case 1:
						Professor = new Professor();
						Professor.readProInfo(scan,Department);
						averageEarnings += (float) Professor.getSalary();
						professors.add(Professor);
						break;
					case 2:
						Employee = new Employee();
						Employee.readEmInfo(scan,Department);
						averageEarnings += (float) Employee.getSalary();
						employees.add(Employee);
						break;
					default:
						System.out.println("You have entered an invalid value. Please try again.");
					}
				}
				System.out.println("The average earnings for the " + Service.getSeName() + " service is " + (averageEarnings/2));
			}
			
			postGraduates = 0;
			// for loop for a total of 6 students. Each department has 6 students.
			// 2 of the 6 students must be post graduate students
			for(int d = 0; d < 6; d++) {
				System.out.println("If you are a post graduate student press 1.\nIf you are a undergraduate student press 2.\n\tThere must be at least 2 post graduate students.");
				int choice = Integer.parseInt(scan.nextLine());
				switch(choice) {
				case 1:
					Graduate = new Graduate();
					Graduate.readGradInfo(scan,Department);
					System.out.println("Who was the supervisor for your project? Enter his AFM : ");
					String afm = scan.nextLine();
					for(Professor pr : professors) {
						if(pr.getAFM().equals(afm)) {
							Graduate.setPro(pr);
							break;
						}
					}
					tempGraduates.add(Graduate);
					graduates.add(Graduate);
					++postGraduates;
					break;
				case 2:
					Student = new Student();
					Student.readStudInfo(scan,Department);
					tempStudents.add(Student);
					students.add(Student);
					break;
				default:
					System.out.println("You have entered an invalid value. Please try again.");
				}
			}
			
			// If user has entered less than 2 post graduates students.
			if(postGraduates < 2) {
				System.out.println("You have entered "+ postGraduates + " post graduates students. 2 are required.");
				for(int ps = 0;ps < (2 - postGraduates);ps++) {
					Graduate = new Graduate();
					Graduate.readGradInfo(scan,Department);
					System.out.println("Who was the supervisor for your project? Enter his AFM : ");
					String afm = scan.nextLine();
					for(Professor pr : professors) {
						if(pr.getAFM().equals(afm)) {
							Graduate.setPro(pr);
							break;
						}
					}
					tempGraduates.add(Graduate);
					graduates.add(Graduate);
					++postGraduates;
				}
			}
				
			
			// Print Department information
			System.out.println(Department);
			// Print services information
			for(Service service : services) System.out.println(service);
			// Print employees information
			for(Employee employee : employees) System.out.println(employee); 
			// Print professors information
			for(Professor professor : professors) System.out.println(professor);
			// Print students information
			for(Student student : tempStudents) System.out.println(student);
			// Print graduates information
			for(Graduate graduate : tempGraduates) System.out.println(graduate);
			
			System.out.println("The average number of publications for the " + Department.getDepName() + " department is " + averageDEP(professors));
			
			
		}
		
		// Sort students and graduates based on the year of import
		Collections.sort(students, Collections.reverseOrder());
		Collections.sort(graduates, Collections.reverseOrder());
		
		// Print students information sorted by the year of import
		System.out.println("Students : \n");
		for(Student student : students) {
			System.out.println("\t" + student.getAM() + " " + student.getPeFirstName() + " " + student.getPeLastName() + " " + student.getAverage() + " " + student.getYearOfImport());
		}
		
		// Print graduates information sorted by the year of import
		System.out.println("Graduates : \n");
		for(Graduate graduate : graduates) {
			System.out.println("\t" + graduate.getAM() + " " + graduate.getPeFirstName() + " " + graduate.getPeLastName() + " " + graduate.getAverage() + " " + graduate.getYearOfImport());
		}
	}

}
