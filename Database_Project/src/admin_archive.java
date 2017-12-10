import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class admin_archive {

	private JFrame frame;
	private JButton btnOk;
	Connection connection;
    JDatePickerImpl archiveRatingsDatePicker;
    JDatePickerImpl archiveReservationDatePicker;

	/**
	 * Create the application.
	 */
	public admin_archive() {
		initialize();
	}

	/**
	 * Launch the application.
	 */
	public static void newScreen(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					admin_archive window = new admin_archive();
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
					admin_archive window = new admin_archive();
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
		frame.setBounds(100, 100, 500, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblArchive = new JLabel("Archive");
		lblArchive.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblArchive.setBounds(15, 15, 150, 30);
		frame.getContentPane().add(lblArchive);
		
		JButton btnArchiveRatings = new JButton("Archive Ratings");
		btnArchiveRatings.setBounds(15, 85, 180, 30);
		frame.getContentPane().add(btnArchiveRatings);
		btnArchiveRatings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!archiveRatingsDatePicker.getJFormattedTextField().getText().isEmpty())
				{
					int input = JOptionPane.showConfirmDialog(null, "Are you sure you want to archive ratings?", null, JOptionPane.INFORMATION_MESSAGE);
			        System.out.println(input);
			        if(input == JOptionPane.OK_OPTION)
			        {
			        	System.out.println(archiveRatingsDatePicker.getJFormattedTextField().getText());
			        	archiveRatings(archiveRatingsDatePicker.getJFormattedTextField().getText());
			        }
				}
				else
				{
					JOptionPane.showMessageDialog(frame, "Must enter a date");
				}
				
			}
		});
		
		JButton btnArchiveReservations = new JButton("Archive Reservations");
		btnArchiveReservations.setBounds(15, 150, 180, 30);
		frame.getContentPane().add(btnArchiveReservations);
		btnArchiveReservations.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!archiveReservationDatePicker.getJFormattedTextField().getText().isEmpty())
				{
					int input = JOptionPane.showConfirmDialog(null, "Are you sure you want to archive reservations?", null, JOptionPane.INFORMATION_MESSAGE);
			        System.out.println(input);
			        if(input == JOptionPane.OK_OPTION)
			        {
			        	System.out.println(archiveReservationDatePicker.getJFormattedTextField().getText());
			        	archiveReservations(archiveReservationDatePicker.getJFormattedTextField().getText());
			        }
				}
				else
				{
					JOptionPane.showMessageDialog(frame, "Must enter a date");
				}
				
			}
		});

		UtilDateModel archiveRatingsDateModel = new UtilDateModel();
		archiveRatingsDateModel.setDate(2017, 11, 18);
		Properties p2 = new Properties();
		p2.put("text.today", "Today");
		p2.put("text.month", "Month");
		p2.put("text.year", "Year");
		JDatePanelImpl departureDatePanel = new JDatePanelImpl(archiveRatingsDateModel, p2);
		archiveRatingsDatePicker = new JDatePickerImpl(departureDatePanel, new DateLabelFormatter());
		archiveRatingsDatePicker.setBounds(250, 85, 150, 30);
		frame.getContentPane().add(archiveRatingsDatePicker);

		UtilDateModel archiveReservationDateModel = new UtilDateModel();
		archiveReservationDateModel.setDate(2017, 11, 18);
		Properties properties = new Properties();
		properties.put("text.today", "Today");
		properties.put("text.month", "Month");
		properties.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(archiveReservationDateModel, properties);
		archiveReservationDatePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		archiveReservationDatePicker.setBounds(250, 150, 150, 30);
		frame.getContentPane().add(archiveReservationDatePicker);
	
		
		btnOk = new JButton("OK");
		btnOk.setBounds(345, 310, 130, 30);
		frame.getContentPane().add(btnOk);
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                if (!archiveRatingsDatePicker.getJFormattedTextField().getText().isEmpty()) {
                    archiveRatings(archiveRatingsDatePicker.getJFormattedTextField().getText());
                }
                if (!archiveReservationDatePicker.getJFormattedTextField().getText().isEmpty()) {
                    archiveReservations(archiveReservationDatePicker.getJFormattedTextField().getText());
                }
				frame.setVisible(false);
				admin_menu aF = new admin_menu();
				admin_menu.newScreen();
			}
		});	
		
		
	}
	
	/**
	 * This function selects all the ratings before a certain cutoff date and inserts them into
	 * ratingsarchive table and deletes from ratings table
	 * @param cutoffDate the date that sets the cutoff for the tuples to be inserted and deleted
	 */
	public void archiveRatings(String cutoffDate) {
		DatabaseConnection conn = new DatabaseConnection();
		connection = conn.getConnection();
		
		try {
			String sqlCutoff = "SELECT * FROM ratings where updatedAt <= ?";
			PreparedStatement selectStatement = connection.prepareStatement(sqlCutoff);
			selectStatement.setString(1, cutoffDate);
            ResultSet rs = selectStatement.executeQuery();
            while(rs.next())	{
            	
            	String sqlInsert = "INSERT INTO ratingsarchive VALUES (?,?,?,?,?)";
            	PreparedStatement insertStatement = connection.prepareStatement(sqlInsert);
            	insertStatement.setInt(1,rs.getInt(1));
            	insertStatement.setInt(2,rs.getInt(2));
            	insertStatement.setInt(3, rs.getInt(3));
            	insertStatement.setString(4, rs.getString(4));
            	insertStatement.setDate(5, rs.getDate(5));
            	insertStatement.execute();
            	
          
                String sqlDelete = "DELETE FROM ratings WHERE updatedAt <= ?";
                PreparedStatement deleteStatement = connection.prepareStatement(sqlDelete);
                deleteStatement.setString(1, cutoffDate);
                deleteStatement.execute();
                
            }
		} catch (SQLException e) {
			
            e.printStackTrace();
        }
	}
	
	/**
	 * This function selects all the ratings before a certain cutoff date and inserts them into
	 * reservationarchive table and deletes from reservation table
	 * @param cutoffDate the date that sets the cutoff for the tuples to be inserted and deleted
	 */
	public void archiveReservations(String cutoffDate) {
		DatabaseConnection conn = new DatabaseConnection();
		connection = conn.getConnection();
		
		try {
			String sqlCutoff = "SELECT * FROM reservation where updatedAt <= ?";
			PreparedStatement selectStatement = connection.prepareStatement(sqlCutoff);
			selectStatement.setString(1, cutoffDate);
            ResultSet rs = selectStatement.executeQuery();
            while(rs.next())	{
            	
            	String sqlInsert = "INSERT INTO reservationarchive VALUES (?,?,?,?,?,?,?,?,?,?)";
            	PreparedStatement insertStatement = connection.prepareStatement(sqlInsert);
            	insertStatement.setInt(1,rs.getInt(1));
            	insertStatement.setInt(2,rs.getInt(2));
            	insertStatement.setInt(3, rs.getInt(3));
            	insertStatement.setDate(4, rs.getDate(4));
            	insertStatement.setDate(5, rs.getDate(5));
            	insertStatement.setInt(6, rs.getInt(6));
            	insertStatement.setDouble(7, rs.getDouble(7));
            	insertStatement.setInt(8, rs.getInt(8));
            	insertStatement.setString(9, rs.getString(9));
            	insertStatement.setDate(10, rs.getDate(10));
            	insertStatement.execute();
            	
          
                String sqlDelete = "DELETE FROM reservation WHERE updatedAt <= ?";
                PreparedStatement deleteStatement = connection.prepareStatement(sqlDelete);
                deleteStatement.setString(1, cutoffDate);
                deleteStatement.execute();
                
            }
		} catch (SQLException e) {
			
            e.printStackTrace();
        }
	}
	
}
