import java.util.*;

public class Transport {
	
	// Method implemented for the 2nd switch case of the main method
	// Method to find cubism given registartionNumber
	public static int CubismSearch(HashMap<String,Vehicle> vehicles, String reNum) {
		Set<String> set = vehicles.keySet();
		Iterator<String> it = set.iterator();
		while(it.hasNext()) {
			String key = it.next();
			if(vehicles.get(key).getRegistrationNumber().equals(reNum)) {
				return vehicles.get(key).getEng().getCubism();
			}
		}
		return -1;
	}
	
	// Method implemented for the 3rd switch case of the main method
	// Method to store the horsepower of every vehicle constructed in yOC and sort it by the horsepower value
	public static void horsePower(HashMap<String, Vehicle> vehicles, int yOC) {
		Set<String> set = vehicles.keySet();
		Iterator<String> it = set.iterator();
		LinkedList<Vehicle> list = new LinkedList<Vehicle>();
		int count = 0;
		while(it.hasNext()) {
			String key = it.next();
			if(vehicles.get(key).getYearOfConstuction() == yOC) {
				++count;
				list.add(vehicles.get(key));
			}
		}
		
		System.out.println("There are " + count + " vehicles with the " + yOC + " year of construction");
		Collections.sort(list, Collections.reverseOrder());
		for(int i = 0; i < list.size(); i++) {
			System.out.println("Vehicle " + (i+1) + " has " + list.get(i).getEng().getHorsepower() + " horse power.");
		}
	}
	
	// Method implemented for the 4th switch case of the main method
	// Method to find the owner of a vehicle given the registrationNumber
	public static void whoIsTheOwner(HashMap<String, Vehicle> vehicles, String reNum) {
		Set<String> set = vehicles.keySet();
		Iterator<String> it = set.iterator();
		while(it.hasNext()) {
			String key = it.next();
			if(vehicles.get(key).getRegistrationNumber().equals(reNum)) {
				System.out.println("The owner is " + vehicles.get(key).getOwner() + " and the year of construction is " + vehicles.get(key).getYearOfConstuction());
				break;
			}
		}
	}
	
	
	public static void main(String[] args) {
		HashMap<String, Vehicle> vehicles = new HashMap<>();			
		Scanner scan = new Scanner(System.in);							
		
		String owner;
		String registrationNumber;
		int yearOfConstruction;
		int cubism;
		int horsepower;
		boolean hasGivenValues = false;
		
		System.out.println("Would you like to start? ");
		String Continue = scan.nextLine();
		
		while((!Continue.equals("no") || !Continue.equals("n")) && (Continue.equals("yes") || Continue.equals("y"))) {
			
			// User Input
			System.out.println("Enter the name of the owner : ");
			owner = scan.nextLine();
			System.out.println("Enter the registration number : ");
			registrationNumber = scan.nextLine();
			System.out.println("Enter the year of construction : ");
			yearOfConstruction = Integer.parseInt(scan.nextLine());
			System.out.println("Enter the cubism(kyvismos) : ");
			cubism = Integer.parseInt(scan.nextLine());
			System.out.println("Enter the horse power : ");
			horsepower = Integer.parseInt(scan.nextLine());
			
			
			// Object creation
			// Initialise objects with the values that were given above
			Engine engine = new Engine(cubism, horsepower);			// Create an Engine object
			AddressingSystem sys = new AddressingSystem();			// Create an AddressingSystem object
			
			Vehicle vehicle = new Vehicle(owner, registrationNumber, yearOfConstruction, sys, engine);			// Create a Vehicle object
			vehicles.put(registrationNumber, vehicle);			// Add Vehicle object to HashMap
			hasGivenValues = true;
			
			System.out.println("\nWould you like to continue? ");
			Continue = scan.nextLine();
		}
		
		System.out.println("Enter a number 0 - 4 :\n\t0 to exit the program.\n\t1 to find cubism given registration number.\n\t2 to list the horsepower of every vehicle given the year of construction"
				+ "\n\t3 to find the owner of a vehicle given the registration number\n\t4 to execute a method of the engine or the addressing system\n");
		int choice = Integer.parseInt(scan.nextLine());
		
		// Switch
		switch(choice) {
		case 0:
			System.exit(1);
			break;
		case 1:
			if(!hasGivenValues){
				System.out.println("User hasn't entered any information. Can't procced.");
			} else {
				System.out.println("Enter the registration number : ");
				registrationNumber = scan.nextLine();
				System.out.println("The cubism is : " + CubismSearch(vehicles,registrationNumber));
			}
			break;
			
		case 2:
			if(!hasGivenValues){
				System.out.println("User hasn't entered any information. Can't procced.");
			} else {
				System.out.println("Enter the year of construction : ");
				yearOfConstruction = scan.nextInt();
				System.out.println("Horse power : ");
				horsePower(vehicles, yearOfConstruction);
			}
			break;
			
		case 3:
			if(!hasGivenValues) {
				System.out.println("User hasn't entered any information. Can't procced.");
			} else {
				System.out.println("Enter the registration nymber : ");
				registrationNumber = scan.nextLine();
				whoIsTheOwner(vehicles, registrationNumber);
			}
			break;
			
		case 4:
			
			System.out.println("Which method would you like the program to execute : ");
			
			System.out.println("\tFor the engine : ");
			System.out.println("\t\t1.engineOn()");
			System.out.println("\t\t2.engineFunctioning()");
			System.out.println("\t\t3.engineOff()");
			System.out.println("\tFor the addressing system :");
			System.out.println("\t\t4.turnOn()");
			System.out.println("\t\t5.turnOff()");
			System.out.println("\t\t6.turnLeft()");
			System.out.println("\t\t7.turnRight()");
			int method_choice = scan.nextInt();
			
			// Initialise objects for use
			AddressingSystem sys = new AddressingSystem();
			Engine eng = new Engine();
			
			// Nested Switch
			switch(method_choice) {
			case 1:
				eng.engineOn();
				break;
			case 2:
				eng.engineFunctioning();;
				break;
			case 3:
				eng.engineOff();
				break;
			case 4:
				sys.turnOn();
				break;
			case 5:
				sys.turnOff();
				break;
			case 6:
				sys.turnLeft();
				break;
			case 7:
				sys.turnRight();
				break;
			default:
				System.out.println("Wrong choice of method.");
				break;
			}
			break;
			
		default:
			
			System.out.println("Wrong choice.");
			break;
			
		}
		scan.close();
	}

}
