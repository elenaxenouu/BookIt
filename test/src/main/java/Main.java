import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Δημιουργία υπηρεσιών
        List<Service> services = new ArrayList<>();
        services.add(new Service("Massage", 50.0, 2));
        services.add(new Service("Facial", 30.0, 1));

        // Δημιουργία κρατήσεων
        List<Booking> bookings = new ArrayList<>();
        bookings.add(new Booking(new User("John Doe", "10-14", services.get(0), LocalDate.now(), 10)));
        bookings.add(new Booking(new User("Jane Smith", "12-16", services.get(1), LocalDate.now(), 12)));

        // Χρονικά διαστήματα
        List<TimeSlot> timeSlots = new ArrayList<>();
        timeSlots.add(new TimeSlot(10, 12));
        timeSlots.add(new TimeSlot(12, 14));

        // Εκτέλεση βελτιστοποίησης
        OptimizationAlgorithm algorithm = new OptimizationAlgorithm();
        Schedule optimizedSchedule = algorithm.optimizeSchedule(bookings, timeSlots);

        // Εμφάνιση αποτελεσμάτων
        System.out.println("Optimized Schedule:");
        optimizedSchedule.getBookingList().forEach(System.out::println);
    }
}
