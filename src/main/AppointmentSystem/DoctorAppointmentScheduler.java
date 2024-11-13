package AppointmentSystem;

import enums.Type;
import enums.Flag;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class DoctorAppointmentScheduler {

    private Map<String, Appointment> appointmentRecords;
    private AppointmentService service;
    private static final Scanner sc = new Scanner(System.in);
    private String doctorId; // The ID of the logged-in doctor

    public DoctorAppointmentScheduler(Map<String, Appointment> appointmentRecords, String doctorId) {
        this.appointmentRecords = appointmentRecords;
        this.service = new AppointmentService();
        this.doctorId = doctorId;

        try {
            // Load initial appointments into the map
            @SuppressWarnings("unchecked")
            List<Appointment> loadedAppointments = (List<Appointment>) service.load();
            for (Appointment appointment : loadedAppointments) {
                appointmentRecords.put(appointment.getAppointmentId(), appointment);
            }
        } catch (IOException e) {
            System.err.println("Error loading initial appointments: " + e.getMessage());
        }
    }

    // Method to set or update flag for appointments on a specific date
    public void setAvailabilityForDate() {
        System.out.print("Enter the date to set flag (yyyy-MM-dd): ");
        LocalDate date = LocalDate.parse(sc.nextLine());

        // Generate all slots for the selected date with default flag of AVAILABLE
        List<LocalTime> allSlots = generateTimeSlots();

        System.out.println("Set flag for each slot:");
        for (LocalTime slot : allSlots) {
            // Check if there is an existing appointment or timeslot for the slot
            Type currentAvailability = getAvailabilityFlag(date, slot);

            System.out.println(slot + " - " + slot.plusHours(1) + " is currently " + currentAvailability);
            System.out.print("Enter 'leave' to mark as leave, 'available' to mark as available, or press Enter to keep as is: ");
            String input = sc.nextLine().toLowerCase();

            // Determine flag based on input
            Type newAvailability;
            if ("leave".equals(input)) {
                newAvailability = Type.CANCELLED;
            } else if ("available".equals(input)) {
                newAvailability = Type.AVAILABLE;
            } else {
                continue; // Skip updating if the input is empty or not recognized
            }

            // Create or update the timeslot with the new flag Flag
            createOrUpdateTimeslot(date, slot, newAvailability);
        }

        try {
            // Save the updated flag to the file
            service.save(new ArrayList<>(appointmentRecords.values()));
            System.out.println("Type set successfully for " + date);
        } catch (IOException e) {
            System.err.println("Error saving flag: " + e.getMessage());
        }
    }

    // Method to create or update a timeslot for a given date and time
    private void createOrUpdateTimeslot(LocalDate date, LocalTime slot, Type flag) {
        // Check if an appointment entry exists for this timeslot
        for (Appointment appointment : appointmentRecords.values()) {
            if (appointment.getDoctorId().equals(doctorId) && appointment.getDate().equals(date) && appointment.getTimeSlot().equals(slot.toString())) {
                // Update the flag of an existing timeslot
                appointment.setAvailability(flag);
                return;
            }
        }

        // If no existing timeslot is found, create a new one
        Appointment newTimeslot = new Appointment(
                UUID.randomUUID().toString(), // Generate a unique appointment ID
                "N/A", // No patient assigned yet
                doctorId,
                date,
                slot.toString(),
                flag,
                null // Flag not used here as it is specific to appointment Flag
        );
        appointmentRecords.put(newTimeslot.getAppointmentId(), newTimeslot);
    }

    // Method to retrieve the current flag Flag of a timeslot for a given date
    private Type getAvailabilityFlag(LocalDate date, LocalTime slot) {
        for (Appointment appointment : appointmentRecords.values()) {
            if (appointment.getDoctorId().equals(doctorId) && appointment.getDate().equals(date) && appointment.getTimeSlot().equals(slot.toString())) {
                return appointment.getAvailability(); // Return existing flag Flag if found
            }
        }
        return Type.AVAILABLE; // Default to AVAILABLE if no entry exists
    }

    // Helper method to generate time slots from 9 AM to 5 PM, excluding lunch hour (12 PM - 1 PM)
    private List<LocalTime> generateTimeSlots() {
        List<LocalTime> slots = new ArrayList<>();
        LocalTime startTime = LocalTime.of(9, 0);
        LocalTime endTime = LocalTime.of(17, 0);

        while (startTime.isBefore(endTime)) {
            if (!startTime.equals(LocalTime.of(12, 0))) { // Exclude 12 PM - 1 PM lunch hour
                slots.add(startTime);
            }
            startTime = startTime.plusHours(1);
        }
        return slots;
    }

    // Additional methods for viewing and managing appointments
    public void viewPersonalSchedule() {
        System.out.println("Your upcoming appointments:");
        boolean hasAppointments = false;

        for (Appointment appointment : appointmentRecords.values()) {
            if (appointment.getDoctorId().equals(doctorId)) {
                System.out.println("Appointment ID: " + appointment.getAppointmentId()
                        + ", Date: " + appointment.getDate()
                        + ", Time: " + appointment.getTimeSlot()
                        + ", Patient ID: " + appointment.getPatientId()
                        + ", Flag: " + appointment.getFlag());
                hasAppointments = true;
            }
        }

        if (!hasAppointments) {
            System.out.println("You have no upcoming appointments.");
        }
    }

    public void manageAppointmentRequests() {
        System.out.println("Pending appointment requests:");
        List<Appointment> pendingRequests = new ArrayList<>();

        // Display pending requests
        for (Appointment appointment : appointmentRecords.values()) {
            if (appointment.getDoctorId().equals(doctorId) && appointment.getFlag() == Flag.PENDING) {
                System.out.println("Appointment ID: " + appointment.getAppointmentId()
                        + ", Date: " + appointment.getDate()
                        + ", Time: " + appointment.getTimeSlot()
                        + ", Patient ID: " + appointment.getPatientId());
                pendingRequests.add(appointment);
            }
        }

        if (pendingRequests.isEmpty()) {
            System.out.println("No pending requests.");
            return;
        }

        // Select an appointment request to update
        System.out.print("Enter the Appointment ID to confirm or decline: ");
        String appointmentId = sc.nextLine();
        Appointment appointment = appointmentRecords.get(appointmentId);

        if (appointment == null || !appointment.getDoctorId().equals(doctorId)) {
            System.out.println("Invalid appointment ID.");
            return;
        }

        // Accept or decline the appointment
        System.out.print("Enter 'confirm' to accept or 'decline' to reject the appointment: ");
        String action = sc.nextLine().toLowerCase();

        if ("confirm".equals(action)) {
            appointment.setFlag(Flag.CONFIRMED);
            System.out.println("Appointment confirmed.");
        } else if ("decline".equals(action)) {
            appointment.setFlag(Flag.REJECTED);
            System.out.println("Appointment declined.");
        } else {
            System.out.println("Invalid action.");
            return;
        }

        // Save changes
        try {
            service.save(new ArrayList<>(appointmentRecords.values()));
        } catch (IOException e) {
            System.err.println("Error saving appointment Flag: " + e.getMessage());
        }
    }

    public void viewUpcomingAppointments() {
        LocalDate today = LocalDate.now();
        System.out.println("Your upcoming appointments from today:");

        boolean hasAppointments = false;
        for (Appointment appointment : appointmentRecords.values()) {
            if (appointment.getDoctorId().equals(doctorId) && !appointment.getDate().isBefore(today)) {
                System.out.println("Appointment ID: " + appointment.getAppointmentId()
                        + ", Date: " + appointment.getDate()
                        + ", Time: " + appointment.getTimeSlot()
                        + ", Patient ID: " + appointment.getPatientId()
                        + ", Flag: " + appointment.getFlag());
                hasAppointments = true;
            }
        }

        if (!hasAppointments) {
            System.out.println("You have no upcoming appointments from today.");
        }
    }
}