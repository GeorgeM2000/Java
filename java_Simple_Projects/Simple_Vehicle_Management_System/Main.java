/*
 * In this script i implement a simple vehicle management system.
 * The user enters information about trucks, motorcycles and cars.
 * 10% are trucks and motorcycles and 80% are cars. The information
 * regarding each vehicle is stored inside an array.
 */

import java.util.Scanner;

public class Main {
	
	
	// Method to find a position in the array v
	public static int findPosition(Vehicle[] V, int len) {
		int position;
		int randomPosition; 
		
		// Run until you find a position
		while(true) {
			
			// Calculate a random position
			randomPosition = (int) (Math.random() * len) + 0;
			
			// Check if there is an instance of the Car, Truck and Motorcycle class in that position
			if(!(V[randomPosition] instanceof Car) && !(V[randomPosition] instanceof Truck) && !(V[randomPosition] instanceof Motorcycle)) {
				position = randomPosition;
				break;
			}
		}
		return position;
	}
	

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("How many vehicle would you like to register? ");
		int N = Integer.parseInt(scan.nextLine());
		Vehicle[] v = new Vehicle[N];
		Truck t;
		Car c;
		Motorcycle mt;
		String owner;
		String registrationNumber;
		int yearOfConstruction;
		int iterations = ( ((int) Math.ceil((10/100.0) * N)) * 2);
		
		// Truck Input
		for(int a=0; a<((int) Math.ceil((10/100.0) * N)); a++) {
			
			System.out.println("Enter the name of the owner of the truck : ");
			owner = scan.nextLine();
			System.out.println("Enter the registration number of the truck : ");
			registrationNumber = scan.nextLine();
			System.out.println("Enter the number of axels of the truck : ");
			int numberOfAxels = Integer.parseInt(scan.nextLine());
			System.out.println("Enter the year of construction of the truck : ");
			yearOfConstruction = Integer.parseInt(scan.nextLine());
			
			
			// Create Truck object
			t = new Truck(numberOfAxels, owner, registrationNumber, yearOfConstruction);
			// Get the position
			int position = findPosition(v, N);
			
			// Store Truck object in array v
			v[position] = t;
		}
		
		
		
		// Motorcycle Input
		for(int b=0; b<((int) Math.ceil((10/100.0) * N)); b++) {
			
			System.out.println("Enter the owner of the motorcycle : ");
			owner = scan.nextLine();
			System.out.println("Enter the registration number of the motorcycle : ");
			registrationNumber = scan.nextLine();
			System.out.println("Enter the year of construction of the motorcycle : ");
			yearOfConstruction = Integer.parseInt(scan.nextLine());
			System.out.println("Does the motorcycle have a basket? Press true for yes and false for no : ");
			boolean hB = Boolean.parseBoolean(scan.nextLine());
			
			
			// Create Motorcycle object
			mt = new Motorcycle(hB, owner, registrationNumber, yearOfConstruction);
			// Get the position
			int position = findPosition(v, N);
			
			// Store Motorcycle object in array v
			v[position] = mt;
		}
		
		
		// Car Input
		for(int i=0; i<(N-iterations); i++) {
			
			System.out.println("Enter the owner of the car : ");
			owner = scan.nextLine();
			System.out.println("Enter the registration number of the car : ");
			registrationNumber = scan.nextLine();
			System.out.println("Enter the year of construction of the car : ");
			yearOfConstruction = Integer.parseInt(scan.nextLine());
			System.out.println("Enter the number of car doors : ");
			int numberOfPorts = Integer.parseInt(scan.nextLine());
			
			// Create Car object
			c = new Car(numberOfPorts, owner, registrationNumber, yearOfConstruction);
			// Get the position
			int position = findPosition(v, N);
			
			// Store Car object in array v
			v[position] = c;
		}
		
		
		// Show the contents of the array
		for(int j=0; j<N; j++) {
			v[j].print();
			System.out.println();
		}
		
		scan.close();
	}

}
