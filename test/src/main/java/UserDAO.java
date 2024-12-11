import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private static final String INSERT_USER = "INSERT INTO User (name, availableHours, selectedService, dateOfRegistration, start_time) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_ALL_USERS = "SELECT * FROM User";

    // Προσθήκη χρήστη
    public void addUser(User user) throws SQLException {
        try (Connection conn = DB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(INSERT_USER)) {
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getAvailableHours());
            stmt.setString(3, user.getSelectedService().getName());
            stmt.setDate(4, java.sql.Date.valueOf(user.getDateOfRegistration())); // Προσαρμογή για date
            stmt.setInt(5, user.getStartTime());
            stmt.executeUpdate();
        }
    }

    // Λήψη όλων των χρηστών
    public List<User> getUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        try (Connection conn = DB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_ALL_USERS);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                User user = new User(
                        rs.getString("name"),
                        rs.getString("availableHours"),
                        new Service(rs.getString("selectedService"), 0, 0), // Προσαρμογή για Service
                        rs.getDate("dateOfRegistration").toLocalDate(), // Μετατροπή σε LocalDate
                        rs.getInt("start_time")
                );
                users.add(user);
            }
        }
        return users;
    }
}
