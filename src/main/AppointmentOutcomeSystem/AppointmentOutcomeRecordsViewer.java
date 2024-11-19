package AppointmentOutcomeSystem;

import java.io.IOException;
import java.util.*;

/**
 * Responsible for viewing appointment outcome records.
 * Provides methods to display all records, pending records, and records filtered by ID.
 */

public class AppointmentOutcomeRecordsViewer {

    /**
     * A map storing appointment outcome records with appointment ID as the key.
     */
    private Map<String, AppointmentOutcomeRecord> appointmentOutcomeRecords;

    static final Scanner sc = new Scanner(System.in);

    /**
     * Constructs a new {@code AppointmentOutcomeRecordsViewer} with the
     * specified map containing appointment outcome records to be viewed.
     *
     * @param appointmentOutcomeRecords the map of appointment outcome records
     * to view
     */
    public AppointmentOutcomeRecordsViewer(Map<String, AppointmentOutcomeRecord> appointmentOutcomeRecords) {
        this.appointmentOutcomeRecords = appointmentOutcomeRecords;
    }

    /**
     * Displays all appointment outcome records stored in the system.
     */
    public void viewAllRecords() {
        System.out.println("All Appointment Outcome Records:");
        for (AppointmentOutcomeRecord record : appointmentOutcomeRecords.values()) {
            System.out.println(record.toString());
        }
        System.out.println();
    }

    /**
     * Displays all pending appointment outcome with prescriptions
     *
     * @throws IOException if an error occurs while viewing records
     */
    public void viewPendingRecords() throws IOException {
        for (AppointmentOutcomeRecord record : appointmentOutcomeRecords.values()) {
            for (Prescription prescription : record.getPrescriptions()) {
                System.out.println(record.getApptId() + " " + prescription);
            }
        }
    }

    /**
     * Displays appointment outcome records filtered by a either ApptId or
     * PatientId. Records are sorted by appointment date in ascending order.
     *
     * @param Id the appointment or patient ID to filter records
     * @return {@code true} if matching records are found and displayed,
     * {@code false} otherwise
     * @throws IOException if an error occurs while viewing records
     */
    public boolean viewRecordsById(String Id) throws IOException {
        List<AppointmentOutcomeRecord> matchingRecords = new ArrayList<>();
        System.out.println("For Id " + Id);

        // Collect matching records
        for (AppointmentOutcomeRecord medicalRecord : appointmentOutcomeRecords.values()) {
            if (Id.equalsIgnoreCase(medicalRecord.getApptId()) || Id.equalsIgnoreCase(medicalRecord.getPatientId())) {
                matchingRecords.add(medicalRecord);
            }
        }

        // Sort records in ascending order by appointment date
        matchingRecords.sort(Comparator.comparing(AppointmentOutcomeRecord::getAppointmentDate));

        // Display sorted records
        if (!matchingRecords.isEmpty()) {
            for (AppointmentOutcomeRecord medicalRecord : matchingRecords) {
                if (Id.equalsIgnoreCase(medicalRecord.getApptId())) {
                    System.out.println(medicalRecord.toString());
                } else if (Id.equalsIgnoreCase(medicalRecord.getPatientId())) {
                    System.out.println("Appointment Id: " + medicalRecord.getApptId());
                    System.out.println("Doctor Id: " + medicalRecord.getDoctorId());
                    System.out.println("Appointment Date: " + medicalRecord.getAppointmentDate());
                    System.out.println("Service Provided: " + medicalRecord.getServiceProvided());
                    System.out.println("Diagnoses: " + String.join(", ", medicalRecord.getDiagnoses()));

                    // Display prescriptions
                    for (Prescription prescription : medicalRecord.getPrescriptions()) {
                        System.out.println("Prescription: " + prescription.getMedName() + ", "
                                + prescription.getAmount() + ", " + prescription.getDosage());
                    }
                    System.out.println("\n");
                }
            }
        } else {
            System.out.println("No Outcome Records found");
            return false;
        }

        System.out.println("Press Enter to go back");
        sc.nextLine();
        return true;
    }

    /**
     * Displays appointment outcome records filtered by a either ApptId or
     * PatientId. Records are sorted by appointment date in ascending order.
     * does not ask for sc.nextLine();
     *
     * @param Id the appointment or patient ID to filter records
     * @return {@code true} if matching records are found and displayed,
     * {@code false} otherwise
     * @throws IOException if an error occurs while viewing records
     */
    public boolean viewRecordsByIdnoNewline(String Id) throws IOException {
        List<AppointmentOutcomeRecord> matchingRecords = new ArrayList<>();
        System.out.println("For Id " + Id);

        // Collect matching records
        for (AppointmentOutcomeRecord medicalRecord : appointmentOutcomeRecords.values()) {
            if (Id.equalsIgnoreCase(medicalRecord.getApptId()) || Id.equalsIgnoreCase(medicalRecord.getPatientId())) {
                matchingRecords.add(medicalRecord);
            }
        }

        // Sort records in ascending order by appointment date
        matchingRecords.sort(Comparator.comparing(AppointmentOutcomeRecord::getAppointmentDate));

        // Display sorted records
        if (!matchingRecords.isEmpty()) {
            for (AppointmentOutcomeRecord medicalRecord : matchingRecords) {
                if (Id.equalsIgnoreCase(medicalRecord.getApptId())) {
                    System.out.println(medicalRecord.toString());
                } else if (Id.equalsIgnoreCase(medicalRecord.getPatientId())) {
                    System.out.println("Appointment Id: " + medicalRecord.getApptId());
                    System.out.println("Doctor Id: " + medicalRecord.getDoctorId());
                    System.out.println("Appointment Date: " + medicalRecord.getAppointmentDate());
                    System.out.println("Service Provided: " + medicalRecord.getServiceProvided());
                    System.out.println("Diagnoses: " + String.join(", ", medicalRecord.getDiagnoses()));

                    // Display prescriptions
                    for (Prescription prescription : medicalRecord.getPrescriptions()) {
                        System.out.println("Prescription: " + prescription.getMedName() + ", "
                                + prescription.getAmount() + ", " + prescription.getDosage());
                    }
                    System.out.println("\n");
                }
            }
        } else {
            System.out.println("No Outcome Records found");
            return false;
        }

        return true;
    }
}
