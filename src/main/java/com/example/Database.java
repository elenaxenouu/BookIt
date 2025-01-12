package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Database {

    private static final String DB_URL = "jdbc:sqlite:appointments.db";

    // Μέθοδος για σύνδεση με τη βάση δεδομένων
    public static Connection connect() {
        try {
            return DriverManager.getConnection(DB_URL);
        } catch (Exception e) {
            System.out.println("Connection failed: " + e.getMessage());
            return null;
        }
    }

    // Μέθοδος για ανάγνωση δεδομένων από τη βάση (π.χ. να πάρεις τους χρήστες)
    public static void getUsers() {
        try (Connection conn = connect(); Statement stmt = conn.createStatement()) {
            String query = "SELECT * FROM Customer";
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                System.out.println("Username: " + rs.getString("username"));
                System.out.println("First Name: " + rs.getString("firstName"));
                System.out.println("Last Name: " + rs.getString("lastName"));
            }
        } catch (Exception e) {
            System.out.println("Error reading from database: " + e.getMessage());
        }
    }
}
