import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class guest_menu {

	private JFrame frame;

	/**
	 * Create the application.
	 */
	public guest_menu() {
		initialize();
	}

	/**
	 * Launch the application.
	 */
	public static void newScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					guest_menu window = new guest_menu();
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
		
		JLabel lblReservationPage = new JLabel("Reservation Page");
		lblReservationPage.setBounds(30, 30, 260, 35);
		lblReservationPage.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblReservationPage.setHorizontalAlignment(SwingConstants.LEFT);
		frame.getContentPane().add(lblReservationPage);
		
		JButton btnMakeReservation = new JButton("Make Reservation");
		btnMakeReservation.setBounds(15, 170, 165, 30);
		btnMakeReservation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				guest_makeReservation mF = new guest_makeReservation();
				guest_makeReservation.newScreen(null);
			}
		});
		frame.getContentPane().add(btnMakeReservation);
		
		JButton btnViewReservation = new JButton("View Reservation");
		btnViewReservation.setBounds(15, 230, 165, 30);
		frame.getContentPane().add(btnViewReservation);
		btnViewReservation.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				guest_ViewReservations mF = new guest_ViewReservations();
				guest_ViewReservations.newScreen(null);
			}
		});
		
		
		JButton btnCancelReservation = new JButton("Cancel Reservation");
		btnCancelReservation.setBounds(15, 290, 165, 30);
		btnCancelReservation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				guest_cancelReservation cF = new guest_cancelReservation();
				guest_cancelReservation.newScreen(null);
			}
		});
		frame.getContentPane().add(btnCancelReservation);


		JButton RoomTypeByRatings = new JButton("Ratings by Room Type");
		RoomTypeByRatings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				guest_popularRooms ff = new guest_popularRooms();
				guest_popularRooms.newScreen();
			}
		});
		RoomTypeByRatings.setBounds(15, 410, 165, 30);
		frame.getContentPane().add(RoomTypeByRatings);
		
		JButton btnProvideFeedback = new JButton("Provide Feedback");
		btnProvideFeedback.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				guest_provideFeedback ff = new guest_provideFeedback();
				guest_provideFeedback.newScreen();
			}
		});
		btnProvideFeedback.setBounds(310, 170, 165, 30);
		frame.getContentPane().add(btnProvideFeedback);
		
		JButton btnHelp = new JButton("Help");
		btnHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnHelp.setBounds(310, 230, 165, 30);
		//frame.getContentPane().add(btnHelp);
		
		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainClass window = new MainClass();
				frame.setVisible(false);
				MainClass.main(null);
			}
		});
		btnLogOut.setBounds(315, 410, 160, 30);
		frame.getContentPane().add(btnLogOut);
	}
}
