import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReservationPage {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void newScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReservationPage window = new ReservationPage();
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
	public ReservationPage() {
		initialize();
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
				ReservationEntry mF = new ReservationEntry();
                ReservationEntry.main(null);
			}
		});
		frame.getContentPane().add(btnMakeReservation);
		
		JButton btnViewReservation = new JButton("View Reservation");
		btnViewReservation.setBounds(15, 230, 165, 30);
		frame.getContentPane().add(btnViewReservation);
		btnViewReservation.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				ViewReservation mF = new ViewReservation();
                ViewReservation.main(null);
			}
		});
		
		
		JButton btnCancelReservation = new JButton("Cancel Reservation");
		btnCancelReservation.setBounds(15, 290, 165, 30);
		btnCancelReservation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		frame.getContentPane().add(btnCancelReservation);
		
		JButton btnViewReservation_1 = new JButton("View Reservation");
		btnViewReservation_1.setBounds(15, 350, 165, 30);
		frame.getContentPane().add(btnViewReservation_1);
		
		JButton btnEditReservation = new JButton("Edit Reservation");
		btnEditReservation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnEditReservation.setBounds(15, 410, 165, 30);
		frame.getContentPane().add(btnEditReservation);
		
		JButton btnProvideFeedback = new JButton("Provide Feedback");
		btnProvideFeedback.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
		frame.getContentPane().add(btnHelp);
		
		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainFrame window = new mainFrame();
				frame.setVisible(false);
				mainFrame.main(null);
			}
		});
		btnLogOut.setBounds(315, 410, 160, 30);
		frame.getContentPane().add(btnLogOut);
	}
}
