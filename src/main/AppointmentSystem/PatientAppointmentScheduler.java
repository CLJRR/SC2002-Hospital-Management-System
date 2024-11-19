package AppointmentSystem;

import UserSystem.GetUser;
import UserSystem.User;
import enums.Flag;
import enums.Type;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

// //Schedule
// //Reschedule
// //Cancel
/**
 * /**
 * The {@code PatientAppointmentScheduler} class handles appointment management
 * for patients. It provides functionalities for scheduling, rescheduling, and
 * canceling appointments.
 */
public class PatientAppointmentScheduler {

    private Map<String, Appointment> appointmentRecords;
    private final Scanner sc = new Scanner(System.in);
    private PatientAppointmentViewer PatientAppointmentViewer;
    private Appointment appointment;

    /**
     * Constructs a {@code PatientAppointmentScheduler} with the given
     * appointment records.
     *
     * @param appointmentRecords a map containing the appointment records.
     */
    public PatientAppointmentScheduler(Map<String, Appointment> appointmentRecords) {
        this.appointmentRecords = appointmentRecords;
    }

    /**
     * Cancels an appointment for a given patient.
     *
     * @param patientId the ID of the patient who wants to cancel an
     * appointment.
     */
    public void cancelAppointment(String patientId) {
        while (true) {
            System.out.print("Enter the Appointment ID to cancel (or type 'x' to exit): ");
            String appointmentId = sc.nextLine().toUpperCase();

            // Allow the user to exit
            if (appointmentId.equalsIgnoreCase("x")) {
                System.out.println("Cancel appointment process exited.");
                return;
            }

            // Check if the appointment exists
            if (!appointmentRecords.containsKey(appointmentId)) {
                System.out.println("Error: Appointment not found. Please try again.");
                continue;
            }

            Appointment appointmentToCancel = appointmentRecords.get(appointmentId);

            // Check if the appointment belongs to the patient
            if (!appointmentToCancel.getPatientId().equals(patientId)) {
                System.out.println("Error: You are not authorized to cancel this appointment.");
                return;
            }

            // Check if the patient has any scheduled appointments
            if (appointmentToCancel.getPatientId() == null) {
                System.out.println("Error: No scheduled appointments found.");
                return;
            }

            // Confirm cancellation
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

            // Remove the appointment
            appointmentRecords.remove(appointmentId);
            System.out.println("Appointment canceled successfully.");
            return;
        }
    }

    /**
     * Generates a unique appointment ID in the format "Axxx", where "xxx" is a
     * zero-padded number. The method counts the existing appointments and
     * generates the next ID in the sequence.
     *
     * @return a unique appointment ID in the format "Axxx", where "xxx" is the
     * next available number based on the size of the appointment records.
     */
    private String generateAppointmentId() {
        // Filter appointmentRecords to count only valid appointment entries
        long appointmentCount = appointmentRecords.values().stream()
                .filter(appointment -> appointment.getType() == Type.APPOINTMENT)
                .count();

        // Generate the next appointment ID based on the count of existing appointments
        return String.format("A%03d", appointmentCount + 1);
    }

    private String getDoctorNameById(String doctorId) {
        // Fetch all doctors using GetUser
        GetUser getUser = new GetUser();
        List<User> doctors = getUser.getAllDoctors();

        // Iterate through the list of doctors to find the matching ID
        for (User doctor : doctors) {
            if (doctor.getId().equalsIgnoreCase(doctorId)) {
                return doctor.getName();
            }
        }

        // Return "Unknown Doctor" if no match is found
        return "Unknown Doctor";
    }

    /**
     * Schedules a new appointment for a patient.
     *
     * @param patientId the ID of the patient scheduling the appointment.
     */
    public void scheduleAppointment(String patientId) {
        while (true) {
            System.out.print("Enter the date for scheduling in yyyy-mm-dd format (or type 'x' to cancel): ");
            String input = sc.nextLine();

            // Allow the user to exit
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

            // Fetch available timeslots and doctors
            List<String> timeslots = Timeslot.getTimeslot();
            Map<String, Map<String, String>> schedule = new HashMap<>();

            GetUser getUser = new GetUser();
            List<User> doctors = getUser.getAllDoctors();

            // Initialize the schedule for each timeslot with all doctors marked as
            // "AVAILABLE"
            for (String timeslot : timeslots) {
                Map<String, String> doctorAvailability = new HashMap<>();
                for (User doctor : doctors) {
                    doctorAvailability.put(doctor.getName(), "AVAILABLE");
                }
                schedule.put(timeslot, doctorAvailability);
            }

            // Check appointments and update the schedule for unavailable times
            for (Appointment appointment : appointmentRecords.values()) {
                if (appointment.getDate().equals(date) && appointment.getFlag() != Flag.CANCELLED
                        && appointment.getFlag() != Flag.REJECTED) {
                    String timeSlot = appointment.getTimeSlot();
                    String doctorId = appointment.getDoctorId();

                    // Find the doctor and mark them as "UNAVAILABLE"
                    for (User doctor : doctors) {
                        if (doctor.getId().equals(doctorId)) {
                            schedule.get(timeSlot).put(doctor.getName(), "UNAVAILABLE");
                            break;
                        }
                    }
                }
            }

            // Display available timeslots and doctors
            System.out.println("Available Timeslots for " + date + ":");
            List<String> sortedTimeslots = new ArrayList<>(schedule.keySet());
            Collections.sort(sortedTimeslots);

            boolean hasAvailableSlots = false;
            for (int i = 0; i < sortedTimeslots.size(); i++) {
                String timeslot = sortedTimeslots.get(i);
                Map<String, String> doctorAvailability = schedule.get(timeslot);

                List<String> availableDoctors = new ArrayList<>();
                for (Map.Entry<String, String> doctorEntry : doctorAvailability.entrySet()) {
                    if (doctorEntry.getValue().equals("AVAILABLE")) {
                        availableDoctors.add(doctorEntry.getKey());
                    }
                }

                if (!availableDoctors.isEmpty()) {
                    System.out.println(
                            (i + 1) + ") " + timeslot + ": Available Doctors - " + String.join(", ", availableDoctors));
                    hasAvailableSlots = true;
                }
            }

            if (!hasAvailableSlots) {
                System.out.println("No available timeslots for this date.");
                continue; // Allow user to enter a new date
            }

            while (true) {
                // Ask the patient to select a timeslot
                System.out.print("Select an available timeslot (or type '99' to go back to enter a new date): ");

                while (!sc.hasNextInt()) {
                    System.out.println("Invalid input. Please enter an integer");
                    System.out.print("Select an available timeslot (or type '99' to go back to enter a new date): ");
                    sc.next();
                }

                int slotChoice = sc.nextInt();
                sc.nextLine(); // Consume newline

                if (slotChoice == 99) {
                    System.out.println("Going back to enter a new date.");
                    break; // Exit to re-enter a new date
                }

                if (slotChoice < 1 || slotChoice > sortedTimeslots.size()) {
                    System.out.println("Invalid slot selection. Please try again.");
                    continue;
                }

                String selectedSlot = sortedTimeslots.get(slotChoice - 1);

                while (true) {
                    // Ask the patient to select a doctor
                    System.out.print(
                            "Select a doctor from the available list (or type 'x' to go back to select a new timeslot): ");
                    String selectedDoctorName = sc.nextLine();

                    for (Map<String, String> m : schedule.values()) {
                        for (Map.Entry<String, String> en : m.entrySet()) {
                            if (selectedDoctorName.equalsIgnoreCase(en.getKey())) {
                                selectedDoctorName = en.getKey();
                            }
                        }
                    }

                    if (selectedDoctorName.equalsIgnoreCase("x")) {
                        System.out.println("Going back to enter new timeslot.");
                        break; // Exit to re-enter a new date
                    }

                    if (!schedule.get(selectedSlot).getOrDefault(selectedDoctorName, "").equals("AVAILABLE")) {
                        System.out.println("The selected doctor is not available for this timeslot. Please try again.");
                        continue; // Prompt again for a valid doctor
                    }

                    // Create the appointment
                    String appointmentId = generateAppointmentId();
                    String doctorId = getDoctorIdByName(selectedDoctorName);
                    Appointment appointment = new Appointment(appointmentId, patientId, doctorId, date, selectedSlot,
                            Type.APPOINTMENT, Flag.PENDING);
                    appointmentRecords.put(appointmentId, appointment);

                    System.out.println("Appointment scheduled successfully for " + date + " at " + selectedSlot
                            + " with Dr. " + selectedDoctorName);
                    return; // Exit after successful scheduling
                }
            }
        }
    }

    /**
     * Reschedules an existing appointment for a patient.
     *
     * @param patientId the ID of the patient rescheduling the appointment.
     */
    public void rescheduleAppointment(String patientId) {
        while (true) {
            System.out.print("Enter the Appointment ID to reschedule (or type 'x' to cancel): ");
            String appointmentId = sc.nextLine().toUpperCase();

            // Allow the user to exit
            if (appointmentId.equalsIgnoreCase("x")) {
                System.out.println("Rescheduling canceled.");
                return;
            }

            // Check if the appointment exists and belongs to the patient
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

            while (true) {
                System.out.print("Enter the new date for rescheduling in yyyy-mm-dd format (or type 'x' to go back): ");
                String input = sc.nextLine();

                // Allow the user to go back
                if (input.equalsIgnoreCase("x")) {
                    System.out.println("Going back to enter Appointment ID.");
                    return;
                }

                LocalDate newDate;
                try {
                    newDate = LocalDate.parse(input);
                } catch (DateTimeParseException e) {
                    System.out.println("Invalid date format. Please try again.");
                    continue;
                }

                // Fetch available timeslots and doctors
                List<String> timeslots = Timeslot.getTimeslot();
                Map<String, Map<String, String>> schedule = new HashMap<>();

                GetUser getUser = new GetUser();
                List<User> doctors = getUser.getAllDoctors();

                // Initialize the schedule for each timeslot with all doctors marked as
                // "AVAILABLE"
                for (String timeslot : timeslots) {
                    Map<String, String> doctorAvailability = new HashMap<>();
                    for (User doctor : doctors) {
                        doctorAvailability.put(doctor.getName(), "AVAILABLE");
                    }
                    schedule.put(timeslot, doctorAvailability);
                }

                // Check appointments and update the schedule for unavailable times
                for (Appointment appointment : appointmentRecords.values()) {
                    if (appointment.getDate().equals(newDate) && appointment.getFlag() != Flag.CANCELLED
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

                // Display available timeslots and doctors
                System.out.println("Available Timeslots for " + newDate + ":");
                List<String> sortedTimeslots = new ArrayList<>(schedule.keySet());
                Collections.sort(sortedTimeslots);

                boolean hasAvailableSlots = false;
                for (int i = 0; i < sortedTimeslots.size(); i++) {
                    String timeslot = sortedTimeslots.get(i);
                    Map<String, String> doctorAvailability = schedule.get(timeslot);

                    List<String> availableDoctors = new ArrayList<>();
                    for (Map.Entry<String, String> doctorEntry : doctorAvailability.entrySet()) {
                        if (doctorEntry.getValue().equalsIgnoreCase("AVAILABLE")) {
                            availableDoctors.add(doctorEntry.getKey());
                        }
                    }

                    if (!availableDoctors.isEmpty()) {
                        System.out.println((i + 1) + ") " + timeslot + ": Available Doctors - "
                                + String.join(", ", availableDoctors));
                        hasAvailableSlots = true;
                    }
                }

                if (!hasAvailableSlots) {
                    System.out.println("No available timeslots for this date.");
                    continue; // Allow user to enter a new date
                }

                while (true) {
                    // Ask the patient to select a new timeslot
                    System.out.print("Select an available timeslot (or type '99' to go back to enter a new date): ");

                    // Loop until an integer is entered
                    while (!sc.hasNextInt()) {
                        System.out.println("Invalid input. Please enter an integer");
                        System.out
                                .print("Select an available timeslot (or type '99' to go back to enter a new date): ");
                        sc.next(); // Clear the invalid input
                    }

                    int slotChoice = sc.nextInt();
                    sc.nextLine(); // Consume newline

                    if (slotChoice == 99) {
                        System.out.println("Going back to enter a new date.");
                        break; // Exit to re-enter a new date
                    }

                    if (slotChoice < 1 || slotChoice > sortedTimeslots.size()) {
                        System.out.println("Invalid slot selection. Please try again.");
                        continue; // Prompt again
                    }

                    String selectedSlot = sortedTimeslots.get(slotChoice - 1);

                    while (true) {
                        // Ask the patient to select a doctor
                        System.out.print(
                                "Select a doctor from the available list (or type 'x' to go back to enter a new date): ");
                        String selectedDoctorName = sc.nextLine();

                        if (selectedDoctorName.equals("x")) {
                            System.out.println("Going back to enter a new date");
                            break; // Exit to re-enter a new date
                        }

                        for (Map<String, String> m : schedule.values()) {
                            for (Map.Entry<String, String> en : m.entrySet()) {
                                if (selectedDoctorName.equalsIgnoreCase(en.getKey())) {
                                    selectedDoctorName = en.getKey();
                                }
                            }
                        }
                        if (!schedule.get(selectedSlot).getOrDefault(selectedDoctorName, "")
                                .equalsIgnoreCase("AVAILABLE")) {
                            System.out.println(
                                    "The selected doctor is not available for this timeslot. Please try again.");
                            continue; // Prompt again for a valid doctor
                        }

                        // Reschedule the appointment
                        appointmentRecords.remove(appointmentId);
                        String newAppointmentId = generateAppointmentId();
                        String doctorId = getDoctorIdByName(selectedDoctorName);
                        Appointment newAppointment = new Appointment(newAppointmentId, patientId, doctorId, newDate,
                                selectedSlot, Type.APPOINTMENT, Flag.PENDING);
                        appointmentRecords.put(newAppointmentId, newAppointment);

                        System.out.println("Appointment rescheduled successfully to " + newDate + " at " + selectedSlot
                                + " with Dr. " + selectedDoctorName);
                        return; // Exit after successful rescheduling
                    }
                    break; // Exit to re-enter a new date if user selects "99"
                }
            }
        }
    }

    // Get a doctor's ID by their name
    private String getDoctorIdByName(String doctorName) {
        GetUser getUser = new GetUser();
        for (User doctor : getUser.getAllDoctors()) {
            if (doctor.getName().equalsIgnoreCase(doctorName)) {
                return doctor.getId();
            }
        }
        return null;
    }

}
