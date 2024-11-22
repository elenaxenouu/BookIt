public class User {
    private String name;
    private int availableHours; // Τύπος int για διαθέσιμες ώρες
    private Service selectedService; // Αποθήκευση αντικειμένου Service

    public User(String name, int availableHours, Service selectedService) {
        this.name = name;
        this.availableHours = availableHours;
        this.selectedService = selectedService;
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getAvailableHours() {
        return availableHours;
    }

    public Service getSelectedService() {
        return selectedService;
    }
}
