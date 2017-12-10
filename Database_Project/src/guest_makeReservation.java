import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Properties;

public class guest_makeReservation {

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
	public guest_makeReservation() {
		initialize();
	}




	/**
	 * Launch the application.
	 */
	public static void newScreen(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					guest_makeReservation window = new guest_makeReservation();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
					guest_makeReservation window = new guest_makeReservation();
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
		frame.setBounds(100, 100, 500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblMakeReservationLabel = new JLabel("Make Reservation");
		lblMakeReservationLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblMakeReservationLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblMakeReservationLabel.setBounds(10, 50, 300, 30);
		frame.getContentPane().add(lblMakeReservationLabel);

		JLabel lblArrivalDate = new JLabel("Arrival Date");
		lblArrivalDate.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblArrivalDate.setBounds(10, 120, 150, 30);
		frame.getContentPane().add(lblArrivalDate);

        UtilDateModel arrivaldatemodel = new UtilDateModel();
        arrivaldatemodel.setDate(2017, 11, 18);
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JDatePanelImpl arrivalDatePanel = new JDatePanelImpl(arrivaldatemodel, p);
        JDatePickerImpl arrivalDatePicker = new JDatePickerImpl(arrivalDatePanel, new DateLabelFormatter());
        arrivalDatePicker.setBounds(10, 150, 150, 30);
        frame.getContentPane().add(arrivalDatePicker);

        JLabel lblDepartureDate = new JLabel("Departure Date");
        lblDepartureDate.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblDepartureDate.setBounds(300, 120, 150, 30);
        frame.getContentPane().add(lblDepartureDate);

        UtilDateModel departuredatemodel = new UtilDateModel();
        departuredatemodel.setDate(2017, 11, 18);
        Properties p2 = new Properties();
        p2.put("text.today", "Today");
        p2.put("text.month", "Month");
        p2.put("text.year", "Year");
        JDatePanelImpl departureDatePanel = new JDatePanelImpl(departuredatemodel, p2);
        JDatePickerImpl departureDatePicker = new JDatePickerImpl(departureDatePanel, new DateLabelFormatter());
        departureDatePicker.setBounds(300, 150, 150, 30);
        frame.getContentPane().add(departureDatePicker);

        JLabel lblNumberOfGuests = new JLabel("No. Of Guests");
		lblNumberOfGuests.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNumberOfGuests.setBounds(10, 200, 150, 30);
		frame.getContentPane().add(lblNumberOfGuests);

        textFieldNumberOfGuests = new JTextField();
        textFieldNumberOfGuests.setBounds(10, 230, 150, 30);
        frame.getContentPane().add(textFieldNumberOfGuests);
        textFieldNumberOfGuests.setColumns(10);

        JLabel lblRoomType = new JLabel("Room Type");
        lblRoomType.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblRoomType.setBounds(300, 200, 150, 30);
        frame.getContentPane().add(lblRoomType);

        String[] roomType = { "Standard", "Deluxe", "Luxury", "Suite", "Villa" };
        JComboBox roomtypecombobox = new JComboBox(roomType);
        roomtypecombobox.setBounds(300, 230, 150, 30);
        frame.getContentPane().add(roomtypecombobox);

        JLabel lblComments = new JLabel("Comments");
		lblComments.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblComments.setBounds(10, 280, 150, 30);
		frame.getContentPane().add(lblComments);

		JTextPane commentsTextPane = new JTextPane();
		commentsTextPane.setBounds(10, 310, 400, 100);
		frame.getContentPane().add(commentsTextPane);

        JButton btnCancel = new JButton("Cancel");
        btnCancel.setBounds(160, 420, 150, 30);
        frame.getContentPane().add(btnCancel);
        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
				guest_menu rP = new guest_menu();
				guest_menu.newScreen();
            }
        });
        frame.getContentPane().add(btnCancel);

		JButton btnOk = new JButton("Ok");
		btnOk.setBounds(310, 420, 150, 30);
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
				guest_menu rP = new guest_menu();
				guest_menu.newScreen();
			}
		});
		frame.getContentPane().add(btnOk);

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