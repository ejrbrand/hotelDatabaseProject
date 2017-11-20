import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class DatabaseConnection extends JFrame {

    Connection connection;
    private String host = "team10.mysql.database.azure.com";
    private String database = "hotelReservation";
    private String user = "team10@team10";
    private String password = "Password01!";

    public Connection getConnection() {
        try {
            String url = String.format("jdbc:mysql://%s/%s", host, database);
            Properties properties = new Properties();
            properties.setProperty("user", user);
            properties.setProperty("password", this.password);
            properties.setProperty("useSSL", "true");
            properties.setProperty("verifyServerCertificate", "true");
            properties.setProperty("requireSSL", "false");
            connection = DriverManager.getConnection(url, properties);
            System.out.println("Connected");
        } catch (SQLException ex) {

            System.out.println(ex);
        }
        return connection;
    }
}
