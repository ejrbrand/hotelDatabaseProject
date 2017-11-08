import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JButton;

public class LoginFrame {

	private JFrame frame;
	private JTextField usernameTextField;
	private JTextField passwordTextField;
	private JButton btnLogIn;
	private JLabel lblUserName;
	private JLabel lblPassword;
	private JButton btnCancel;

	/**
	 * Launch the application.
	 */
	public static void newScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame window = new LoginFrame();
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
	public LoginFrame() {
		initialize();
	}

	/*
	 * Check if textfields are empty
	 */
	public boolean validateField( JTextField f)
	{
	  if ( f.getText().equals("") )
	  {
	    return false;
	  }
	  else
	    return true; // validation successful
	}
	
	/**
	 * compares password from both text fields to make sure they are equal
	 * @param s1 first string to be compared
	 * @param s2 second string to be compared 
	 * @return true if passwords match otherwise false
	 */
	public static boolean validatePasswordText(String s1, String s2)
	{
		if(s1 == s2) return true;
		return false;
	}
	
	/**
	 * Validates username and password on the LogIn Page
	 * @return true if username and password aren't empty are in database, false otherwise
	 */
	public boolean validate()
	{
		if(!validateField(usernameTextField))
		{
			System.out.println("Field cannot be empty");
			return false;
		}
		else
			
			if(!validateField(passwordTextField))
			{
				System.out.println("Field cannot be empty");
				return false;
			}
			else
				return true;
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblLoginPage = new JLabel("Login Page");
		lblLoginPage.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblLoginPage.setHorizontalAlignment(SwingConstants.CENTER);
		lblLoginPage.setBounds(115, 16, 207, 44);
		frame.getContentPane().add(lblLoginPage);
		
		usernameTextField = new JTextField();
		usernameTextField.setBounds(15, 140, 400, 30);
		frame.getContentPane().add(usernameTextField);
		usernameTextField.setColumns(10);
		
		
		TextPrompt usernamePrompt = new TextPrompt("Username", usernameTextField);
		usernamePrompt.setForeground(Color.gray);
		usernamePrompt.changeAlpha(150);
		
		
		passwordTextField = new JTextField();
		passwordTextField.setBounds(15, 215, 400, 30);
		frame.getContentPane().add(passwordTextField);
		passwordTextField.setColumns(10);
		
		TextPrompt passwordPrompt = new TextPrompt("Password", passwordTextField);
		passwordPrompt.setForeground(Color.gray);
		passwordPrompt.changeAlpha(150);
		
		
		
		btnLogIn = new JButton("Log In");
		btnLogIn.setBounds(300, 290, 115, 30);
		frame.getContentPane().add(btnLogIn);
		btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				validate();
				if(usernameTextField.getText().isEmpty() == true
					|| passwordTextField.getText().isEmpty() == true)
					{
						System.out.println("Must fill out all fields");
					}
				else
				{
					frame.setVisible(false);
					ReserveRoom rR = new ReserveRoom();
					rR.newScreen();
				}
				
			}
		});
		
		lblUserName = new JLabel("User Name");
		lblUserName.setBounds(15, 110, 80, 30);
		frame.getContentPane().add(lblUserName);
		
		lblPassword = new JLabel("Password");
		lblPassword.setBounds(15, 185, 80, 30);
		frame.getContentPane().add(lblPassword);
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				mainFrame mF = new mainFrame();
				mF.main(null);
			}
		});
		btnCancel.setBounds(15, 290, 115, 30);
		frame.getContentPane().add(btnCancel);
	}

}
