package AppointmentSystem;

import enums.Flag;
import enums.Type;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import UserSystem.GetUser;
import UserSystem.User;


// //Schedule
// //Reschedule
// //Cancel

public class PatientAppointmentScheduler {

    private Map<String, Appointment> appointmentRecords;
    private final Scanner sc = new Scanner(System.in);
    private PatientApptViewer patientApptViewer;
    private Appointment appointment;


    public PatientAppointmentScheduler(Map<String, Appointment> appointmentRecords) {
        this.appointmentRecords = appointmentRecords;
    }

    //cancel appointment
    public void cancelAppointment(String patientId) {
        System.out.print("Enter the Appointment ID to cancel: ");
        String appointmentId = sc.nextLine();

        if (!appointmentRecords.containsKey(appointmentId)) {
            System.out.println("Appointment not found.");
            return;
        }

        Appointment appointmentToCancel = appointmentRecords.get(appointmentId);

        if (!appointmentToCancel.getPatientId().equals(patientId)) {
            System.out.println("You cannot cancel this appointment.");
            return;
        }

        if (appointmentToCancel.getPatientId() == null){
            System.out.println("You have no scheduled appointments.");
            return;
        }

        appointmentRecords.remove(appointmentId);
        System.out.println("Appointment canceled successfully.");
    }

    // Helper method to generate the next sequential appointment ID
    private String generateAppointmentId() {
        int nextId = appointmentRecords.size() + 1;
        return "A" + nextId;
    }

public void scheduleAppointment(LocalDate date, String patientId) {
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
        if (appointment.getDate().equals(date) && appointment.getFlag() != Flag.CANCELLED && appointment.getFlag() != Flag.REJECTED) {
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
    Collections.sort(sortedTimeslots); // Sort timeslots for consistent display

    boolean hasAvailableSlots = false;
    for (int i = 0; i < sortedTimeslots.size(); i++) {
        String timeslot = sortedTimeslots.get(i);
        Map<String, String> doctorAvailability = schedule.get(timeslot);

        // Filter available doctors
        List<String> availableDoctors = new ArrayList<>();
        for (Map.Entry<String, String> doctorEntry : doctorAvailability.entrySet()) {
            if (doctorEntry.getValue().equals("AVAILABLE")) {
                availableDoctors.add(doctorEntry.getKey());
            }
        }

        // Display only if there are available doctors
        if (!availableDoctors.isEmpty()) {
            System.out.println((i + 1) + ") " + timeslot + ": Available Doctors - " + String.join(", ", availableDoctors));
            hasAvailableSlots = true;
        }
    }

    if (!hasAvailableSlots) {
        System.out.println("No available timeslots for this date.");
        return;
    }

    //    // Ask the patient to select a timeslot
    System.out.print("Select an available timeslot: ");
    int slotChoice = sc.nextInt();
    sc.nextLine(); // Consume newline

    if (slotChoice < 1 || slotChoice > sortedTimeslots.size()) {
        System.out.println("Invalid slot selection.");
        return;
    }

    String selectedSlot = sortedTimeslots.get(slotChoice - 1);

    // Ask the patient to select a doctor
    System.out.print("Select a doctor from the available list: ");
    String selectedDoctorName = sc.nextLine();

    // Validate the selected doctor
    if (!schedule.get(selectedSlot).getOrDefault(selectedDoctorName, "").equals("AVAILABLE")) {
        System.out.println("The selected doctor is not available for this timeslot. Please try again.");
        return;
    }

    // Create the appointment
    String appointmentId = generateAppointmentId();
    String doctorId = getDoctorIdByName(selectedDoctorName);
    Appointment appointment = new Appointment(appointmentId, patientId, doctorId, date, selectedSlot, Type.APPOINTMENT, Flag.PENDING);
    appointmentRecords.put(appointmentId, appointment);

    System.out.println("Appointment scheduled successfully for " + date + " at " + selectedSlot + " with Dr. " + selectedDoctorName);
}

public void rescheduleAppointment(String patientId) {
    System.out.print("Enter the Appointment ID to reschedule: ");
    String appointmentId = sc.nextLine();

    // Check if the appointment exists and belongs to the patient
    if (!appointmentRecords.containsKey(appointmentId)) {
        System.out.println("Appointment not found.");
        return;
    }

    Appointment existingAppointment = appointmentRecords.get(appointmentId);

    if (!existingAppointment.getPatientId().equals(patientId)) {
        System.out.println("You are not authorized to reschedule this appointment.");
        return;
    }

    if (existingAppointment.getPatientId() == null){
        System.out.println("You have no scheduled appointments.");
        return;
    }

    System.out.print("Enter the new date for rescheduling in yyyy-mm-dd format: ");
    LocalDate newDate = LocalDate.parse(sc.nextLine());

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
        if (appointment.getDate().equals(newDate) && appointment.getFlag() != Flag.CANCELLED && appointment.getFlag() != Flag.REJECTED) {
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
    System.out.println("Available Timeslots for " + newDate + ":");
    List<String> sortedTimeslots = new ArrayList<>(schedule.keySet());
    Collections.sort(sortedTimeslots); // Sort timeslots for consistent display

    boolean hasAvailableSlots = false;
    for (int i = 0; i < sortedTimeslots.size(); i++) {
        String timeslot = sortedTimeslots.get(i);
        Map<String, String> doctorAvailability = schedule.get(timeslot);

        // Filter available doctors
        List<String> availableDoctors = new ArrayList<>();
        for (Map.Entry<String, String> doctorEntry : doctorAvailability.entrySet()) {
            if (doctorEntry.getValue().equals("AVAILABLE")) {
                availableDoctors.add(doctorEntry.getKey());
            }
        }

        // Display only if there are available doctors
        if (!availableDoctors.isEmpty()) {
            System.out.println((i + 1) + ") " + timeslot + ": Available Doctors - " + String.join(", ", availableDoctors));
            hasAvailableSlots = true;
        }
    }

    if (!hasAvailableSlots) {
        System.out.println("No available timeslots for this date.");
        return;
    }

    // Ask the patient to select a new timeslot
    System.out.print("Select an available timeslot: ");
    int slotChoice = sc.nextInt();
    sc.nextLine(); // Consume newline

    if (slotChoice < 1 || slotChoice > sortedTimeslots.size()) {
        System.out.println("Invalid slot selection.");
        return;
    }

    String selectedSlot = sortedTimeslots.get(slotChoice - 1);

    // Ask the patient to select a doctor
    System.out.print("Select a doctor from the available list: ");
    String selectedDoctorName = sc.nextLine();

    // Validate the selected doctor
    if (!schedule.get(selectedSlot).getOrDefault(selectedDoctorName, "").equals("AVAILABLE")) {
        System.out.println("The selected doctor is not available for this timeslot. Please try again.");
        return;
    }

    // Remove old appointment and create a new one
    appointmentRecords.remove(appointmentId);
    String newAppointmentId = generateAppointmentId();
    String doctorId = getDoctorIdByName(selectedDoctorName);
    Appointment newAppointment = new Appointment(newAppointmentId, patientId, doctorId, newDate, selectedSlot, Type.APPOINTMENT, Flag.PENDING);
    appointmentRecords.put(newAppointmentId, newAppointment);

    System.out.println("Appointment rescheduled successfully to " + newDate + " at " + selectedSlot + " with Dr. " + selectedDoctorName);
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
