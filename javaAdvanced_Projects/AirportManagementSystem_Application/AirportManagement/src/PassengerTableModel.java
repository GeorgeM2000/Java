import java.util.List;

import javax.swing.table.AbstractTableModel;

public class PassengerTableModel extends AbstractTableModel{
	
	private static final long serialVersionUID = 1L;
	
	// Class Members -----------------------------------------------
	private static final int PASSENGER_ID_COL = 0;
	private static final int FIRST_NAME_COL = 1;
	private static final int LAST_NAME_COL = 2;
	private static final int FLIGHT_NUMBER_COL = 3;
	private static final int AIRPORT_START_POINT_COL = 4;
	private static final int AIRPORT_END_POINT_COL = 5;
	//private static final int AIRLINE_CODE_COL = 8;
	private static final int START_DATE_COL = 6;
	private static final int END_DATE_COL = 7;
	private static final int SEAT_NUMBER_COL = 8;
	

	private String[] columnNames = {"Passenger ID", "First Name", "Last Name", "Flight Code", "Airport Source", "Airport Destination", 
			"Departure Date", "Arrival Date", "Seat Number"};
	private List<Passenger> passengers;
	
	
	// Class Constructor --------------------------------------------
	public PassengerTableModel(List<Passenger> thePassengers) {
		passengers = thePassengers;
	}
	
	
	// Class Methods ------------------------------------------------
	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return passengers.size();
	}

	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public Object getValueAt(int row, int col) {

		Passenger tempPassenger = passengers.get(row);

		switch (col) {
		case PASSENGER_ID_COL:
			return tempPassenger.getPassengerid();
		case FIRST_NAME_COL:
			return tempPassenger.getFirstname();
		case LAST_NAME_COL:
			return tempPassenger.getLastname();
		case FLIGHT_NUMBER_COL:
			return tempPassenger.getFlightnumber();
		case AIRPORT_START_POINT_COL:
			return tempPassenger.getAirportstartpoint();
		case AIRPORT_END_POINT_COL:
			return tempPassenger.getAirportendpoint();
		//case AIRLINE_CODE_COL:
			//return tempPassenger.getAirline();
		case START_DATE_COL:
			return tempPassenger.getStartdate();
		case END_DATE_COL:
			return tempPassenger.getEnddate();
		case SEAT_NUMBER_COL:
			return tempPassenger.getSeatnumber();
		default:
			return tempPassenger.getPassengerid();
		}
	}

	/*@Override
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}*/
}
