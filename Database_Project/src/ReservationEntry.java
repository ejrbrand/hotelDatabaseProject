import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class ReservationEntry {

	private JFrame frame;
	private JTextField textFieldNumberOfGuests;

	private String host = "team10.mysql.database.azure.com";
	private String database = "hotelReservation";
	private String user = "team10@team10";
	private String password = "Password01!";
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReservationEntry window = new ReservationEntry();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ReservationEntry() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblMakeReservationLabel = new JLabel("Make Reservation");
		lblMakeReservationLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblMakeReservationLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblMakeReservationLabel.setBounds(50, 40, 300, 30);
		frame.getContentPane().add(lblMakeReservationLabel);
		
		JLabel lblArrivalDate = new JLabel("Arrival Date");
		lblArrivalDate.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblArrivalDate.setBounds(50, 150, 150, 30);
		frame.getContentPane().add(lblArrivalDate);
		
		JLabel lblNumberOfGuests = new JLabel("No. Of Guests");
		lblNumberOfGuests.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNumberOfGuests.setBounds(50, 310, 150, 30);
		frame.getContentPane().add(lblNumberOfGuests);
		
		JLabel lblComments = new JLabel("Comments");
		lblComments.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblComments.setBounds(50, 390, 150, 30);
		frame.getContentPane().add(lblComments);
		
		textFieldNumberOfGuests = new JTextField();
		textFieldNumberOfGuests.setBounds(215, 310, 150, 30);
		frame.getContentPane().add(textFieldNumberOfGuests);
		textFieldNumberOfGuests.setColumns(10);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(215, 390, 500, 200);
		frame.getContentPane().add(textPane);
		
		JLabel lblRoomType = new JLabel("Room Type");
		lblRoomType.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblRoomType.setBounds(400, 310, 150, 30);
		frame.getContentPane().add(lblRoomType);
		
		JLabel lblDepartureDate = new JLabel("Departure Date");
		lblDepartureDate.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDepartureDate.setBounds(400, 150, 150, 30);
		frame.getContentPane().add(lblDepartureDate);
		
		JLabel labelArrivalDateCalendar = new JLabel();
		labelArrivalDateCalendar.setText("Choose Date by selecting below.");
		labelArrivalDateCalendar.setBounds(150, 150, 150, 150); 
		
		UtilDateModel model = new UtilDateModel();
		model.setDate(2017, 11, 18);
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		datePicker.setBounds(215, 150, 150, 30);
		frame.getContentPane().add(datePicker);
		
		JLabel labelDepartureDateCalendar = new JLabel();
		labelArrivalDateCalendar.setText("Choose Date by selecting below.");
		labelArrivalDateCalendar.setBounds(150, 150, 150, 150);
		
		UtilDateModel model2 = new UtilDateModel();
		model2.setDate(2017, 11, 18);
		Properties p2 = new Properties();
		p2.put("text.today", "Today");
		p2.put("text.month", "Month");
		p2.put("text.year", "Year");
		JDatePanelImpl datePanel2 = new JDatePanelImpl(model2, p2);
		JDatePickerImpl datePicker2 = new JDatePickerImpl(datePanel2, new DateLabelFormatter());
		datePicker2.setBounds(565, 150, 150, 30);
		frame.getContentPane().add(datePicker2);
		
		String[] roomType = {"Standard", "Deluxe", "Luxury", "Suite", "Villa"};
		JComboBox comboBox_1 = new JComboBox(roomType);
		comboBox_1.setBounds(565, 310, 150, 30);
		frame.getContentPane().add(comboBox_1);
		
		JButton btnOk = new JButton("Ok");
		btnOk.setBounds(627, 685, 150, 30);
		frame.getContentPane().add(btnOk);
		btnOk.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				ReservationPage rP = new ReservationPage();
                ReservationPage.newScreen();
			}
		});
		frame.getContentPane().add(btnOk);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(462, 685, 150, 30);
		frame.getContentPane().add(btnCancel);
		btnCancel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				ReservationPage rP = new ReservationPage();
                ReservationPage.newScreen();
			}
		});
		frame.getContentPane().add(btnCancel);
		
	}
	
	public void connectToAndQueryDatabase(String arrivalDate, String departureDate, String numberGuests, String roomType, String comments) {

		try {
			String url = String.format("jdbc:mysql://%s/%s", host, database);
			Properties properties = new Properties();
			properties.setProperty("user", user);
			properties.setProperty("password", this.password);
			properties.setProperty("useSSL", "true");
			properties.setProperty("verifyServerCertificate", "true");
			properties.setProperty("requireSSL", "false");
			Connection conn = DriverManager.getConnection(url, properties);
			System.out.println("Connected");
			String sql = "INSERT INTO hotelReservation.reservedrooms(`dateIn` , `dateOut`,`noOfPeople`,`amountDue`,`paid`, `comments`)"
					+ "VALUES (?,?,?,?,?);";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, arrivalDate);
			preparedStatement.setString(2, departureDate);
			preparedStatement.setString(3, numberGuests);
			preparedStatement.setString(4, roomType);
			preparedStatement.setString(5, comments);
			preparedStatement.execute();
			
			
			
			
			
			
			conn.close();

		} catch (SQLException ex) {

			System.out.println(ex);
		}
	}
}
