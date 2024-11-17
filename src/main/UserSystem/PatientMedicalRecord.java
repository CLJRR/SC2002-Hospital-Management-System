package UserSystem;

import AppointmentOutcomeSystem.*;
import SessionManager.Session;
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
            // Display patient information
            System.out.println("Patient: " + patient.getId());
            System.out.println("Name: " + patient.getName());
            System.out.println("Date of Birth: " + patient.getDateOfBirth());
            System.out.println("Gender: " + patient.getGender());
            System.out.println("Phone Number: " + patient.getPhoneNumber());
            System.out.println("Email: " + patient.getEmail());
            System.out.println("Blood Type: " + patient.getBloodType());
            System.out.println("----------------------------------------------");
            System.out.println("");

            boolean hasRecords = false;

            // Display all appointment records for the patient
            for (AppointmentOutcomeRecord record : appointmentOutcomeRecords.values()) {
                if (record.getPatientId().equalsIgnoreCase(Session.getLoginID())) {
                    hasRecords = true;

                    // Display appointment ID
                    System.out.println("Appointment: " + record.getApptId());

                    // Display diagnoses
                    if (!record.getDiagnoses().isEmpty()) {
                        String diagnoses = String.join(", ", record.getDiagnoses());
                        System.out.println("Diagnoses: " + diagnoses);
                    } else {
                        System.out.println("Diagnoses: None recorded.");
                    }

                    // Display prescriptions
                    if (!record.getPrescriptions().isEmpty()) {
                        System.out.println("Treatments:");
                        for (Prescription prescription : record.getPrescriptions()) {
                            System.out.println("  - " + prescription.getMedName() + ", " +
                                               prescription.getAmount() + ", " +
                                               prescription.getDosage());
                        }
                    } else {
                        System.out.println("Treatments: None prescribed.");
                    }
                    System.out.println("");
                }
            }

            // If no records are found for the patient
            if (!hasRecords) {
                System.out.println("No medical records found for this patient.");
            }
        } else {
            System.out.println("No patient data available.");
        }

        // Wait for user input to exit
        Scanner sc = new Scanner(System.in);
        System.out.println("Press Enter to exit");
        sc.nextLine();
    }

}
