package AppointmentOutcomeSystem;

import AppointmentSystem.*;
import MedicineInventorySystem.*;
import SessionManager.Session;
import enums.Flag;
import java.io.IOException;
import java.util.*;

/**
 * Handles the process of recording appointment outcomes.
 * Includes creating new appointment outcome records, validating appointments,
 * and interacting with inventory and appointment services.
 */

public class RecordOutcome {

    /**
     * Map storing appointment outcome records, keyed by their appointment ID.
     */
    private Map<String, AppointmentOutcomeRecord> appointmentOutcomeRecords;

    /**
     * Service for managing appointments.
     */
    private AppointmentService appointmentService = new AppointmentService();

    /**
     * Map storing medication inventory, keyed by medication name.
     */
    private Map<String, MedicationInventory> inventory;

    /**
     * Controller for managing inventory operations.
     */
    private InventoryController inventoryController;

    /**
     * Scanner for user input.
     */
    static final Scanner sc = new Scanner(System.in);

    /**
     * Constructs a new {@code RecordOutcome} with the specified map of
     * appointment outcome records. Initializes the inventory controller and
     * retrieves the current inventory.
     *
     * @param appointmentOutcomeRecords the map of appointment outcome records
     *                                  to manage
     */
    public RecordOutcome(Map<String, AppointmentOutcomeRecord> appointmentOutcomeRecords) {
        this.inventory = new HashMap<>();
        this.appointmentOutcomeRecords = appointmentOutcomeRecords;
        this.inventoryController = new InventoryController();
        this.inventory = inventoryController.getInventory();
    }

    /**
     * Prompts the user to create a new appointment outcome record. Validates
     * the appointment ID, ensures the appointment is confirmed, and saves the
     * new record.
     *
     * @throws IOException if an error occurs during the process
     */
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

    /**
     * Prompts the user to input details for a new appointment outcome record.
     * Validates inputs such as appointment ID, service provided, diagnosis, and
     * prescription details.
     * Information that can be loaded from records are loaded dynamically
     *
     * @return a new {@link AppointmentOutcomeRecord} if successfully created,
     *         or {@code null} if canceled
     * @throws IOException if an error occurs during input or processing
     */
    public AppointmentOutcomeRecord prompts() throws IOException {
        String apptId = null;
        @SuppressWarnings("unchecked")
        List<Appointment> AppointmentList = (List<Appointment>) appointmentService.load();
        Appointment findRecord = null;

        // Validate appointment and user input
        while (true) {
            System.out.println("Enter AppointmentId: ");
            apptId = sc.nextLine().toUpperCase();
            findRecord = containsAppointmentId(AppointmentList, apptId);

            if (apptId.equalsIgnoreCase("x")) {
                return null; // Cancel operation
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
                System.out.println("An Outcome record for Appointment ID " + apptId + " already exists.");
                System.out.println("Press x to cancel");
                continue;
            }

            if (findRecord.getFlag() != Flag.CONFIRMED) {
                System.out.println("Appointment ID " + apptId + " is not confirmed.");
                System.out.println("Press x to cancel");
                continue;
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

            String prescriptionName;
            while (true) {
                System.out.println("Enter prescription Name: ");
                prescriptionName = sc.nextLine().trim();
                prescriptionName = prescriptionName.substring(0, 1).toUpperCase()
                        + prescriptionName.substring(1).toLowerCase();

                if (inventory.containsKey(prescriptionName)) {
                    break;
                }

                System.out.println(
                        "Error: Medication " + prescriptionName + " is not available in inventory. Please try again.");
            }

            System.out.println("Enter amount: ");
            while (!sc.hasNextInt()) {
                System.out.println("Invalid input. Please enter an integer.");
                sc.next();
            }
            Integer amt = sc.nextInt();
            sc.nextLine();

            System.out.println("Enter dosage: ");
            String dosage = sc.nextLine();

            Prescription prescription = new Prescription(prescriptionName, amt, dosage);
            List<Prescription> prescriptions = new ArrayList<>();
            prescriptions.add(prescription);

            changeFlagToCompleted(AppointmentList, apptId);
            appointmentService.save(AppointmentList);

            return new AppointmentOutcomeRecord(
                    apptId,
                    findRecord.getPatientId(),
                    findRecord.getDoctorId(),
                    findRecord.getDate(),
                    service,
                    diagnoses,
                    prescriptions);
        }

        System.out.println("Appointment not confirmed yet.");
        return null;
    }

    /**
     * Checks if a given appointment ID exists in the list of appointments.
     *
     * @param appointments  the list of appointments to search
     * @param appointmentId the appointment ID to look for
     * @return the matching {@link Appointment} object, or {@code null} if not
     *         found
     */
    private Appointment containsAppointmentId(List<Appointment> appointments, String appointmentId) {
        for (Appointment appointment : appointments) {
            if (appointment.getAppointmentId().equalsIgnoreCase(appointmentId)) {
                return appointment;
            }
        }
        return null;
    }

    /**
     * Updates the flag of an appointment to {@link Flag#COMPLETED}.
     *
     * @param appointments  the list of appointments to update
     * @param appointmentId the appointment ID whose flag needs to be updated
     * @return the updated list of appointments
     */
    private List<Appointment> changeFlagToCompleted(List<Appointment> appointments, String appointmentId) {
        for (Appointment appointment : appointments) {
            if (appointment.getAppointmentId().equalsIgnoreCase(appointmentId)) {
                appointment.setFlag(Flag.COMPLETED);
            }
        }
        return appointments;
    }
}
