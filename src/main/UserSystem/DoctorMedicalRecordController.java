package UserSystem;

import AppointmentOutcomeSystem.*;
import java.util.*;

/**
 * Controller class for managing and interacting with medical records
 * and appointment outcomes for a specific doctor. This class handles the
 * intialisation, upating, viewing and saving of
 * of appointment outcome records.
 */

public class DoctorMedicalRecordController {
    private final Map<String, AppointmentOutcomeRecord> appointmentOutcomeRecords;
    private final AppointmentOutcomeRecordSaver appointmentOutcomeRecordSaver;
    private final AppointmentOutcomeRecordLoader appointmentOutcomeRecordLoader;
    private final DoctorMedicalRecordUpdater doctorMedicalRecordUpdater;
    private final DoctorMedicalRecordViewer doctorMedicalRecordViewer;

    /**
     * Constructor for initialising the DoctorMedicalRecordController with
     * the specified doctor ID. It sets up the necessary components for
     * managing appointment outcome records.
     * 
     * @param doctorId The unique ID of the doctor logged in.
     */

    public DoctorMedicalRecordController(String doctorId) {
        this.appointmentOutcomeRecords = new HashMap<>();
        this.appointmentOutcomeRecordSaver = new AppointmentOutcomeRecordSaver(this.appointmentOutcomeRecords);
        this.appointmentOutcomeRecordLoader = new AppointmentOutcomeRecordLoader(this.appointmentOutcomeRecords);
        this.doctorMedicalRecordUpdater = new DoctorMedicalRecordUpdater(doctorId, this.appointmentOutcomeRecords);
        this.doctorMedicalRecordViewer = new DoctorMedicalRecordViewer(doctorId, this.appointmentOutcomeRecords);
        appointmentOutcomeRecordLoader.loadInitialAppointmentOutcomes();
    }

    /**
     * Saves all the appointment outcome records to the storage system.
     */

    public void saveRecords() {
        appointmentOutcomeRecordSaver.saveRecords();
    }

    /**
     * Updates a specific appointment outcome record for the logged-in doctor.
     * The update operation modifies key details such as diagnoses or prescriptions
     * associated with the specified appointment.
     * 
     * @param apptId The unique ID of the appointment to be updated.
     */

    public void updateRecords(String apptId) {
        doctorMedicalRecordUpdater.updateRecord(apptId);
    }

    /**
     * Displays all the medical records and appointment outcomes for the doctor.
     * This includes patient details, diagnoses and prescriptions for each
     * appointment.
     */

    public void viewRecords() {
        doctorMedicalRecordViewer.viewMedicalRecords();
    }
}