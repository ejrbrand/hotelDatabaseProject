import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class reservationCountFrame {

    Connection connection;
    private JFrame frame;

    /**
     * Create the application.
     */
    public reservationCountFrame() {
        initialize();
    }

    /**
     * Launch the application.
     */
    public static void newScreen() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    reservationCountFrame window = new reservationCountFrame();
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
                    reservationCountFrame window = new reservationCountFrame();
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
        frame.setBounds(100, 100, 500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblReservations = new JLabel("No. of Reservations by CheckIn Date");
        lblReservations.setFont(new Font("Tahoma", Font.PLAIN, 28));
        lblReservations.setBounds(15, 16, 500, 30);
        frame.getContentPane().add(lblReservations);

        String[][] data = getReservationCount();
        String[] column_headers = {"CheckIn Date", "No. of Reservations"};

        JTable table = new JTable(data, column_headers);
        JTableHeader header = table.getTableHeader();
        JPanel panel = new JPanel();
        panel.setLocation(15, 55);
        panel.setSize(400, 400);
        panel.setLayout(new BorderLayout());
        panel.add(header, BorderLayout.NORTH);
        panel.add(table, BorderLayout.CENTER);
        frame.getContentPane().add(panel);
    }

    public String[][] getReservationCount() {
        DatabaseConnection conn = new DatabaseConnection();
        connection = conn.getConnection();
        String data[][] = new String[100][2];
        String sql = "SELECT dateCheckIn AS `CheckIn Date`, COUNT(bookingID) FROM hotelReservation.reservation GROUP BY dateCheckIn ORDER BY dateCheckIn;";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            int i = 0;
            while (rs.next()) {
                data[i][0] = rs.getString(1);
                data[i][1] = rs.getInt(2) + "";
                i++;
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }
}
