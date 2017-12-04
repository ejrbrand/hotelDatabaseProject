import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.JTableHeader;

public class CancelReservation {

	private JFrame frame;
	private JTable table;
	String[] column_headers = {"Booking ID", "Date Check In","Date Check Out", "No. of People", "Amount Due", "Paid", "Comments"};
	String[][] exampleData = {{"01", "2017-02-03", "2017-02-07", "3", "$2000", "True", "SUCKS"}};
	private JButton btnDelete;
	private JButton btnCancel;

	/**
	 * Launch the application.
	 */
	public static void newScreen(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					CancelReservation window = new CancelReservation();
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
	public CancelReservation() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 900, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblReservations = new JLabel("Reservations");
		lblReservations.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblReservations.setBounds(15, 16, 200, 30);
		frame.getContentPane().add(lblReservations);
		
		
		
		
		table = new JTable(exampleData, column_headers);
		table.getModel().addTableModelListener(new TableModelListener() {

		      public void tableChanged(TableModelEvent e) {
		    	  
		    	  
		         //database changes RAJ
		      }
		    });
		
		JTableHeader header = table.getTableHeader();
		JPanel panel = new JPanel();
		panel.setLocation(15, 55);
		panel.setSize(862, 187);
		panel.setLayout(new BorderLayout());
		panel.add(header, BorderLayout.NORTH);
		panel.add(table, BorderLayout.CENTER);
		frame.getContentPane().add(panel);
		
		btnDelete = new JButton("Delete");
		btnDelete.setBounds(727, 312, 150, 30);
		btnDelete.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
		    	int input = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete selected reservation?", null, JOptionPane.INFORMATION_MESSAGE);
		        System.out.println(input);
		        if(input == JOptionPane.OK_OPTION)
		        {
		        	System.out.println("I CLICKED THE OK OPTION");
		        	// DATABASE DELETE STUFF
		        	frame.setVisible(false);
		        	MainMenuFrame rP = new MainMenuFrame();
		        	MainMenuFrame.newScreen();
		        	
		        }
		        else 
		        {
		        	System.out.println("click cancel option");
		        }
			}
		});
		frame.getContentPane().add(btnDelete);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(15, 312, 115, 30);
		btnCancel.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				MainMenuFrame rP = new MainMenuFrame();
				MainMenuFrame.newScreen();
			}
		});
		
		frame.getContentPane().add(btnCancel);
		
		
	}

}
