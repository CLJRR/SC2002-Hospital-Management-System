package UserSystem;

import AppointmentOutcomeSystem.*;
import java.util.*;

/**
 * The {@code DoctorMedicalRecordController} class is responsible for managing medical records
 * associated with a specific doctor. It facilitates the loading, saving, updating, and viewing
 * of appointment outcome records.
 */
public class DoctorMedicalRecordController {

    private Map<String, AppointmentOutcomeRecord> appointmentOutcomeRecords;
    private AppointmentOutcomeRecordSaver appointmentOutcomeRecordSaver;
    private AppointmentOutcomeRecordLoader appointmentOutcomeRecordLoader;
    private DoctorMedicalRecordUpdater doctorMedicalRecordUpdater;
    private DoctorMedicalRecordViewer doctorMedicalRecordViewer;
    private String doctorId;

    /**
     * Constructs a {@code DoctorMedicalRecordController} object for a specific doctor.
     * It initializes the medical record components and loads initial appointment outcomes.
     *
     * @param doctorId the unique ID of the doctor whose medical records are being managed.
     */
    public DoctorMedicalRecordController(String doctorId) {
        this.appointmentOutcomeRecords = new HashMap<>();
        this.appointmentOutcomeRecordSaver = new AppointmentOutcomeRecordSaver(this.appointmentOutcomeRecords);
        this.appointmentOutcomeRecordLoader = new AppointmentOutcomeRecordLoader(this.appointmentOutcomeRecords);
        this.doctorMedicalRecordUpdater = new DoctorMedicalRecordUpdater(doctorId, this.appointmentOutcomeRecords);
        this.doctorMedicalRecordViewer = new DoctorMedicalRecordViewer(doctorId, this.appointmentOutcomeRecords);
        this.doctorId = doctorId;

        appointmentOutcomeRecordLoader.loadInitialAppointmentOutcomes();
    }

    /**
     * Loads the initial set of appointment outcome records from persistent storage.
     */
    public void loadRecords() {
        appointmentOutcomeRecordLoader.loadInitialAppointmentOutcomes();
    }

    /**
     * Saves the current state of all appointment outcome records to persistent storage.
     */
    public void saveRecords() {
        appointmentOutcomeRecordSaver.saveRecords();
    }

    /**
     * Updates a specific appointment outcome record.
     *
     * @param apptId the unique ID of the appointment to be updated.
     */
    public void updateRecords(String apptId) {
        loadRecords();
        doctorMedicalRecordUpdater.updateRecord(apptId);
        saveRecords();
    }

    /**
     * Displays all medical records associated with the doctor.
     * Records are loaded, displayed, and saved afterward.
     */
    public void viewRecords() {
        loadRecords();

        doctorMedicalRecordViewer.viewMedicalRecords();
        saveRecords();
    }

    /**
     * Displays medical records associated with a specific appointment ID.
     * Records are loaded, displayed, and saved afterward.
     *
     * @param Id the unique ID of the appointment whose records are to be viewed.
     */
    public void viewRecordsById(String Id) {
        loadRecords();

        doctorMedicalRecordViewer.viewMedicalRecordsById(Id);
        saveRecords();
    }
}
