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
            System.out.print("Choice: ");
            choice = scanner.nextInt();
            
            while (choice !=0 && choice !=1 && choice!=2) {
           System.out.println("Not valid answer.Please try again.");
           System.out.println("1. Admin login");
           System.out.println("2. Customer ");
           System.out.println("0. Exit");
           System.out.print("Choice: ");
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
        System.out.println("===Admin Connection===");
        System.out.print("User name: ");
        String username = scanner.next();
        System.out.print("Password: ");
        String password = scanner.next();

        if (username.equals("admin") && password.equals("1234")) {
            System.out.println("Connection succeed!");
            system.displayServices(); // Εμφάνιση υπηρεσιών για έλεγχο
        } else {
            System.out.println("Wrong username or password.Please try again.");
        }
    }
     
    private void showCustomerMenu(Scanner scanner) {
        System.out.println("=== Customer Menu ===");
        system.displayServices();
        System.out.print("Choose service number: ");
        int serviceChoice = scanner.nextInt();
        Scanner scanner1 = new Scanner(System.in);
        System.out.println("Customer Reservation");

        System.out.print("Name : ");
        String name = scanner1.nextLine();

        System.out.print("Availability range (for example 10-14): ");
        
        String hours = scanner1.nextLine();

        Service selectedService = system.getServiceByIndex(serviceChoice - 1);
        if (selectedService != null) {
            System.out.println("You chose: " + selectedService.getName());
            System.out.println("Cost: " + selectedService.getCost() + "€");
            System.out.println("Duration: " + selectedService.getDuration() + " hours");
            System.out.println("Thank you for choosing us you will be informed tommorow for the exact time of your reservation");
        } else {
            System.out.println("Not valid answer.");
        }
    }
}
   