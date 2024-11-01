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

    // Load all MedicalRecords from the text file
    public List<MedicalRecord> loadAll() {
        List<MedicalRecord> medicalRecords = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                MedicalRecord record = toObject(line);
                if (record != null) {
                    medicalRecords.add(record);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading medical records: " + e.getMessage());
        }
        return medicalRecords;
    }

    // Save a MedicalRecord to a text file if no duplicate ID exists
    public void save(MedicalRecord record) {
        List<MedicalRecord> medicalRecords = loadAll();

        // Check for duplicate by appointment ID
        if (isDuplicateApptId(record.getApptId(), medicalRecords)) {
            System.out.println("ID: " + record.getApptId() + " already exists. Cannot add duplicate.");
            return;
        }

        // If no duplicate, save the record
        medicalRecords.add(record);
        if (writeRecordsToFile(medicalRecords)) {
            System.out.println("Medical record " + record.getApptId() + " saved successfully.");
        }
    }

    // Get an appointment by appointment ID
    public MedicalRecord getApptById(String apptId) {
        return loadAll().stream()
                .filter(record -> record.getApptId().equals(apptId))
                .findFirst()
                .orElse(null);
    }

    // Delete a MedicalRecord by appointment ID
    public void deleteByApptId(String apptId) {
        List<MedicalRecord> records = loadAll();
        boolean isDeleted = records.removeIf(record -> record.getApptId().equals(apptId));

        if (writeRecordsToFile(records)) {
            if (isDeleted) {
                System.out.println("Appointment with ID " + apptId + " has been deleted.");
            } else {
                System.out.println("Appointment with ID " + apptId + " not found.");
            }
        }
    }

    // Delete all MedicalRecords
    public void deleteAll() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, false))) {
            System.out.println("All medical records have been deleted.");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    // Helper: Convert a line of text to a MedicalRecord object
    private MedicalRecord toObject(String line) {
        String[] data = line.split(",");
        if (data.length == 9) {
            try {
                String apptId = data[0];
                String patientId = data[1];
                String doctorId = data[2];
                LocalDate appointmentDate = LocalDate.parse(data[3], DATE_FORMATTER);
                String serviceProvided = data[4];
                String medName = data[5];
                Status status = Status.valueOf(data[6].toUpperCase());
                int amount = Integer.parseInt(data[7]);
                String dosage = data[8];

                Prescription prescription = new Prescription(medName, status, amount, dosage);
                return new MedicalRecord(apptId, patientId, doctorId, appointmentDate, serviceProvided, prescription);
            } catch (Exception e) {
                System.err.println("Error parsing medical record data: " + line + " - " + e.getMessage());
            }
        } else {
            System.err.println("Invalid data format: " + line);
        }
        return null;
    }

    // Helper: Format a MedicalRecord object as a line for file storage
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

    // Helper: Check if a MedicalRecord appointment ID is duplicate
    private boolean isDuplicateApptId(String apptId, List<MedicalRecord> records) {
        return records.stream().anyMatch(record -> record.getApptId().equals(apptId));
    }

    // Helper: Write all MedicalRecords to the file
    private boolean writeRecordsToFile(List<MedicalRecord> records) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, false))) {
            for (MedicalRecord record : records) {
                writer.write(format(record));
                writer.newLine();
            }
            return true;
        } catch (IOException e) {
            System.err.println("Error writing medical records to file: " + e.getMessage());
            return false;
        }
    }
}
