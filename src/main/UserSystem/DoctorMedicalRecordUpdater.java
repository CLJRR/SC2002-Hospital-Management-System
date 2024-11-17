package UserSystem;

import AppointmentOutcomeSystem.*;
import MedicineInventorySystem.InventoryController;
import MedicineInventorySystem.MedicationInventory;
import SessionManager.Session;
import java.util.*;

public class DoctorMedicalRecordUpdater {

    private User doctor;
    private GetUser getUser;
    private Map<String, AppointmentOutcomeRecord> appointmentOutcomeRecords;
    AppointmentOutcomeRecordSaver recordSaver;
    static final Scanner sc = new Scanner(System.in);
    private InventoryController inventoryController;

    public DoctorMedicalRecordUpdater(String UserId, Map<String, AppointmentOutcomeRecord> appointOutcomeRecords) {
        this.getUser = new GetUser();
        this.doctor = getUser.getUser(UserId);
        this.appointmentOutcomeRecords = appointOutcomeRecords;
        this.recordSaver = new AppointmentOutcomeRecordSaver(appointmentOutcomeRecords);
        this.inventoryController = new InventoryController();
    }

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

                    while (!sc.hasNextInt()) { // Check if input is an integer
                        System.out.println("Option not valid. Please try again:");
                        sc.next(); // Clear the invalid input
                    }

                    int option = sc.nextInt();
                    sc.nextLine(); // Consumes newline

                    switch (option) {
                        case 1 -> {
                            System.out.print("Enter a new diagnosis: ");
                            String newDiagnosis = sc.nextLine().trim(); // Trim to avoid unnecessary spaces

                            if (!newDiagnosis.isEmpty()) {
                                // Get the current diagnoses, append the new one, and update the list
                                List<String> updatedDiagnoses = record.getDiagnoses(); // This returns a copy
                                if (!updatedDiagnoses.contains(newDiagnosis)) {
                                    updatedDiagnoses.add(newDiagnosis); // Append the new diagnosis
                                    record.setDiagnoses(updatedDiagnoses); // Update the record with the new list
                                    recordSaver.saveRecords(); // Save changes
                                    System.out.println("Updated Record:");
                                    System.out.println(record); // Display updated record
                                    System.out.println("Diagnosis added successfully.");
                                } else {
                                    System.out.println("Diagnosis already exists in the record.");
                                }
                            } else {
                                System.out.println("No diagnosis entered. Please provide a valid input.");
                            }
                        }
                        case 2 -> {
                            List<Prescription> updatedPrescriptions = record.getPrescriptions(); // This returns a copy

                            System.out.println("Current Medications:");
                            inventoryController.viewInventory(); // Display available inventory

                            String prescriptionName = null;
                            while (true) {
                                System.out.println("Enter prescription Name: ");
                                prescriptionName = sc.nextLine().trim();

                                if (inventory.containsKey(prescriptionName)) { // Check if medication exists
                                    break; // Exit loop if the medication exists in inventory
                                }

                                System.out.println("Error: Medication " + prescriptionName + " is not available in inventory. Please try again.");
                            }
                            System.out.println("Enter new prescription Amount: ");
                            int prescriptionAmount = 0;
                            while (!sc.hasNextInt()) { // Check if input is an integer
                                System.out.println("Option not valid. Please try again:");
                                sc.next(); // Clear the invalid input
                            }
                            prescriptionAmount = sc.nextInt();
                            sc.nextLine(); // Consume newline

                            System.out.println("Enter new prescription Dosage: ");
                            String prescriptionDosage = sc.nextLine().trim();

                            // Create and add the new prescription
                            Prescription newPrescription = new Prescription(prescriptionName, prescriptionAmount, prescriptionDosage);
                            updatedPrescriptions.add(newPrescription);
                            record.setPrescriptions(updatedPrescriptions);
                            recordSaver.saveRecords();

                            System.out.println("Prescription updated successfully.");
                        }
                        case 3 -> {
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
