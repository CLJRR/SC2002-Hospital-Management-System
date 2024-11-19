/**
 * The {@code Timeslot} class provides a list of predefined timeslots for scheduling appointments.
 * It includes methods to access and update the list of timeslots.
 */
package AppointmentSystem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Timeslot {

    /**
     * A static list of predefined timeslots.
     */
    private static List<String> timeslot;

    /**
     * Static initializer block to initialize the timeslots with default values.
     * The timeslots include common working hours for scheduling.
     */
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

    /**
     * Returns an unmodifiable list of predefined timeslots to prevent external modifications.
     *
     * @return an unmodifiable {@code List<String>} of timeslots
     */
    public static List<String> getTimeslot() {
        return Collections.unmodifiableList(timeslot);
    }

    /**
     * Replaces the current list of timeslots with a new list.
     * This method creates a copy of the provided list to maintain encapsulation.
     *
     * @param newTimeslot a new list of timeslots to replace the existing list
     */
    public static void setTimeslot(List<String> newTimeslot) {
        timeslot = new ArrayList<>(newTimeslot);
    }
}
