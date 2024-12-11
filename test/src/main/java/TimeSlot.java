//Αναπαριστά τα χρονικά διαστήματα (π.χ. 10:00 - 11:00). Χρησιμοποιείται ως μεταβλητή κατά τη βελτιστοποίηση.
public class TimeSlot {
    private final int startHour;
    private final int endHour;

    public TimeSlot(int startHour, int endHour) {
        this.startHour = startHour;
        this.endHour = endHour;
    }

    public int getStartHour() {
        return startHour;
    }

    public int getEndHour() {
        return endHour;
    }

    @Override
    public String toString() {
        return startHour + ":00 - " + endHour + ":00";
    }
}