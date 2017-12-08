
// takes user input and then display the data

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class adminCount {

	Connection connection;

	User currentUser;
	private JFrame frame;
	private JTextField userInput;
	static String date;
	private int count = 0;

	/**
	 * Create the application.
	 */
	public adminCount() {
		initialize();
	}

	/**
	 * Launch the application.
	 */
	public static void newScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					adminCount window = new adminCount();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Check if textfields are empty
	 */
	public boolean validateField(JTextField f) {
		return !f.getText().equals("");
	}

	public boolean validate() {
		if (!validateField(userInput)) {
			return false;
		} else
			return true;
	}

	/**
	 * Initialize the contents of the frame. left side
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel search = new JLabel("Search Reservation");
		search.setHorizontalAlignment(SwingConstants.CENTER);
		search.setFont(new Font("Tahoma", Font.PLAIN, 20));
		search.setBounds(130, 16, 219, 42);
		frame.getContentPane().add(search);

		JLabel currentDate = new JLabel("Date");
		currentDate.setFont(new Font("Tahoma", Font.PLAIN, 20));
		currentDate.setBounds(15, 90, 160, 30);
		frame.getContentPane().add(currentDate);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				adminFunction.newScreen();
			}
		});
		btnCancel.setBounds(241, 413, 115, 29);
		frame.getContentPane().add(btnCancel);

		JButton btnOk = new JButton("Ok");

		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String datePattern = "\\d{4}-\\d{2}-\\d{2}";
				validate();
				
				if (userInput.getText().isEmpty() == true) {
					System.out.println("Field cannot be empty");
				} else if (!userInput.getText().matches(datePattern)) {
					System.out.println("Incorrect Format");
				} else {
					// change to count*
					date = userInput.getText();
					getCount();
					
					int input = JOptionPane.showConfirmDialog(null, "Count is " + count, null,
							JOptionPane.INFORMATION_MESSAGE);
					System.out.println(input);
					if (input == JOptionPane.OK_OPTION) {

						System.out.println("I CLICKED THE OK OPTION");
						frame.setVisible(false);
						adminView2.newScreen(null);

					}
				}
			}
		});

		btnOk.setBounds(362, 413, 115, 29);
		frame.getContentPane().add(btnOk);

		userInput = new JTextField();
		userInput.setBounds(215, 90, 160, 30);
		frame.getContentPane().add(userInput);
		userInput.setColumns(10);

		TextPrompt usernamePrompt = new TextPrompt("yyyy-mm-dd", userInput);
		usernamePrompt.setForeground(Color.GRAY);
		usernamePrompt.changeAlpha(150);

	}

	public void getCount() {

		DatabaseConnection conn = new DatabaseConnection();
		connection = conn.getConnection();
		try {

			String sql = "select count(*) from reservation where dateCheckIn = ?";

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, date);
			//statement.executeQuery();
			ResultSet rs = statement.executeQuery();

			// work here
			while (rs.next()) {
				count = rs.getInt("count(*)");
			}

			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
