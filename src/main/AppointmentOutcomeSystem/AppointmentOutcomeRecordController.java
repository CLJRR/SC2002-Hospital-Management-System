/**
 * Controller class for managing appointment outcome records. 
 * Handles loading, saving, viewing, and updating records.
 */
package AppointmentOutcomeSystem;

import SessionManager.Session;
import java.io.IOException;
import java.util.*;

public class AppointmentOutcomeRecordController {

    /**
     * A map storing appointment outcome records with appointment ID as the key.
     */
    private Map<String, AppointmentOutcomeRecord> appointmentOutcomeRecords;

    /**
     * Responsible for loading appointment outcome records.
     */
    private AppointmentOutcomeRecordLoader loader;

    /**
     * Responsible for saving appointment outcome records.
     */
    private AppointmentOutcomeRecordSaver saver;

    /**
     * Responsible for viewing appointment outcome records.
     */
    private AppointmentOutcomeRecordsViewer viewer;

    /**
     * Responsible for updating the flag of prescriptions within appointment records.
     */
    private PrescriptionFlagUpdater prescriptionFlagUpdater;

    /**
     * Scanner object for user input.
     */
    static final Scanner sc = new Scanner(System.in);

    /**
     * Handles the recording of appointment outcomes.
     */
    private RecordOutcome recordAppointmentOutcome;

    /**
     * Constructs a new {@code AppointmentOutcomeRecordController} and initializes its components.
     * Loads initial appointment outcome records upon creation.
     */
    public AppointmentOutcomeRecordController() {
        this.appointmentOutcomeRecords = new HashMap<>();
        this.loader = new AppointmentOutcomeRecordLoader(appointmentOutcomeRecords);
        this.saver = new AppointmentOutcomeRecordSaver(appointmentOutcomeRecords);
        this.viewer = new AppointmentOutcomeRecordsViewer(appointmentOutcomeRecords);
        this.prescriptionFlagUpdater = new PrescriptionFlagUpdater(appointmentOutcomeRecords);
        loader.loadInitialAppointmentOutcomes();
        this.recordAppointmentOutcome = new RecordOutcome(appointmentOutcomeRecords);
    }

    /**
     * Loads the initial set of appointment outcome records from the storage.
     */
    public void loadRecords() {
        loader.loadInitialAppointmentOutcomes();
    }

    /**
     * Saves all appointment outcome records to the storage.
     */
    public void saveRecords() {
        saver.saveRecords();
    }

    /**
     * Views all appointment outcome records. Ensures records are loaded before viewing.
     */
    public void viewAllRecords() {
        loadRecords();
        viewer.viewAllRecords();
    }

    /**
     * Views all pending appointment outcome records. Ensures records are loaded before viewing.
     *
     * @throws IOException if an error occurs during viewing
     */
    public void viewPendingRecords() throws IOException {
        loadRecords();
        viewer.viewPendingRecords();
    }

    /**
     * Allows a patient to view their past appointment outcome records.
     * Uses the session's login ID to get the current logged in User's medical records. Ensures records are loaded before viewing.
     *
     * @throws IOException if an error occurs during viewing
     */
    public void patientViewPastRecords() throws IOException {
        loadRecords();
        viewer.viewRecordsById(Session.getLoginID());
    }

    /**
     * Allows an admin to view records by appointment ID.
     * Ensures records are loaded before viewing.
     * the naming kinda ass cause last minute
     *
     * @param apptId the appointment ID to search for
     * @return {@code true} if the record exists and is displayed, {@code false} otherwise
     * @throws IOException if an error occurs during viewing
     */
    public boolean adminViewRecords(String apptId) throws IOException {
        loadRecords();
        return (viewer.viewRecordsByIdnoNewline(apptId));
    }

    /**
     * Records the outcome of an appointment. Ensures records are loaded before recording,
     * and saves records afterward.
     *
     * @throws IOException if an error occurs during the process
     */
    public void recordAppointmentOutcome() throws IOException {
        loadRecords();
        recordAppointmentOutcome.recordOutcome();
        saveRecords();
    }

    /**
     * Updates the flag for prescriptions in appointment outcome records.
     * Ensures records are loaded before updating, and saves records afterward.
     *
     * @throws IOException if an error occurs during the update
     */
    public void updatePrescriptionFlag() throws IOException {
        loadRecords();
        prescriptionFlagUpdater.updatePrescriptionFlag();
        saveRecords();
    }
}
