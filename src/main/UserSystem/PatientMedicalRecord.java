package UserSystem;

import AppointmentOutcomeSystem.*;
import java.util.*;

public class PatientMedicalRecord {
    private User patient;
    private Map<String, AppointmentOutcomeRecord> appointmentOutcomeRecords;
    private AppointmentOutcomeRecordLoader appointmentOutcomeRecordLoader;
    private GetUser getUser;
    
    public PatientMedicalRecord(String UserId) {
        this.appointmentOutcomeRecords = new HashMap<>();
        this.getUser = new GetUser();
        this.patient = getUser.getUser(UserId);
        this.appointmentOutcomeRecordLoader = new AppointmentOutcomeRecordLoader(this.appointmentOutcomeRecords);
        appointmentOutcomeRecordLoader.loadInitialAppointmentOutcomes();
    }
    
    public void patientMedicalRecord() {
        if (this.patient != null) {
            System.out.println("Patient: " + patient.getId());
            System.out.println("Name: " + patient.getName());
            System.out.println("Date of Birth: " + patient.getDateOfBirth());
            System.out.println("Gender: " + patient.getGender());
            System.out.println("Phone Number: " + patient.getPhoneNumber());
            System.out.println("Email: " + patient.getEmail());
            System.out.println("Blood Type: " + patient.getBloodType());
            String diagnoses = "";
            String treatments = "";
            for (AppointmentOutcomeRecord record : appointmentOutcomeRecords.values()) {
                if (record.getPatientId().equals(patient.getId())) {
                    diagnoses = diagnoses + record.getDiagnoses() + " ";
                    treatments = treatments + record.getPrescription().getMedName() + ", " + record.getPrescription().getAmount() + ", " + record.getPrescription().getDosage() + " ";
                }
            }
            System.out.println("Diagnoses: " + diagnoses);
            System.out.println("Treatments: " + treatments);
        }
    }
}
