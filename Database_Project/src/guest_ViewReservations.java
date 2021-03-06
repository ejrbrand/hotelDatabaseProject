import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class guest_ViewReservations {

	private JFrame frame;
	private JTable table;
    String[] column_headers = {"Booking ID", "Date Check In", "Date Check Out", "No. of People", "Amount Due", "Paid", "Comments"};

    String[][] data = new String[100][7];
    Connection connection;
	private JButton btnDone;

	/**
	 * Create the application.
	 */
	public guest_ViewReservations() {
		initialize();
	}

	/**
	 * Launch the application.
	 */
	public static void newScreen(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					guest_ViewReservations window = new guest_ViewReservations();
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
		frame.setBounds(100, 100, 900, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblReservations = new JLabel("Reservations");
		lblReservations.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblReservations.setBounds(15, 16, 200, 30);
		frame.getContentPane().add(lblReservations);

        data = getReservations();
        for (int i = 0; i <= 6; i++) {
            System.out.print(data[0][i] + ", ");
        }

        table = new JTable(data, column_headers);
		
		JTableHeader header = table.getTableHeader();
		JPanel panel = new JPanel();
		panel.setLocation(15, 55);
		panel.setSize(862, 187);
		panel.setLayout(new BorderLayout());
		panel.add(header, BorderLayout.NORTH);
		panel.add(table, BorderLayout.CENTER);
		frame.getContentPane().add(panel);
		
		btnDone = new JButton("Done");
		btnDone.setBounds(727, 312, 150, 30);
		btnDone.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				guest_menu rP = new guest_menu();
				guest_menu.newScreen();
			}
		});
		frame.getContentPane().add(btnDone);
		
		
		
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