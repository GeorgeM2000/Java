import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.awt.event.ActionEvent;

public class MainPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	//Connection Variable
	private Connection con;
	

	public static void main(String[] args) {
		new MainPage();								// Constructor call				
	}

	public MainPage() {
		
		//Creation of Choose an Option label
		JLabel chooseAnOptionLbl = new JLabel("Choose an Option");
		chooseAnOptionLbl.setFont(new Font("Tahoma", Font.PLAIN, 25));
		chooseAnOptionLbl.setHorizontalAlignment(SwingConstants.CENTER);
		chooseAnOptionLbl.setBounds(0, 11, 424, 28);
		
		// Book A Flight
		JButton bookAFlightBtn = new JButton("Book a Flight");
		bookAFlightBtn.setFont(new Font("Tahoma", Font.BOLD, 15));
		bookAFlightBtn.setBounds(146, 94, 139, 36);
		
		// Book A Flight Action listener
		bookAFlightBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(currentNumberOfFlights() == 1) {
					//JFrame frame = new JFrame("Exit");
					if(JOptionPane.showConfirmDialog(null, "No available flights.\nWould you like to delete current information and start over?","No available flights",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION) {
						deleteEntriesFromDB();
					}
				} else {
					BookAFlight bookaflight = new BookAFlight();
					bookaflight.tempBookAFlight = bookaflight;
					bookaflight.setVisible(true);
					dispose();
				}
			}
		});
		
		// My Bookings Button
		JButton myBookingsBtn = new JButton("My Bookings");
		myBookingsBtn.setFont(new Font("Tahoma", Font.BOLD, 15));
		myBookingsBtn.setBounds(146, 141, 139, 36);

		// My Bookings Action Listener
		myBookingsBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					MyBookings mybookings = new MyBookings();
					mybookings.tempMyBookings = mybookings;
					mybookings.setVisible(true);
					dispose(); 
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		// Exit Page Button
		JButton exitPageBtn = new JButton("Exit Page");
		exitPageBtn.setFont(new Font("Tahoma", Font.BOLD, 15));
		exitPageBtn.setBounds(146, 189, 139, 36);
		
		// Exit Page Button Action Listener
		exitPageBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		// Creation of Panel
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		contentPane.add(chooseAnOptionLbl);			// Choose an option label placement
		contentPane.add(bookAFlightBtn);			// Book a flight button placement
		contentPane.add(myBookingsBtn);				// My bookings button placement
		contentPane.add(exitPageBtn);				// Exit page button placement
		
		
		setVisible(true);		// Make the Frame visible after creation and placement of objects
	}
	
	
	// availableFlights()
	private int currentNumberOfFlights() {
		
		// Retrieve username, password and database url from db.properties file
		try {
			Properties proper = new Properties();
			proper.load(new FileInputStream("resources/db.properties"));
					
			String user = proper.getProperty("user");
			String password = proper.getProperty("password");
			String dburl = proper.getProperty("dburl");
					
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(dburl, user, password);
			
			PreparedStatement statement = con.prepareStatement("select FlightCode from flight");
			ResultSet result = statement.executeQuery();
			
			int rowCount = 0;
			while(result.next()) {
				rowCount++;
			}
			return rowCount;
			
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
		
		return 0;
	}
	
	
	// deleteEntriesFromDB()
	private void deleteEntriesFromDB() {
		// Retrieve username, password and database url from db.properties file
				try {
					Properties proper = new Properties();
					proper.load(new FileInputStream("resources/db.properties"));
							
					String user = proper.getProperty("user");
					String password = proper.getProperty("password");
					String dburl = proper.getProperty("dburl");
							
					Class.forName("com.mysql.jdbc.Driver");
					con = DriverManager.getConnection(dburl, user, password);
					
					PreparedStatement passengerInfo = con.prepareStatement("delete from passenger");
					PreparedStatement flightInfo = con.prepareStatement("delete from flight");
					PreparedStatement bookingTableInfo = con.prepareStatement("delete from bookingtable");
					PreparedStatement seatInfo = con.prepareStatement("delete from seat");
					
					seatInfo.executeUpdate();
					bookingTableInfo.executeUpdate();
					passengerInfo.executeUpdate();
					flightInfo.executeUpdate();
				
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
	}
}
