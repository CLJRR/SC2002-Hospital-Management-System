package AppointmentSystem;

import enums.Flag;
import java.util.Map;
import java.util.Scanner;

public class AppointmentFlagUpdater {

    private Map<String, Appointment> appointmentRecords;

    public AppointmentFlagUpdater(Map<String, Appointment> appointmentRecords) {
        this.appointmentRecords = appointmentRecords;

    }

    public void promptUpdateAppointmentFlag(String doctorId) {
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter Appointment Id to update:");
        String appointmentId = sc.next();

        System.out.println("Choose an option to update appointment status:");
        System.out.println("1. Reject");
        System.out.println("2. Confirm");

        System.out.println("Enter amount: ");
        while (!sc.hasNextInt()) { // Loop until an integer is entered
            System.out.println("Invalid input. Please enter an integer.");
            sc.next(); // Clear the invalid input
        }
        int choice = sc.nextInt();
        sc.nextLine(); // Consumes newline

        Flag newStatus = null;

        switch (choice) {
            case 1:
                newStatus = Flag.REJECTED;
                break;
            case 2:
                newStatus = Flag.CONFIRMED;
                break;
            default:
                System.out.println("Invalid choice. Please select 1 or 2.");
                return;
        }

        // Call method to update the appointment status
        updateAppointmentStatus(appointmentId, doctorId, newStatus);
    }

    // Method to update the status of a PENDING appointment
    public void updateAppointmentStatus(String appointmentId, String doctorId, Flag newStatus) {
        Appointment appointment = appointmentRecords.get(appointmentId);

        // Validate that the appointment exists, belongs to the specified doctor, and is
        // PENDING
        if (appointment == null) {
            System.out.println("Appointment not found.");
            return;
        }
        if (!appointment.getDoctorId().equalsIgnoreCase(doctorId)) {
            System.out.println("Appointment does not belong to Doctor " + doctorId + ".");
            return;
        }
        if (appointment.getFlag() != Flag.PENDING) {
            System.out.println("Appointment is not in PENDING status and cannot be updated.");
            return;
        }

        // Update the appointment status to the selected new status (REJECTED or
        // CONFIRMED
        appointment.setFlag(newStatus);
    }
}
