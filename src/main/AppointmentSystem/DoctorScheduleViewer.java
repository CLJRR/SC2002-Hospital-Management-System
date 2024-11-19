/**
 * The {@code DoctorScheduleViewer} class provides functionality to view a doctor's schedule.
 * It supports viewing schedules for specific dates or a range of three consecutive days, including
 * details about appointments, leaves, and available timeslots.
 */
package AppointmentSystem;

import enums.Flag;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class DoctorScheduleViewer {

    /**
     * A map containing all appointment records, keyed by their appointment ID.
     */
    private Map<String, Appointment> appointmentRecords;

    /**
     * Scanner for user input.
     */
    private final Scanner sc = new Scanner(System.in);

    /**
     * Constructs a new {@code DoctorScheduleViewer} with the specified map of appointment records.
     *
     * @param appointmentRecords the map of appointment records to manage and view
     */
    public DoctorScheduleViewer(Map<String, Appointment> appointmentRecords) {
        this.appointmentRecords = appointmentRecords;
    }

    /**
     * Displays the schedule for a doctor over the next three consecutive days starting from a specified date.
     * Each day's schedule includes timeslots and their statuses (e.g., available, appointment, or leave).
     *
     * @param doctorId  the ID of the doctor whose schedule is to be displayed
     * @param startDate the starting date for the schedule
     */
    public void viewDoctorScheduleForNextThreeDays(String doctorId, LocalDate startDate) {
        // Loop over the specified date and the next two days
        for (int i = 0; i < 3; i++) {
            LocalDate date = startDate.plusDays(i);
            viewDoctorScheduleForDate(doctorId, date);
            System.out.println(); // Add spacing between days
        }
        System.out.println("Press Enter to go back");
        sc.nextLine();
    }

    /**
     * Displays the schedule for a doctor for a specific date.
     * The schedule includes details for each timeslot (e.g., available, appointment, or leave).
     *
     * @param doctorId the ID of the doctor whose schedule is to be displayed
     * @param date     the date for which the schedule is to be displayed
     */
    public void viewDoctorScheduleForDate(String doctorId, LocalDate date) {
        List<String> timeslots = Timeslot.getTimeslot();
        Map<String, String> schedule = new HashMap<>();

        // Initialize all timeslots to "AVAILABLE"
        for (String timeslot : timeslots) {
            schedule.put(timeslot, "AVAILABLE");
        }

        // Check appointments and leaves for the specified doctor and date
        for (Appointment appointment : appointmentRecords.values()) {
            if (appointment.getDoctorId().equalsIgnoreCase(doctorId)
                    && appointment.getDate().equals(date)
                    && appointment.getFlag() != Flag.CANCELLED
                    && appointment.getFlag() != Flag.REJECTED) {
                String timeSlot = appointment.getTimeSlot();
                String details;

                if (appointment.getType() == null) {
                    details = "AVAILABLE";
                } else {
                    switch (appointment.getType()) {
                        case APPOINTMENT:
                            details = String.format("APPOINTMENT - ID: %s, Patient ID: %s, Flag: %s",
                                    appointment.getAppointmentId(), appointment.getPatientId(), appointment.getFlag());
                            break;
                        case LEAVE:
                            details = String.format("LEAVE - Flag: %s", appointment.getFlag());
                            break;
                        default:
                            details = "AVAILABLE";
                            break;
                    }
                }
                schedule.put(timeSlot, details);
            }
        }

        // Display the schedule with additional details for the specified date
        System.out.println("Doctor's Schedule for " + doctorId + " on " + date + ":");
        for (String timeslot : timeslots) {
            System.out.println(timeslot + "\t" + schedule.get(timeslot));
        }
    }
}
