
public class Person {
	// Class members -------------------------------------
	private String PeFirstName;
	private String PeLastName;
	private int Age;
	private String PeAddress;
	
	// Class constructors --------------------------------
	public Person() {
		PeFirstName = "";
		PeLastName = "";
		Age = 0;
		PeAddress = "";
	}
	
	public Person(String peFirstName, String peLastName, int age, String peAddress) {
		PeFirstName = peFirstName;
		PeLastName = peLastName;
		Age = age;
		PeAddress = peAddress;
	}

	// Class methods --------------------------------------

	public String getPeFirstName() {
		return PeFirstName;
	}

	public void setPeFirstName(String peFirstName) {
		PeFirstName = peFirstName;
	}

	public String getPeLastName() {
		return PeLastName;
	}

	public void setPeLastName(String peLastName) {
		PeLastName = peLastName;
	}

	public int getAge() {
		return Age;
	}

	public void setAge(int age) {
		Age = age;
	}

	public String getPeAddress() {
		return PeAddress;
	}

	public void setPeAddress(String peAddress) {
		PeAddress = peAddress;
	}

	@Override
	public String toString() {
		return "Person [PeFirstName=" + PeFirstName + ", PeLastName=" + PeLastName + ", Age=" + Age + ", PeAddress="
				+ PeAddress + "]";
	}
	
	
}
