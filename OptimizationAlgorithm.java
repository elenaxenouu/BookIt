//Για να λειτουργήσειγ πρέπει να δημιουργηθούν τα αρχεία TimeSlot.java;Booking.java;Shedule.java 
//+ scheduleSolverConfig.xml + ScheduleConstraintProvider.java (για τους περιορισμούς)

import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;

import java.util.ArrayList;
import java.util.List;

public class OptimizationAlgorithm {
    public void optimizeSchedule(List<User> users, int maxHoursPerDay) {
        // Δημιουργία SolverFactory από το configuration του OptaPlanner
        SolverFactory<Schedule> solverFactory = SolverFactory.createFromXmlResource("scheduleSolverConfig.xml");
        Solver<Schedule> solver = solverFactory.buildSolver();

        // Δημιουργία TimeSlots (π.χ. 10:00 - 18:00)
        List<TimeSlot> timeSlots = new ArrayList<>();
        for (int i = 9; i < 18; i++) {
            timeSlots.add(new TimeSlot(i, i + 1));
        }

        // Δημιουργία κρατήσεων (Booking) από τους χρήστες
        List<Booking> bookings = new ArrayList<>();
        for (User user : users) {
            bookings.add(new Booking(user));
        }

        // Δημιουργία προβλήματος (Schedule)
        Schedule unsolvedSchedule = new Schedule();
        unsolvedSchedule.setBookingList(bookings);
        unsolvedSchedule.setTimeSlotList(timeSlots);

        // Επίλυση προβλήματος
        Schedule solvedSchedule = solver.solve(unsolvedSchedule);

        // Εμφάνιση αποτελεσμάτων
        for (Booking booking : solvedSchedule.getBookingList()) {
            System.out.println(booking);
        }
    }
}
