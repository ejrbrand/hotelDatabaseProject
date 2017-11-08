import java.awt.FlowLayout;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;



public class Database extends JFrame{
	
	private JFrame mainFrame;
	private JLabel headerLabel;
	private JPanel controlPanel;

	public Database(){
		prepare();
	}
	
	public static void main(String[] args) {
		
		Database database = new Database();
		database.showButton();
		
}
	
	private void prepare() {
		JLabel jlbHelloWorld = new JLabel("Hello World");
		add(jlbHelloWorld);
		this.setSize(500, 500);
		// pack();
		
		headerLabel = new JLabel("", JLabel.CENTER);        
		controlPanel = new JPanel();
	    controlPanel.setLayout(new FlowLayout());

	    mainFrame.add(headerLabel);
		setVisible(true);

	}
	
	private void showButton()
	{
		JButton registerButton = new JButton("Register");
		JButton loginButton = new JButton("Login");
		
		controlPanel.add(registerButton);
		controlPanel.add(loginButton);
	}
}
