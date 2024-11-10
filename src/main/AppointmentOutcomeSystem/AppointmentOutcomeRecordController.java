package AppointmentOutcomeSystem;

import MedicineInventorySystem.InventoryController;
import SessionManager.Session;
import enums.Flag;
import java.io.IOException;
import java.util.*;

public class AppointmentOutcomeRecordController {

    private Map<String, AppointmentOutcomeRecord> appointmentOutcomeRecords;
    private AppointmentOutcomeRecordLoader loader;
    private AppointmentOutcomeRecordSaver saver;
    private AppointmentOutcomeRecordsViewer viewer;
    private InventoryController invController;
    static final Scanner sc = new Scanner(System.in);
    private RecordAppointmentOutcomePrompts prompts;

    public AppointmentOutcomeRecordController() {
        this.appointmentOutcomeRecords = new HashMap<>();
        this.loader = new AppointmentOutcomeRecordLoader(appointmentOutcomeRecords);
        this.saver = new AppointmentOutcomeRecordSaver(appointmentOutcomeRecords);
        this.viewer = new AppointmentOutcomeRecordsViewer(appointmentOutcomeRecords);
        this.invController = new InventoryController();
        loader.loadInitialAppointmentOutcomes();
        this.prompts = new RecordAppointmentOutcomePrompts();

    }

    public void loadRecords() {
        loader.loadInitialAppointmentOutcomes();
    }

    public void saveRecords() {
        saver.saveRecords();
    }

    public void viewAllRecords() {
        viewer.viewAllRecords();
    }

    public void viewPendingRecords() throws IOException {
        viewer.viewPendingRecords();
    }

    public void patientViewPastRecords() throws IOException {
        loader.loadInitialAppointmentOutcomes();
        viewer.viewRecordsById(Session.getLoginID());

    }

    public void recordAppointmentOutcome() throws IOException {
        AppointmentOutcomeRecord newRecord = prompts.prompts();
        appointmentOutcomeRecords.put(newRecord.getApptId(), newRecord);
        saver.saveRecords();
    }

    public void updatePrescriptionStatus() throws IOException {
        System.out.println("Pending Records:");
        viewPendingRecords();
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
                    if (invController.decreaseStock(record.getPrescription().getMedName(), record.getPrescription().getAmount())) {
                        appointmentOutcomeRecords.remove(apptId);
                        record.setStatus(Flag.DISPENSED);
                        appointmentOutcomeRecords.put(apptId, record);


                        
                        saver.saveRecords();

                    }
                    viewPendingRecords();
                    System.out.println("1) Update Record");
                    System.out.println("2) Go back");
                    break;
                }
                case 2 -> {
                    break;
                }
                default -> {
                    System.err.println("Invalid key");
                }
            }
        }
    }
}
