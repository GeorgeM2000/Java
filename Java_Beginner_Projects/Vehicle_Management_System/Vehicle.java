
public class Vehicle implements Comparable<Vehicle>{
	// Class members ----------------------------------------
	private String owner;
	private String registrationNumber;
	private int yearOfConstuction;
	private AddressingSystem addressSys;
	private Engine eng;
	
	// Class constructors -----------------------------------
	public Vehicle() {
		this.owner = "";
		this.registrationNumber = "";
		this.yearOfConstuction = -1;
		this.eng = new Engine();
		this.addressSys = new AddressingSystem();
	}
	
	public Vehicle(String own, String reNum, int yOC, AddressingSystem sd, Engine engine) {
		this.owner = own;
		this.registrationNumber = reNum;
		this. yearOfConstuction = yOC;
		this.eng = engine;
		this.addressSys = sd;
	}
	
	
	// I Override this method to sort by horsepower
	@Override
	public int compareTo(Vehicle v) {
		if(this.eng.getHorsepower() > v.eng.getHorsepower()) {
			return 1;
		}
		else if(this.eng.getHorsepower() == v.eng.getHorsepower()) {
			return 0;
		}
		else {
			return -1;
		}
	}

	// Class methods -----------------------------------------
	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public int getYearOfConstuction() {
		return yearOfConstuction;
	}

	public void setYearOfConstuction(int yearOfConstuction) {
		this.yearOfConstuction = yearOfConstuction;
	}

	public AddressingSystem getAddressSys() {
		return addressSys;
	}

	public void setAddressSys(AddressingSystem addressSys) {
		this.addressSys = addressSys;
	}

	public Engine getEng() {
		return eng;
	}

	public void setEng(Engine eng) {
		this.eng = eng;
	}
	
	
}
