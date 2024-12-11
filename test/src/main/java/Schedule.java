import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;

import java.util.List;
//Ορίζει το πρόβλημα που θα λύσει το OptaPlanner.Περιλαμβάνει λίστες κρατήσεων και χρονικών διαστημάτων.
@PlanningSolution
public class Schedule {
    private List<Booking> bookingList;

    @ValueRangeProvider(id = "timeSlotRange")
    private List<TimeSlot> timeSlotList;

    private HardSoftScore score;
    
// Getters and Setters
    public List<Booking> getBookingList() {
        return bookingList;
    }

    public void setBookingList(List<Booking> bookingList) {
        this.bookingList = bookingList;
    }

    public List<TimeSlot> getTimeSlotList() {
        return timeSlotList;
    }

    public void setTimeSlotList(List<TimeSlot> timeSlotList) {
        this.timeSlotList = timeSlotList;
    }

    public HardSoftScore getScore() {
        return score;
    }

    public void setScore(HardSoftScore score) {
        this.score = score;
    }
}
