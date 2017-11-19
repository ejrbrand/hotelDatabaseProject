import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class RegisterFrame {

	private JFrame frame;
	private JTextField usernameTextField;
	private JTextField passwordTextField;
	private JTextField confirmPasswordTextField;
	private static JTextField ageTextField;
	private JTextField emailTextField;
	private JTextField firstnameTextField;
	private JTextField lastnameTextField;

	private String host = "team10.mysql.database.azure.com";
	private String database = "hotelReservation";
	private String user = "team10@team10";
	private String password = "Password01!";


	/**
	 * Launch the application.
	 */
	public static void newScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterFrame window = new RegisterFrame();
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
	public RegisterFrame() {
		initialize();
	}
	
	/**
	 * compares password from both text fields to make sure they are equal
	 * @param s1 first string to be compared
	 * @param s2 second string to be compared 
	 * @return true if passwords match otherwise false
	 */
	public static boolean validatePasswordText(String s1, String s2)
	{
		return s1 == s2;
	}
	
	/**
	 * Validates the age text field to make sure its a number
	 * @return true if input is a number or false if not
	 */
	public static boolean validateAgeText()
	{
		return isNumeric(ageTextField.getText()) == true;
	}
	
	/**
	 * Helper method for validateAgeText method
	 * @param s String input 
	 * @return true if input is all numbers otherwise false 
	 */
	public static boolean isNumeric(String s) {  
	    return s != null && s.matches("[-+]?\\d*\\.?\\d+");  
	}
	
	/*
	 * Check if textfields are empty
	 */
	public boolean validateField( JTextField f)
	{
		return !f.getText().equals("");
	}
	
	public boolean validate()
	{
		if(!validateField(usernameTextField))
		{
			System.out.println("Field cannot be empty");
			return false;
		}
		else
			
			if(!validateField(passwordTextField)
			|| !validateField(confirmPasswordTextField))
			{
				System.out.println("Field cannot be empty");
				return false;
			}
			else
				if(passwordTextField.getText().equals(confirmPasswordTextField.getText()) == false)
				{
					System.out.println("Passwords must match");
					return false;
				}
				else
				{
					if(validateAgeText() == false)
					{
						System.out.println("Age must be a number");
						return false;
					}
					else
						return true;
				}
	}
	
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblRegisterUser = new JLabel("Register User");
		lblRegisterUser.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegisterUser.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblRegisterUser.setBounds(130, 16, 219, 42);
		frame.getContentPane().add(lblRegisterUser);

		JLabel lblFName = new JLabel("First Name");
		lblFName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblFName.setBounds(15, 60, 160, 30);
		frame.getContentPane().add(lblFName);

		JLabel lblLName = new JLabel("Last Name");
		lblLName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblLName.setBounds(15, 90, 160, 30);
		frame.getContentPane().add(lblLName);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblUsername.setBounds(15, 120, 160, 30);
		frame.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPassword.setBounds(15, 150, 160, 30);
		frame.getContentPane().add(lblPassword);
		
		JLabel lblConfirmPassword = new JLabel("Confirm Password");
		lblConfirmPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblConfirmPassword.setBounds(15, 180, 160, 30);
		frame.getContentPane().add(lblConfirmPassword);
		
		JLabel lblAge = new JLabel("Age");
		lblAge.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAge.setBounds(15, 210, 160, 30);
		frame.getContentPane().add(lblAge);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEmail.setBounds(15, 240, 160, 30);
		frame.getContentPane().add(lblEmail);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				mainFrame mF = new mainFrame();
				mainFrame.main(null);
			}
		});
		btnCancel.setBounds(241, 413, 115, 29);
		frame.getContentPane().add(btnCancel);
		
		
		JButton btnOk = new JButton("Ok");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(validate())
				{
					if(usernameTextField.getText().isEmpty() == true
							|| passwordTextField.getText().isEmpty() == true
							|| confirmPasswordTextField.getText().isEmpty() == true
							|| ageTextField.getText().isEmpty() == true
							|| emailTextField.getText().isEmpty() == true)
							{
								System.out.println("Must fill out all fields");
							}
					else
					{
						String username = usernameTextField.getText();
						String fName = firstnameTextField.getText();
						String lName = lastnameTextField.getText();
						String pass = passwordTextField.getText();
						String email = emailTextField.getText();
						int age = Integer.parseInt(ageTextField.getText());
						connectToAndQueryDatabase(username, pass, fName, lName, age, email);
						frame.setVisible(false);
						LoginFrame lF = new LoginFrame();
						LoginFrame.newScreen();
					}
				}
				else
					System.out.println("error");
				
				
				
			}
		});
				
	
		btnOk.setBounds(362, 413, 115, 29);
		frame.getContentPane().add(btnOk);

		firstnameTextField = new JTextField();
		firstnameTextField.setBounds(215, 60, 160, 30);
		frame.getContentPane().add(firstnameTextField);
		firstnameTextField.setColumns(10);

		TextPrompt firstNamePrompt = new TextPrompt("First Name", firstnameTextField);
		firstNamePrompt.setForeground(Color.GRAY);
		firstNamePrompt.changeAlpha(150);

		lastnameTextField = new JTextField();
		lastnameTextField.setBounds(215, 90, 160, 30);
		frame.getContentPane().add(lastnameTextField);
		lastnameTextField.setColumns(10);

		TextPrompt lastNamePrompt = new TextPrompt("Last Name", lastnameTextField);
		lastNamePrompt.setForeground(Color.GRAY);
		lastNamePrompt.changeAlpha(150);
		
		usernameTextField = new JTextField();
		usernameTextField.setBounds(215, 120, 160, 30);
		frame.getContentPane().add(usernameTextField);
		usernameTextField.setColumns(10);
		
		TextPrompt usernamePrompt = new TextPrompt("Username", usernameTextField);
		usernamePrompt.setForeground(Color.GRAY);
		usernamePrompt.changeAlpha(150);
		
<<<<<<< HEAD
		passwordTextField = new JPasswordField();
=======
		passwordTextField = new JTextField();
>>>>>>> master
		passwordTextField.setBounds(215, 150, 160, 30);
		frame.getContentPane().add(passwordTextField);
		passwordTextField.setColumns(10);
		
		TextPrompt passwordPrompt = new TextPrompt("Password", passwordTextField);
		passwordPrompt.setForeground(Color.GRAY);
		passwordPrompt.changeAlpha(150);
		
<<<<<<< HEAD
		confirmPasswordTextField = new JPasswordField();
=======
		confirmPasswordTextField = new JTextField();
>>>>>>> master
		confirmPasswordTextField.setBounds(215, 180, 160, 30);
		frame.getContentPane().add(confirmPasswordTextField);
		confirmPasswordTextField.setColumns(10);
		
		TextPrompt confirmPasswordPrompt = new TextPrompt("Confirm Password", confirmPasswordTextField);
		confirmPasswordPrompt.setForeground(Color.GRAY);
		confirmPasswordPrompt.changeAlpha(150);
		
		
		ageTextField = new JTextField();
		ageTextField.setBounds(215, 210, 160, 30);
		frame.getContentPane().add(ageTextField);
		ageTextField.setColumns(10);
		
		TextPrompt agePrompt = new TextPrompt("Age", ageTextField);
		agePrompt.setForeground(Color.gray);
		agePrompt.changeAlpha(150);
		
		
		emailTextField = new JTextField();
		emailTextField.setBounds(215, 240, 160, 30);
		frame.getContentPane().add(emailTextField);
		emailTextField.setColumns(10);
		
		TextPrompt emailPrompt = new TextPrompt("Email Address", emailTextField);
		emailPrompt.setForeground(Color.gray);
		emailPrompt.changeAlpha(150);
		
	}

	public void connectToAndQueryDatabase(String username, String password, String firstN, String lastN, int age, String email) {

		try {
			String url = String.format("jdbc:mysql://%s/%s", host, database);
			Properties properties = new Properties();
			properties.setProperty("user", user);
			properties.setProperty("password", this.password);
			properties.setProperty("useSSL", "true");
			properties.setProperty("verifyServerCertificate", "true");
			properties.setProperty("requireSSL", "false");
			Connection conn = DriverManager.getConnection(url, properties);
			System.out.println("Connected");
			String sql = "INSERT INTO hotelReservation.guest(`username`,`password`,`first_name`,`last_name`,`age`,`email`) VALUES (?,?,?,?,?,?);";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			preparedStatement.setString(3, firstN);
			preparedStatement.setString(4, lastN);
			preparedStatement.setInt(5, age);
			preparedStatement.setString(6, email);
			preparedStatement.execute();
			conn.close();

		} catch (SQLException ex) {

			System.out.println(ex);
		}
	}
}
