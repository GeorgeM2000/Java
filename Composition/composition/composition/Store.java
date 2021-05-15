package composition;

public class Store {

	private String address;
	private int numOfEmployees;
	private String city;
	
	public Store(String address, int numOfEmployees, String city) {
		super();
		this.address = address;
		this.numOfEmployees = numOfEmployees;
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getNumOfEmployees() {
		return numOfEmployees;
	}

	public void setNumOfEmployees(int numOfEmployees) {
		this.numOfEmployees = numOfEmployees;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	public String toString() {
		return "\n address: "+address+"\n numofemployess: "+numOfEmployees+"\n city:"+city;
	}
}
