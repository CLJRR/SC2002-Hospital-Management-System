package service;

import entity.*;
import enums.*;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class MedicalRecordService {

    private static final String FILE_NAME = "./data/medicalRecords.txt";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    // Save a MedicalRecord to a text file
    public void save(MedicalRecord record) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(format(record));
            writer.newLine();
            System.out.println("Medical record saved successfully.");
        } catch (IOException e) {
            System.err.println("Error saving medical record: " + e.getMessage());
        }
    }

    // Load all MedicalRecords from the text file
    public List<MedicalRecord> loadAll() {
        List<MedicalRecord> medicalRecords = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 9) { // Adjust to 9 to include Status
                    try {
                        String apptId = data[0];
                        String patientId = data[1];
                        String doctorId = data[2];
                        LocalDate appointmentDate = LocalDate.parse(data[3], DATE_FORMATTER);
                        String serviceProvided = data[4];
                        String medName = data[5];
                        Status status = Status.valueOf(data[6].toUpperCase()); // Parse Status
                        int amount = Integer.parseInt(data[7]);
                        String dosage = data[8];

                        Prescription prescription = new Prescription(medName, status, amount, dosage);
                        MedicalRecord medicalRecord = new MedicalRecord(apptId, patientId, doctorId, appointmentDate, serviceProvided, prescription);
                        medicalRecords.add(medicalRecord);
                    } catch (Exception e) {
                        System.err.println("Error parsing line: " + line + " - " + e.getMessage());
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading medical records: " + e.getMessage());
        }
        return medicalRecords;
    }

    // Get an appointment by apptId
    public MedicalRecord getApptById(String apptId) {
        List<MedicalRecord> records = loadAll();
        for (MedicalRecord record : records) {
            if (record.getApptId().equals(apptId)) {
                return record;
            }
        }
        return null; // Return null if not found
    }

    // Delete an medical record by apptId
    public void deleteByApptId(String apptId) {
        List<MedicalRecord> records = loadAll();
        boolean isDeleted = false;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (MedicalRecord record : records) {
                if (!record.getApptId().equals(apptId)) {
                    writer.write(format(record));
                    writer.newLine();
                } else {
                    isDeleted = true;
                }
            }
            if (isDeleted) {
                System.out.println("Appointment with ID " + apptId + " has been deleted.");
            } else {
                System.out.println("Appointment with ID " + apptId + " not found.");
            }
        } catch (IOException e) {
            System.err.println("Error deleting appointment: " + e.getMessage());
        }
    }

    // Format a MedicalRecord as a single line for saving to the file
    private String format(MedicalRecord record) {
        StringBuilder sb = new StringBuilder();
        sb.append(record.getApptId()).append(",")
                .append(record.getPatientId()).append(",")
                .append(record.getDoctorId()).append(",")
                .append(record.getAppointmentDate().format(DATE_FORMATTER)).append(",")
                .append(record.getServiceProvided()).append(",");

        Prescription prescription = record.getPrescription();
        sb.append(prescription.getMedName()).append(",")
                .append(prescription.getStatus()).append(",")
                .append(prescription.getAmount()).append(",")
                .append(prescription.getDosage());

        return sb.toString();
    }

}
