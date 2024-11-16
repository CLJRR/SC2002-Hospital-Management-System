package AppointmentOutcomeSystem;

import AppointmentSystem.*;
import MedicineInventorySystem.*;
import SessionManager.Session;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class RecordOutcome {

    private Map<String, AppointmentOutcomeRecord> appointmentOutcomeRecords;

    private AppointmentService appointmentService = new AppointmentService();
    @SuppressWarnings("unchecked")

    // private AppointmentController appointmentController;
    private InventoryController inventoryController;
    static final Scanner sc = new Scanner(System.in);

    public RecordOutcome(Map<String, AppointmentOutcomeRecord> appointmentOutcomeRecords) {
        this.appointmentOutcomeRecords = appointmentOutcomeRecords;
        this.inventoryController = new InventoryController();
    }

    public void recordOutcome() throws IOException {
        AppointmentOutcomeRecord newRecord = prompts();

        if (newRecord != null) {
            if (appointmentOutcomeRecords.containsKey(newRecord.getApptId())) {
                System.out.println("Record creation cancelled");
            } else {
                appointmentOutcomeRecords.put(newRecord.getApptId(), newRecord);
                System.out.println("New appointment outcome record saved successfully.");
            }
        } else {
            System.out.println("No new record to save.");
        }
    }

    public AppointmentOutcomeRecord prompts() throws IOException {
        String apptId = null;
        @SuppressWarnings("unchecked")
        List<Appointment> AppointmentList = (List<Appointment>) appointmentService.load();
        Appointment findRecord = null;
        //get appointment
        while (true) {
            System.out.println("Enter AppointmentId: ");
            apptId = sc.next();
            findRecord = containsAppointmentId(AppointmentList, apptId);
            // Check if Id exists in appt database
            if (apptId.equalsIgnoreCase("x")) {
                break;
            }
            if (findRecord == null) {
                System.out.println("Appointment ID " + apptId + " does not exist .");
                System.out.println("Press x to cancel");
                continue;
            }
            if (!findRecord.getDoctorId().equalsIgnoreCase(Session.getLoginID())) {
                System.out.println("Appointment is under Doctor " + findRecord.getDoctorId());
                continue;

            }
            // Check for duplicate appointment ID
            if (appointmentOutcomeRecords.containsKey(apptId)) {
                System.out.println("A Outcome record for Appointment ID " + apptId + " already exists.");
                System.out.println("Press x to cancel");
                continue; // Exit without creating a new record
            }
            break;
        }

        System.out.println("Enter Service Provided: ");
        String service = sc.next();

        System.out.println("Enter Diagnosis: ");
        String diagnosis = sc.next();

        System.out.println("Current Medications: ");
        inventoryController.viewInventory();
        System.out.println("Enter Medicine Prescribed: ");
        String medName = sc.next();

        System.out.println("Enter amount: ");
        Integer amt = sc.nextInt();

        System.out.println("Enter dosage: ");
        String dosage = sc.next();

        // Create a Prescription object
        Prescription prescription = new Prescription(medName, amt, dosage);

        // Create and return an AppointmentOutcomeRecord instance
        return new AppointmentOutcomeRecord(
                apptId, // Appointment ID
                findRecord.getPatientId(), // get patient Id
                Session.getLoginID(), // Assuming this retrieves the logged-in doctor ID
                findRecord.getDate(), // Appointment date
                service, // Service provided
                diagnosis, // Diagnosis
                prescription // Prescription object
        );
    }

    private Appointment containsAppointmentId(List<Appointment> appointments, String appointmentId) {
        for (Appointment appointment : appointments) {
            if (appointment.getAppointmentId().equals(appointmentId)) {
                return appointment; // Found the appointment
            }
        }
        return null; // Not found
    }
}
