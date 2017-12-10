import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class guest_provideFeedback {

    JFrame frame;
    JLabel bookingIDlbl;
    JTextField bookingIDtextfield;
    JLabel starslbl;
    JTextField starsTextfield;
    JLabel feedbacklbl;
    JTextPane feedbacktextPane;
    JButton btnDone;
    JButton btnCancel;
    JLabel incorrectstars;
    Connection connection;

    /**
     * Create the application.
     */
    public guest_provideFeedback() {
        initialize();
    }

    /**
     * Launch the application.
     */
    public static void newScreen() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    guest_provideFeedback window = new guest_provideFeedback();
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
        frame.setBounds(200, 200, 500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel feedbackFrameLbl = new JLabel("Ratings Page");
        feedbackFrameLbl.setFont(new Font("Tahoma", Font.PLAIN, 30));
        feedbackFrameLbl.setHorizontalAlignment(SwingConstants.CENTER);
        feedbackFrameLbl.setBounds(115, 16, 207, 44);
        frame.getContentPane().add(feedbackFrameLbl);

        bookingIDlbl = new JLabel("Enter BookingID");
        bookingIDlbl.setBounds(15, 85, 150, 30);
        frame.getContentPane().add(bookingIDlbl);

        bookingIDtextfield = new JTextField();
        bookingIDtextfield.setBounds(15, 115, 400, 30);
        frame.getContentPane().add(bookingIDtextfield);
        bookingIDtextfield.setColumns(10);

        starslbl = new JLabel("Enter a rating from 1 to 5");
        starslbl.setBounds(15, 145, 250, 30);
        frame.getContentPane().add(starslbl);

        starsTextfield = new JTextField();
        starsTextfield.setBounds(15, 175, 400, 30);
        frame.getContentPane().add(starsTextfield);

        incorrectstars = new JLabel("Please enter a value between 1 and 5");
        incorrectstars.setBounds(15, 205, 300, 30);

        feedbacklbl = new JLabel("Enter feedback");
        feedbacklbl.setBounds(15, 235, 250, 30);
        frame.getContentPane().add(feedbacklbl);

        feedbacktextPane = new JTextPane();
        feedbacktextPane.setBounds(15, 275, 400, 60);
        frame.getContentPane().add(feedbacktextPane);


        btnDone = new JButton("Done");
        btnDone.setBounds(300, 335, 115, 30);
        frame.getContentPane().add(btnDone);
        btnDone.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int bookingID = Integer.parseInt(bookingIDtextfield.getText());
                int stars = Integer.parseInt(starsTextfield.getText());
                String feedback = feedbacktextPane.getText();
                if (stars < 1 || stars > 5) {
                    frame.getContentPane().add(incorrectstars);
                    frame.revalidate();
                    frame.repaint();
                } else {
                    frame.setVisible(false);
                    insertRating(bookingID, stars, feedback);
                    guest_menu mf = new guest_menu();
                    guest_menu.newScreen();
                }
            }
        });

        btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                guest_menu mF = new guest_menu();
                guest_menu.newScreen();
            }
        });
        btnCancel.setBounds(15, 335, 115, 30);
        frame.getContentPane().add(btnCancel);


    }

    public void insertRating(int bookingID, int stars, String feedback) {
        DatabaseConnection conn = new DatabaseConnection();
        connection = conn.getConnection();
        String query = "INSERT INTO hotelReservation.ratings(`uID`,`bookingID`,`stars`,`feedback`) VALUES (?,?,?,?);";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, User.getInstance().getuID());
            statement.setInt(2, bookingID);
            statement.setInt(3, stars);
            statement.setString(4, feedback);
            statement.execute();
            connection.close();
        } catch (SQLException e) {
            JLabel wrong = new JLabel("Enter a valid bookingID. Your bookingID can be found under View reservation");
            wrong.setBounds(15, 365, 500, 30);
            frame.getContentPane().add(wrong);
            frame.revalidate();
            frame.repaint();
            frame.setVisible(true);

        }
    }

}