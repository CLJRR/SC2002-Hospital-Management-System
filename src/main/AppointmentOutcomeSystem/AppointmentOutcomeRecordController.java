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
    private RecordOutcome recordAppointmentOutcome;

    public AppointmentOutcomeRecordController() {
        this.appointmentOutcomeRecords = new HashMap<>();
        this.loader = new AppointmentOutcomeRecordLoader(appointmentOutcomeRecords);
        this.saver = new AppointmentOutcomeRecordSaver(appointmentOutcomeRecords);
        this.viewer = new AppointmentOutcomeRecordsViewer(appointmentOutcomeRecords);
        this.prescriptionFlagUpdater = new PrescriptionFlagUpdater(appointmentOutcomeRecords);
        loader.loadInitialAppointmentOutcomes();
        this.recordAppointmentOutcome = new RecordOutcome(appointmentOutcomeRecords);

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
        loadRecords();
        recordAppointmentOutcome.recordOutcome();
        saveRecords();
    }

    public void updatePrescriptionFlag() throws IOException {
        loadRecords();
        prescriptionFlagUpdater.updatePrescriptionFlag();
        saveRecords(); // Save updates after modifying flags
    }
}
