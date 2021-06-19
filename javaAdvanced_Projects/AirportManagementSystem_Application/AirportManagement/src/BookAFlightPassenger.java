public class BookAFlightPassenger {
	
	private String passengerID;
    private String FirstName;
    private String LastName;
    private int Age;
    private String Gender;
    private String FlightNumber;
    private String SeatNumber;
    
    public BookAFlightPassenger(String passID, String firstName, String lastName, int age, String gender, String flightNumber, String seatNumber) {
    	this.Age = age;
    	this.FirstName = firstName;
    	this.FlightNumber = flightNumber;
    	this.Gender = gender;
    	this.LastName = lastName;
    	this.passengerID = passID;
    	this.SeatNumber = seatNumber;
    	
    }
    
    
	public String getSeatNumber() {
		return SeatNumber;
	}
	public void setSeatNumber(String seatNumber) {
		SeatNumber = seatNumber;
	}
	public String getPassengerID() {
		return passengerID;
	}
	public void setPassengerID(String passengerID) {
		this.passengerID = passengerID;
	}
	public String getFirstName() {
		return FirstName;
	}
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	public int getAge() {
		return Age;
	}
	public void setAge(int age) {
		Age = age;
	}
	public String getGender() {
		return Gender;
	}
	public void setGender(String gender) {
		Gender = gender;
	}
	public String getFlightNumber() {
		return FlightNumber;
	}
	public void setFlightNumber(String flightNumber) {
		FlightNumber = flightNumber;
	}
    
    
    
    
}
