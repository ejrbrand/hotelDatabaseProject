import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MakeRoom {

	private JFrame frame;
	private JLabel lblMakeRoom;
	private JTextField textFieldrID;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MakeRoom window = new MakeRoom();
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
	public MakeRoom() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 550, 550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		lblMakeRoom = new JLabel("Make Room");
		lblMakeRoom.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblMakeRoom.setBounds(30, 40, 200, 30);
		frame.getContentPane().add(lblMakeRoom);
		
		JButton btnCreateRoom = new JButton("Create Room");
		btnCreateRoom.setBounds(30, 130, 160, 30);
		frame.getContentPane().add(btnCreateRoom);
		
		JButton btnEditRoom = new JButton("Edit Room");
		btnEditRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnEditRoom.setBounds(30, 200, 160, 30);
		frame.getContentPane().add(btnEditRoom);
		
		JButton btn = new JButton("New button");
		btn.setBounds(30, 270, 160, 30);
		frame.getContentPane().add(btn);
		
		textFieldrID = new JTextField();
		textFieldrID.setBounds(381, 132, 146, 26);
		frame.getContentPane().add(textFieldrID);
		textFieldrID.setColumns(10);
		
	}
}
