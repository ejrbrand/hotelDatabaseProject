import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class guest_popularRooms {
    Connection connection;
    String[] column_headers = {"Room Type", "Avg Rating", "No of reviewers"};
    String[][] data = new String[100][3];
    private JFrame frame;
    private JTable table;
    private JButton btnDone;

    /**
     * Create the application.
     */
    public guest_popularRooms() {
        initialize();
    }

    /**
     * Launch the application.
     */
    public static void newScreen() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    guest_popularRooms window = new guest_popularRooms();
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
                    guest_popularRooms window = new guest_popularRooms();
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

        JLabel lblReservations = new JLabel("Popular Room Types");
        lblReservations.setFont(new Font("Tahoma", Font.PLAIN, 28));
        lblReservations.setBounds(15, 16, 300, 30);
        frame.getContentPane().add(lblReservations);

        data = getInfo();

        table = new JTable(data, column_headers);

        JTableHeader header = table.getTableHeader();
        JPanel panel = new JPanel();
        panel.setLocation(15, 55);
        panel.setSize(250, 150);
        panel.setLayout(new BorderLayout());
        panel.add(header, BorderLayout.NORTH);
        panel.add(table, BorderLayout.CENTER);
        frame.getContentPane().add(panel);

        btnDone = new JButton("Done");
        btnDone.setBounds(300, 300, 150, 30);
        btnDone.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                guest_menu.newScreen();
            }
        });
        frame.getContentPane().add(btnDone);
    }

    public String[][] getInfo() {
        DatabaseConnection conn = new DatabaseConnection();
        connection = conn.getConnection();
        String[][] data = new String[100][3];
        String sql = "SELECT description, AVG(stars), count(reservation.roomID)" +
                " FROM ratings, reservation, room, roomtype WHERE ratings.bookingID = reservation.bookingID" + "" +
                " AND reservation.roomID = room.roomID AND room.roomTypeID = roomType.roomTypeID " + "" +
                "GROUP BY room.roomtypeid;";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            int i = 0;
            while (rs.next()) {
                data[i][0] = rs.getString(1);
                data[i][1] = rs.getDouble(2) + "";
                data[i][2] = rs.getInt(3) + "";
                i++;
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }
}
