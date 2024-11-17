package AppointmentSystem;

import enums.Flag;
import enums.Type;
import java.time.LocalDate;
import java.util.*;

public class PatientAppointmentScheduler {

    private Map<String, Appointment> appointmentRecords;

    public PatientAppointmentScheduler(Map<String, Appointment> appointmentRecords) {
        this.appointmentRecords = appointmentRecords;
    }

    private static final Scanner sc = new Scanner(System.in);
    private PatientApptViewer patientApptViewer;
    // private String patientId; // The ID of the logged-in patient
    private String doctorId;

    // public PatientAppointmentScheduler(Map<String, Appointment>
    // appointmentRecords, String patientId) {
    // this.appointmentRecords = appointmentRecords;
    // this.service = new AppointmentService();
    // this.patientId = patientId;

    // try {
    // // Load initial appointments into the map
    // @SuppressWarnings("unchecked")
    // List<Appointment> loadedAppointments = (List<Appointment>) service.load();
    // for (Appointment appointment : loadedAppointments) {
    // appointmentRecords.put(appointment.getAppointmentId(), appointment);
    // }
    // } catch (IOException e) {
    // System.err.println("Error loading initial appointments: " + e.getMessage());
    // }
    // }

    // // Method to create a new appointment
    // public Appointment createAppointment(String patientId, String doctorId,
    // LocalDate date, String timeSlot, Type type, Flag flag) {
    // String appointmentId = UUID.randomUUID().toString(); // Generates a unique ID
    // return new Appointment(appointmentId, patientId, doctorId, date, timeSlot,
    // type, flag);
    // }

    // // Method to view available slots and schedule a new appointment
    // public void viewAndScheduleAppointment() {
    // System.out.print("Enter the desired date for the appointment (yyyy-MM-dd):
    // ");
    // LocalDate date = LocalDate.parse(sc.nextLine());

    // List<LocalTime> availableSlots = getAvailableSlots(date);
    // if (availableSlots.isEmpty()) {
    // System.out.println("No available slots for " + date);
    // return;
    // }

    // System.out.println("Available slots for " + date + ":");
    // for (int i = 0; i < availableSlots.size(); i++) {
    // LocalTime slot = availableSlots.get(i);
    // System.out.println((i + 1) + ") " + slot + " - " + slot.plusHours(1));
    // }

    // System.out.print("Select a slot number to schedule: ");
    // int slotChoice = sc.nextInt();
    // sc.nextLine(); // Consume newline

    // if (slotChoice < 1 || slotChoice > availableSlots.size()) {
    // System.out.println("Invalid slot selection.");
    // return;
    // }

    // LocalTime selectedSlot = availableSlots.get(slotChoice - 1);

    // System.out.print("Enter Doctor ID: ");
    // String doctorId = sc.nextLine();

    // // Schedule appointment
    // Appointment appointment = createAppointment(patientId, doctorId, date,
    // selectedSlot.toString(), Type.BOOKED, Flag.PENDING);
    // appointmentRecords.put(appointment.getAppointmentId(), appointment);

    // try {
    // // Save the updated list of appointments
    // service.save(new ArrayList<>(appointmentRecords.values()));
    // System.out.println("Appointment scheduled successfully for " + date + " at "
    // + selectedSlot);
    // } catch (IOException e) {
    // System.err.println("Error saving appointment: " + e.getMessage());
    // }
    // }

    // System.out.print("Enter Doctor ID: ");
    // String doctorId = sc.nextLine();

    // Method to schedule a new appointment using a selected timeslot
    public void scheduleAppointment(String patientId, LocalDate date) {
        List<String> availableSlots = getAvailableSlots(date);
        if (availableSlots == null || availableSlots.isEmpty()) {
            System.out.println("No slots available to schedule.");
            return;
        }

        System.out.print("Select a slot number to schedule: ");
        while (!sc.hasNextInt()) { // Loop until an integer is entered
            System.out.println("Invalid input. Please enter an integer.");
            sc.next(); // Clear the invalid input
        }
        int slotChoice = sc.nextInt();
        sc.nextLine(); // Consume newline

        if (slotChoice < 1 || slotChoice > availableSlots.size()) {
            System.out.println("Invalid slot selection.");
            return;
        }

        String selectedSlot = availableSlots.get(slotChoice);

        String appointmentId = generateAppointmentId();

        // Schedule appointment
        Appointment appointment = new Appointment(appointmentId, patientId, doctorId, date, selectedSlot.toString(),
                Type.APPOINTMENT, Flag.PENDING);
        appointmentRecords.put(appointment.getAppointmentId(), appointment);

        System.out.println("Appointment scheduled successfully for " + date + " at " + selectedSlot);
    }

    // Method to reschedule an existing appointment
    public void rescheduleAppointment(String patientId) {
        // View current appointments and select one to reschedule
        patientApptViewer.viewAllScheduledAppointments(patientId);
        System.out.print("Enter the Appointment ID to reschedule: ");
        String appointmentId = sc.next();

        if (!appointmentRecords.containsKey(appointmentId)) {
            System.out.println("Appointment not found.");
            return;
        }

        Appointment existingAppointment = appointmentRecords.get(appointmentId);

        // Display available slots for rescheduling
        System.out.print("Enter new date for rescheduling in yyyy-mm-dd format): ");
        LocalDate newDate = LocalDate.parse(sc.nextLine());

        System.out.println("Available slots for " + newDate + ":");
        List<String> availableSlots = getAvailableSlots(newDate);

        for (int i = 0; i < availableSlots.size(); i++) {
            String slot = availableSlots.get(i);
            System.out.println((i + 1) + ") " + slot);
        }

        // Select a new slot and reschedule
        System.out.print("Select a slot number to reschedule: ");
        while (!sc.hasNextInt()) { // Loop until an integer is entered
            System.out.println("Invalid input. Please enter an integer.");
            sc.next(); // Clear the invalid input
        }
        int slotChoice = sc.nextInt();
        sc.nextLine(); // Consume newline

        if (slotChoice < 1 || slotChoice > availableSlots.size()) {
            System.out.println("Invalid slot selection.");
            return;
        }

        String selectedSlot = availableSlots.get(slotChoice - 1);

        // Step 4: Remove old appointment and create a new one
        appointmentRecords.remove(appointmentId);
        Appointment appointment = new Appointment(appointmentId, patientId, existingAppointment.getDoctorId(), newDate,
                selectedSlot.toString(), Type.APPOINTMENT, Flag.PENDING);
        appointmentRecords.put(appointment.getAppointmentId(), appointment);

        // updated list of appointments
        System.out.println("Appointment rescheduled successfully to " + newDate + " at " + selectedSlot);
    }

    // Method to cancel a scheduled appointment
    public void cancelAppointment(String patientId) {
        // View all scheduled appointments for the patient
        patientApptViewer.viewAllScheduledAppointments(patientId);

        System.out.print("Enter the Appointment ID to cancel: ");
        String appointmentId = sc.next();

        // Check if the appointment exists and belongs to the patient
        if (!appointmentRecords.containsKey(appointmentId)) {
            System.out.println("Appointment not found.");
            return;
        }

        Appointment appointmentToCancel = appointmentRecords.get(appointmentId);

        if (!appointmentToCancel.getPatientId().equals(patientId)) {
            System.out.println("You cannot cancel this appointment.");
            return;
        }

        // Cancel appointment by removing it from records
        appointmentRecords.remove(appointmentId);

        // Save the updated list of appointments
        System.out.println("Appointment canceled successfully.");
    }

    // Helper method to generate an appointment ID
    private String generateAppointmentId() {
        int nextId = appointmentRecords.size() + 1;
        return "A" + nextId;
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

    // // Generates time slots from 9 AM to 5 PM, excluding lunch hour (12 PM - 1
    // PM)
    // private List<LocalTime> generateTimeSlots() {
    // List<LocalTime> slots = new ArrayList<>();
    // LocalTime startTime = LocalTime.of(9, 0);
    // LocalTime endTime = LocalTime.of(17, 0);

    // while (startTime.isBefore(endTime)) {
    // if (!startTime.equals(LocalTime.of(12, 0))) { // Exclude 12 PM - 1 PM lunch
    // hour
    // slots.add(startTime);
    // }
    // startTime = startTime.plusHours(1);
    // }
    // return slots;
    // }

}

// Schedule
// Reschedule
// Cancel