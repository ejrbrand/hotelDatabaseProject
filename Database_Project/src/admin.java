import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Properties;

public class admin {

    private JFrame frame;
    private JTextField usernameTextField;
    private JButton btnLogIn;
    private JLabel lblUserName;
    private JLabel lblPassword;
    private JButton btnCancel;
    private JLabel lblincorrect;
    private String host = "team10.mysql.database.azure.com";
    private String database = "hotelReservation";
    private String user = "team10@team10";
    private String password = "Password01!";
    private JPasswordField passwordField;

    /**
     * Create the application.
     */
    public admin() {
        initialize();
    }

    /**
     * Launch the application.
     */
    public static void newScreen() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    admin window = new admin();
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
     * @param s1 first string to be compared
     * @param s2 second string to be compared
     * @return true if passwords match otherwise false
     */
    public static boolean validatePasswordText(String s1, String s2) {
        return s1 == s2;
    }

    /*
     * Check if textfields are empty
     */
    public boolean validateField(JTextField f) {
        return !f.getText().equals("");
    }

    /**
     * Validates username and password on the LogIn Page
     *
     * @return true if username and password aren't empty are in database, false otherwise
     */
    public boolean validate() {
        if (!validateField(usernameTextField)) {
            System.out.println("Field cannot be empty");
            return false;
        } else if (!validateField(passwordField)) {
            System.out.println("Field cannot be empty");
            return false;
        } else
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

        JLabel lblLoginPage = new JLabel("Admin Login");
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

        passwordField = new JPasswordField();
        passwordField.setBounds(15, 215, 400, 30);
        frame.getContentPane().add(passwordField);


        TextPrompt passwordPrompt = new TextPrompt("Password", passwordField);
        passwordPrompt.setForeground(Color.gray);
        passwordPrompt.changeAlpha(150);


        btnLogIn = new JButton("Log In");
        btnLogIn.setBounds(300, 290, 115, 30);
        frame.getContentPane().add(btnLogIn);
        btnLogIn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                validate();
                if (usernameTextField.getText().isEmpty() == true
                        || passwordField.getText().isEmpty() == true) {
                    System.out.println("Must fill out all fields");
                } else {
                    if (validateCredentials(usernameTextField.getText(), passwordField.getText())) {
                        frame.setVisible(false);
                        
                        adminFunction rR = new adminFunction();
                        adminFunction.newScreen();
                    } else {
                        frame.getContentPane().add(lblincorrect);
                        frame.revalidate();
                        frame.repaint();
                    }
                }

            }
        });

        lblUserName = new JLabel("User Name");
        lblUserName.setBounds(15, 110, 80, 30);
        frame.getContentPane().add(lblUserName);

        lblPassword = new JLabel("Password");
        lblPassword.setBounds(15, 185, 80, 30);
        frame.getContentPane().add(lblPassword);

        lblincorrect = new JLabel("Incorrect username or password");
        lblincorrect.setBounds(15, 250, 350, 30);


        btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                launchAppFrame mF = new launchAppFrame();
                launchAppFrame.main(null);
            }
        });
        btnCancel.setBounds(15, 290, 115, 30);
        frame.getContentPane().add(btnCancel);


    }

    boolean validateCredentials(String username, String password) {
        boolean validate = false;
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
            String sql = "SELECT username,password FROM hotelReservation.admin WHERE admin.username=? "
            		+ "AND admin.password=?;";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.first()) {

                if (rs.getString("username").trim().equals(username) && rs.getString("password").equals(password)) {
                    validate = true;
                    return validate;
                }
            }
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return validate;
    }

}
