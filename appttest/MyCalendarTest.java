
import java.util.List;

public class MyCalendarTest {

    public static void main(String[] args) {
        MyCalendar calendar = new MyCalendar();

        // Add some appointments
        Appointment appointment1 = new Appointment("Meeting", "2024-11-01", "09:00");
        Appointment appointment2 = new Appointment("Lunch", "2024-11-01", "12:00");

        // Attempt to add appointments
        calendar.addAppointment(appointment1);
        calendar.addAppointment(appointment2);

        // Try to add overlapping appointment
        Appointment overlappingAppointment = new Appointment("Conference", "2024-11-01", "09:00");
        calendar.addAppointment(overlappingAppointment); // Should print "Time slot not available."

        // Check available slots on a specific date
        System.out.println("Available slots on 2024-11-01:");
        List<String> availableSlots = calendar.findAvailableSlots("2024-11-01");
        for (String slot : availableSlots) {
            System.out.println(slot);
        }
    }
}
