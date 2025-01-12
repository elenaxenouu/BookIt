package com.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class AppointmentSystem {

    private static ArrayList<Customer> customers = new ArrayList<>();
    private static ArrayList<Service> services = new ArrayList<>();

    // Φόρτωμα των υπηρεσιών από τη βάση δεδομένων


public static void loadServices() {
    services.clear();  // Καθαρίζει τη λίστα πριν την ξαναγεμίσει
    String query = "SELECT * FROM services";

    try (Connection conn = DatabaseHelper.connect(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
        while (rs.next()) {

            String name = rs.getString("name");
            double cost = rs.getDouble("cost");
            services.add(new Service( name, cost));  // Προσθήκη της υπηρεσίας στη λίστα
        }
    } catch (SQLException e) {
        System.out.println("Error loading services.");
        e.printStackTrace();
    }
}



    // Φόρτωμα πελατών από τη βάση
    public static void loadCustomers() {
        String query = "SELECT * FROM customers";
        try (Connection conn = DatabaseHelper.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                String name = rs.getString("name");
                String username = rs.getString("username");
                String password = rs.getString("password");

                Customer customer = new Customer(name, username, password);
                customers.add(customer);
            }
        } catch (SQLException e) {
            System.out.println("Error loading customers.");
            e.printStackTrace();
        }

        // Ελέγξτε αν οι πελάτες έχουν φορτωθεί σωστά
        if (customers.isEmpty()) {
            System.out.println("No customers loaded.");
        }
    }


    // Αποθήκευση πελατών στη βάση
    public static void saveCustomers() {
        String query = "INSERT OR REPLACE INTO customers (name, username, password) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseHelper.connect();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            // Ενεργοποίηση της αυτόματης αποθήκευσης (commit)
            conn.setAutoCommit(false); // Για να ελέγξετε την αποθήκευση με batch

            for (Customer customer : customers) {
                pstmt.setString(1, customer.getName());
                pstmt.setString(2, customer.getUsername());
                pstmt.setString(3, customer.getPassword());
                pstmt.addBatch(); // Προσθήκη στο batch
            }

            // Εκτέλεση του batch
            pstmt.executeBatch();

            // Επιβεβαίωση των αλλαγών
            conn.commit(); // Κανονική αποθήκευση

        } catch (SQLException e) {
            System.out.println("Error saving customers.");
            e.printStackTrace();
        } finally {
            try {
                // Επαναφορά του autoCommit στο κανονικό του
                Connection conn = DatabaseHelper.connect();
                conn.setAutoCommit(true); // Επιστροφή στο κανονικό autoCommit
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
