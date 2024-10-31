
import java.util.ArrayList;
import java.util.List;

public class Appointment {

    private String title;
    private String date;
    private String timeSlot; // Time slot should be one of the predefined slots
    // Define available time slots from 9 AM to 5 PM
    private static final List<String> TIME_SLOTS = new ArrayList<>();

    static {
        int startHour = 9; // 9 AM
        int endHour = 17;  // 5 PM
        for (int hour = startHour; hour < endHour; hour++) {
            TIME_SLOTS.add(String.format("%02d:00", hour));
        }
    }

    // Constructor to create an appointment with title, date, and specific time slot
    public Appointment(String title, String date, String timeSlot) {
        if (!TIME_SLOTS.contains(timeSlot)) {
            throw new IllegalArgumentException("Invalid time slot. Choose a time between 09:00 and 16:00.");
        }
        this.title = title;
        this.date = date;
        this.timeSlot = timeSlot;
    }

    // Getters
    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    // Static method to get all available time slots
    public static List<String> getAvailableTimeSlots() {
        return TIME_SLOTS;
    }

    // Override toString for easy display
    @Override
    public String toString() {
        return "Appointment [Title: " + title + ", Date: " + date + ", Time Slot: " + timeSlot + "]";
    }
}
