
public class Main {
    public static void main(String[] args) {
        AppointmentSystem system = new AppointmentSystem();

        // Προσθήκη υπηρεσιών από τον διαχειριστή
        system.addService(new Service("Κούρεμα", 15.0, 1));
        system.addService(new Service("Βαφή Μαλλιών", 50.0, 3));
        system.addService(new Service("Μανικιούρ", 20.0, 1));

        // Εμφάνιση διαθέσιμων υπηρεσιών στον πελάτη
        system.displayServices();

        // Προσομοίωση επιλογής από τον χρήστη
        int userChoice = 2; // Ο χρήστης επιλέγει τη 2η υπηρεσία (Βαφή Μαλλιών)
        Service selectedService = system.getServiceByIndex(userChoice - 1);

        if (selectedService != null) {
            System.out.println("Επιλέξατε: " + selectedService.getName());
        } else {
            System.out.println("Μη έγκυρη επιλογή!");
        }
    }
}
