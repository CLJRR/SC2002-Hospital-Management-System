package UserSystem;

import AppointmentOutcomeSystem.AppointmentOutcomeRecord;
import java.util.*;

/**
 * This calss provides functionality for a doctor to view the medical records
 * of their patients. It retrieves and displays information such as patient
 * details,
 * diagnoses, and treatments.
 */

public class DoctorMedicalRecordViewer {

    private final User doctor;
    private final GetUser getUser;
    private final Map<String, AppointmentOutcomeRecord> appointmentOutcomeRecords;

    /**
     * Constructs a DoctorMedicalRecordViewer instance for a specific doctor.
     * 
     * @param UserId                    The unique ID of the doctor.
     * @param appointmentOutcomeRecords A map containing appointment outcome
     *                                  records, keyed by their appointment IDs.
     */

    public DoctorMedicalRecordViewer(String UserId, Map<String, AppointmentOutcomeRecord> appointmentOutcomeRecords) {
        this.appointmentOutcomeRecords = appointmentOutcomeRecords;
        this.getUser = new GetUser();
        this.doctor = getUser.getUser(UserId);
    }

    /**
     * Displays the medical records of all patients associated with the doctor.
     * The method retrieves patient details and compiles information such as
     * diagnoses and treatments
     * from appointment outcome records.
     */

    public void viewMedicalRecords() {
        Map<String, User> patients = new HashMap<>();

        // Gather all patients assigned to the doctor
        for (AppointmentOutcomeRecord record : appointmentOutcomeRecords.values()) {
            if (record.getDoctorId().equals(doctor.getId())) {
                User patient = getUser.getUser(record.getPatientId());
                patients.put(record.getPatientId(), patient);
            }
        }

        // Display patient details and their associated records
        for (User patient : patients.values()) {
            if (patient != null) {
                System.out.println("Patient: " + patient.getId());
                System.out.println("Name: " + patient.getName());
                System.out.println("Date of Birth: " + patient.getDateOfBirth());
                System.out.println("Gender: " + patient.getGender());
                System.out.println("Phone Number: " + patient.getPhoneNumber());
                System.out.println("Email: " + patient.getEmail());
                System.out.println("Blood Type: " + patient.getBloodType());
                StringBuilder diagnoses = new StringBuilder();
                StringBuilder treatments = new StringBuilder();

                // Aggregate diagnoses and treatments for the patient
                for (AppointmentOutcomeRecord record : appointmentOutcomeRecords.values()) {
                    if (record.getPatientId().equals(patient.getId())) {
                        diagnoses.append(record.getDiagnoses()).append(" ");
                        treatments.append(record.getPrescription().getMedName()).append(", ")
                                .append(record.getPrescription().getAmount()).append(", ")
                                .append(record.getPrescription().getDosage())
                                .append(" ");
                    }
                }

                System.out.println("Diagnoses: " + diagnoses);
                System.out.println("Treatments: " + treatments);
                System.out.println("\n");
            }
        }

        // Wait for user input to return to the previous menu
        Scanner sc = new Scanner(System.in);
        System.out.println("Press Enter to go back");
        sc.nextLine();
    }
}
