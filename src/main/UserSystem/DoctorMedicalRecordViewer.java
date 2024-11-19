package UserSystem;

import AppointmentOutcomeSystem.AppointmentOutcomeRecord;
import AppointmentOutcomeSystem.AppointmentOutcomeRecordLoader;
import AppointmentOutcomeSystem.Prescription;
import java.util.*;
import java.util.stream.Collectors;

/**
 * The {@code DoctorMedicalRecordViewer} class provides functionality for doctors to 
 * view medical records of patients. It can display records for all patients or 
 * for a specific patient based on their ID.
 */
public class DoctorMedicalRecordViewer {

    private User doctor;
    private GetUser getUser;
    private Map<String, AppointmentOutcomeRecord> appointmentOutcomeRecords;
    private Map<String, User> users;
    private UserLoader loader;
    private AppointmentOutcomeRecordLoader recordLoader;

    /**
     * Constructs a {@code DoctorMedicalRecordViewer} object.
     *
     * @param UserId the ID of the doctor using the viewer.
     * @param appointmentOutcomeRecords the map of appointment outcome records to be displayed.
     */
    public DoctorMedicalRecordViewer(String UserId, Map<String, AppointmentOutcomeRecord> appointmentOutcomeRecords) {
        this.users = new HashMap<>();
        this.appointmentOutcomeRecords = appointmentOutcomeRecords;
        this.loader = new UserLoader(this.users);
        this.getUser = new GetUser();
        this.recordLoader = new AppointmentOutcomeRecordLoader(this.appointmentOutcomeRecords);

        // Load records on initialization
        recordLoader.loadInitialAppointmentOutcomes();
    }

    /**
     * Displays the medical records for all patients. Records are sorted by appointment date.
     */
    public void viewMedicalRecords() {
        System.out.println("");

        // Fetch all patients
        List<User> patients = getUser.getAllPatients();

        // Sort records by appointment date
        List<AppointmentOutcomeRecord> sortedRecords = appointmentOutcomeRecords.values()
                .stream()
                .sorted(Comparator.comparing(AppointmentOutcomeRecord::getAppointmentDate))
                .collect(Collectors.toList());

        // Display details for each patient
        for (User patient : patients) {
            System.out.println("Patient: " + patient.getId());
            System.out.println("Name: " + patient.getName());
            System.out.println("Date of Birth: " + patient.getDateOfBirth());
            System.out.println("Gender: " + patient.getGender());
            System.out.println("Phone Number: " + patient.getPhoneNumber());
            System.out.println("Email: " + patient.getEmail());
            System.out.println("Blood Type: " + patient.getBloodType());
            System.out.println("");

            boolean hasRecords = false;

            // Display medical records for the patient
            for (AppointmentOutcomeRecord record : sortedRecords) {
                if (record.getPatientId().equalsIgnoreCase(patient.getId())) {
                    hasRecords = true;
                    String diagnoses = String.join(", ", record.getDiagnoses());

                    System.out.println("Appointment: " + record.getApptId());

                    // Get doctor information
                    User doctor = getUser.getUser(record.getDoctorId());
                    String doctorName = (doctor != null) ? doctor.getName() : "Unknown";
                    System.out.println("By Doctor: " + doctorName);

                    // Display diagnoses
                    if (!diagnoses.isEmpty()) {
                        System.out.println("Diagnoses: " + diagnoses);
                    } else {
                        System.out.println("Diagnoses: None recorded.");
                    }

                    // Display prescriptions
                    if (!record.getPrescriptions().isEmpty()) {
                        System.out.println("Treatments:");
                        for (Prescription prescription : record.getPrescriptions()) {
                            System.out.println("  - " + prescription.getMedName() + ", "
                                    + prescription.getAmount() + ", " + prescription.getDosage());
                        }
                    } else {
                        System.out.println("Treatments: None prescribed.");
                    }
                    System.out.println("");
                }
            }

            if (!hasRecords) {
                System.out.println("No records found for this patient.");
            }
            System.out.println("----------------------------------------------");
        }

        // Wait for user input to exit
        Scanner sc = new Scanner(System.in);
        System.out.println("Press Enter to exit");
        sc.nextLine();
    }

    /**
     * Displays the medical records for a specific patient based on their ID.
     *
     * @param patientId the ID of the patient whose records are to be displayed.
     */
    public void viewMedicalRecordsById(String patientId) {
        // Fetch patient details
        User patient = getUser.getUser(patientId);

        if (patient == null) {
            System.out.println("No patient found with ID: " + patientId);
            return;
        }
        System.out.println("");

        // Display patient details
        System.out.println("Patient: " + patient.getId());
        System.out.println("Name: " + patient.getName());
        System.out.println("Date of Birth: " + patient.getDateOfBirth());
        System.out.println("Gender: " + patient.getGender());
        System.out.println("Phone Number: " + patient.getPhoneNumber());
        System.out.println("Email: " + patient.getEmail());
        System.out.println("Blood Type: " + patient.getBloodType());
        System.out.println("----------------------------------------------");

        // Sort records by appointment date
        List<AppointmentOutcomeRecord> sortedRecords = appointmentOutcomeRecords.values()
                .stream()
                .filter(record -> record.getPatientId().equalsIgnoreCase(patientId))
                .sorted(Comparator.comparing(AppointmentOutcomeRecord::getAppointmentDate))
                .collect(Collectors.toList());

        // Display medical records
        if (sortedRecords.isEmpty()) {
            System.out.println("No records found for this patient.");
        } else {
            for (AppointmentOutcomeRecord record : sortedRecords) {
                String diagnoses = String.join(", ", record.getDiagnoses());

                System.out.println("Appointment: " + record.getApptId());

                // Get doctor information
                User doctor = getUser.getUser(record.getDoctorId());
                String doctorName = (doctor != null) ? doctor.getName() : "Unknown";
                System.out.println("By Doctor: " + doctorName);

                // Display diagnoses
                if (!diagnoses.isEmpty()) {
                    System.out.println("Diagnoses: " + diagnoses);
                } else {
                    System.out.println("Diagnoses: None recorded.");
                }

                // Display prescriptions
                if (!record.getPrescriptions().isEmpty()) {
                    System.out.println("Treatments:");
                    for (Prescription prescription : record.getPrescriptions()) {
                        System.out.println("  - " + prescription.getMedName() + ", "
                                + prescription.getAmount() + ", " + prescription.getDosage());
                    }
                } else {
                    System.out.println("Treatments: None prescribed.");
                }
                System.out.println("");
            }
        }

        System.out.println("----------------------------------------------");

        // Wait for user input to exit
        Scanner sc = new Scanner(System.in);
        System.out.println("Press Enter to exit");
        sc.nextLine();
    }
}
