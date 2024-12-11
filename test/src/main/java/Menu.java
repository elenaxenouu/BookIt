import java.util.Scanner;
import java.time.LocalDate;
import java.util.List;

public class Menu {
    private final AppointmentSystem system; // Διαχείριση υπηρεσιών και χρηστών
    private final UserDAO userDAO;         // Αλληλεπίδραση με τη βάση δεδομένων

    // Κατασκευαστής
    public Menu(AppointmentSystem system, UserDAO userDAO) {
        this.system = system;
        this.userDAO = userDAO;
    }

    // Εμφάνιση Κεντρικού Μενού
    public void showMainMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("=== Welcome to BookIt ===");
            System.out.println("1. Admin login");
            System.out.println("2. Customer");
            System.out.println("0. Exit");
            System.out.print("Select an option: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1: 
                   showAdminMenu(scanner);
                   break;
                case 2: 
                   showCustomerMenu(scanner);
                   break;
                case 0: 
                   System.out.println("Goodbye!");
                   break;
                default:
                   System.out.println("Invalid choice. Please try again.");
                   break;
            }
        } while (choice != 0);
    }

    // Μενού Διαχειριστή
    private void showAdminMenu(Scanner scanner) {
        System.out.println("=== Admin Login ===");
        System.out.print("Username: ");
        String username = scanner.next();
        System.out.print("Password: ");
        String password = scanner.next();

        if (username.equals("admin") && password.equals("1234")) {
            System.out.println("Login successful!");
            adminActions(scanner);
        } else {
            System.out.println("Invalid credentials.");
        }
    }

    // Ενέργειες Διαχειριστή
    private void adminActions(Scanner scanner) {
        int choice;
        do {
            System.out.println("=== Admin Menu ===");
            System.out.println("1. View all services");
            System.out.println("2. View all users");
            System.out.println("0. Logout");
            System.out.print("Select an option: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1: 
                  system.displayServices();
                  break;
                case 2:
                  viewAllUsers();
                  break;
                case 0:
                  System.out.println("Logging out...");
                  break;
                default: 
                  System.out.println("Invalid choice. Please try again.");
                  break; 
            }
        } while (choice != 0);
    }

    // Εμφάνιση όλων των χρηστών
    private void viewAllUsers() {
        try {
            List<User> users = userDAO.getUsers();
            if (users.isEmpty()) {
                System.out.println("No users found.");
            } else {
                System.out.println("=== Registered Users ===");
                users.forEach(System.out::println);
            }
        } catch (Exception e) {
            System.out.println("Error retrieving users: " + e.getMessage());
        }
    }

    // Μενού Πελάτη
    private void showCustomerMenu(Scanner scanner) {
        System.out.println("=== Customer Menu ===");
        system.displayServices();
        System.out.print("Select a service (1-N): ");
        int serviceChoice = scanner.nextInt();

        Service selectedService = system.getServiceByIndex(serviceChoice - 1);
        if (selectedService == null) {
            System.out.println("Invalid service selection.");
            return;
        }

        scanner.nextLine(); // Καθαρισμός scanner buffer
        System.out.print("Enter your full name: ");
        String name = scanner.nextLine();
        System.out.print("Enter availability range (e.g., 10-14): ");
        String hours = scanner.nextLine();

        if (!hours.matches("\\d{1,2}-\\d{1,2}")) {
            System.out.println("Invalid time range format. Use the format '10-14'.");
            return;
        }

        String[] hourRange = hours.split("-");
        int startTime = Integer.parseInt(hourRange[0]);

        User newUser = new User(name, hours, selectedService, LocalDate.now(), startTime);
        try {
            userDAO.addUser(newUser);
            System.out.println("Your booking has been registered. You will be notified about the exact time.");
        } catch (Exception e) {
            System.out.println("Error registering booking: " + e.getMessage());
        }
    }
}
