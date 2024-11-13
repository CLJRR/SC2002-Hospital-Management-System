package ApptTest;

import SessionManager.Session;
import enums.Flag;
import java.io.IOException;
import java.util.*;

public class AppointmentController {

    private Map<String, Appointment> appointmentRecords;
    private ApptLoader loader;
    private ApptSaver saver;
    private ApptViewer viewer;
    static final Scanner sc = new Scanner(System.in);

    public AppointmentController() {
        this.appointmentRecords = new HashMap<>();
        this.loader = new ApptLoader(appointmentRecords);
        this.saver = new ApptSaver(appointmentRecords);
        this.viewer = new ApptViewer(appointmentRecords);
        loader.loadInitialAppointments();
    }

    // Method to load appointment records
    public void loadRecords() {
        loader.loadInitialAppointments();
    }

    // Method to save appointment records
    public void saveRecords() {
        saver.saveRecords();
    }

    //view all records
    public void viewAllRecords() throws IOException {
        viewer.viewAllRecords();
    }

    //ADMIN
    public void adminViewAllRecords() throws IOException {
        viewer.adminViewAllRecords();
    }

    //Doctor
    public void viewPendingRecords() throws IOException {
        viewer.viewPendingRecords();
    }

    // Method for a patient to view their past appointment records
    public void patientViewPastRecords() throws IOException {
        loader.loadInitialAppointments();
        viewer.viewRecordsById(Session.getLoginID());
    }

    // Method for a doctor to record a new appointment outcome
    public void recordAppointmentOutcome() throws IOException {
        System.out.println("Recording a new appointment outcome:");
        System.out.print("Enter appointment ID: ");
        String apptId = sc.next().toUpperCase();

        if (appointmentRecords.containsKey(apptId)) {
            Appointment appointment = appointmentRecords.get(apptId);
            System.out.println("Enter new Flag (CONFIRMED/PENDING/COMPLETED): ");
            String newFlag = sc.next().toUpperCase();

            try {
                Flag flag = Flag.valueOf(newFlag);
                appointment.setFlag(flag);
                appointmentRecords.put(apptId, appointment);
                saver.saveRecords();
                System.out.println("Appointment outcome recorded successfully.");
            } catch (IllegalArgumentException e) {
                System.err.println("Invalid Flag entered.");
            }
        } else {
            System.out.println("Appointment ID not found.");
        }
    }

    public void AdminViewAllAppointments() throws IOException {

    }

    // Method for an admin to update appointment details
    // public void updateAppointmentDetails() throws IOException {
    //     System.out.println("Enter the appointment ID to update: ");
    //     String apptId = sc.next().toUpperCase();
    //     if (appointmentRecords.containsKey(apptId)) {
    //         Appointment appointment = appointmentRecords.get(apptId);
    //         System.out.println("Enter new flag (AVAILABLE/BOOKED/CANCELLED): ");
    //         String availabilityInput = sc.next().toUpperCase();
    //         try {
    //             Availability flag = Availability.valueOf(availabilityInput);
    //             appointment.setAvailability(flag);
    //             appointmentRecords.put(apptId, appointment);
    //             saver.saveRecords();
    //             System.out.println("Appointment updated successfully.");
    //         } catch (IllegalArgumentException e) {
    //             System.err.println("Invalid flag entered.");
    //         }
    //     } else {
    //         System.out.println("Appointment ID not found.");
    //     }
    // }
}
