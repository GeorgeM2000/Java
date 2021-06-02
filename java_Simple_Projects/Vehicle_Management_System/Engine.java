
public class Engine {
	
	// Class members ---------------------------------------
	private int cubism;
	private int horsepower;
	
	// Class constructors ----------------------------------
	public Engine() {
		this.cubism = -1;
		this.horsepower = -1;
	}
	
	public Engine(int cb, int hp) {
		this.horsepower = hp;
		this.cubism = cb;
	}
	
	// Class methods ---------------------------------------
	public void engineOn() {
		System.out.println("The engine is turned on.");
	};
	public void engineFunctioning() {
		System.out.println("The engine is functioning.");
	};
	public void engineOff() {
		System.out.println("The engine is turned off.");
	}

	public int getCubism() {
		return cubism;
	}

	public void setCubism(int cubism) {
		this.cubism = cubism;
	}

	public int getHorsepower() {
		return horsepower;
	}

	public void setHorsepower(int horsepower) {
		this.horsepower = horsepower;
	}
	
	
	
	
}
