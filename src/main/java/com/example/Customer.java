package com.example;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class Customer {

    private String name;
    private String username;
    private String password;
    private ArrayList<Appointment> appointments;

    // Κατασκευαστής για νέους πελάτες χωρίς id (όπως κατά την εγγραφή)
    public Customer(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.appointments = new ArrayList<>();
    }




   // Μέθοδος για να προσθέσουμε ραντεβού για τον πελάτη και να το αποθηκεύσουμε στη βάση
   public void addAppointment(Appointment appointment) {
    this.appointments.add(appointment);  // Προσθήκη του ραντεβού στον πελάτη

    // Αποθήκευση του ραντεβού στη βάση δεδομένων
    String query = "INSERT INTO appointments (username, servicename, cost, start_time, end_time) VALUES (?, ?, ?, ?, ?)";

    try (Connection conn = DatabaseHelper.connect();
         PreparedStatement pstmt = conn.prepareStatement(query)) {

        pstmt.setString(1, this.username);  // Username του πελάτη
        pstmt.setString(2, appointment.getServiceName());  // Όνομα υπηρεσίας
        pstmt.setDouble(3, appointment.getCost());  // Κόστος
        pstmt.setString(4, appointment.getStartTime().toString());  // Ώρα έναρξης
        pstmt.setString(5, appointment.getEndTime().toString());  // Ώρα λήξης

        pstmt.executeUpdate();  // Εκτέλεση της εντολής για αποθήκευση στη βάση

    } catch (SQLException e) {
        System.out.println("Error saving appointment.");
        e.printStackTrace();
    }
}



    // Μέθοδος για να πάρουμε τα ραντεβού του πελάτη
    public ArrayList<Appointment> getAppointments() {
        return this.appointments;
    }

    // Μέθοδος για να ελέγξουμε αν ο πελάτης έχει ραντεβού
    public boolean hasAppointments() {
        return !appointments.isEmpty();
    }

    // Μέθοδος για να πάρουμε το όνομα του πελάτη
    public String getName() {
        return this.name;
    }

    // Μέθοδος για να πάρουμε το username του πελάτη
    public String getUsername() {
        return this.username;
    }

    // Μέθοδος για να πάρουμε τον κωδικό πρόσβασης του πελάτη
    public String getPassword() {
        return this.password;
    }


    // Μέθοδος για να ενημερώσουμε το όνομα του πελάτη
    public void setName(String name) {
        this.name = name;
    }

    // Μέθοδος για να ενημερώσουμε το password του πελάτη
    public void setPassword(String password) {
        this.password = password;
    }

    // Μέθοδος για να ελέγξουμε αν το password είναι το ίδιο
    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }

    @Override
    public String toString() {
        return " Name: " + name + ", Username: " + username;
    }
}
