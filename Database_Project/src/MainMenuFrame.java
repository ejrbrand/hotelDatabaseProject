import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuFrame {

	private JFrame frame;

	/**
	 * Create the application.
	 */
	public MainMenuFrame() {
		initialize();
	}

	/**
	 * Launch the application.
	 */
	public static void newScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenuFrame window = new MainMenuFrame();
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
				makeReservationFrame mF = new makeReservationFrame();
				makeReservationFrame.newScreen(null);
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
				ViewReservation.newScreen(null);
			}
		});
		
		
		JButton btnCancelReservation = new JButton("Cancel Reservation");
		btnCancelReservation.setBounds(15, 290, 165, 30);
		btnCancelReservation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				CancelReservation cF = new CancelReservation();
				CancelReservation.newScreen(null);
			}
		});
		frame.getContentPane().add(btnCancelReservation);

		
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
				launchAppFrame window = new launchAppFrame();
				frame.setVisible(false);
				launchAppFrame.main(null);
			}
		});
		btnLogOut.setBounds(315, 410, 160, 30);
		frame.getContentPane().add(btnLogOut);
	}
}
