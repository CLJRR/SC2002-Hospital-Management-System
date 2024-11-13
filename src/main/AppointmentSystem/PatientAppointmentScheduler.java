package AppointmentSystem;

import enums.Type;
import enums.Flag;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class PatientAppointmentScheduler {

    private Map<String, Appointment> appointmentRecords;
    private AppointmentService service;
    private static final Scanner sc = new Scanner(System.in);
    private String patientId; // The ID of the logged-in patient

    public PatientAppointmentScheduler(Map<String, Appointment> appointmentRecords, String patientId) {
        this.appointmentRecords = appointmentRecords;
        this.service = new AppointmentService();
        this.patientId = patientId;

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

    // Method to create a new appointment
    public Appointment createAppointment(String patientId, String doctorId, LocalDate date, String timeSlot, Type type, Flag flag) {
        String appointmentId = UUID.randomUUID().toString(); // Generates a unique ID
        return new Appointment(appointmentId, patientId, doctorId, date, timeSlot, type, flag);
    }

    // Method to view available slots and schedule a new appointment
    public void viewAndScheduleAppointment() {
        System.out.print("Enter the desired date for the appointment (yyyy-MM-dd): ");
        LocalDate date = LocalDate.parse(sc.nextLine());

        List<LocalTime> availableSlots = getAvailableSlots(date);
        if (availableSlots.isEmpty()) {
            System.out.println("No available slots for " + date);
            return;
        }

        System.out.println("Available slots for " + date + ":");
        for (int i = 0; i < availableSlots.size(); i++) {
            LocalTime slot = availableSlots.get(i);
            System.out.println((i + 1) + ") " + slot + " - " + slot.plusHours(1));
        }

        System.out.print("Select a slot number to schedule: ");
        int slotChoice = sc.nextInt();
        sc.nextLine(); // Consume newline

        if (slotChoice < 1 || slotChoice > availableSlots.size()) {
            System.out.println("Invalid slot selection.");
            return;
        }

        LocalTime selectedSlot = availableSlots.get(slotChoice - 1);

        System.out.print("Enter Doctor ID: ");
        String doctorId = sc.nextLine();

        // Schedule appointment
        Appointment appointment = createAppointment(patientId, doctorId, date, selectedSlot.toString(), Type.BOOKED, Flag.PENDING);
        appointmentRecords.put(appointment.getAppointmentId(), appointment);

        try {
            // Save the updated list of appointments
            service.save(new ArrayList<>(appointmentRecords.values()));
            System.out.println("Appointment scheduled successfully for " + date + " at " + selectedSlot);
        } catch (IOException e) {
            System.err.println("Error saving appointment: " + e.getMessage());
        }
    }

    // Method to reschedule an existing appointment
    public void rescheduleAppointment() {
        // Step 1: View current appointments and select one to reschedule
        viewAllScheduledAppointments();
        System.out.print("Enter the Appointment ID to reschedule: ");
        String appointmentId = sc.nextLine();

        if (!appointmentRecords.containsKey(appointmentId)) {
            System.out.println("Appointment not found.");
            return;
        }

        Appointment existingAppointment = appointmentRecords.get(appointmentId);

        // Step 2: Display available slots for rescheduling
        System.out.print("Enter new date for rescheduling (yyyy-MM-dd): ");
        LocalDate newDate = LocalDate.parse(sc.nextLine());

        System.out.println("Available slots for " + newDate + ":");
        List<LocalTime> availableSlots = getAvailableSlots(newDate);

        for (int i = 0; i < availableSlots.size(); i++) {
            LocalTime slot = availableSlots.get(i);
            System.out.println((i + 1) + ") " + slot + " - " + slot.plusHours(1));
        }

        // Step 3: Select a new slot and reschedule
        System.out.print("Select a slot number to reschedule: ");
        int slotChoice = sc.nextInt();
        sc.nextLine(); // Consume newline

        if (slotChoice < 1 || slotChoice > availableSlots.size()) {
            System.out.println("Invalid slot selection.");
            return;
        }

        LocalTime selectedSlot = availableSlots.get(slotChoice - 1);

        // Step 4: Remove old appointment and create a new one
        appointmentRecords.remove(appointmentId);
        Appointment newAppointment = createAppointment(patientId, existingAppointment.getDoctorId(), newDate, selectedSlot.toString(), Type.BOOKED, Flag.PENDING);
        appointmentRecords.put(newAppointment.getAppointmentId(), newAppointment);

        try {
            // Save the updated list of appointments
            service.save(new ArrayList<>(appointmentRecords.values()));
            System.out.println("Appointment rescheduled successfully to " + newDate + " at " + selectedSlot);
        } catch (IOException e) {
            System.err.println("Error saving rescheduled appointment: " + e.getMessage());
        }
    }

    // Method to view all scheduled appointments for the logged-in patient
    public void viewAllScheduledAppointments() {
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

    // Method to cancel a scheduled appointment
    public void cancelAppointment() {
        // Step 1: View all scheduled appointments for the patient
        viewAllScheduledAppointments();

        System.out.print("Enter the Appointment ID to cancel: ");
        String appointmentId = sc.nextLine();

        // Step 2: Check if the appointment exists and belongs to the patient
        if (!appointmentRecords.containsKey(appointmentId)) {
            System.out.println("Appointment not found.");
            return;
        }

        Appointment appointmentToCancel = appointmentRecords.get(appointmentId);

        if (!appointmentToCancel.getPatientId().equals(patientId)) {
            System.out.println("You are not authorized to cancel this appointment.");
            return;
        }

        // Step 3: Cancel the appointment by removing it from the records
        appointmentRecords.remove(appointmentId);

        try {
            // Save the updated list of appointments
            service.save(new ArrayList<>(appointmentRecords.values()));
            System.out.println("Appointment canceled successfully.");
        } catch (IOException e) {
            System.err.println("Error saving updated appointments: " + e.getMessage());
        }
    }

    // Helper method to get available slots for a specific date
    private List<LocalTime> getAvailableSlots(LocalDate date) {
        List<LocalTime> allSlots = generateTimeSlots();
        List<LocalTime> bookedSlots = new ArrayList<>();

        for (Appointment appointment : appointmentRecords.values()) {
            if (appointment.getDate().equals(date) && appointment.getAvailability() == Type.BOOKED) {
                bookedSlots.add(LocalTime.parse(appointment.getTimeSlot()));
            }
        }

        allSlots.removeAll(bookedSlots);
        return allSlots;
    }

    // Generates time slots from 9 AM to 5 PM, excluding lunch hour (12 PM - 1 PM)
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
}
