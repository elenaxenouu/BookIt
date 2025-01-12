package com.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        DatabaseHelper.createTables();

        Scanner scanner = new Scanner(System.in);

        // Δημιουργία των πινάκων αν δεν υπάρχουν ήδη
        DatabaseHelper.createTables(); // Προσθήκη της κλήσης της createTables()
        DatabaseHelper.addStaticServices();
        DatabaseHelper.addStaticCustomers();

        // Φόρτωση δεδομένων συστήματος

        AppointmentSystem.loadCustomers();
       AppointmentSystem.loadAppointments();


        // Εκκίνηση του κύριου μενού
        Menu.showMainMenu(scanner);




        System.out.println("Exiting the system. Thank you!");
    }
}
