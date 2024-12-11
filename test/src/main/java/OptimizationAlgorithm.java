import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;

import java.util.List;

public class OptimizationAlgorithm {
    public Schedule optimizeSchedule(List<Booking> bookings, List<TimeSlot> timeSlots) {
        SolverFactory<Schedule> solverFactory = SolverFactory.createFromXmlResource("scheduleSolverConfig.xml");
        Solver<Schedule> solver = solverFactory.buildSolver();

        Schedule unsolvedSchedule = new Schedule();
        unsolvedSchedule.setBookingList(bookings);
        unsolvedSchedule.setTimeSlotList(timeSlots);

        // Εύρεση λύσης
        return solver.solve(unsolvedSchedule);
    }
}
