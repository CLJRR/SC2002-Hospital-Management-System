package UserSystem;

import AppointmentOutcomeSystem.AppointmentOutcomeRecord;
import java.util.*;

public class DoctorMedicalRecordViewer {
    private User doctor;
    private GetUser getUser;
    private Map<String, AppointmentOutcomeRecord> appointmentOutcomeRecords;

    public DoctorMedicalRecordViewer(String UserId, Map<String, AppointmentOutcomeRecord> appointmentOutcomeRecords) {
        this.appointmentOutcomeRecords = appointmentOutcomeRecords;
        this.getUser = new GetUser();
        this.doctor = getUser.getUser(UserId);
    }

    public void viewMedicalRecords() {
        Map<String, User> patients = new HashMap<>();
        for (AppointmentOutcomeRecord record : appointmentOutcomeRecords.values()) {
            if (record.getDoctorId().equals(doctor.getId())) {
                User patient = getUser.getUser(record.getPatientId());
                patients.put(record.getPatientId(), patient);
            }
        }
        for (User patient : patients.values()) {
            if (patient != null) {
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
                        treatments = treatments + record.getPrescription().getMedName() + ", "
                                + record.getPrescription().getAmount() + ", " + record.getPrescription().getDosage()
                                + " ";
                    }
                }
                System.out.println("Diagnoses: " + diagnoses);
                System.out.println("Treatments: " + treatments);
            }
        }
    }
}
