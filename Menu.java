import java.util.Scanner;

public class Menu {
    private final AppointmentSystem system; // Αναφορά στο AppointmentSystem για χρήση υπηρεσιών

    // Κατασκευαστής
    public Menu(AppointmentSystem system) {
        this.system = system;
    }

    // Κεντρικό μενού
    public void showMainMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("Καλώς ήρθατε στο Σύστημα Κρατήσεων!");
            System.out.println("1. Σύνδεση Διαχειριστή");
            System.out.println("2. Είσοδος Πελάτη");
            System.out.println("0. Έξοδος");
            System.out.print("Επιλογή: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    showAdminMenu(scanner);
                    break;
                case 2:
                    showCustomerMenu(scanner);
                    break;
                case 0:
                    System.out.println("Έξοδος από το πρόγραμμα.");
                    break;
                default:
                    System.out.println("Μη έγκυρη επιλογή. Προσπαθήστε ξανά.");
            }
        } while (choice != 0);
    }

    // Μενού Διαχειριστή
    private void showAdminMenu(Scanner scanner) {
        System.out.println("=== Σύνδεση Διαχειριστή ===");
        System.out.print("Όνομα χρήστη: ");
        String username = scanner.next();
        System.out.print("Κωδικός πρόσβασης: ");
        String password = scanner.next();

        if (username.equals("admin") && password.equals("1234")) {
            System.out.println("Επιτυχής σύνδεση!");
            system.displayServices(); // Εμφάνιση υπηρεσιών για έλεγχο
        } else {
            System.out.println("Λάθος όνομα χρήστη ή κωδικός πρόσβασης.");
        }
    }

    // Μενού Πελάτη
    private void showCustomerMenu(Scanner scanner) {
        System.out.println("=== Μενού Πελάτη ===");
        system.displayServices();
        System.out.print("Επιλέξτε αριθμό υπηρεσίας: ");
        int serviceChoice = scanner.nextInt();
        Scanner scanner1 = new Scanner(System.in);
        System.out.println("Κρατήσεις Πελατών");

        System.out.print("Όνοματεπώνυμο: ");
        String name = scanner1.nextLine();

        System.out.print("Διαθέσιμο εύρος ωρών (π.χ. 10-14): ");
        
        String hours = scanner1.nextLine();

        Service selectedService = system.getServiceByIndex(serviceChoice - 1);
        if (selectedService != null) {
            System.out.println("Επιλέξατε: " + selectedService.getName());
            System.out.println("Κόστος: " + selectedService.getCost() + "€");
            System.out.println("Διάρκεια: " + selectedService.getDuration() + " ώρες");
            System.out.println("Ευχαριστούμε που μας επιλέξατε θα ενημερωθείτε αύριο για την ακριβή ώρα του ραντεβού σας");
        } else {
            System.out.println("Μη έγκυρη επιλογή.");
        }
    }
}
