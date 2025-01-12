package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseHelper {

    private static final String DB_URL = "jdbc:sqlite:appointments.db";

    // Δημιουργία σύνδεσης με τη βάση δεδομένων
    static Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    // Δημιουργία των πινάκων αν δεν υπάρχουν ήδη
    public static void createTables() {

        String createServiceTable = "CREATE TABLE IF NOT EXISTS services (" +
                "name TEXT PRIMARY KEY, " +  // Το name είναι το PRIMARY KEY
                "cost REAL NOT NULL);";

        String createCustomerTable = "CREATE TABLE IF NOT EXISTS customers (" +
                "name TEXT NOT NULL, " +
                "username TEXT PRIMARY KEY, " +
                "password TEXT NOT NULL);";

        String createAppointmentTable = "CREATE TABLE IF NOT EXISTS appointments (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "username TEXT NOT NULL, " +
                "servicename TEXT NOT NULL, " +  // Αναφορά στο name του πίνακα services
                "cost REAL NOT NULL," +
                "start_time TEXT NOT NULL, " +
                "end_time TEXT NOT NULL, " +
                "FOREIGN KEY (username) REFERENCES customers(username), " +
                "FOREIGN KEY (cost) REFERENCES services(cost)"+
                "FOREIGN KEY (servicename) REFERENCES services(name));";  // Εδώ το servicename αναφέρεται στο όνομα υπηρεσίας

        try (Connection conn = connect()) {
            conn.createStatement().execute(createCustomerTable);
            conn.createStatement().execute(createAppointmentTable);
            conn.createStatement().execute(createServiceTable);
        } catch (SQLException e) {
            System.out.println("Error creating tables.");
            e.printStackTrace();
        }
    }

    // Προσθήκη υπηρεσιών στο πρόγραμμα
    public static void addStaticServices() {
        String[] services = {
            "Haircut|20.0",
            "Facial|50.0",
            "Manicure|25.0",
            "Pedicure|30.0",
            "Massage|60.0",
            "Waxing|40.0",
            "Eyebrow shaping|15.0",
            "Makeup|45.0"
        };

        String insertServiceQuery = "INSERT INTO services (name, cost) VALUES (?, ?)";
        String checkIfExistsQuery = "SELECT COUNT(*) FROM services WHERE name = ?";

        try (Connection conn = connect();
             PreparedStatement checkStmt = conn.prepareStatement(checkIfExistsQuery);
             PreparedStatement insertStmt = conn.prepareStatement(insertServiceQuery)) {

            for (String service : services) {
                String[] parts = service.split("\\|");
                checkStmt.setString(1, parts[0]);
                ResultSet rs = checkStmt.executeQuery();
                if (rs.getInt(1) == 0) {  // Ελέγχει αν η υπηρεσία υπάρχει ήδη
                    insertStmt.setString(1, parts[0]);
                    insertStmt.setDouble(2, Double.parseDouble(parts[1]));
                    insertStmt.executeUpdate();
                }
            }

        } catch (SQLException e) {
            System.out.println("Error inserting services.");
            e.printStackTrace();
        }
    }

    // Προσθήκη πελατών με τυχαία δεδομένα
    public static void addStaticCustomers() {
        String[] customers = {
            "John Doe|john.doe|password123",
            "Jane Smith|jane.smith|password456",
            "Elena|elena|elena123",
            "Michael Johnson|michael.johnson|michael123",
            "Sarah Brown|sarah.brown|sarah123"
        };

        String insertCustomerQuery = "INSERT INTO customers (name, username, password) VALUES (?, ?, ?)";
        String checkIfExistsQuery = "SELECT COUNT(*) FROM customers WHERE username = ?";

        try (Connection conn = connect();
             PreparedStatement checkStmt = conn.prepareStatement(checkIfExistsQuery);
             PreparedStatement insertStmt = conn.prepareStatement(insertCustomerQuery)) {

            for (String customer : customers) {
                String[] parts = customer.split("\\|");
                checkStmt.setString(1, parts[1]);  // Ελέγχει αν υπάρχει ήδη το username
                ResultSet rs = checkStmt.executeQuery();
                if (rs.getInt(1) == 0) {  // Εάν δεν υπάρχει ήδη ο πελάτης
                    insertStmt.setString(1, parts[0]);  // Όνομα
                    insertStmt.setString(2, parts[1]);  // Username
                    insertStmt.setString(3, parts[2]);  // Κωδικός πρόσβασης
                    insertStmt.executeUpdate();

                }
            }

        } catch (SQLException e) {
            System.out.println("Error inserting customers.");
            e.printStackTrace();
        }
    }

    // Προσθήκη ραντεβού για πελάτες
    public static void addAppointment(String username, String serviceName, Double cost, String startTime, String endTime) {
        String insertAppointmentQuery = "INSERT INTO appointments (username, servicename,cost, start_time, end_time) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(insertAppointmentQuery)) {
            pstmt.setString(1, username);        // Username
            pstmt.setString(2, serviceName);      // Όνομα υπηρεσίας (servicename)
            pstmt.setDouble(3, cost);
            pstmt.setString(4, startTime);        // Ώρα έναρξης
            pstmt.setString(5, endTime);          // Ώρα λήξης
            pstmt.executeUpdate();

            System.out.println("Appointment added successfully for " + username + " with service " + serviceName + " at " + startTime);

        } catch (SQLException e) {
            System.out.println("Error inserting appointment.");
            e.printStackTrace();
        }
    }

    // Εμφάνιση ραντεβού για συγκεκριμένο χρήστη με το όνομα υπηρεσίας
    public static void showAppointmentsForUser(String username) {
        String query = "SELECT a.start_time, s.name, a.end_time FROM appointments a " +
                       "JOIN services s ON a.servicename = s.name " +
                       "WHERE a.username = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, username);  // Ορίζουμε το username στην προετοιμασία του query
            ResultSet rs = pstmt.executeQuery();  // Εκτέλεση του query

            // Έλεγχος αν υπάρχουν αποτελέσματα για το χρήστη
            if (!rs.isBeforeFirst()) {  // Αν δεν υπάρχουν ραντεβού
                System.out.println("You haven't booked any appointment yet.");
            } else {
                System.out.println("Appointments for " + username + ":");
                while (rs.next()) {
                    String startTime = rs.getString("start_time");
                    String endTime = rs.getString("end_time");
                    String serviceName = rs.getString("name");
                    System.out.println("Service: " + serviceName + ", Time: " + startTime + " to " + endTime);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving appointments. Please try again later.");
            e.printStackTrace();  // Μπορείς να το αφήσεις για debugging, αλλά δεν είναι απαραίτητο για την παραγωγή
        }
    }



}
