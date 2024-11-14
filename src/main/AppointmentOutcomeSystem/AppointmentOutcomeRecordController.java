package AppointmentOutcomeSystem;

import SessionManager.Session;
import java.io.IOException;
import java.util.*;

public class AppointmentOutcomeRecordController {

    private Map<String, AppointmentOutcomeRecord> appointmentOutcomeRecords;
    private AppointmentOutcomeRecordLoader loader;
    private AppointmentOutcomeRecordSaver saver;
    private AppointmentOutcomeRecordsViewer viewer;
    private PrescriptionFlagUpdater prescriptionFlagUpdater;
    static final Scanner sc = new Scanner(System.in);
    private RecordAppointmentOutcomePrompts recordAppointmentOutcome;

    public AppointmentOutcomeRecordController() {
        this.appointmentOutcomeRecords = new HashMap<>();
        this.loader = new AppointmentOutcomeRecordLoader(appointmentOutcomeRecords);
        this.saver = new AppointmentOutcomeRecordSaver(appointmentOutcomeRecords);
        this.viewer = new AppointmentOutcomeRecordsViewer(appointmentOutcomeRecords);
        this.prescriptionFlagUpdater = new PrescriptionFlagUpdater(appointmentOutcomeRecords);
        loader.loadInitialAppointmentOutcomes();
        this.recordAppointmentOutcome = new RecordAppointmentOutcomePrompts();

    }

    public void loadRecords() {
        loader.loadInitialAppointmentOutcomes();
    }

    public void saveRecords() {
        saver.saveRecords();
    }

    public void viewAllRecords() {
        loadRecords();
        viewer.viewAllRecords();
    }

    public void viewPendingRecords() throws IOException {
        loadRecords();
        viewer.viewPendingRecords();
    }

    public void patientViewPastRecords() throws IOException {
        loadRecords();
        viewer.viewRecordsById(Session.getLoginID());

    }

    public boolean adminViewRecords(String apptId) throws IOException {
        loadRecords();
        return (viewer.viewRecordsById(apptId));
    }

    public void recordAppointmentOutcome() throws IOException {
        AppointmentOutcomeRecord newRecord = recordAppointmentOutcome.prompts();

        if (newRecord != null) {
            appointmentOutcomeRecords.put(newRecord.getApptId(), newRecord);
            saveRecords(); // Save after adding the new record
            System.out.println("New appointment outcome record saved successfully.");
        } else {
            System.out.println("Record creation skipped due to duplicate Appointment ID.");
        }

    }

    public void updatePrescriptionFlag() throws IOException {
        loadRecords();
        prescriptionFlagUpdater.updatePrescriptionFlag();
        saveRecords(); // Save updates after modifying flags
    }
}
