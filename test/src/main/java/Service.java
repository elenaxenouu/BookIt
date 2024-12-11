public class Service {
    private String name;
    private double cost;
    private int duration; // Διάρκεια σε ώρες

    public Service(String name, double cost, int duration) {
        this.name = name;
        this.cost = cost;
        this.duration = duration;
    }

    // Getters and Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Service{" +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", duration=" + duration +
                '}';
    }
}