import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.variable.PlanningVariable;

@PlanningEntity
public class Booking {

    private int id; // Προστέθηκε ID για χρήση στους περιορισμούς
    private User user;

    @PlanningVariable(valueRangeProviderRefs = "timeSlotRange")
    private TimeSlot assignedTimeSlot;

    // Κατασκευαστής
    public Booking(User user) {
        this.user = user;
    }

    // Getters και Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public TimeSlot getAssignedTimeSlot() {
        return assignedTimeSlot;
    }

    public void setAssignedTimeSlot(TimeSlot assignedTimeSlot) {
        this.assignedTimeSlot = assignedTimeSlot;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", user=" + user +
                ", assignedTimeSlot=" + assignedTimeSlot +
                '}';
    }
}
