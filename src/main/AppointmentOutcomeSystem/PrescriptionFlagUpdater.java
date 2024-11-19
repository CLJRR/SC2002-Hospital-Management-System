/**
 * Handles the updating of prescription flags for appointment outcome records.
 * Supports operations such as marking prescriptions as DISPENSED or REJECTED,
 * and ensures updates are reflected in the inventory system.
 */
package AppointmentOutcomeSystem;

import MedicineInventorySystem.InventoryController;
import enums.Flag;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public class PrescriptionFlagUpdater {

    /**
     * Map containing appointment outcome records, keyed by their appointment ID.
     */
    private Map<String, AppointmentOutcomeRecord> appointmentOutcomeRecords;

    /**
     * Controller responsible for managing the inventory of medicines.
     */
    private InventoryController invController;

    /**
     * Viewer responsible for displaying appointment outcome records.
     */
    private AppointmentOutcomeRecordsViewer viewer;

    /**
     * Scanner for user input during the update process.
     */
    private static final Scanner sc = new Scanner(System.in);

    /**
     * Constructs a new {@code PrescriptionFlagUpdater} with the specified appointment outcome records.
     *
     * @param appointmentOutcomeRecords the map of appointment outcome records to update
     */
    public PrescriptionFlagUpdater(Map<String, AppointmentOutcomeRecord> appointmentOutcomeRecords) {
        this.appointmentOutcomeRecords = appointmentOutcomeRecords;
        this.invController = new InventoryController();
        this.viewer = new AppointmentOutcomeRecordsViewer(appointmentOutcomeRecords);
    }

    /**
     * Updates the flags of prescriptions in appointment outcome records.
     * Provides options to mark prescriptions as DISPENSED or REJECTED.
     * Updates inventory stock when marking prescriptions as DISPENSED.
     *
     * @throws IOException if an error occurs during the update process
     */
    public void updatePrescriptionFlag() throws IOException {
        System.out.println("Pending Records:");
        viewer.viewPendingRecords();

        System.out.println("1) Dispense Prescription");
        System.out.println("2) Reject Prescription");
        System.out.println("3) Go back");
        int option = 0;

        while (option != 3) {
            // Validate user input
            while (!sc.hasNextInt()) {
                System.out.println("Invalid input. Please enter an integer.");
                sc.next();
            }
            option = sc.nextInt();
            sc.nextLine(); // Consume newline character

            switch (option) {
                case 1 -> {
                    System.out.println("Please enter Appt Id: ");
                    String apptId = sc.next().toUpperCase();
                    AppointmentOutcomeRecord record = appointmentOutcomeRecords.get(apptId);

                    if (record != null) {
                        boolean allUpdated = true;
                        boolean foundPending = false;

                        // Process each pending prescription
                        for (Prescription prescription : record.getPrescriptions()) {
                            if (prescription.getFlag() == Flag.PENDING) {
                                foundPending = true;
                                if (invController.decreaseStock(prescription.getMedName(), prescription.getAmount())) {
                                    prescription.setFlag(Flag.DISPENSED);
                                    System.out.println("Prescription " + prescription.getMedName()
                                            + " for " + apptId + " updated to DISPENSED.");
                                } else {
                                    allUpdated = false;
                                    System.out.println("Unable to update prescription " + prescription.getMedName()
                                            + " for " + apptId + ".");
                                }
                            }
                        }

                        if (!foundPending) {
                            System.out.println("No pending prescriptions found for " + apptId + ".");
                        } else if (allUpdated) {
                            System.out.println("All pending prescriptions for " + apptId + " updated to DISPENSED.");
                        } else {
                            System.out.println("Some prescriptions for " + apptId + " could not be updated.");
                        }
                    } else {
                        System.out.println("Appointment ID " + apptId + " not found.");
                    }

                    viewer.viewPendingRecords();
                    System.out.println("1) Update another record");
                    System.out.println("2) Reject Prescription");
                    System.out.println("3) Go back");
                }
                case 2 -> {
                    System.out.println("Please enter Appt Id: ");
                    String apptId = sc.next().toUpperCase();
                    AppointmentOutcomeRecord record = appointmentOutcomeRecords.get(apptId);

                    if (record != null) {
                        boolean foundPending = false;

                        // Process each pending prescription
                        for (Prescription prescription : record.getPrescriptions()) {
                            if (prescription.getFlag() == Flag.PENDING) {
                                foundPending = true;
                                System.out.println("Do you want to reject the prescription for " + prescription.getMedName() + "? (yes/no)");
                                String response = sc.next().toLowerCase();

                                if (response.equalsIgnoreCase("yes")) {
                                    prescription.setFlag(Flag.REJECTED);
                                    System.out.println("Prescription " + prescription.getMedName() + " for " + apptId + " has been rejected.");
                                } else {
                                    System.out.println("Prescription " + prescription.getMedName() + " was not rejected.");
                                }
                            }
                        }

                        if (!foundPending) {
                            System.out.println("No pending prescriptions found for " + apptId + ".");
                        }
                    } else {
                        System.out.println("Appointment ID " + apptId + " not found.");
                    }

                    viewer.viewPendingRecords();
                    System.out.println("1) Update another record");
                    System.out.println("2) Reject Prescription");
                    System.out.println("3) Go back");
                }
                case 3 -> {
                    break;
                }
                default -> System.err.println("Invalid key");
            }
        }
    }
}
