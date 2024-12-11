import java.time.LocalDate;

public class User {
    private String name;
    private String availableHours; // Τύπος String για διαθέσιμες ώρες π.χ. "10-14"
    private Service selectedService; // Αποθήκευση αντικειμένου Service, επιλεγμένη υπηρεσία
    private LocalDate dateOfRegistration; // Ημερομηνία εγγραφής του χρήστη
    private int startTime; // Μικρότερη ώρα διαθεσιμότητας (π.χ. 10)

    // Κατασκευαστής
    public User(String name, String availableHours, Service selectedService, LocalDate dateOfRegistration, int startTime) {
        this.name = name;
        this.availableHours = availableHours;
        this.selectedService = selectedService;
        this.dateOfRegistration = dateOfRegistration;
        this.startTime = startTime;
    }

    // Getters και Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvailableHours() {
        return availableHours;
    }

    public void setAvailableHours(String availableHours) {
        this.availableHours = availableHours;
    }

    public Service getSelectedService() {
        return selectedService;
    }

    public void setSelectedService(Service selectedService) {
        this.selectedService = selectedService;
    }

    public LocalDate getDateOfRegistration() {
        return dateOfRegistration;
    }

    public void setDateOfRegistration(LocalDate dateOfRegistration) {
        this.dateOfRegistration = dateOfRegistration;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    // toString() για εύκολη απεικόνιση αντικειμένων
    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", availableHours='" + availableHours + '\'' +
                ", selectedService=" + selectedService +
                ", dateOfRegistration=" + dateOfRegistration +
                ", startTime=" + startTime +
                '}';
    }
}
