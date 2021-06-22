import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class BookAFlight extends JFrame {

	
	// Class Members -----------------------------------------------------

	//GUI global components
	private JPanel PanelContainer;
	private JFormattedTextField txtStartDate, txtReturnDate;
	private JTextField textFieldPassengersNo, textFieldName, textFieldLastName, textFieldAge, textFieldIDNo,textFieldSeatNo;
	private JLabel lblSeatValidation, lblPassengerIDValidation, lblPassengersRemain;
	DefaultComboBoxModel<?> dcb = new DefaultComboBoxModel<Object>();
	private JComboBox<Object> comboBoxFrom, comboBoxTo, comboBoxFlightNo, comboBoxGender;

	//Array Lists
	private ArrayList<BookAFlightPassenger> arrPassengers = new ArrayList<>();
	private ArrayList<String> arrSeatNo = new ArrayList<>(); //this arraylist holds both seat numbers from the database but also temporary seat no before they are placed in the database
	private ArrayList<String> arrTempPassengerID = new ArrayList<>();	
	private ArrayList<String> arrFlightNo = new ArrayList<>();
	public ArrayList<String> arrDBPassengerID = new ArrayList<>();
	
	//Other Variables
	private int passengerCounter = 0;
	private String[] airline = {"Aegean", "Finnair", "Lufthansa", "KLM"};
	public BookAFlight tempBookAFlight;
	private static final long serialVersionUID = 1L;
	
	//Connection Variable
	private Connection con;
	
	// Class Constructor --------------------------------------------------
	public BookAFlight() {
		
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
		catch (IOException e) {
			System.out.println("File not found..");
			e.printStackTrace();
		} 
		catch (SQLException e) {
			System.out.println("Couldn't connect to database successfully..");
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		fetchFlightCodes(); 		// Fetching existing flight codes from the DataBase 
		fetchPassengerID();			// Fetching existing passenger ID's from the DateBase
		availableFlights();	
		
		//Creation Book A Flight Label
		JLabel bookAFlightLbl = new JLabel("Book a flight");
		bookAFlightLbl.setBounds(235, 0, 170, 75); 
		bookAFlightLbl.setFont(new Font("Tahoma", Font.PLAIN, 30));
		bookAFlightLbl.setForeground(new Color(0, 0, 139));
		
		//Creation of From Label
		JLabel fromLbl = new JLabel("From");
		fromLbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		fromLbl.setBounds(48, 68, 45, 13);
		
		//Creation of ComboBox From 
		comboBoxFrom = new JComboBox<Object>();
		comboBoxFrom.setModel(new DefaultComboBoxModel<Object>(new String[] {"Athens Airport", "Heraklion Airport", "Thessaloniki Airport", "Rhodes Airport", "Chania Airport ", "Corfu Airport", "Kos Airport", "Santorini (Thira) Airport"}));
		comboBoxFrom.setBounds(212, 91, 119, 21);
		
		//Creation of To Label
		JLabel toLbl = new JLabel("To");
		toLbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		toLbl.setBounds(212, 70, 45, 13);
		
		//Creation of ComboBox To
		comboBoxTo = new JComboBox<Object>();
		comboBoxTo.setModel(new DefaultComboBoxModel<Object>(new String[] {"Athens Airport", "Heraklion Airport", "Thessaloniki Airport", "Rhodes Airport", "Chania Airport ", "Corfu Airport", "Kos Airport", "Santorini (Thira) Airport "}));
		comboBoxTo.setBounds(48, 91, 119, 21);
		
		//Creation of Dates Label
		JLabel datesLbl = new JLabel("Dates");
		datesLbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		datesLbl.setBounds(386, 70, 52, 13);
		
		try {
			//Creation of Start Date TextField
			txtStartDate = new JFormattedTextField(new MaskFormatter("##/##/####"));
			txtStartDate.setHorizontalAlignment(SwingConstants.LEFT);
			txtStartDate.setBounds(462, 92, 96, 19);
			txtStartDate.setColumns(10);	
		} 
		catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		try {
			
			//Creation of Return Date TextField
			txtReturnDate = new JFormattedTextField(new MaskFormatter("##/##/####"));
			txtReturnDate.setBounds(462, 134, 96, 19);
			txtReturnDate.setColumns(10);	
		} 
		catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		
		//Creation of Flight Number Label
		JLabel flightCodeLbl = new JLabel("Flight No.");
		flightCodeLbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		flightCodeLbl.setBounds(48, 135, 80, 13);
			
		//Creation of ComboBox Flight No
		comboBoxFlightNo = new JComboBox<Object>();
		comboBoxFlightNo.setModel(new DefaultComboBoxModel<Object>(availableFlights()));
		comboBoxFlightNo.setBounds(48, 158, 119, 21);
	
		//Creation of Passengers No Label
		JLabel numberOfPassengersLbl = new JLabel("Passengers No.");
		numberOfPassengersLbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		numberOfPassengersLbl.setBounds(212, 137, 119, 13);
		
		//Creation of Passengers No Text Field
		textFieldPassengersNo = new JTextField("");
		textFieldPassengersNo.setBounds(212, 159, 119, 19);
		textFieldPassengersNo.setColumns(10);
		
		//Execution of Passengers No Text Field Listener
        textFieldPassengersNo.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if(!(Character.isDigit(c) || (c==KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) 
                {
                    getToolkit().beep();
                    e.consume();
                }
            }
        });
        //Same
		textFieldPassengersNo.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(textFieldPassengersNo.getText().equals("")) textFieldPassengersNo.setText("-1");
					
				passengerCounter = Integer.parseInt(textFieldPassengersNo.getText());
			}
		});
		
		
		//Creation of Passenger Details Label
		JLabel passengersDetailsLbl = new JLabel("Passenger Details");
		passengersDetailsLbl.setForeground(new Color(0, 0, 128));
		passengersDetailsLbl.setFont(new Font("Tahoma", Font.PLAIN, 23));
		passengersDetailsLbl.setBounds(235, 220, 180, 32);
		
		
		//Creation of First Name Label
		JLabel firstNameLbl = new JLabel("First Name");
		firstNameLbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		firstNameLbl.setBounds(48, 285, 80, 13);
		
		//Creation of Name Text Field
		textFieldName = new JTextField();
		textFieldName.setBounds(48, 308, 119, 19);
		textFieldName.setColumns(10);
		
		//Creation of Last name Label
		JLabel lastNameLbl = new JLabel("Last Name");
		lastNameLbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lastNameLbl.setBounds(212, 287, 119, 13);
		
		//Creation of Last Name Text Field
		textFieldLastName = new JTextField();
		textFieldLastName.setBounds(212, 308, 119, 19);
		textFieldLastName.setColumns(10);
	
		//Creation of Age Label 
		JLabel ageLbl = new JLabel("Age");
		ageLbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		ageLbl.setBounds(379, 281, 26, 21);
		
		//Creation of Age Text Field
		textFieldAge = new JTextField();
		textFieldAge.setBounds(379, 308, 70, 19);
		textFieldAge.setColumns(10);
		
		//Execution of Age Text Field Listener
        textFieldAge.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if(!(Character.isDigit(c) || (c==KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) 
                {
                    getToolkit().beep();
                    e.consume();
                }
            }
        });
		
		
		//Creation of Gender Label
		JLabel genderLbl = new JLabel("Gender");
		genderLbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		genderLbl.setBounds(492, 285, 52, 13);
		
		//Creation of Gender Combo Box
		comboBoxGender = new JComboBox<Object>();
		comboBoxGender.setModel(new DefaultComboBoxModel<Object>(new String[] {"Male", "Female", "Other"}));
		comboBoxGender.setBounds(492, 307, 96, 21);
		
		//Creation of Passenger ID Label
		JLabel passengerIDLbl = new JLabel("ID No.");
		passengerIDLbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		passengerIDLbl.setBounds(48, 358, 64, 13);
		
		//Creation of ID No Text Field
		textFieldIDNo = new JTextField();
		textFieldIDNo.setBounds(48, 381, 119, 19);
		textFieldIDNo.setColumns(10);
		
		//Execution of ID No Text Field Listener
		textFieldIDNo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				if(textFieldIDNo.getText().matches("[A-Z]{2}[0-9]+")) {
					boolean isValid = false;
					for(String str: arrTempPassengerID)
					{
						if(textFieldIDNo.getText().equals(str))
						{
							lblPassengerIDValidation.setText("This ID already exists for this flight.");
							isValid = true;
							break;
						}
					}
					if(!isValid) {
						lblPassengerIDValidation.setText("");
					}
					
				} else if(!textFieldIDNo.getText().matches("[A-Z]{2}[0-9]+") && !textFieldIDNo.getText().equals("")){
					lblPassengerIDValidation.setText("This ID is unavailable");
				} 
				
			}
		});
		
		
		//Creation of Seat Number Label
		JLabel seatNumberLbl = new JLabel("Seat No.");
		seatNumberLbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		seatNumberLbl.setBounds(212, 360, 70, 13);
		
		//Creation of Seat No Text Field
		textFieldSeatNo = new JTextField("");
		textFieldSeatNo.setBounds(212, 381, 119, 19);
		textFieldSeatNo.setColumns(10);
		
		//Execution of Seat No Text Field Listener
		textFieldSeatNo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				
				for(String str: arrSeatNo)
				{
					if(textFieldSeatNo.getText().equals(str))
					{
						lblSeatValidation.setText("This seat is unavailable");
					}
					else lblSeatValidation.setText("");
				}
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				lblSeatValidation.setText("");
			}
		});
		
		//Creation of Main Page Button
		JButton mainButton = new JButton("Main Page");
		mainButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		mainButton.setBounds(48, 437, 119, 21);
		
		//Execution of Main Button Listener
		mainButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				MainPage mainpage = new MainPage();
				mainpage.setVisible(true);
				tempBookAFlight.dispose();
			}
		});
		
		//Creation of Next Passenger Button
		JButton buttonNextPassenger = new JButton("Save Passenger");
		buttonNextPassenger.setFont(new Font("Tahoma", Font.PLAIN, 15));
		buttonNextPassenger.setBounds(379, 378, 143, 21);
		
		//Execution of Next Passenger Button
		buttonNextPassenger.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(passengerCounter == -1 || (textFieldName.getText().isEmpty() || textFieldLastName.getText().isEmpty() || textFieldAge.getText().isEmpty() || textFieldIDNo.getText().isEmpty() || textFieldPassengersNo.getText().isEmpty() || textFieldSeatNo.getText().isEmpty())) {
					JOptionPane.showMessageDialog(null, "Information missing.");
					return;
				}
				
				if(passengerCounter == 0) {
					JOptionPane.showMessageDialog(null, "You are not allowed to enter information for more passengers.\nPress the Book button.");
					return;
				}
							
				if(!lblSeatValidation.getText().isEmpty() || !lblPassengerIDValidation.getText().isEmpty() ) {
					JOptionPane.showMessageDialog(null, "Please check your answers for mistakes!");
					return;
				}
				
				
				arrSeatNo.add(textFieldSeatNo.getText()); //add temporary seat no so that we can make sure that the next passenger doesn't have the same seat no
				arrTempPassengerID.add(textFieldIDNo.getText()); //add temporary ID no so that we can make sure that the next passenger doesn't have the same ID no
				
				
				passengerCounter--;
				lblPassengersRemain.setText("Passengers Remain : " + Integer.toString(passengerCounter));
				
				
				
				BookAFlightPassenger passenger = new BookAFlightPassenger(textFieldIDNo.getText(), textFieldName.getText(), textFieldLastName.getText(),  
						Integer.parseInt(textFieldAge.getText()), (String)comboBoxGender.getSelectedItem(), (String)comboBoxFlightNo.getSelectedItem(),
						textFieldSeatNo.getText());
			
				// Save passenger to an arrayList
				arrPassengers.add(passenger);
				
				// Refresh text fields of passenger
				textFieldName.setText("");
				textFieldLastName.setText("");
				textFieldAge.setText("");
				textFieldSeatNo.setText("");
				textFieldIDNo.setText("");
				comboBoxGender.setSelectedIndex(0);
			}
		});
		
		
		//Creation of Book Button
		JButton buttonBook = new JButton("Book");
		buttonBook.setFont(new Font("Tahoma", Font.PLAIN, 15));
		buttonBook.setBounds(279, 437, 85, 21);
		
		//Execution of Book Button
		buttonBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(txtStartDate.getText().equals("  /  /    ") || txtReturnDate.getText().equals("  /  /    ") || textFieldPassengersNo.getText().equals("0") || arrPassengers.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Error.\nInformation missing.\nCan't proceed.");
					return;
				} else if(comboBoxFrom.getSelectedItem().equals(comboBoxTo.getSelectedItem())) {
					JOptionPane.showMessageDialog(null, "Departure and Arrival airports are the same.\nCan't proceed.");
					return;
				}
				fetchPassengerID();
				
				if(textFieldName.getText().equals("")) {
					saveBooking();
					
					// Refresh text fields of flight 
					comboBoxFrom.setSelectedIndex(0);
					comboBoxTo.setSelectedIndex(0);
					txtStartDate.setText("");
					txtReturnDate.setText("");
					comboBoxFlightNo.setSelectedIndex(0);
					textFieldPassengersNo.setText("");
				}
				else
				{
					JOptionPane.showMessageDialog(null, "You have unsaved changes in passenger details! \n"
							+ "                Press 'Save Passenger' first");
				}
				arrTempPassengerID.clear();
				arrSeatNo.clear();
			}	
		});
		
		
		//Creation of Departure Date Label
		JLabel departureDateLbl = new JLabel("Start Date:");
		departureDateLbl.setBounds(386, 95, 80, 13);
		
		//Creation of Arrival Date Label
		JLabel arrivaldateLbl = new JLabel("Return Date:"); 
		arrivaldateLbl.setBounds(386, 137, 96, 13);
		
		//Creation of Seat Validation Label
		lblSeatValidation = new JLabel("");
		lblSeatValidation.setForeground(new Color(255, 0, 0));
		lblSeatValidation.setBounds(212, 410, 133, 13);
		
		//Creation of PassengerID Validation Label 
		lblPassengerIDValidation = new JLabel("");
		lblPassengerIDValidation.setForeground(new Color(255, 0, 0));
		lblPassengerIDValidation.setBounds(48, 410, 133, 13);
		
		//Creation of Passengers Remain Label
		lblPassengersRemain = new JLabel("");
		lblPassengersRemain.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblPassengersRemain.setBounds(415, 251, 150, 13); 
		
		//Creation of Panel
		setTitle("Book a flight");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 655, 512); 
		PanelContainer = new JPanel();
		PanelContainer.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(PanelContainer);
		PanelContainer.setLayout(null);
		
		//Adding Combonents to Panel
		PanelContainer.add(bookAFlightLbl);						//Book A Flight label placement
		PanelContainer.add(fromLbl);							//From label placement
		PanelContainer.add(comboBoxFrom);						//From combobx placement 
		PanelContainer.add(toLbl);								//To label placement
		PanelContainer.add(comboBoxTo);							//To combobx placement 
		PanelContainer.add(datesLbl);							//Dates label placement
		PanelContainer.add(txtStartDate);						//StartDate TextField placement
		PanelContainer.add(flightCodeLbl);						//FlightCode label placement
		PanelContainer.add(comboBoxFlightNo);					//FlightNo combobox placement
		PanelContainer.add(textFieldName);						//Name Textfield placement
		PanelContainer.add(passengersDetailsLbl);				//Passenger Details label placement
		PanelContainer.add(textFieldIDNo);						//ID No Textfield placement 
		PanelContainer.add(textFieldSeatNo);					//Seat No Textfield placement
		PanelContainer.add(textFieldLastName);					//last name Textfield placement
		PanelContainer.add(txtReturnDate);						//Return Date TextField placement
		PanelContainer.add(numberOfPassengersLbl);				//Number of Passengers label placement
		PanelContainer.add(textFieldPassengersNo);				//Passengers No TextField placement 
		PanelContainer.add(lastNameLbl); 						//last Name Label placement
		PanelContainer.add(firstNameLbl);						//First Name Label placement 
		PanelContainer.add(ageLbl);								//Age Label placement
		PanelContainer.add(textFieldAge);						//Text Field Age placement
		PanelContainer.add(genderLbl);							//Gender Label placement
		PanelContainer.add(comboBoxGender);						//Gender ComboBox placement
		PanelContainer.add(passengerIDLbl);						//Passeger ID Label placement
		PanelContainer.add(seatNumberLbl);						//Seat Number Label placement
		PanelContainer.add(mainButton);							//Button Main placement
		PanelContainer.add(buttonNextPassenger);				//Button Next Passenger placement
		PanelContainer.add(buttonBook);							//Button Book placement
		PanelContainer.add(departureDateLbl);					//Departure Date Label placement
		PanelContainer.add(arrivaldateLbl);						//Arrival Date Label placement
		PanelContainer.add(lblSeatValidation);					//Seat Validation Label placement
		PanelContainer.add(lblPassengerIDValidation);			//Passengers ID Validation Label placement
		PanelContainer.add(lblPassengersRemain);				//Passengers Remain Label placement
		
	}
	
	
	
	// saveBooking()
	private void saveBooking()
	{
		try 
		{  
			Random random = new Random();
			
			// Flight insert statement
			PreparedStatement flightStatement = con.prepareStatement("insert into Flight values(?, ?, ?, ?, ?, ?)");
			
			
			// Flight insert information
			flightStatement.setString(1, (String)comboBoxFlightNo.getSelectedItem());
			flightStatement.setString(2, (String)comboBoxFrom.getSelectedItem());
			flightStatement.setString(3, (String)comboBoxTo.getSelectedItem());
			flightStatement.setString(4, txtStartDate.getText());
			flightStatement.setString(5, txtReturnDate.getText());
			flightStatement.setString(6,  airline[random.nextInt(4)]);
			
			flightStatement.executeUpdate();
			
			
			// Passenger insert statement
			PreparedStatement passengerStament = con.prepareStatement("insert into Passenger values(?, ?, ?, ?, ?)");
			
			// Seat insert statement
			PreparedStatement passengerSeatStatement = con.prepareStatement("insert into Seat values(?, ?, ?)");
			
			// BookaFlight statement
			PreparedStatement bookingTableStatement = con.prepareStatement("insert into bookingtable values(?, ?, ?)");
			
			// Passenger, Seat and BookAFlight insert information
			for(BookAFlightPassenger pas: arrPassengers)
			{
				// Passenger ------------------------------------------------------------------
				
				// If passenger ID already exists, passenger information will not be saved
				boolean passengerIDExist = false;
				for(String str: arrDBPassengerID) {
					if(pas.getPassengerID().equals(str)) {
						passengerIDExist = true;
						break;
					}
				}
				// If passenger ID doesn't already exists, save his information into 'passenger' table
				if(!passengerIDExist) {
					
					passengerStament.setString(1, pas.getPassengerID());
					passengerStament.setString(2, pas.getFirstName());
					passengerStament.setString(3, pas.getLastName());
					passengerStament.setInt(4, pas.getAge());
					passengerStament.setString(5, pas.getGender());
					
					passengerStament.executeUpdate();
				}
				 
				
				// Seat ------------------------------------------------------------------------
				passengerSeatStatement.setString(1, pas.getSeatNumber());
				passengerSeatStatement.setString(2, (String)comboBoxFlightNo.getSelectedItem());				
				passengerSeatStatement.setString(3, pas.getPassengerID());
				passengerSeatStatement.executeUpdate();
				    
					
			    // Booking Table ---------------------------------------------------------------
			    String bookingNumber = bookingCode();
			    bookingTableStatement.setString(1, bookingNumber);
			    bookingTableStatement.setString(2, pas.getPassengerID());
			    bookingTableStatement.setString(3, (String)comboBoxFlightNo.getSelectedItem());
			    
			    bookingTableStatement.executeUpdate();
			    JOptionPane.showMessageDialog(null, "Booking number : " + bookingNumber + "\nID : " + pas.getPassengerID() + "\nFlight : " + (String)comboBoxFlightNo.getSelectedItem());
			        
			}
			comboBoxFlightNo.removeItem(comboBoxFlightNo.getSelectedItem()); 
			
			JOptionPane.showMessageDialog(null, "Saved Successfully!");
			arrPassengers.clear(); // Clear array list after the booking because otherwise its going to display a dublicate error!
		} 
		catch (Exception e) 
		{
			System.out.println("Something went wrong..");
			e.printStackTrace();
		}
	}
	

	// fetchPasssengerID()
	private void fetchPassengerID()
    {
        Statement stmt;
        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select PassengerID from Passenger");
            while(rs.next())
            {
                arrDBPassengerID.add(rs.getString(1));
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }
	
	private void fetchFlightCodes()
    {
        Statement stmt;
        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select FlightCode from Flight");
            while(rs.next())
            {
                arrFlightNo.add(rs.getString(1));
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }
	
	// availableFlights()
	private String[] availableFlights() {
		String[] original = new String[] {"2341df", "1324af", "4576s", "123hg", "5634df", "23123tf", "3412rd"};
		ArrayList<String> temp = new ArrayList<String>();
		for(int i=0; i<7; i++) {
			boolean doesExist = false;
			for(int j=0; j<arrFlightNo.size(); j++) {
				if(arrFlightNo.get(j).equals(original[i])) {
					doesExist = true;
					break;
				}
			}
			if(doesExist == false) {
				temp.add(original[i]);
			}
		}
		String[] newArray = new String[temp.size()];
		for(int i=0; i<temp.size(); i++) {
			newArray[i] = temp.get(i);
		}
		
		return newArray;
		
	}
 
	// bookingCode()
 	private String bookingCode() {
		Statement stmt;
		ArrayList<String> arr = new ArrayList<>();
		Random random = new Random();
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select BookingCode from bookingtable");
			while(rs.next())
			{
				arr.add(rs.getString("BookingCode"));
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		} 
		
		String bookingNumber;
		while(true) {
			bookingNumber = String.valueOf(random.nextInt(99999999 - 10000000) + 10000000) + String.valueOf(random.nextInt(99999999 - 10000000) + 10000000);
			boolean doesExist = false;
			for(int i=0; i<arr.size(); i++) {
				if(bookingNumber.equals(arr.get(i))) { 
					doesExist = true;
					break;
				}
			}
			if(doesExist == false) break;
		}
		
		return bookingNumber;
	}
}
