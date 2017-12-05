

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class adminView {

	private JFrame frame;
	private JTable table;
    String[] column_headers = {"Booking ID", "Date Check In", "Date Check Out", "No. of People", "Amount Due", "Paid", "Comments"};
	String[][] exampleData = {{"101", "2017-02-03", "2017-02-07", "3", "$2000", "True", "SUCKS"}};
    String[][] data = new String[100][7];
    Connection connection;
	private JButton btnDone;
	/**
	 * Launch the application.
	 */
	public static void newScreen(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					adminView window = new adminView();
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
	public adminView() {
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
				adminFunction rP = new adminFunction();
				adminFunction.newScreen();
			}
		});
		frame.getContentPane().add(btnDone);
		
		
		
	}

    public String[][] getReservations() {
        DatabaseConnection conn = new DatabaseConnection();
        connection = conn.getConnection();
        String reservationData[][] = new String[100][7];
        try {
            String sql = "SELECT * FROM hotelReservation.reservation";
            PreparedStatement statement = connection.prepareStatement(sql);
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