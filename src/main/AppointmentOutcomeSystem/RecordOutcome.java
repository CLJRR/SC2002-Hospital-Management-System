package AppointmentOutcomeSystem;

import AppointmentSystem.Appointment;
import AppointmentSystem.AppointmentController;
import MedicineInventorySystem.*;
import SessionManager.Session;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public class RecordOutcome {

    private Map<String, AppointmentOutcomeRecord> appointmentOutcomeRecords;
    private AppointmentController appointmentController;
    private InventoryController inventoryController;
    static final Scanner sc = new Scanner(System.in);

    public RecordOutcome(Map<String, AppointmentOutcomeRecord> appointmentOutcomeRecords) {
        this.appointmentOutcomeRecords = appointmentOutcomeRecords;
        this.appointmentController = new AppointmentController();
        this.inventoryController = new InventoryController();
    }

    public void recordOutcome() throws IOException {
        AppointmentOutcomeRecord newRecord = prompts();
        if (newRecord != null) {
            if (appointmentOutcomeRecords.containsKey(newRecord.getApptId())) {
                System.out.println("Record creation skipped due to duplicate Appointment ID.");
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
        Appointment findRecord = null;
        //get appointment
        while (true) {
            System.out.println("Enter AppointmentId: ");
            apptId = sc.next();
            findRecord = appointmentController.getAppointmentById(apptId);
            // Check if Id exists in appt database
            if (findRecord == null) {
                System.out.println("Appointment ID " + apptId + " does not exist .");
                continue;
            }
            // Check for duplicate appointment ID
            if (appointmentOutcomeRecords.containsKey(apptId)) {
                System.out.println("A Outcome record for Appointment ID " + apptId + " already exists.");
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
}
