import java.util.ArrayList;
import java.util.List;

public class AppointmentSystem {
    private final List<Service> services = new ArrayList<>();
    private final List<User> users = new ArrayList<>();
    // Προσθήκη υπηρεσίας από τον διαχειριστή
    public void addService(Service service) {
        services.add(service);
    }
    // Προσθήκη χρήστη
    public void addUser(User user) {
        users.add(user);         
    }
    // Επιστροφή χρηστών
    public List<User> getUsers() {
        return users;
    }
    // Εμφάνιση όλων των υπηρεσιών
    public void displayServices() {
        System.out.println("Available Services:");
        for (int i = 0; i < services.size(); i++) {
            Service service = services.get(i);
            System.out.println((i + 1) + ". " + service.getName() + " - " 
                               + service.getCost() + "€ - " 
                               + service.getDuration() + " hours");
        }
    }

    // Λήψη υπηρεσίας βάσει επιλογής χρήστη
    public Service getServiceByIndex(int index) {
        if (index >= 0 && index < services.size()) {
            return services.get(index);
        } else {
            return null; // Αν η επιλογή είναι εκτός ορίων
        }
    }
}
