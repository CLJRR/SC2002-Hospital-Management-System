
package AppointmentOutcomeSystem;

import AppointmentSystem.Appointment;
import AppointmentSystem.AppointmentService;
import MedicineInventorySystem.InventoryController;
import SessionManager.Session;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class RecordOutcome {

    private Map<String, AppointmentOutcomeRecord> appointmentOutcomeRecords;
    private AppointmentService appointmentService = new AppointmentService();
    private InventoryController inventoryController;
    private static final Scanner sc = new Scanner(System.in);

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
        List<Appointment> appointmentList = (List<Appointment>) appointmentService.load();
        Appointment findRecord = null;

        while (true) {
            System.out.println("Enter AppointmentId: ");
            apptId = sc.nextLine().toUpperCase();
            findRecord = containsAppointmentId(appointmentList, apptId);

            if (apptId.equalsIgnoreCase("x")) {
                break;
            }
            if (findRecord == null) {
                System.out.println("Appointment ID " + apptId + " does not exist.");
                System.out.println("Press x to cancel");
                continue;
            }
            if (!findRecord.getDoctorId().equalsIgnoreCase(Session.getLoginID())) {
                System.out.println("Appointment is under Doctor " + findRecord.getDoctorId());
                continue;
            }
            if (appointmentOutcomeRecords.containsKey(apptId)) {
                System.out.println("An outcome record for Appointment ID " + apptId + " already exists.");
                System.out.println("Press x to cancel");
                continue;
            }
            break;
        }

        System.out.println("Enter Service Provided: ");
        String service = sc.next();

            System.out.println("Enter Diagnoses (comma-separated): ");
            String diagnosisInput = sc.nextLine();
            List<String> diagnoses = Arrays.asList(diagnosisInput.split(", "));

        System.out.println("Current Medications: ");
        inventoryController.viewInventory();
        System.out.println("Enter Medicine Prescribed: ");
        String medName = sc.next();

            System.out.println("Enter amount: ");
            while (!sc.hasNextInt()) {
                System.out.println("Invalid input. Please enter an integer.");
                sc.next();
            }
            int amt = sc.nextInt();
            sc.nextLine();

        System.out.println("Enter dosage: ");
        String dosage = sc.next();

            // Create a Prescription object
            Prescription prescription = new Prescription(medName, amt, dosage);
            List<Prescription> prescriptions = new ArrayList<>();
            prescriptions.add(prescription);

            // Create and return an AppointmentOutcomeRecord instance
            return new AppointmentOutcomeRecord(
                    apptId, // Appointment ID
                    findRecord.getPatientId(), // Patient ID
                    Session.getLoginID(), // Doctor ID
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
            if (appointment.getAppointmentId().equals(appointmentId)) {
                return appointment;
            }
        }
        return null;
    }
}
