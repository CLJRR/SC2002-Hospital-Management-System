/**
 * The {@code PatientApptViewer} class provides functionality for patients to
 * view available schedules and their own scheduled appointments. It helps patients
 * explore available timeslots and doctors and manage their appointments effectively.
 */
package AppointmentSystem;

import UserSystem.GetUser;
import UserSystem.User;
import enums.Flag;
import enums.Type;
import java.time.LocalDate;
import java.util.*;

/**
 * The PatientApptViewer class allows patients to view available schedules and
 * their own scheduled appointments.
 */
public class PatientApptViewer {

    /**
     * A map containing all appointment records, keyed by their appointment ID.
     */
    private Map<String, Appointment> appointmentRecords;

    /**
     * Scanner for user input.
     */
    private final Scanner sc = new Scanner(System.in);

    /**
     * Constructs a new {@code PatientApptViewer} with the specified map of appointment records.
     *
     * @param appointmentRecords the map of appointment records to manage
     */
    public PatientApptViewer(Map<String, Appointment> appointmentRecords) {
        this.appointmentRecords = appointmentRecords;
    }

    /**
     * Displays the schedule for the next three consecutive days starting from a specified date.
     * Each day's schedule includes timeslots and their availability.
     *
     * @param startDate the starting date for the schedule
     */
    public void viewpatientViewScheduleForNextThreeDays(LocalDate startDate) {
        for (int i = 0; i < 3; i++) {
            LocalDate date = startDate.plusDays(i);
            patientViewSchedule(date);
            System.out.println("Press Enter for next day");
            sc.nextLine();
            System.out.println(); // Add spacing between days
        }
        System.out.println("Press Enter to exit");
        sc.nextLine();
    }

    /**
     * Displays the schedule for a specific date.
     * Shows all timeslots along with available doctors for each timeslot.
     *
     * @param date the date for which the schedule is displayed
     */
    public void patientViewSchedule(LocalDate date) {
        List<String> timeslots = Timeslot.getTimeslot(); // Fetch all timeslots
        Map<String, Map<String, String>> schedule = new HashMap<>();

        // Get all doctors
        GetUser getUser = new GetUser();
        List<User> doctors = getUser.getAllDoctors();

        // Initialize the schedule for each timeslot with all doctors marked as "AVAILABLE"
        for (String timeslot : timeslots) {
            Map<String, String> doctorAvailability = new HashMap<>();
            for (User doctor : doctors) {
                doctorAvailability.put(doctor.getName(), "AVAILABLE");
            }
            schedule.put(timeslot, doctorAvailability);
        }

        // Check appointments and update the schedule for unavailable times
        for (Appointment appointment : appointmentRecords.values()) {
            if (appointment.getDate().equals(date)
                    && appointment.getFlag() != Flag.CANCELLED
                    && appointment.getFlag() != Flag.REJECTED) {
                String timeSlot = appointment.getTimeSlot();
                String doctorId = appointment.getDoctorId();

                // Find the doctor and mark them as "UNAVAILABLE"
                for (User doctor : doctors) {
                    if (doctor.getId().equalsIgnoreCase(doctorId)) {
                        schedule.get(timeSlot).put(doctor.getName(), "UNAVAILABLE");
                        break;
                    }
                }
            }
        }

        // Print the schedule
        System.out.println("Available Timeslots for " + date + ":");
        List<String> sortedTimeslots = new ArrayList<>(schedule.keySet());
        Collections.sort(sortedTimeslots);

        boolean hasAvailableSlots = false;
        for (String timeslot : sortedTimeslots) {
            Map<String, String> doctorAvailability = schedule.get(timeslot);

            List<String> availableDoctors = new ArrayList<>();
            for (Map.Entry<String, String> doctorEntry : doctorAvailability.entrySet()) {
                if (doctorEntry.getValue().equalsIgnoreCase("AVAILABLE")) {
                    availableDoctors.add(doctorEntry.getKey());
                }
            }

            if (!availableDoctors.isEmpty()) {
                System.out.println("- " + timeslot + ": Available Doctors - " + String.join(", ", availableDoctors));
                hasAvailableSlots = true;
            }
        }

        if (!hasAvailableSlots) {
            System.out.println("No available timeslots for this date.");
        }
    }

    /**
     * Retrieves the available slots for a specific date.
     *
     * @param date the date for which to retrieve available slots
     * @return a list of available timeslots for the specified date
     */
    public List<String> getAvailableSlots(LocalDate date) {
        List<String> allSlots = Timeslot.getTimeslot();
        List<String> bookedSlots = new ArrayList<>();

        for (Appointment appointment : appointmentRecords.values()) {
            if (appointment.getDate().equals(date) && appointment.getType() == Type.APPOINTMENT) {
                bookedSlots.add(appointment.getTimeSlot());
            }
        }

        allSlots.removeAll(bookedSlots);
        return allSlots;
    }

    /**
     * Retrieves a doctor's name by their ID.
     *
     * @param doctorId the ID of the doctor
     * @return the doctor's name, or "Unknown Doctor" if not found
     */
    private String getDoctorNameById(String doctorId) {
        GetUser getUser = new GetUser();
        List<User> doctors = getUser.getAllDoctors();

        for (User doctor : doctors) {
            if (doctor.getId().equalsIgnoreCase(doctorId)) {
                return doctor.getName();
            }
        }
        return "Unknown Doctor";
    }

    /**
     * Displays all scheduled appointments for the logged-in patient.
     *
     * @param patientId the ID of the logged-in patient
     */
    public void viewAllScheduledAppointments(String patientId) {
        System.out.println("Your scheduled appointments:");
        boolean hasAppointments = false;

        for (Appointment appointment : appointmentRecords.values()) {
            if (appointment.getPatientId() != null && appointment.getPatientId().equalsIgnoreCase(patientId)) {
                System.out.println("Appointment ID: " + appointment.getAppointmentId()
                        + ", Date: " + appointment.getDate()
                        + ", Time: " + appointment.getTimeSlot()
                        + ", Doctor name: " + getDoctorNameById(appointment.getDoctorId())
                        + ", Flag: " + appointment.getFlag());
                hasAppointments = true;
            }
        }

        if (!hasAppointments) {
            System.out.println("You have no scheduled appointments.");
        }
    }
}
