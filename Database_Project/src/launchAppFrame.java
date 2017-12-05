import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class launchAppFrame {

	private JFrame frame;

	/**
	 * Create the application.
	 */
	public launchAppFrame() {
		initialize();
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					launchAppFrame window = new launchAppFrame();
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
		frame.setBounds(100, 100, 600, 350);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel mainPane = new JPanel();
		mainPane.setBounds(0, 0, 585, 304);
		frame.getContentPane().add(mainPane);
		mainPane.setLayout(null);
		mainPane.setLayout(null);
		
		JLabel lblHotelReservation = new JLabel("Hotel Reservation");
		lblHotelReservation.setHorizontalAlignment(SwingConstants.CENTER);
		lblHotelReservation.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblHotelReservation.setBounds(120, 16, 343, 41);
		mainPane.add(lblHotelReservation);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainPane.setVisible(false);
				RegisterFrame rF = new RegisterFrame();
				RegisterFrame.newScreen();
				frame.setVisible(false);
			}
			
		});
		btnRegister.setBounds(45, 120, 160, 30);
		mainPane.add(btnRegister);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainPane.setVisible(false);
				LoginFrame lF = new LoginFrame();
				LoginFrame.newScreen();
				frame.setVisible(false);
			}				
		});
		btnLogin.setBounds(45, 230, 160, 30);
		mainPane.add(btnLogin);
		
		JButton btnAdmin = new JButton("Admin Login");
		btnAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainPane.setVisible(false);
				admin lF = new admin();
				admin.newScreen();
				frame.setVisible(false);
			}				
		});
		
		btnAdmin.setBounds(410, 230, 160, 30);
		mainPane.add(btnAdmin);
	}
}
