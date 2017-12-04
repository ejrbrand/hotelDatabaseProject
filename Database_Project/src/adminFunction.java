
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class adminFunction {

	private JFrame frame;

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
		
		JButton btnUpdatePassword = new JButton("Update Password");
		btnUpdatePassword.setBounds(15, 170, 165, 30);
		btnUpdatePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				admin_updatePassword mF = new admin_updatePassword();
				admin_updatePassword.newScreen();
			}
		});
		frame.getContentPane().add(btnUpdatePassword);
		
		JButton TBE = new JButton("TBE");
		TBE.setBounds(15, 230, 165, 30);
		TBE.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
			}
		});
		frame.getContentPane().add(TBE);
		
		
		JButton TBE2 = new JButton("TBE");
		TBE2.setBounds(15, 290, 165, 30);
		TBE2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		frame.getContentPane().add(TBE2);

		
		JButton TBE3 = new JButton("TBE");
		TBE3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		TBE3.setBounds(15, 410, 165, 30);
		frame.getContentPane().add(TBE3);
		
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
