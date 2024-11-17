package UserSystem;

import AppointmentOutcomeSystem.*;
import java.util.*;

/**
 * This class allows a doctor to update the medical records associated with
 * their appointments.
 * It provides functionality to modify diagnoses and prescriptions for a
 * specific appointment outcome record.
 */

public class DoctorMedicalRecordUpdater {
    private final User doctor;
    private final GetUser getUser;
    private final Map<String, AppointmentOutcomeRecord> appointmentOutcomeRecords;

    /**
     * Constructs a DoctorMedicalRecordUpdater instance for the specified doctor.
     * 
     * @param UserId                The unique ID of the doctor.
     * @param appointOutcomeRecords A map of appointment outcome records, keyed by
     *                              their appointment IDs
     */

    public DoctorMedicalRecordUpdater(String UserId, Map<String, AppointmentOutcomeRecord> appointOutcomeRecords) {
        this.getUser = new GetUser();
        this.doctor = getUser.getUser(UserId);
        this.appointmentOutcomeRecords = appointOutcomeRecords;
    }

    /**
     * Updates a specific appointment outcome record for the doctor.
     * The doctor can choose to update diagnoses, prescriptions, or exit the update
     * process.
     *
     * @param apptId The unique ID of the appointment to be updated.
     */

    public void updateRecord(String apptId) {
        Scanner sc = new Scanner(System.in);
        AppointmentOutcomeRecord record = appointmentOutcomeRecords.get(apptId);

        if (record != null) {
            if (record.getDoctorId().equals(doctor.getId())) {
                boolean exit = false;

                while (!exit) {
                    // Display options for updating the medical record
                    System.out.println("1) Update Diagnoses");
                    System.out.println("2) Update Prescriptions");
                    System.out.println("3) Exit");
                    System.out.println("Select Option: ");

                    while (!sc.hasNextInt()) { // Check if input is an integer
                        System.out.println("Option not valid. Please try again:");
                        sc.next(); // Clear the invalid input
                    }

                    int option = sc.nextInt();
                    sc.nextLine(); // Consumes Newline

                    switch (option) {
                        case 1 -> {
                            System.out.print("Enter new diagnoses: ");
                            String diagnoses = sc.nextLine();
                            record.setDiagnoses(diagnoses);
                            System.out.println("Medical Record updated successfully.");
                            break;
                        }
                        case 2 -> {
                            System.out.println("Enter new prescription Name: ");
                            String prescriptionName = sc.nextLine();

                            System.out.println("Enter new prescription Amount: ");
                            while (!sc.hasNextInt()) { // Check if input is an integer
                                System.out.println("Option not valid. Please try again:");
                                sc.next(); // Clear the invalid input
                            }

                            int prescriptionAmount = sc.nextInt();
                            sc.nextLine(); // Consumes New Line

                            System.out.println("Enter new prescription Dosage: ");
                            String prescriptionDosage = sc.nextLine();

                            Prescription prescription = new Prescription(prescriptionName, prescriptionAmount,
                                    prescriptionDosage);
                            record.setPrescription(prescription);
                            System.out.println("Medical Record updated successfully.");
                            break;
                        }
                        case 3 -> {
                            exit = true; // Exit the update process
                            break;
                        }
                        default -> {
                            System.out.println("Option not valid. Please try again.");
                        }
                    }
                }
            } else {
                System.out.println("Error updating appointment.");
            }
        } else {
            System.out.println("Error updating appointment.");
        }
    }
}
