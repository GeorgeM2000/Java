import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


//import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class MyBookings extends JFrame {

	
	private static final long serialVersionUID = 1L;
	private JPanel PanelContainer;
	private JTextField idTextField;
	private JTextField lastNameTextField;
	public MyBookings tempMyBookings;
	private Connection con;
	//private JLabel errorLabel;

	
	public MyBookings() throws SQLException{
		
		//Creation of my bookings label 
		JLabel myBookingsLabel = new JLabel("My Bookings");
		myBookingsLabel.setForeground(Color.BLUE);
		myBookingsLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		myBookingsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		myBookingsLabel.setBounds(10, 11, 414, 31);
		
		//Creation of ID Text Field 
		idTextField = new JTextField();
		idTextField.setBounds(10, 143, 100, 26);
		idTextField.setColumns(10);
		
		//Creation of Last Name Text Field
		lastNameTextField = new JTextField();
		lastNameTextField.setBounds(181, 143, 100, 26);
		lastNameTextField.setColumns(10);
		
		//Creation of Search Button
		JButton searchBtn = new JButton("Search");
		searchBtn.setFont(new Font("Tahoma", Font.BOLD, 15));
		searchBtn.setBounds(335, 143, 89, 26);
		
		//Execution of Search Button Listener
		searchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String passengerID = idTextField.getText();
				String passengerLastName = lastNameTextField.getText();
				
				if((passengerID.matches("[a-zA-Z]{2}[0-9]+")) && (passengerLastName.matches("[a-zA-Z]+"))) {
				
					//errorLabel.setVisible(false);
					List<Passenger> passengers = new ArrayList<>();
				
					// Retrieve username, password and database url from db.properties file
					try {
						Properties proper = new Properties();
						proper.load(new FileInputStream("resources/db.properties"));
						
						String user = proper.getProperty("user");
						String password = proper.getProperty("password");
						String dburl = proper.getProperty("dburl");
						
						Class.forName("com.mysql.jdbc.Driver");
						con = DriverManager.getConnection(dburl, user, password);
						
						System.out.println("Connected successfully to: " + dburl);
					}
					catch (IOException e2) {
						System.out.println("File not found..");
						e2.printStackTrace();
					} 
					catch (SQLException e2) {
						System.out.println("Couldn't connect to database successfully..");
						e2.printStackTrace();
					}
					catch (ClassNotFoundException e2) {
						e2.printStackTrace();
					}
								
				
					// SQL Query
					String query = "select p.PassengerID, p.FirstName, p.LastName, f.FlightCode, " +
							"f.SourceAirport, f.DestinationAirport, f.DepartureDate, f.ArrivalDate, " +
							"s.SeatNumber "
							+ "from BookingTable as bt inner join passenger as p on bt.PassengerID = p.PassengerID inner join flight as f on " +
							"bt.FlightCode = f.FlightCode inner join Seat as s on bt.FlightCode = s.FlightCode and bt.PassengerID = s.PassengerID " +
							"where bt.PassengerID =? and p.LastName =?";

				
					try {
						Class.forName("com.mysql.jdbc.Driver");
					} catch (ClassNotFoundException e1) {
						e1.printStackTrace();
					}
				
					try {
						
						PreparedStatement statement = con.prepareStatement(query);
						statement.setString(1, passengerID);
						statement.setString(2, passengerLastName);
						ResultSet result = statement.executeQuery();
					
						
						// Alternative Solution --------------------------------------------------------
						/* In order for this solution to work
							* Uncomment the library mentioned above in the library section.
							* Delete the Passenger and PassengerTableModel classes.
							* Delete the convertRowToPassenger() method below.
							* Delete everything from line 137 - 152
							* Delete Lines 73,74
						/*MyBookingTable passengerTable = new MyBookingTable();
						passengerTable.setVisible(true);
						passengerTable.table.setModel(DbUtils.resultSetToTableModel(result));*/
						// -----------------------------------------------------------------------------
					
						while(result.next()) {
						
							// Create a passenger object that contains all the information about a passenger
							Passenger tempPassenger = convertRowToPassenger(result);	
						
							// Add the passenger object to the list of passengers
							passengers.add(tempPassenger);
						
						}
					
						MyBookingTable mybookingstable = new MyBookingTable();			// Create the object that manages the passenger information table
						PassengerTableModel model = new PassengerTableModel(passengers);	// Create the passenger table model. Use the list of passengers to show every passenger 
						mybookingstable.table.setModel(model);								// Set the table model
						mybookingstable.setVisible(true);									// Open the passenger information table
						
					
					} catch (SQLException e1) {
						
						e1.printStackTrace();
					}
					
				} else {
					
					JFrame informationFrame = new JFrame("Information");
					JOptionPane.showMessageDialog(informationFrame, "You have entered invalid information.\n\nPlease try again.");
					//errorLabel.setVisible(true);
				}
				
			}
		});
		
		//Creation of ID Label
		JLabel idLbl = new JLabel("ID Number");
		idLbl.setBounds(10, 126, 80, 14);
		
		//Creation of Last Name Label
		JLabel lastNameLbl = new JLabel("Last Name");
		lastNameLbl.setBounds(181, 126, 89, 14);
		
		
		//Creation of Exit Button
		JButton exitBtn = new JButton("Exit");
		exitBtn.setFont(new Font("Tahoma", Font.BOLD, 15));
		exitBtn.setBounds(335, 211, 89, 23);
		
		//Execution of Main Page Button Listener
		exitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		//Creation of Main Page Button
		JButton mainPageBtn = new JButton("Main Page");
		mainPageBtn.setFont(new Font("Tahoma", Font.BOLD, 15));
		mainPageBtn.setBounds(159, 211, 122, 23);
		
		//Execution of Main Page Button Listener
		mainPageBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainPage mainpage = new MainPage();
				mainpage.setVisible(true);
				tempMyBookings.dispose();	
			}
		});
		
		
		/*errorLabel = new JLabel("Invalid Information. Check the ID number or the last name and try again.");
		errorLabel.setForeground(Color.RED);
		errorLabel.setBounds(10, 73, 414, 26);
		contentPane.add(errorLabel);
		errorLabel.setVisible(false);*/
		
		//Creation of Panel
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		PanelContainer = new JPanel();
		PanelContainer.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(PanelContainer);
		PanelContainer.setLayout(null);
		
		PanelContainer.add(myBookingsLabel);			//My Bookings Label placement
		PanelContainer.add(idTextField);				//ID Name Text Field placement
		PanelContainer.add(lastNameTextField);			//Last Name Text Field placement
		PanelContainer.add(searchBtn);					//Search Button placement
		PanelContainer.add(idLbl);						//ID Label placement
		PanelContainer.add(lastNameLbl);				//Last Name Label placement
		PanelContainer.add(exitBtn);					//Exit Button placement
		PanelContainer.add(mainPageBtn);				//Main Page Button placement
	}
	
	private Passenger convertRowToPassenger(ResultSet myRs) throws SQLException {
		
		String passengerid = myRs.getString("PassengerID");
		String firstname = myRs.getString("FirstName");
		String lastname = myRs.getString("LastName");
		String flightnumber = myRs.getString("FlightCode");
		String airportstartpoint = myRs.getString("SourceAirport");
		String airportendpoint = myRs.getString("DestinationAirport");
		//String airline = myRs.getString("AirLineCode");
		String startdate = myRs.getString("DepartureDate");
		String enddate = myRs.getString("ArrivalDate");
		String seatnumber = myRs.getString("SeatNumber");
		
		Passenger tempPassenger = new Passenger(passengerid, firstname, lastname, flightnumber, airportstartpoint, airportendpoint, startdate, enddate, seatnumber);
		
		return tempPassenger;
	}
}
