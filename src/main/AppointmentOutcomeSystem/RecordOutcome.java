package AppointmentOutcomeSystem;

import AppointmentSystem.*;
import MedicineInventorySystem.*;
import SessionManager.Session;
import enums.Flag;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class RecordOutcome {

    private Map<String, AppointmentOutcomeRecord> appointmentOutcomeRecords;

    private AppointmentService appointmentService = new AppointmentService();
    private ApptLoader apptLoader;
    private ApptSaver apptSaver;

    @SuppressWarnings("unchecked")
    private Map<String, MedicationInventory> inventory;
    // private AppointmentController appointmentController;
    private InventoryController inventoryController;
    static final Scanner sc = new Scanner(System.in);

    public RecordOutcome(Map<String, AppointmentOutcomeRecord> appointmentOutcomeRecords) {
        this.inventory = new HashMap<>();
        this.appointmentOutcomeRecords = appointmentOutcomeRecords;
        this.inventoryController = new InventoryController();
        this.inventory = inventoryController.getInventory();
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
        // get appointment
        while (true) {
            System.out.println("Enter AppointmentId: ");
            apptId = sc.nextLine().toUpperCase();
            findRecord = containsAppointmentId(AppointmentList, apptId);

            // Check if the user wants to cancel
            if (apptId.equalsIgnoreCase("x")) {
                return null; // Cancel operation
            }

            // Check if the appointment exists
            if (findRecord == null) {
                System.out.println("Appointment ID " + apptId + " does not exist.");
                System.out.println("Press x to cancel");
                continue;
            }

            // Check if the appointment is assigned to the logged-in doctor
            if (!findRecord.getDoctorId().equalsIgnoreCase(Session.getLoginID())) {
                System.out.println("Appointment is under Doctor " + findRecord.getDoctorId());
                continue;
            }

            // Check if an outcome record already exists for this appointment
            if (appointmentOutcomeRecords.containsKey(apptId)) {
                System.out.println("An Outcome record for Appointment ID " + apptId + " already exists.");
                System.out.println("Press x to cancel");
                continue;
            }

            // Check if the appointment is confirmed
            if (findRecord.getFlag() != Flag.CONFIRMED) { // Ensure the appointment is confirmed
                System.out.println("Appointment ID " + apptId + " is not confirmed.");
                System.out.println("Press x to cancel");
                continue; // Re-prompt the user
            }

            break; // Exit the loop if all checks pass
        }

        if (findRecord.getFlag() == Flag.CONFIRMED) {
            System.out.println("Enter Service Provided: ");
            String service = sc.nextLine();

            System.out.println("Enter Diagnosis: ");
            String diagnosis = sc.nextLine();
            List<String> diagnoses = Arrays.asList(diagnosis.split(", "));

            System.out.println("Current Medications: ");
            inventoryController.viewInventory();
            String prescriptionName = null;
            while (true) {
                System.out.println("Enter prescription Name: ");
                prescriptionName = sc.nextLine().trim();
                prescriptionName = prescriptionName.substring(0, 1).toUpperCase() + prescriptionName.substring(1).toLowerCase();

                if (inventory.containsKey(prescriptionName)) { // Check if medication exists
                    break; // Exit loop if the medication exists in inventory
                }

                System.out.println("Error: Medication " + prescriptionName + " is not available in inventory. Please try again.");
            }
            System.out.println("Enter amount: ");
            while (!sc.hasNextInt()) { // Loop until an integer is entered
                System.out.println("Invalid input. Please enter an integer.");
                sc.next(); // Clear the invalid input
            }
            Integer amt = sc.nextInt();
            sc.nextLine(); // Consumes Newline

            System.out.println("Enter dosage: ");
            String dosage = sc.nextLine();

            // Create a Prescription object
            Prescription prescription = new Prescription(prescriptionName, amt, dosage);
            List<Prescription> prescriptions = new ArrayList<>();
            prescriptions.add(prescription);
            changeFlagToCompleted(AppointmentList, apptId);
            appointmentService.save(AppointmentList);
            // Create and return an AppointmentOutcomeRecord instance
            return new AppointmentOutcomeRecord(
                    apptId, // Appointment ID
                    findRecord.getPatientId(), // get patient Id
                    Session.getLoginID(), // Assuming this retrieves the logged-in doctor ID
                    findRecord.getDate(), // Appointment date
                    service, // Service provided
                    diagnoses, // Diagnoses (as List)
                    prescriptions // Prescriptions (as List)
            );
        }

        System.out.println("Appointment not confirmed yet.");
        return null;
    }

    private Appointment containsAppointmentId(List<Appointment> appointments, String appointmentId) {
        for (Appointment appointment : appointments) {
            if (appointment.getAppointmentId().equalsIgnoreCase(appointmentId)) {
                return appointment; // Found the appointment
            }
        }
        return null; // Not found
    }

    private List<Appointment> changeFlagToCompleted(List<Appointment> appointments, String appointmentId) {
        for (Appointment appointment : appointments) {
            if (appointment.getAppointmentId().equalsIgnoreCase(appointmentId)) {
                appointment.setFlag(Flag.COMPLETED);
            }
        }
        return appointments; // Not found
    }
}
