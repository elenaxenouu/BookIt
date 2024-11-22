public class Service {
    private final String name;     // Όνομα της υπηρεσίας
    private final double cost;     // Κόστος της υπηρεσίας
    private final int duration;    // Διάρκεια σε ώρες

    // Κατασκευαστής (προσθήκη υπηρεσίας)
    public Service(String name, double cost, int duration) {
        this.name = name;
        this.cost = cost;
        this.duration = duration;
    }

    // Getters
    public String getName() {
        return name;
    }

    public double getCost() {
        return cost;
    }

    public int getDuration() {
        return duration;
    }
}
