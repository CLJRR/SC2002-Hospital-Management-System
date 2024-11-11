package UserSystem;

import AppointmentOutcomeSystem.*;
import java.util.*;

public class DoctorMedicalRecordUpdater {
    private User doctor;
    private GetUser getUser;
    private Map<String, AppointmentOutcomeRecord> appointmentOutcomeRecords;

    public DoctorMedicalRecordUpdater(String UserId, Map<String, AppointmentOutcomeRecord> appointOutcomeRecords) {
        this.getUser = new GetUser();
        this.doctor = getUser.getUser(UserId);
        this.appointmentOutcomeRecords = appointOutcomeRecords;
    }

    public void updateRecord(String apptId) {
        Scanner sc = new Scanner(System.in);
        AppointmentOutcomeRecord record = appointmentOutcomeRecords.get(apptId);
        if (record != null) {
            if (record.getDoctorId().equals(doctor.getId())) {
                boolean exit = false;
                while (!exit) {
                    System.out.println("1) Update Diagnoses");
                    System.out.println("2) Update Prescriptions");
                    System.out.println("3) Exit");
                    System.out.println("Select Option: ");

                    int option = sc.nextInt();
                    sc.nextLine(); // Consumes NewLine

                    switch(option) {
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
                            int prescriptionAmount = 0;
                            while (!sc.hasNextInt()) {
                                prescriptionAmount = sc.nextInt();
                            }
                            sc.nextLine(); // Consumes New Line
                            System.out.println("Enter new prescription Dosage: ");
                            String prescriptionDosage = sc.nextLine();
                            Prescription prescription = new Prescription(prescriptionName, prescriptionAmount, prescriptionDosage);
                            record.setPrescription(prescription);
                            System.out.println("Medical Record updated successfully.");
                            break;
                        }
                        case 3 -> {
                            exit = true;
                            break;
                        }
                        default -> {
                            System.out.println("Option not valid. Please try again.");
                        }
                    }
                }
            }
            else {
                System.out.println("Error updating appointment.");
            }
        }
        else {
            System.out.println("Error updating appointment.");
        }
    }
}
