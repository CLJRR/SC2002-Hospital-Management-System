package AppointmentSystem;

import UserSystem.GetUser;
import UserSystem.User;
import enums.Flag;
import enums.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class PatientApptViewer {

    private Map<String, Appointment> appointmentRecords;
    private final Scanner sc = new Scanner(System.in);

    public PatientApptViewer(Map<String, Appointment> appointmentRecords) {
        this.appointmentRecords = appointmentRecords;

    }

    public void viewpatientViewScheduleForNextThreeDays(LocalDate startDate) {

        // Loop over the specified date and the next two days
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

        // Print the schedule: Available timeslots and doctors
        System.out.println("Available Timeslots for " + date + ":");

// Sort the timeslots in ascending order
        List<String> sortedTimeslots = new ArrayList<>(schedule.keySet());
        Collections.sort(sortedTimeslots); // Sorts timeslots alphabetically (e.g., "09:00", "10:00", etc.)

        boolean hasAvailableSlots = false;
        for (String timeslot : sortedTimeslots) {
            Map<String, String> doctorAvailability = schedule.get(timeslot);

            // Check if at least one doctor is available
            List<String> availableDoctors = new ArrayList<>();
            for (Map.Entry<String, String> doctorEntry : doctorAvailability.entrySet()) {
                if (doctorEntry.getValue().equals("AVAILABLE")) {
                    availableDoctors.add(doctorEntry.getKey());
                }
            }

            // Print the timeslot and available doctors
            if (!availableDoctors.isEmpty()) {
                System.out.println("- " + timeslot + ": Available Doctors - " + String.join(", ", availableDoctors));
                hasAvailableSlots = true;
            }
        }

        if (!hasAvailableSlots) {
            System.out.println("No available timeslots for this date.");
        }
    }

    // Helper method to get available slots for a specific date
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

    //viewScheduledApppointments
    //need to test check
    // Method to view all scheduled appointments for the logged-in patient
    public void viewAllScheduledAppointments(String patientId) {
        System.out.println("Your scheduled appointments:");
        boolean hasAppointments = false;

        for (Appointment appointment : appointmentRecords.values()) {
            if (appointment.getPatientId().equals(patientId)) {
                System.out.println("Appointment ID: " + appointment.getAppointmentId()
                        + ", Date: " + appointment.getDate()
                        + ", Time: " + appointment.getTimeSlot()
                        + ", Doctor ID: " + appointment.getDoctorId()
                        + ", Flag: " + appointment.getFlag());
                hasAppointments = true;
            }
        }

        if (!hasAppointments) {
            System.out.println("You have no scheduled appointments.");
        }
    }
}
