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
        
            System.out.println("Welcome to reservation system!");
            System.out.println("1. Admin login");
            System.out.println("2. Customer ");
            System.out.println("0. Exit");
            choice = scanner.nextInt();
            // Ελεγχος εγκυροτητας στοιχειων εισοδου 
            while (choice !=0 && choice !=1 && choice!=2) {
           System.out.println("Not valid answer.Please try again.");
           System.out.println("1. Admin login");
           System.out.println("2. Customer ");
           System.out.println("0. Exit");
           choice = scanner.nextInt();
            }
            switch (choice) {
                case 1:
                    showAdminMenu(scanner);
                    break;
                case 2:
                    showCustomerMenu(scanner);
                    break;
                case 0:
                    System.out.println("You're offline.");
                    break;
            
                  
            }
        
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
