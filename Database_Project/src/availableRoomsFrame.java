import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Properties;

public class availableRoomsFrame {

    Connection connection;
    DefaultTableModel model;
    JScrollPane pane;
    String[] column_headers = {"RoomID", "Room Type"};
    String[][] data = new String[100][2];
    private JFrame frame;
    private String arrivalDate;
    private String departureDate;
    private String roomTypeSelection;
    private JTable table;

    /**
     * Create the application.
     */
    public availableRoomsFrame() {
        initialize();
    }

    /**
     * Launch the application.
     */
    public static void newScreen(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    availableRoomsFrame window = new availableRoomsFrame();
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
                    availableRoomsFrame window = new availableRoomsFrame();
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

        JLabel lblArrivalDate = new JLabel("Arrival Date");
        lblArrivalDate.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblArrivalDate.setBounds(10, 50, 150, 30);
        frame.getContentPane().add(lblArrivalDate);

        JLabel lblDepartureDate = new JLabel("Departure Date");
        lblDepartureDate.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblDepartureDate.setBounds(300, 50, 150, 30);
        frame.getContentPane().add(lblDepartureDate);

        JLabel labelArrivalDateCalendar = new JLabel();
        labelArrivalDateCalendar.setText("Choose Date by selecting below.");
        labelArrivalDateCalendar.setBounds(10, 80, 150, 30);

        UtilDateModel arrivaldatemodel = new UtilDateModel();
        arrivaldatemodel.setDate(2017, 11, 18);
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JDatePanelImpl arrivalDatePanel = new JDatePanelImpl(arrivaldatemodel, p);
        JDatePickerImpl arrivalDatePicker = new JDatePickerImpl(arrivalDatePanel, new DateLabelFormatter());
        arrivalDatePicker.setBounds(10, 100, 150, 30);
        frame.getContentPane().add(arrivalDatePicker);

        JLabel labelDepartureDateCalendar = new JLabel();
        labelDepartureDateCalendar.setText("Choose Date by selecting below.");
        labelDepartureDateCalendar.setBounds(300, 80, 150, 30);

        UtilDateModel departuredatemodel = new UtilDateModel();
        departuredatemodel.setDate(2017, 11, 18);
        Properties p2 = new Properties();
        p2.put("text.today", "Today");
        p2.put("text.month", "Month");
        p2.put("text.year", "Year");
        JDatePanelImpl departureDatePanel = new JDatePanelImpl(departuredatemodel, p2);
        JDatePickerImpl departureDatePicker = new JDatePickerImpl(departureDatePanel, new DateLabelFormatter());
        departureDatePicker.setBounds(300, 100, 150, 30);
        frame.getContentPane().add(departureDatePicker);

        JLabel lblRoomType = new JLabel("Room Type");
        lblRoomType.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblRoomType.setBounds(10, 150, 150, 30);
        frame.getContentPane().add(lblRoomType);

        String[] roomType = {"Any", "Standard", "Deluxe", "Luxury", "Suite", "Villa"};
        JComboBox roomtypecombobox = new JComboBox(roomType);
        roomtypecombobox.setBounds(10, 180, 150, 30);
        frame.getContentPane().add(roomtypecombobox);

        table = new JTable();
        JTableHeader header = table.getTableHeader();
        JPanel panel = new JPanel();
        panel.setLocation(10, 220);
        panel.setSize(450, 200);
        panel.setLayout(new BorderLayout());
        panel.add(header, BorderLayout.NORTH);
        panel.add(table, BorderLayout.CENTER);
        pane = new JScrollPane(table);
        panel.add(pane, BorderLayout.CENTER);
        frame.getContentPane().add(panel);


        JButton btnCancel = new JButton("Cancel");
        btnCancel.setBounds(160, 420, 150, 30);
        frame.getContentPane().add(btnCancel);
        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                adminFunction af = new adminFunction();
                adminFunction.newScreen();
            }
        });
        frame.getContentPane().add(btnCancel);

        JButton btnOk = new JButton("Ok");
        btnOk.setBounds(310, 420, 150, 30);
        frame.getContentPane().add(btnOk);
        btnOk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                arrivalDate = arrivalDatePicker.getJFormattedTextField().getText();
                departureDate = departureDatePicker.getJFormattedTextField().getText();
                String combox = roomtypecombobox.getSelectedItem().toString();
                roomTypeSelection = combox;
                if (roomTypeSelection.equals("Any")) {
                    data = findAllAvailableRooms(arrivalDate, departureDate);
                } else {
                    data = findAllAvailableRooms(arrivalDate, departureDate, roomTypeSelection);
                }
                model = new DefaultTableModel(data, column_headers);
                table.setModel(model);
                frame.revalidate();
                frame.repaint();
            }
        });
        frame.getContentPane().add(btnOk);
    }

    public String[][] findAllAvailableRooms(String arrivalDate, String departureDate) {
        String data[][] = new String[100][2];
        DatabaseConnection conn = new DatabaseConnection();
        connection = conn.getConnection();
        ResultSet availableRooms;
        try {
            CallableStatement callableStatement = connection.prepareCall("{call getAvailableRoomsBetweenDates(?, ?)}");
            callableStatement.setString(1, arrivalDate);
            callableStatement.setString(2, departureDate);
            callableStatement.execute();
            availableRooms = callableStatement.getResultSet();
            int i = 0;
            while (availableRooms.next()) {
                data[i][0] = "" + availableRooms.getInt(1);
                String sql = "SELECT description FROM roomType NATURAL join room WHERE roomID = ?";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setInt(1, Integer.parseInt(data[i][0]));
                ResultSet rs = statement.executeQuery();
                rs.first();
                data[i][1] = rs.getString(1);
                System.out.println(data[i][0] + " " + data[i][1]);
                i++;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    public String[][] findAllAvailableRooms(String arrivalDate, String departureDate, String roomTypeSelection) {
        String data[][] = new String[100][2];
        DatabaseConnection conn = new DatabaseConnection();
        connection = conn.getConnection();
        int rmType = 0;
        ResultSet availableRooms;
        roomTypeSelection.trim();
        if (roomTypeSelection.equals("Standard"))
            rmType = 1;
        else if (roomTypeSelection.equals("Deluxe"))
            rmType = 2;
        else if (roomTypeSelection.equals("Luxury"))
            rmType = 3;
        else if (roomTypeSelection.equals("Suite"))
            rmType = 4;
        else if (roomTypeSelection.equals("Villa"))
            rmType = 5;
        try {
            CallableStatement callableStatement = connection.prepareCall("{call getAvailableRooms(?, ?, ?)}");
            callableStatement.setString(1, arrivalDate);
            callableStatement.setString(2, departureDate);
            callableStatement.setInt(3, rmType);
            callableStatement.execute();
            availableRooms = callableStatement.getResultSet();
            int i = 0;
            while (availableRooms.next()) {
                data[i][0] = "" + availableRooms.getInt(1);
                data[i][1] = roomTypeSelection;
                i++;
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    public String roomIDtoroom(int roomID) {
        DatabaseConnection conn = new DatabaseConnection();
        connection = conn.getConnection();
        String description = "";
        try {
            String sql = "SELECT description FROM roomType NATURAL join room WHERE roomID = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, roomID);
            ResultSet rs = statement.executeQuery();
            rs.first();
            description = rs.getString(1);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return description;
    }
}
