package UserSystem;

import AppointmentOutcomeSystem.*;
import java.util.*;

/**
 * This class provides functionality for managing and viewing a patient's
 * medical record.
 * It displays the patient's details along with their appointment outcomes,
 * including diagnoses and treatments.
 */

public class PatientMedicalRecord {
    private final User patient;
    private final Map<String, AppointmentOutcomeRecord> appointmentOutcomeRecords;
    private final AppointmentOutcomeRecordLoader appointmentOutcomeRecordLoader;
    private final GetUser getUser;

    /**
     * Constructs a PatientMedicalRecord instance for a specific patient.
     * Initialises the patient's details and loads their associated appointment
     * outcomes.
     * 
     * @param UserId The unique ID of the patient whose medical record is being
     *               managed.
     */

    public PatientMedicalRecord(String UserId) {
        this.appointmentOutcomeRecords = new HashMap<>();
        this.getUser = new GetUser();
        this.patient = getUser.getUser(UserId);
        this.appointmentOutcomeRecordLoader = new AppointmentOutcomeRecordLoader(this.appointmentOutcomeRecords);

        // Load initial appointment outcomes into the system
        appointmentOutcomeRecordLoader.loadInitialAppointmentOutcomes();
    }

    /**
     * Displays the medical record of the patient.
     * This includes personal details (e.g., name, gender, date of birth, contact
     * info)
     * as well as diagnoses and treaments from their appointment outcomes.
     */

    public void patientMedicalRecord() {
        if (this.patient != null) {
            // Display patient details
            System.out.println("Patient: " + patient.getId());
            System.out.println("Name: " + patient.getName());
            System.out.println("Date of Birth: " + patient.getDateOfBirth());
            System.out.println("Gender: " + patient.getGender());
            System.out.println("Phone Number: " + patient.getPhoneNumber());
            System.out.println("Email: " + patient.getEmail());
            System.out.println("Blood Type: " + patient.getBloodType());

            StringBuilder diagnoses = new StringBuilder();
            StringBuilder treatments = new StringBuilder();

            // Aggregate diagnoses and treatments from appointment outcomes
            for (AppointmentOutcomeRecord record : appointmentOutcomeRecords.values()) {
                if (record.getPatientId().equals(patient.getId())) {
                    diagnoses.append(record.getDiagnoses()).append(" ");
                    treatments.append(record.getPrescription().getMedName())
                            .append(", ").append(record.getPrescription().getAmount())
                            .append(", ").append(record.getPrescription().getDosage()).append(" ");
                }
            }

            // Display aggregated diagnoses and treatments
            System.out.println("Diagnoses: " + diagnoses);
            System.out.println("Treatments: " + treatments);
        } else {
            System.out.println("Patient record not found.");
        }
    }
}
