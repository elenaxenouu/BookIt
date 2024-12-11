import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {
    private static final String URL = "jdbc:mysql://localhost:3306/bookit";
    private static final String USER = "root"; // Αν έχετε αλλάξει το username, αλλάξτε το εδώ
    private static final String PASSWORD = "my password";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
