import org.optaplanner.core.api.score.stream.Constraint;
import org.optaplanner.core.api.score.stream.ConstraintFactory;
import org.optaplanner.core.api.score.stream.ConstraintProvider;
import org.optaplanner.core.api.score.stream.Joiners;

public class ScheduleConstraintProvider implements ConstraintProvider {

    @Override
    public Constraint[] defineConstraints(ConstraintFactory factory) {
        return new Constraint[]{
            noOverlappingBookings(factory),
            withinWorkingHours(factory)
        };
    }

    // Περιορισμός: Να μην επικαλύπτονται τα ραντεβού
    @SuppressWarnings("removal")
    private Constraint noOverlappingBookings(ConstraintFactory factory) {
        return factory.forEach(Booking.class)
            .join(Booking.class,
                  Joiners.equal(Booking::getAssignedTimeSlot),
                  Joiners.lessThan(Booking::getId))
            .penalizeConfigurable("No overlapping bookings", (b1, b2) -> 1);
    }

    // Περιορισμός: Τα ραντεβού να είναι εντός ωραρίου
    @SuppressWarnings("removal")
    private Constraint withinWorkingHours(ConstraintFactory factory) {
        return factory.forEach(Booking.class)
            .filter(booking -> booking.getAssignedTimeSlot().getEndHour() > 21)
            .penalizeConfigurable("Booking exceeds working hours", booking -> 1);
    }
}