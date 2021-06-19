
public class Passenger {
	
	// Class Members -----------------------------------------------
	private String passengerid;
	private String firstname;
	private String lastname;
	private String flightnumber;
	private String airportstartpoint;
	private String airportendpoint;
	private String startdate;
	private String enddate;
	private String seatnumber;
	
	// Class Constructor -----------------------------------------
	public Passenger( String passengerId, String firstName, String lastName, String flightNumber, String airportStartPoint, String airportEndPoint,
			String startDate, String endDate, String seatNumber) {
		 this.passengerid = passengerId;
		 this.firstname = firstName;
		 this.lastname = lastName;
		 this.flightnumber = flightNumber;
		 this.airportstartpoint = airportStartPoint;
		 this.airportendpoint = airportEndPoint;
		 this.startdate = startDate;
		 this.enddate = endDate;
		 this.seatnumber = seatNumber;
	}
	
	
	// Class methods ---------------------------------------------
	public String getPassengerid() {
		return passengerid;
	}

	public void setPassengerid(String passengerid) {
		this.passengerid = passengerid;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getFlightnumber() {
		return flightnumber;
	}

	public void setFlightnumber(String flightnumber) {
		this.flightnumber = flightnumber;
	}

	public String getAirportstartpoint() {
		return airportstartpoint;
	}

	public void setAirportstartpoint(String airportstartpoint) {
		this.airportstartpoint = airportstartpoint;
	}

	public String getAirportendpoint() {
		return airportendpoint;
	}

	public void setAirportendpoint(String airportendpoint) {
		this.airportendpoint = airportendpoint;
	}

	/*public String getAirline() {
		return airline;
	}

	public void setAirline(String airline) {
		this.airline = airline;
	}*/

	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public String getEnddate() {
		return enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}

	public String getSeatnumber() {
		return seatnumber;
	}

	public void setSeatnumber(String seatnumber) {
		this.seatnumber = seatnumber;
	}

	
}

