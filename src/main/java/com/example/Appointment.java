package com.example;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Appointment {
    private String customerUsername; // Το username του πελάτη στον οποίο ανήκει το ραντεβού
    // Το ID της υπηρεσίας για το ραντεβού
    private String serviceName; // Όνομα υπηρεσίας
    private double cost; // Κόστος υπηρεσίας

    private LocalTime startTime; // Ώρα έναρξης
    private LocalTime endTime; // Ώρα λήξης

    // Κατασκευαστής για τη δημιουργία νέου ραντεβού
    public Appointment(String customerUsername,  String serviceName, double cost,  LocalTime startTime, LocalTime endTime) {
        this.customerUsername = customerUsername;

        this.serviceName = serviceName;
        this.cost = cost;

        this.startTime = startTime;
        this.endTime = endTime;
    }

    // Μέθοδος για να πάρουμε το username του πελάτη
    public String getCustomerUsername() {
        return customerUsername;
    }

    // Μέθοδος για να πάρουμε το ID της υπηρεσίας


    // Μέθοδος για να πάρουμε το όνομα της υπηρεσίας
    public String getServiceName() {
        return serviceName;
    }

    // Μέθοδος για να πάρουμε το κόστος της υπηρεσίας
    public double getCost() {
        return cost;
    }



    // Μέθοδος για να πάρουμε την ώρα έναρξης
    public LocalTime getStartTime() {
        return startTime;
    }

    // Μέθοδος για να πάρουμε την ώρα λήξης
    public LocalTime getEndTime() {
        return endTime;
    }

    // Μέθοδος για την αναπαράσταση του ραντεβού σε μορφή κειμένου
    @Override
    public String toString() {
        return "Appointment for " + serviceName + " from " + startTime.format(DateTimeFormatter.ofPattern("HH:mm")) + " to " + endTime.format(DateTimeFormatter.ofPattern("HH:mm")) + " - Cost: " + cost + "€";
    }
}
