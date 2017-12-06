import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Properties;

public class makeReservationFrame {

	private JFrame frame;
	private JTextField textFieldNumberOfGuests;

	private String host = "team10.mysql.database.azure.com";
	private String database = "hotelReservation";
	private String user = "team10@team10";
	private String password = "Password01!";
	private Connection connection;

	private String arrivalDate;
	private String departureDate;
	private int noOfGuestsSelection;
	private String roomTypeSelection;
	private String comments;
	private String username;

	private int price;

	/**
	 * Create the application.
	 */
	public makeReservationFrame() {
		initialize();
	}

	/**
	 * Launch the application.
	 */
	public static void newScreen(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					makeReservationFrame window = new makeReservationFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
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

		JTextPane commentsTextPane = new JTextPane();
		commentsTextPane.setBounds(215, 390, 500, 200);
		frame.getContentPane().add(commentsTextPane);

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

		UtilDateModel arrivaldatemodel = new UtilDateModel();
		arrivaldatemodel.setDate(2017, 11, 18);
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl arrivalDatePanel = new JDatePanelImpl(arrivaldatemodel, p);
		JDatePickerImpl arrivalDatePicker = new JDatePickerImpl(arrivalDatePanel, new DateLabelFormatter());
		arrivalDatePicker.setBounds(215, 150, 150, 30);
		frame.getContentPane().add(arrivalDatePicker);

		JLabel labelDepartureDateCalendar = new JLabel();
		labelDepartureDateCalendar.setText("Choose Date by selecting below.");
		labelDepartureDateCalendar.setBounds(150, 150, 150, 150);

		UtilDateModel departuredatemodel = new UtilDateModel();
		departuredatemodel.setDate(2017, 11, 18);
		Properties p2 = new Properties();
		p2.put("text.today", "Today");
		p2.put("text.month", "Month");
		p2.put("text.year", "Year");
		JDatePanelImpl departureDatePanel = new JDatePanelImpl(departuredatemodel, p2);
		JDatePickerImpl departureDatePicker = new JDatePickerImpl(departureDatePanel, new DateLabelFormatter());
		departureDatePicker.setBounds(565, 150, 150, 30);
		frame.getContentPane().add(departureDatePicker);

		String[] roomType = { "Standard", "Deluxe", "Luxury", "Suite", "Villa" };
		JComboBox roomtypecombobox = new JComboBox(roomType);
		roomtypecombobox.setBounds(565, 310, 150, 30);
		frame.getContentPane().add(roomtypecombobox);

		JButton btnOk = new JButton("Ok");
		btnOk.setBounds(627, 685, 150, 30);
		frame.getContentPane().add(btnOk);
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				arrivalDate = arrivalDatePicker.getJFormattedTextField().getText();
				System.out.println(arrivalDate);
				departureDate = departureDatePicker.getJFormattedTextField().getText();
				System.out.println(departureDate);
				noOfGuestsSelection = Integer.parseInt(textFieldNumberOfGuests.getText());
				System.out.println(noOfGuestsSelection);
				comments = commentsTextPane.getText();
				String combox = roomtypecombobox.getSelectedItem().toString();
				roomTypeSelection = combox;
				System.out.println(roomTypeSelection);
				insertReservation(arrivalDate, departureDate, noOfGuestsSelection, roomTypeSelection, comments);
				frame.setVisible(false);
				MainMenuFrame rP = new MainMenuFrame();
				MainMenuFrame.newScreen();
			}
		});
		frame.getContentPane().add(btnOk);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(462, 685, 150, 30);
		frame.getContentPane().add(btnCancel);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				MainMenuFrame rP = new MainMenuFrame();
				MainMenuFrame.newScreen();
			}
		});
		frame.getContentPane().add(btnCancel);

	}

	public int assignRoom(String arrivalDate, String departureDate, String roomTypeSelection) {
		DatabaseConnection conn = new DatabaseConnection();
		int rmType = 0;
		int assignedRoomID = 0;
		ResultSet availableRooms;
		roomTypeSelection.trim();
		if (roomTypeSelection.equals("Standard")) {
			rmType = 1;
			price = 50;
		} else if (roomTypeSelection.equals("Deluxe")) {
			rmType = 2;
			price = 85;
		} else if (roomTypeSelection.equals("Luxury")) {
			rmType = 3;
			price = 130;
		} else if (roomTypeSelection.equals("Suite")) {
			rmType = 4;
			price = 180;
		} else if (roomTypeSelection.equals("Villa")) {
			rmType = 5;
			price = 210;
		}
		connection = conn.getConnection();
		try {
			CallableStatement callableStatement = connection.prepareCall("{call getAvailableRooms(?, ?, ?)}");
			callableStatement.setString(1, arrivalDate);
			callableStatement.setString(2, departureDate);
			callableStatement.setInt(3, rmType);
			callableStatement.execute();
			availableRooms = callableStatement.getResultSet();
			availableRooms.first();
			assignedRoomID = availableRooms.getInt(1);

			/*
			 * "SELECT price FROM hotelReservation.roomtype WHERE roomTypeID = ?;"
			 * ; PreparedStatement statement = connection.prepareStatement(sql);
			 * statement.setInt(1, rmType); ResultSet rs =
			 * statement.executeQuery(sql); price =
			 * Integer.parseInt(rs.getString("price"));
			 */

			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(assignedRoomID);

		return assignedRoomID;
	}

	public void insertReservation(String arrivalDate, String departureDate, int noOfGuestsSelection,
			String roomTypeSelection, String comments) {

		try {
			DatabaseConnection conn = new DatabaseConnection();
			connection = conn.getConnection();
			String sql = "INSERT INTO hotelReservation.reservation(`roomID`,`uID`,`dateCheckIn`,`dateCheckOut`,`noOfPeople`,`amountDue`,`paid`,`comments`) VALUES (?,?,?,?,?,?,?,?);";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, assignRoom(arrivalDate, departureDate, roomTypeSelection));
			preparedStatement.setInt(2, User.getInstance().getuID());
			preparedStatement.setString(3, arrivalDate);
			preparedStatement.setString(4, departureDate);
			preparedStatement.setInt(5, noOfGuestsSelection);

			preparedStatement.setInt(6, price);

			preparedStatement.setBoolean(7, false);
			preparedStatement.setString(8, comments);
			preparedStatement.execute();
			connection.close();

		} catch (SQLException ex) {

			System.out.println(ex);
		}
	}
}
