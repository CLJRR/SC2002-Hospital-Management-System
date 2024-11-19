package UserSystem;

import AppointmentOutcomeSystem.*;
import MedicineInventorySystem.InventoryController;
import MedicineInventorySystem.MedicationInventory;
import SessionManager.Session;
import java.util.*;

/**
 * The {@code DoctorMedicalRecordUpdater} class provides functionality for doctors 
 * to update medical records associated with appointments. It includes options to 
 * update diagnoses and prescriptions for a specific appointment.
 */
public class DoctorMedicalRecordUpdater {

    private User doctor;
    private GetUser getUser;
    private Map<String, AppointmentOutcomeRecord> appointmentOutcomeRecords;
    AppointmentOutcomeRecordSaver recordSaver;
    static final Scanner sc = new Scanner(System.in);
    private InventoryController inventoryController;

    /**
     * Constructs a {@code DoctorMedicalRecordUpdater} object.
     *
     * @param UserId the ID of the doctor.
     * @param appointOutcomeRecords the map of appointment outcome records to manage.
     */
    public DoctorMedicalRecordUpdater(String UserId, Map<String, AppointmentOutcomeRecord> appointOutcomeRecords) {
        this.getUser = new GetUser();
        this.doctor = getUser.getUser(UserId);
        this.appointmentOutcomeRecords = appointOutcomeRecords;
        this.recordSaver = new AppointmentOutcomeRecordSaver(appointmentOutcomeRecords);
        this.inventoryController = new InventoryController();
    }

    /**
     * Updates the medical record for a specific appointment.
     *
     * @param apptId the ID of the appointment to update.
     */
    public void updateRecord(String apptId) {
        AppointmentOutcomeRecord record = appointmentOutcomeRecords.get(apptId);
        Map<String, MedicationInventory> inventory = inventoryController.getInventory();
        if (record != null) {
            if (record.getDoctorId().equalsIgnoreCase(Session.getLoginID())) { // Validate doctor access
                boolean exit = false;
                while (!exit) {
                    System.out.println("1) Update Diagnoses");
                    System.out.println("2) Update Prescriptions");
                    System.out.println("3) Exit");
                    System.out.print("Select Option: ");

                    while (!sc.hasNextInt()) { // Validate input as integer
                        System.out.println("Option not valid. Please try again:");
                        sc.next();
                    }

                    int option = sc.nextInt();
                    sc.nextLine(); // Consume newline

                    switch (option) {
                        case 1 -> {
                            // Update Diagnoses
                            System.out.print("Enter a new diagnosis: ");
                            String newDiagnosis = sc.nextLine().trim();
                            if (!newDiagnosis.isEmpty()) {
                                List<String> updatedDiagnoses = record.getDiagnoses();
                                if (!updatedDiagnoses.contains(newDiagnosis)) {
                                    updatedDiagnoses.add(newDiagnosis);
                                    record.setDiagnoses(updatedDiagnoses);
                                    recordSaver.saveRecords();
                                    System.out.println("Updated Record:");
                                    System.out.println(record);
                                    System.out.println("Diagnosis added successfully.");
                                } else {
                                    System.out.println("Diagnosis already exists in the record.");
                                }
                            } else {
                                System.out.println("No diagnosis entered. Please provide a valid input.");
                            }
                        }
                        case 2 -> {
                            // Update Prescriptions
                            List<Prescription> updatedPrescriptions = record.getPrescriptions();
                            System.out.println("Current Medications:");
                            inventoryController.viewInventory();

                            String prescriptionName;
                            while (true) {
                                System.out.println("Enter prescription Name: ");
                                prescriptionName = sc.nextLine().trim();
                                if (inventory.containsKey(prescriptionName)) {
                                    break;
                                }
                                System.out.println("Error: Medication " + prescriptionName + " is not available in inventory. Please try again.");
                            }

                            System.out.println("Enter new prescription Amount: ");
                            int prescriptionAmount = 0;
                            while (!sc.hasNextInt()) {
                                System.out.println("Option not valid. Please try again:");
                                sc.next();
                            }
                            prescriptionAmount = sc.nextInt();
                            sc.nextLine();

                            System.out.println("Enter new prescription Dosage: ");
                            String prescriptionDosage = sc.nextLine().trim();

                            Prescription newPrescription = new Prescription(prescriptionName, prescriptionAmount, prescriptionDosage);
                            updatedPrescriptions.add(newPrescription);
                            record.setPrescriptions(updatedPrescriptions);
                            recordSaver.saveRecords();

                            System.out.println("Prescription updated successfully.");
                        }
                        case 3 -> {
                            // Exit menu
                            exit = true;
                            System.out.println("Exiting update menu...");
                        }
                        default -> {
                            System.out.println("Option not valid. Please try again.");
                        }
                    }
                }
            } else {
                System.out.println("Error: You are not authorized to update this appointment.");
            }
        } else {
            System.out.println("Error: Appointment not found.");
        }
    }
}
