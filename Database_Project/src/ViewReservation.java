import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewReservation {

	private JFrame frame;
	private JTable table;
	String[] column_headers = {"Booking ID", "Date Check In","Date Check Out", "No. of People", "Amount Due", "Paid", "Comments"};
	String[][] exampleData = {{"01", "2017-02-03", "2017-02-07", "3", "$2000", "True", "SUCKS"}};
	private JButton btnDone;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewReservation window = new ViewReservation();
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
	public ViewReservation() {
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
		
		JTableHeader header = table.getTableHeader();
		JPanel panel = new JPanel();
		panel.setLocation(15, 55);
		panel.setSize(862, 187);
		panel.setLayout(new BorderLayout());
		panel.add(header, BorderLayout.NORTH);
		panel.add(table, BorderLayout.CENTER);
		frame.getContentPane().add(panel);
		
		btnDone = new JButton("Done");
		btnDone.setBounds(727, 312, 150, 30);
		btnDone.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				ReservationPage rP = new ReservationPage();
                ReservationPage.newScreen();
			}
		});
		frame.getContentPane().add(btnDone);
		
		
		
	}
}
