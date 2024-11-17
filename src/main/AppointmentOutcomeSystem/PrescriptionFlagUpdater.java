package AppointmentOutcomeSystem;

import MedicineInventorySystem.InventoryController;
import enums.Flag;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public class PrescriptionFlagUpdater {

    private Map<String, AppointmentOutcomeRecord> appointmentOutcomeRecords;
    private InventoryController invController;
    private AppointmentOutcomeRecordsViewer viewer;

    private static final Scanner sc = new Scanner(System.in);

    public PrescriptionFlagUpdater(Map<String, AppointmentOutcomeRecord> appointmentOutcomeRecords) {
        this.appointmentOutcomeRecords = appointmentOutcomeRecords;
        this.invController = new InventoryController();
        this.viewer = new AppointmentOutcomeRecordsViewer(appointmentOutcomeRecords);
    }

    public void updatePrescriptionFlag() throws IOException {
        System.out.println("Pending Records:");
        viewer.viewPendingRecords();

        System.out.println("1) Dispense Prescription");
        System.out.println("2) Reject Prescription");
        System.out.println("3) Go back");
        int option = 0;

        while (option != 3) {
            while (!sc.hasNextInt()) { // Loop until an integer is entered
                System.out.println("Invalid input. Please enter an integer.");
                sc.next(); // Clear the invalid input
            }
            option = sc.nextInt(); // Read the integer after validation
            sc.nextLine(); // Consumes Newline

            switch (option) {
                case 1 -> {
                    System.out.println("Please enter Appt Id: ");
                    String apptId = sc.next().toUpperCase();
                    AppointmentOutcomeRecord record = appointmentOutcomeRecords.get(apptId);

                    if (record != null) {
                        boolean allUpdated = true;
                        boolean foundPending = false;

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
                default ->
                    System.err.println("Invalid key");
            }
        }
    }
}
