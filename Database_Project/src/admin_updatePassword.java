
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class admin_updatePassword {

	Connection connection;

	User currentUser;
	private JFrame frame;
	private JTextField usernameTextField;
	private JTextField passwordTextField;
	private JTextField confirmPasswordTextField;

	/**
	 * Create the application.
	 */
	public admin_updatePassword() {
		initialize();
	}

	/**
	 * Launch the application.
	 */
	public static void newScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					admin_updatePassword window = new admin_updatePassword();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * compares password from both text fields to make sure they are equal
	 *
	 * @param s1
	 *            first string to be compared
	 * @param s2
	 *            second string to be compared
	 * @return true if passwords match otherwise false
	 */
	public static boolean validatePasswordText(String s1, String s2) {
		return s1 == s2;
	}

	/**
	 * Check if textfields are empty
	 */
	public boolean validateField(JTextField f) {
		return !f.getText().equals("");
	}

	public boolean validate() {
		if (!validateField(passwordTextField) || !validateField(confirmPasswordTextField)) {
			System.out.println("Field cannot be empty");
			return false;
		} else if (passwordTextField.getText().equals(confirmPasswordTextField.getText()) == false) {
			System.out.println("Passwords must match");
			return false;
		} else
			return true;
	}

	/**
	 * Initialize the contents of the frame.
	 * left side
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel updatePass = new JLabel("Update User");
		updatePass.setHorizontalAlignment(SwingConstants.CENTER);
		updatePass.setFont(new Font("Tahoma", Font.PLAIN, 30));
		updatePass.setBounds(130, 16, 219, 42);
		frame.getContentPane().add(updatePass);

		JLabel lbluser = new JLabel("Username");
		lbluser.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbluser.setBounds(15, 90, 160, 30);
		frame.getContentPane().add(lbluser);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPassword.setBounds(15, 150, 160, 30);
		frame.getContentPane().add(lblPassword);

		JLabel lblConfirmPassword = new JLabel("Confirm Password");
		lblConfirmPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblConfirmPassword.setBounds(15, 180, 160, 30);
		frame.getContentPane().add(lblConfirmPassword);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				launchAppFrame.main(null);
			}
		});
		btnCancel.setBounds(241, 413, 115, 29);
		frame.getContentPane().add(btnCancel);

		JButton btnOk = new JButton("Ok");

		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (validate()) {
					if (passwordTextField.getText().isEmpty() == true
							|| confirmPasswordTextField.getText().isEmpty() == true) {
						System.out.println("Must fill out all fields");
					}else {
						
						updatePassword();
					}
				} else
					System.out.println("error");

			}
		});

		btnOk.setBounds(362, 413, 115, 29);
		frame.getContentPane().add(btnOk);
		
        usernameTextField = new JTextField();
        usernameTextField.setBounds(215, 90, 160, 30);
        frame.getContentPane().add(usernameTextField);
        usernameTextField.setColumns(10);

        TextPrompt usernamePrompt = new TextPrompt("Username", usernameTextField);
        usernamePrompt.setForeground(Color.GRAY);
        usernamePrompt.changeAlpha(150);


		passwordTextField = new JPasswordField();
		passwordTextField = new JTextField();
		passwordTextField.setBounds(215, 150, 160, 30);
		frame.getContentPane().add(passwordTextField);
		passwordTextField.setColumns(10);

		TextPrompt passwordPrompt = new TextPrompt("Password", passwordTextField);
		passwordPrompt.setForeground(Color.GRAY);
		passwordPrompt.changeAlpha(150);

		confirmPasswordTextField = new JPasswordField();
		confirmPasswordTextField = new JTextField();
		confirmPasswordTextField.setBounds(215, 180, 160, 30);
		frame.getContentPane().add(confirmPasswordTextField);
		confirmPasswordTextField.setColumns(10);

		TextPrompt confirmPasswordPrompt = new TextPrompt("Confirm Password", confirmPasswordTextField);
		confirmPasswordPrompt.setForeground(Color.GRAY);
		confirmPasswordPrompt.changeAlpha(150);

	}

	public void updatePassword() {

		DatabaseConnection conn = new DatabaseConnection();
		connection = conn.getConnection();
		
		String user = User.getInstance().getUsername();
		String pass = passwordTextField.getText();
		
		try {
            String sql = "UPDATE guest SET password = ? WHERE username = ?";
            
            PreparedStatement statement = connection.prepareStatement(sql);
            
            statement.setString(1, pass);
            statement.setString(2, user);
            statement.execute();
            connection.close();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
		

	}
}
