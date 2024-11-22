import java.util.ArrayList;
import java.util.List;

public class AppointmentSystem {
    private final List<Service> services = new ArrayList<>();

    // Προσθήκη υπηρεσίας από τον διαχειριστή
    public void addService(Service service) {
        services.add(service);
    }

    // Εμφάνιση όλων των υπηρεσιών
    public void displayServices() {
        System.out.println("Διαθέσιμες Υπηρεσίες:");
        for (int i = 0; i < services.size(); i++) {
            Service service = services.get(i);
            System.out.println((i + 1) + ". " + service.getName() + " - " 
                               + service.getCost() + "€ - " 
                               + service.getDuration() + " ώρες");
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
