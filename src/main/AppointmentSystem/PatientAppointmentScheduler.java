/**
 * The {@code PatientAppointmentScheduler} class provides functionalities for patients
 * to schedule, reschedule, and cancel their appointments. It includes methods to
 * validate available timeslots and doctors and manage appointment records.
 */
package AppointmentSystem;

import UserSystem.GetUser;
import UserSystem.User;
import enums.Flag;
import enums.Type;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;

public class PatientAppointmentScheduler {

    /**
     * A map containing all appointment records, keyed by their appointment ID.
     */
    private Map<String, Appointment> appointmentRecords;

    /**
     * Scanner for user input.
     */
    private final Scanner sc = new Scanner(System.in);

    /**
     * Constructs a new {@code PatientAppointmentScheduler} with the specified map of appointment records.
     *
     * @param appointmentRecords the map of appointment records to manage
     */
    public PatientAppointmentScheduler(Map<String, Appointment> appointmentRecords) {
        this.appointmentRecords = appointmentRecords;
    }

    /**
     * Allows a patient to cancel an existing appointment.
     * Validates that the appointment belongs to the patient and confirms the cancellation.
     *
     * @param patientId the ID of the patient attempting to cancel the appointment
     */
    public void cancelAppointment(String patientId) {
        while (true) {
            System.out.print("Enter the Appointment ID to cancel (or type 'x' to exit): ");
            String appointmentId = sc.nextLine().toUpperCase();

            if (appointmentId.equalsIgnoreCase("x")) {
                System.out.println("Cancel appointment process exited.");
                return;
            }

            if (!appointmentRecords.containsKey(appointmentId)) {
                System.out.println("Error: Appointment not found. Please try again.");
                continue;
            }

            Appointment appointmentToCancel = appointmentRecords.get(appointmentId);

            if (!appointmentToCancel.getPatientId().equals(patientId)) {
                System.out.println("Error: You are not authorized to cancel this appointment.");
                return;
            }

            System.out.println("Appointment Details:");
            System.out.println("Appointment ID: " + appointmentToCancel.getAppointmentId());
            System.out.println("Date: " + appointmentToCancel.getDate());
            System.out.println("Time: " + appointmentToCancel.getTimeSlot());
            System.out.println("Doctor ID: " + appointmentToCancel.getDoctorId());
            System.out.print("Are you sure you want to cancel this appointment? (yes/no): ");
            String confirmation = sc.nextLine().toLowerCase();

            if (!confirmation.equals("yes")) {
                System.out.println("Cancellation aborted.");
                return;
            }

            appointmentRecords.remove(appointmentId);
            System.out.println("Appointment canceled successfully.");
            return;
        }
    }

    /**
     * Allows a patient to schedule a new appointment.
     * Displays available timeslots and doctors for a specific date, ensuring valid selections.
     *
     * @param patientId the ID of the patient scheduling the appointment
     */
    public void scheduleAppointment(String patientId) {
        while (true) {
            System.out.print("Enter the date for scheduling in yyyy-mm-dd format (or type 'x' to cancel): ");
            String input = sc.nextLine();

            if (input.equalsIgnoreCase("x")) {
                System.out.println("Appointment scheduling canceled.");
                return;
            }

            LocalDate date;
            try {
                date = LocalDate.parse(input);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please try again.");
                continue;
            }

            Map<String, Map<String, String>> schedule = initializeSchedule(date);

            if (!displayAvailableSlots(schedule, date)) {
                continue;
            }

            processSlotAndDoctorSelection(patientId, date, schedule);
        }
    }

    /**
     * Allows a patient to reschedule an existing appointment.
     * Displays new available timeslots for the selected date and confirms the changes.
     *
     * @param patientId the ID of the patient attempting to reschedule the appointment
     */
    public void rescheduleAppointment(String patientId) {
        while (true) {
            System.out.print("Enter the Appointment ID to reschedule (or type 'x' to cancel): ");
            String appointmentId = sc.nextLine().toUpperCase();

            if (appointmentId.equalsIgnoreCase("x")) {
                System.out.println("Rescheduling canceled.");
                return;
            }

            if (!appointmentRecords.containsKey(appointmentId)) {
                System.out.println("Appointment not found. Please try again.");
                continue;
            }

            Appointment existingAppointment = appointmentRecords.get(appointmentId);

            if (!existingAppointment.getPatientId().equalsIgnoreCase(patientId)) {
                System.out.println("You are not authorized to reschedule this appointment.");
                return;
            }

            System.out.println("Currently scheduled with Dr. " + getDoctorNameById(existingAppointment.getDoctorId())
                    + " on " + existingAppointment.getDate() + " at " + existingAppointment.getTimeSlot() + ".");

            scheduleAppointment(patientId);
            return;
        }
    }

    /**
     * Initializes the schedule for a specific date, marking all doctors and timeslots as "AVAILABLE".
     * Updates the schedule to reflect unavailable timeslots and doctors based on existing appointments.
     *
     * @param date the date for which the schedule is being initialized
     * @return a map of timeslots to doctor availability
     */
    private Map<String, Map<String, String>> initializeSchedule(LocalDate date) {
        List<String> timeslots = Timeslot.getTimeslot();
        Map<String, Map<String, String>> schedule = new HashMap<>();
        GetUser getUser = new GetUser();
        List<User> doctors = getUser.getAllDoctors();

        for (String timeslot : timeslots) {
            Map<String, String> doctorAvailability = new HashMap<>();
            for (User doctor : doctors) {
                doctorAvailability.put(doctor.getName(), "AVAILABLE");
            }
            schedule.put(timeslot, doctorAvailability);
        }

        for (Appointment appointment : appointmentRecords.values()) {
            if (appointment.getDate().equals(date) && appointment.getFlag() != Flag.CANCELLED
                    && appointment.getFlag() != Flag.REJECTED) {
                String timeSlot = appointment.getTimeSlot();
                String doctorId = appointment.getDoctorId();

                for (User doctor : doctors) {
                    if (doctor.getId().equalsIgnoreCase(doctorId)) {
                        schedule.get(timeSlot).put(doctor.getName(), "UNAVAILABLE");
                        break;
                    }
                }
            }
        }
        return schedule;
    }

    /**
     * Displays the available timeslots and doctors for a specific date.
     *
     * @param schedule the schedule containing timeslots and doctor availability
     * @param date     the date for which availability is displayed
     * @return {@code true} if there are available slots, {@code false} otherwise
     */
    private boolean displayAvailableSlots(Map<String, Map<String, String>> schedule, LocalDate date) {
        boolean hasAvailableSlots = false;
        List<String> sortedTimeslots = new ArrayList<>(schedule.keySet());
        Collections.sort(sortedTimeslots);

        System.out.println("Available Timeslots for " + date + ":");
        for (String timeslot : sortedTimeslots) {
            List<String> availableDoctors = new ArrayList<>();
            for (Map.Entry<String, String> doctorEntry : schedule.get(timeslot).entrySet()) {
                if (doctorEntry.getValue().equalsIgnoreCase("AVAILABLE")) {
                    availableDoctors.add(doctorEntry.getKey());
                }
            }

            if (!availableDoctors.isEmpty()) {
                System.out.println(timeslot + ": Available Doctors - " + String.join(", ", availableDoctors));
                hasAvailableSlots = true;
            }
        }

        if (!hasAvailableSlots) {
            System.out.println("No available timeslots for this date.");
        }
        return hasAvailableSlots;
    }

    /**
     * Processes the patient's selection of a timeslot and doctor for scheduling an appointment.
     *
     * @param patientId the ID of the patient
     * @param date      the date of the appointment
     * @param schedule  the schedule map containing timeslot and doctor availability
     */
    private void processSlotAndDoctorSelection(String patientId, LocalDate date, Map<String, Map<String, String>> schedule) {
        // Implementation of selection and appointment creation.
    }

    /**
     * Retrieves a doctor's name by their ID.
     *
     * @param doctorId the ID of the doctor
     * @return the name of the doctor, or "Unknown Doctor" if not found
     */
    private String getDoctorNameById(String doctorId) {
        GetUser getUser = new GetUser();
        for (User doctor : getUser.getAllDoctors()) {
            if (doctor.getId().equalsIgnoreCase(doctorId)) {
                return doctor.getName();
            }
        }
        return "Unknown Doctor";
    }
}
