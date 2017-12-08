import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class CancelReservation {

	private JFrame frame;
	private JTable table;
	DefaultTableModel model;
	String[] column_headers = {"Booking ID", "Date Check In","Date Check Out", "No. of People", "Amount Due", "Paid", "Comments"};
	String[][] data = new String[100][7];
	private JButton btnDelete;
	private JButton btnCancel;
	Connection connection;

	/**
	 * Launch the application.
	 */
	public static void newScreen(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					CancelReservation window = new CancelReservation();
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
	public CancelReservation() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 900, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblReservations = new JLabel("Reservations");
		lblReservations.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblReservations.setBounds(15, 16, 200, 30);
		frame.getContentPane().add(lblReservations);
		
		
		data = getReservations();
		model = new DefaultTableModel(data,column_headers);
		table = new JTable(model);
		
		JTableHeader header = table.getTableHeader();
		JPanel panel = new JPanel();
		panel.setLocation(15, 55);
		panel.setSize(862, 187);
		panel.setLayout(new BorderLayout());
		panel.add(header, BorderLayout.NORTH);
		panel.add(table, BorderLayout.CENTER);
		frame.getContentPane().add(panel);
		
		btnDelete = new JButton("Delete");
		btnDelete.setBounds(727, 312, 150, 30);
		btnDelete.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				int row = table.getSelectedRow();
				int selectedBookingID = Integer.parseInt(model.getValueAt(row,0).toString());
                System.out.println(selectedBookingID);
		    	int input = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete selected reservation?", null, JOptionPane.INFORMATION_MESSAGE);
		        System.out.println(input);
		        if(input == JOptionPane.OK_OPTION)
		        {
		        	System.out.println("I CLICKED THE OK OPTION");
		        	deleteReservationFromDatabase(selectedBookingID);
		        	frame.setVisible(false);
		        	MainMenuFrame rP = new MainMenuFrame();
		        	MainMenuFrame.newScreen();
		        	
		        }
		        else 
		        {
		        	System.out.println("click cancel option");
		        }
			}
		});
		frame.getContentPane().add(btnDelete);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(15, 312, 115, 30);
		btnCancel.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				MainMenuFrame rP = new MainMenuFrame();
				MainMenuFrame.newScreen();
			}
		});
		
		frame.getContentPane().add(btnCancel);
		
		
	}

	public void deleteReservationFromDatabase(int bookingID)	{
		DatabaseConnection conn = new DatabaseConnection();
		connection = conn.getConnection();
		String sql = "DELETE FROM reservation WHERE bookingID = ?";
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1,bookingID);
			statement.executeUpdate();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String[][] getReservations() {
		DatabaseConnection conn = new DatabaseConnection();
		connection = conn.getConnection();
		String reservationData[][] = new String[100][7];
		int uID = User.getInstance().getuID();
		try {
			String sql = "SELECT bookingID, dateCheckIn, dateCheckOut, noOfPeople, amountDue, paid, comments FROM hotelReservation.reservation WHERE uID=?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, uID);
			ResultSet rs = statement.executeQuery();
			int i = 0;
			while (rs.next()) {
				reservationData[i][0] = rs.getString(1);
				reservationData[i][1] = rs.getString(2);
				reservationData[i][2] = rs.getString(3);
				reservationData[i][3] = rs.getString(4);
				reservationData[i][4] = rs.getString(5);
				reservationData[i][5] = rs.getString(6);
				reservationData[i][6] = rs.getString(7);
				i++;
			}
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return reservationData;
	}

}
