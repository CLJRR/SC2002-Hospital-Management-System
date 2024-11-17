package AppointmentSystem;

import enums.Flag;
import enums.Type;

import java.util.Map;
import java.util.Scanner;

public class AppointmentFlagUpdater {

    private Map<String, Appointment> appointmentRecords;
    private Appointment appointment;

    public AppointmentFlagUpdater(Map<String, Appointment> appointmentRecords) {
        this.appointmentRecords = appointmentRecords;

    }

    public void promptUpdateAppointmentFlag(String doctorId) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("Please enter Appointment ID to update (or type 'x' to exit): ");
            String appointmentId = sc.nextLine().toUpperCase();

            // Allow the user to exit
            if (appointmentId.equalsIgnoreCase("x")) {
                System.out.println("Update process exited.");
                return;
            }

            // Check if the appointment exists
            if (!appointmentRecords.containsKey(appointmentId)) {
                System.out.println("Error: Appointment not found. Please try again.");
                continue;
            }

            Appointment appointment = appointmentRecords.get(appointmentId);

            // Validate that the appointment belongs to the doctor
            if (!appointment.getDoctorId().equalsIgnoreCase(doctorId)) {
                System.out.println("Error: Appointment does not belong to Doctor " + doctorId + ".");
                return;
            }

            // Ensure the appointment is in PENDING status
            if (appointment.getFlag() != Flag.PENDING) {
                System.out.println("Error: Only PENDING appointments can be updated.");
                return;
            }

            // Ensure the appointment is of type APPOINTMENT
            if (appointment.getType() != Type.APPOINTMENT) {
                System.out.println("Error: You are not authorized to update this type of appointment.");
                return;
            }

            // Prompt for status update
            System.out.println("Choose an option to update appointment status:");
            System.out.println("1. Decline");
            System.out.println("2. Accept");

            int choice;
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter 1 or 2.");
                continue;
            }

            Flag newStatus;
            switch (choice) {
                case 1:
                    newStatus = Flag.CANCELLED;
                    break;
                case 2:
                    newStatus = Flag.CONFIRMED;
                    break;
                default:
                    System.out.println("Invalid choice. Please select 1 or 2.");
                    continue;
            }

            // Confirm the update
            System.out.println("You are about to update the appointment status to " + newStatus + ".");
            System.out.print("Confirm? (yes/no): ");
            String confirmation = sc.nextLine().toLowerCase();

            if (!confirmation.equals("yes")) {
                System.out.println("Update aborted.");
                return;
            }

            // Call method to update the appointment status
            updateAppointmentStatus(appointmentId, doctorId, newStatus);
            return; // Exit after successful update
        }
    }

    public void updateAppointmentStatus(String appointmentId, String doctorId, Flag newStatus) {
        Appointment appointment = appointmentRecords.get(appointmentId);
    
        // Validate that the appointment exists, belongs to the specified doctor, and is PENDING
        if (appointment == null) {
            System.out.println("Error: Appointment not found.");
            return;
        }
        if (!appointment.getDoctorId().equalsIgnoreCase(doctorId)) {
            System.out.println("Error: Appointment does not belong to Doctor " + doctorId + ".");
            return;
        }
        if (appointment.getFlag() != Flag.PENDING) {
            System.out.println("Error: Appointment is not in PENDING status and cannot be updated.");
            return;
        }
        if (appointment.getType() != Type.APPOINTMENT) {
            System.out.println("Error: You are not authorized to approve this appointment.");
            return;
        }
    
        // Update the appointment status to the selected new status
        appointment.setFlag(newStatus);
        System.out.println("Appointment status updated successfully to " + newStatus + ".");
    }
    

}
