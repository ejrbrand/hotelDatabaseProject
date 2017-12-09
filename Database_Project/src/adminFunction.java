
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class adminFunction {

	private JFrame frame;
    Connection connection;

	/**
	 * Create the application.
	 */
	public adminFunction() {
		initialize();
	}

	/**
	 * Launch the application.
	 */
	public static void newScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					adminFunction window = new adminFunction();
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
					adminFunction window = new adminFunction();
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
		
		JLabel lblReservationPage = new JLabel("Admin Page");
		lblReservationPage.setBounds(30, 30, 260, 35);
		lblReservationPage.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblReservationPage.setHorizontalAlignment(SwingConstants.LEFT);
		frame.getContentPane().add(lblReservationPage);
		
		//-----------------------------------------
		
		JButton btnUpdatePassword = new JButton("Update Password");
		btnUpdatePassword.setBounds(15, 100, 165, 30);
		btnUpdatePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				admin_updatePassword mF = new admin_updatePassword();
				admin_updatePassword.newScreen();
			}
		});
		frame.getContentPane().add(btnUpdatePassword);
		
		//-----------------------------------------
		
		JButton admin_View = new JButton("View All Reservations");
		admin_View.setBounds(15, 160, 165, 30);
		admin_View.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				adminView mF = new adminView();
				adminView.newScreen(null);
			}
		});
		frame.getContentPane().add(admin_View);
		
		
		//-----------------------------------------

        JButton availableroomsbutton = new JButton("Find Available Rooms");
        availableroomsbutton.setBounds(15, 220, 165, 30);
        availableroomsbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                availableRoomsFrame aq = new availableRoomsFrame();
                availableRoomsFrame.newScreen(null);
			}
		});
        frame.getContentPane().add(availableroomsbutton);

		JButton archive = new JButton("Archive");
		archive.setBounds(300, 100, 165, 30);
		archive.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				ArchiveFrame aF = new ArchiveFrame();
				aF.newScreen(null);
			}
		});
		frame.getContentPane().add(archive);


		//-----------------------------------------
		JButton TBE3 = new JButton("Search Reservation");
		TBE3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				adminCount.newScreen();
			}
		});
		TBE3.setBounds(300, 160, 165, 30);
		frame.getContentPane().add(TBE3);
		
		//-----------------------------------------



		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				launchAppFrame window = new launchAppFrame();
				frame.setVisible(false);
				launchAppFrame.main(null);
			}
		});
		btnLogOut.setBounds(315, 410, 160, 30);
		frame.getContentPane().add(btnLogOut);
		
		//-----------------------------------------

	}
	
	public String[][] sortByBookingID() {
    	DatabaseConnection conn = new DatabaseConnection();
        connection = conn.getConnection();
        String reservationData[][] = new String[100][7];
        
        try {
            String sql = "SELECT bookingID, dateCheckIn, dateCheckOut, noOfPeople, amountDue, paid, comments FROM hotelReservation.reservation ORDER BY bookingID";
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
