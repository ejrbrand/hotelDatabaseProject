import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User {

    Connection connection;
    private String username;
    private String password;
    private String fName;
    private String lName;
    private String email;
    private int uID;
    private int age;

    public User(String username, String password, String email, String fName, String lName, int age) {
        this.age = age;
        this.email = email;
        this.fName = fName;
        this.lName = lName;
        this.username = username;
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public String getfName() {
        return fName;
    }

    public String getlName() {
        return lName;
    }

    public String getUsername() {
        return username;
    }

    public int getuID() {
        DatabaseConnection conn = new DatabaseConnection();
        connection = conn.getConnection();
        String sql = "SELECT uID FROM hotelReservation.guest WHERE username = ?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            uID = resultSet.getInt(1);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return uID;
    }
}
