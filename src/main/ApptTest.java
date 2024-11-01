
import entity.Appointment;
import enums.Availability;
import java.time.LocalDate;
import java.util.List;
import service.*;

public class ApptTest {

    public static void main(String[] args) {
        AppointmentService databaseService = new AppointmentService();

        // 1. Unique appointment, doctor available
        Appointment appointment1 = new Appointment("A010", "P001", "D001", LocalDate.of(2024, 11, 5), "06:00", Availability.APPOINTMENT);
        databaseService.save(appointment1);

        // 2. Duplicate appointment ID, should not save
        Appointment appointment2 = new Appointment("A001", "P002", "D002", LocalDate.of(2024, 11, 6), "10:00", Availability.APPOINTMENT);
        databaseService.save(appointment2);

        // 3. Doctor unavailable at the time slot, should not save
        Appointment appointment3 = new Appointment("A003", "P003", "D001", LocalDate.of(2024, 11, 5), "09:00", Availability.APPOINTMENT);
        databaseService.save(appointment3);

        // 4. Appointment with doctor on leave, should save as ONLEAVE
        Appointment appointment4 = new Appointment("L001", null, "D002", LocalDate.of(2024, 11, 6), "09:00", Availability.ONLEAVE);
        databaseService.save(appointment4);

        // 5. Unique appointment, same doctor available at different time slot
        Appointment appointment5 = new Appointment("A004", "P004", "D001", LocalDate.of(2024, 11, 5), "11:00", Availability.APPOINTMENT);
        databaseService.save(appointment5);

        // 6. Unique appointment, different doctor at the same date and time slot
        Appointment appointment6 = new Appointment("A005", "P005", "D003", LocalDate.of(2024, 11, 5), "09:00", Availability.APPOINTMENT);
        databaseService.save(appointment6);

        // 7. Doctor unavailable due to ONLEAVE at the same time slot
        Appointment appointment7 = new Appointment("A006", "P006", "D002", LocalDate.of(2024, 11, 6), "09:00", Availability.APPOINTMENT);
        databaseService.save(appointment7);

        // 8. Unique appointment with doctor available on a different date
        Appointment appointment8 = new Appointment("A007", "P007", "D001", LocalDate.of(2024, 11, 7), "10:00", Availability.APPOINTMENT);
        databaseService.save(appointment8);

        // 9. Attempt to save ONLEAVE appointment with duplicate ID, should not save
        Appointment appointment9 = new Appointment("L001", null, "D003", LocalDate.of(2024, 11, 8), "10:00", Availability.ONLEAVE);
        databaseService.save(appointment9);

        // 10. Doctor available for an appointment outside the ONLEAVE date range
        Appointment appointment10 = new Appointment("A008", "P008", "D002", LocalDate.of(2024, 11, 7), "10:00", Availability.APPOINTMENT);
        databaseService.save(appointment10);

        // 3. Check available slots for a specific date
        System.out.println("\nAvailable slots on 2024-11-05:");
        List<String> availableSlots = databaseService.findAvailableSlots(LocalDate.of(2024, 11, 5));
        System.out.println(availableSlots);
        // 4. Load and view all appointments
        System.out.println("\nAll appointments:");
        databaseService.viewAppointments();
        // 5. Get appointments by a specific date
        System.out.println("\nAppointments on 2024-11-05:");
        List<Appointment> appointmentsOnDate = databaseService.getAppointmentsByDate(LocalDate.of(2024, 11, 5));
        for (Appointment appt : appointmentsOnDate) {
            System.out.println(appt);
        }
        // 6. Check if a specific time slot is available
        System.out.println("\nIs 10:00 available on 2024-11-05?");
        boolean isAvailable = databaseService.isTimeSlotAvailable(LocalDate.of(2024, 11, 5), "10:00");
        System.out.println(isAvailable ? "Available" : "Not Available");
        System.out.println("\nIs 11:00 available on 2024-11-05?");
        isAvailable = databaseService.isTimeSlotAvailable(LocalDate.of(2024, 11, 5), "11:00");
        System.out.println(isAvailable ? "Available" : "Not Available");
    }
}
