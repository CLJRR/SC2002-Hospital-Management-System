package AppointmentSystem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Timeslot {

    private static List<String> timeslot;

    // Static initializer block to initialize timeslots only once
    static {
        timeslot = new ArrayList<>();
        timeslot.add("09:00");
        timeslot.add("10:00");
        timeslot.add("11:00");

        timeslot.add("13:00");
        timeslot.add("14:00");
        timeslot.add("15:00");
        timeslot.add("16:00");
        timeslot.add("17:00");
    }

    // Return an unmodifiable list to prevent external modifications
    public static List<String> getTimeslot() {
        return Collections.unmodifiableList(timeslot);
    }

    // Replace the timeslot list (if needed)
    public static void setTimeslot(List<String> newTimeslot) {
        timeslot = new ArrayList<>(newTimeslot);
    }
}
