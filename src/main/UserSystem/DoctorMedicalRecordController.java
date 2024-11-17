package UserSystem;

import AppointmentOutcomeSystem.*;
import java.util.*;

public class DoctorMedicalRecordController {

    private Map<String, AppointmentOutcomeRecord> appointmentOutcomeRecords;
    private AppointmentOutcomeRecordSaver appointmentOutcomeRecordSaver;
    private AppointmentOutcomeRecordLoader appointmentOutcomeRecordLoader;
    private DoctorMedicalRecordUpdater doctorMedicalRecordUpdater;
    private DoctorMedicalRecordViewer doctorMedicalRecordViewer;
    private String doctorId;

    public DoctorMedicalRecordController(String doctorId) {
        this.appointmentOutcomeRecords = new HashMap<>();
        this.appointmentOutcomeRecordSaver = new AppointmentOutcomeRecordSaver(this.appointmentOutcomeRecords);
        this.appointmentOutcomeRecordLoader = new AppointmentOutcomeRecordLoader(this.appointmentOutcomeRecords);
        this.doctorMedicalRecordUpdater = new DoctorMedicalRecordUpdater(doctorId, this.appointmentOutcomeRecords);
        this.doctorMedicalRecordViewer = new DoctorMedicalRecordViewer(doctorId, this.appointmentOutcomeRecords);
        this.doctorId = doctorId;

        appointmentOutcomeRecordLoader.loadInitialAppointmentOutcomes();
    }

    public void loadRecords() {
        appointmentOutcomeRecordLoader.loadInitialAppointmentOutcomes();
    }

    public void saveRecords() {
        appointmentOutcomeRecordSaver.saveRecords();
    }

    public void updateRecords(String apptId) {
        loadRecords();
        doctorMedicalRecordUpdater.updateRecord(apptId);
        saveRecords();
    }

    public void viewRecords() {
        loadRecords();

        doctorMedicalRecordViewer.viewMedicalRecords();
        saveRecords();

    }

    public void viewRecordsById(String Id) {
        loadRecords();

        doctorMedicalRecordViewer.viewMedicalRecordsById(Id);
        saveRecords();

    }
}
