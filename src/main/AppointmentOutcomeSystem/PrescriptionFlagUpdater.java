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

        System.out.println("1) Update Record");
        System.out.println("2) Go back");
        int option = 0;

        while (option != 2) {
            while (!sc.hasNextInt()) { // Loop until an integer is entered
                System.out.println("Invalid input. Please enter an integer.");
                sc.next(); // Clear the invalid input
            }
            option = sc.nextInt(); // Read the integer after validation
            switch (option) {
                case 1 -> {
                    System.out.println("Please enter Appt Id: ");
                    String apptId = sc.next().toUpperCase();
                    AppointmentOutcomeRecord record = appointmentOutcomeRecords.get(apptId);

                    if (record != null && invController.decreaseStock(record.getPrescription().getMedName(), record.getPrescription().getAmount())) {
                        record.setFlag(Flag.DISPENSED);
                        System.out.println("Prescription for " + apptId + " updated to DISPENSED.");
                    } else {
                        System.out.println("Unable to update prescription for " + apptId + ".");
                    }
                    viewer.viewPendingRecords();
                    System.out.println("1) Update another record");
                    System.out.println("2) Go back");
                }
                case 2 -> {
                    break;
                }
                default ->
                    System.err.println("Invalid key");
            }
        }
    }
}
