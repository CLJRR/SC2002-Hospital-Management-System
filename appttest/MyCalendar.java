
import java.util.ArrayList;
import java.util.List;

public class MyCalendar {

    private final TxtDatabaseService databaseService = new TxtDatabaseService();

    // Method to add an appointment after checking for overlap
    public boolean addAppointment(Appointment appointment) {
        if (isTimeSlotAvailable(appointment.getDate(), appointment.getTimeSlot())) {
            boolean saved = databaseService.saveAppointment(appointment);
            if (saved) {
                System.out.println("Appointment saved successfully.");
                return true;
            } else {
                System.out.println("Failed to save appointment.");
                return false;
            }
        } else {
            System.out.println("Time slot not available.");
            return false;
        }
    }

    // Method to find available slots for a specified date
    public List<String> findAvailableSlots(String date) {
        List<String> availableSlots = new ArrayList<>(Appointment.getAvailableTimeSlots());
        List<Appointment> appointmentsOnDate = databaseService.getAppointmentsByDate(date);

        for (Appointment appointment : appointmentsOnDate) {
            availableSlots.remove(appointment.getTimeSlot());
        }
        return availableSlots;
    }

    // Method to check if a time slot is available on a specified date
    public boolean isTimeSlotAvailable(String date, String timeSlot) {
        List<Appointment> appointmentsOnDate = databaseService.getAppointmentsByDate(date);
        for (Appointment appointment : appointmentsOnDate) {
            if (appointment.getTimeSlot().equals(timeSlot)) {
                return false; // Time slot is occupied
            }
        }
        return true; // Time slot is available
    }

    // View all appointments for testing and verification
    public void viewAppointments() {
        List<Appointment> appointments = databaseService.loadAppointments();
        for (Appointment appointment : appointments) {
            System.out.println(appointment);
        }
    }
}
