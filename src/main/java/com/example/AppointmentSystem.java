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

public static void saveAppointments() {
        String query = "INSERT INTO appointments (username, servicename, cost, start_time, end_time) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseHelper.connect();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            conn.setAutoCommit(false);  // Απενεργοποιούμε το auto-commit για βελτιστοποίηση

            for (Customer customer : customers) {  // Εδώ για κάθε πελάτη
                for (Appointment appointment : customer.getAppointments()) {  // Για κάθε ραντεβού του πελάτη
                    pstmt.setString(1, customer.getUsername());  // Εισαγωγή του username
                    pstmt.setString(2, appointment.getServiceName());  // Εισαγωγή του ονόματος της υπηρεσίας
                    pstmt.setDouble(3, appointment.getCost());  // Εισαγωγή της τιμής του ραντεβού (απευθείας ως double)
                    pstmt.setString(4, appointment.getStartTime().toString());  // Εισαγωγή της ώρας έναρξης
                    pstmt.setString(5, appointment.getEndTime().toString());  // Εισαγωγή της ώρας λήξης
                    pstmt.addBatch();  // Προσθέτουμε το ραντεβού στο batch
                }
            }

            pstmt.executeBatch();  // Εκτελούμε το batch
            conn.commit();  // Κάνουμε commit της συναλλαγής
            System.out.println("Appointments saved successfully!");
        } catch (SQLException e) {
            System.out.println("Error saving appointments.");
            e.printStackTrace();
        }
    }


    

    // Είσοδος πελάτη στο σύστημα
    public static void customerLogin(Scanner scanner) {
        loadCustomers();  // Φορτώνει όλους τους πελάτες από τη βάση δεδομένων

        while (true) {
            System.out.print("\nEnter your username: ");
            String username = scanner.nextLine();
            System.out.print("Enter your password: ");
            String password = scanner.nextLine();

            boolean found = false;
            for (Customer customer : customers) {
                // Στην περίπτωση που δεν χρησιμοποιούμε κρυπτογράφηση
                if (customer.getUsername().equals(username) && customer.getPassword().equals(password)) {
                    System.out.println("Login successful! Welcome, " + customer.getName() + "!");
                    // Εδώ καλούμε το μενού του πελάτη
                    Menu.showCustomerMenu(customer, scanner);
                    found = true;
                    break;
                }
            }
    
            // Εάν βρεθεί ο χρήστης, επιστρέφουμε
            if (found) {
                return;
            } else {
                System.out.println("Incorrect username or password. Please try again.");
            }
        }
    }
    




    // Εγγραφή πελάτη στο σύστημα
    public static void registerCustomer(Scanner scanner) {
        System.out.print("\nEnter your name: ");
        String name = scanner.nextLine();

        String username;
        boolean usernameTaken;

        do {
            System.out.print("Enter a username: ");
            username = scanner.nextLine();

            usernameTaken = false;
            for (Customer customer : customers) {
                if (customer.getUsername().equals(username)) {
                    usernameTaken = true;
                    System.out.println("This username already exists. Please try again.");
                    break;
                }
            }
        } while (usernameTaken);

        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        // Δεν κάνουμε κρυπτογράφηση του κωδικού
        String encryptedPassword = password;  // Απλά αποθηκεύουμε τον κωδικό όπως είναι

        Customer newCustomer = new Customer(name, username, encryptedPassword);
        customers.add(newCustomer);
        System.out.println("Registration was successful!");
        saveCustomers();
        Menu.showCustomerMenu(newCustomer, scanner);
    }


    // Είσοδος διαχειριστή
    public static void adminLogin(Scanner scanner) {
        while (true) {
            System.out.print("\nEnter admin username: ");
            String username = scanner.nextLine();
            System.out.print("Enter admin password: ");
            String password = scanner.nextLine();

            if (username.equals("admin") && password.equals("admin123")) {
                System.out.println("Admin login successful.");
                Menu.showAdminMenu(scanner);
                return;
            } else {
                System.out.println("Invalid admin credentials. Please try again.");
            }
        }
    }

