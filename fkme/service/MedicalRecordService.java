package service;

import entity.*;
import enums.Flag;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class MedicalRecordService {

    private static final String FILE_NAME = "./data/medicalRecords.txt";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    // Load all MedicalRecords from the text file
    public List<    @Override
    public String format(Object object) throws IOException {
        if (object instanceof User user) {
            return String.join(",",
                    user.getId(),
                    user.getName(),
                    user.getPassword(),
                    String.valueOf(user.getAge()),
                    user.getDateOfBirth() == null ? "null" : user.getDateOfBirth().toString(),
                    user.getGender().name(),
                    user.getPhoneNumber() == null ? "null" : user.getPhoneNumber(),
                    user.getEmail() == null ? "null" : user.getEmail(),
                    user.getBloodType() == null ? "null" : user.getBloodType(),
                    user.getRole().name()
            );

        } else {
            throw new IOException("Invalid object type");
        }
    }

    @Override
    public Object toObject(String string) throws IOException {
        String[] parts = string.split(",");

        // Validate the format by checking the number of fields
        if (parts.length != 10) {
            throw new IOException("Invalid format.");
        }
        try {
            String id = parts[0];
            String name = parts[1];
            String password = parts[2];
            Integer age = Integer.parseInt(parts[3]);
            // Handle nullable dateOfBirth field
            LocalDate dateOfBirth = "null".equals(parts[4]) ? null : LocalDate.parse(parts[4]);
            // Parse enum values with error handling
            Gender gender = Gender.valueOf(parts[5].toUpperCase());
            String phoneNumber = "null".equals(parts[6]) ? null : parts[6];
            String email = "null".equals(parts[7]) ? null : parts[7];
            String bloodType = "null".equals(parts[8]) ? null : parts[8];
            Role role = Role.valueOf(parts[9].trim().toUpperCase());

            return new User(id, name, password, age, dateOfBirth, gender, phoneNumber, email, bloodType, role);

        } catch (NumberFormatException e) {
            throw new IOException("Invalid data format for age: " + parts[3], e);
        } catch (IllegalArgumentException e) {
            throw new IOException("Invalid enum value in Gender or Role field.", e);
        }
    }> loadAll() {
        List<MedicalRecord> medicalRecords = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String input;
            while ((input = reader.readLine()) != null) {
                MedicalRecord record = toObject(input);
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
    public MedicalRecord getById(String apptId) {
        return loadAll().stream()
                .filter(record -> record.getApptId().equals(apptId))
                .findFirst()
                .orElse(null);
    }

    // Delete a MedicalRecord by appointment ID
    public void deleteById(String apptId) {
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

    // Helper: Convert a input of text to a MedicalRecord object
    private MedicalRecord toObject(String input) {
        String[] data = input.split(",");
        if (data.length == 10) {
            try {
                String apptId = data[0];
                String patientId = data[1];
                String doctorId = data[2];
                LocalDate appointmentDate = LocalDate.parse(data[3], DATE_FORMATTER);
                String serviceProvided = data[4];
                String diagnoses = data[5];
                String medName = data[6];
                Flag flag = Flag.valueOf(data[7].toUpperCase());
                int amount = Integer.parseInt(data[8]);
                String dosage = data[9];

                Prescription prescription = new Prescription(medName, flag, amount, dosage);
                return new MedicalRecord(apptId, patientId, doctorId, appointmentDate, serviceProvided, diagnoses, prescription);
            } catch (Exception e) {
                System.err.println("Error parsing medical record data: " + input + " - " + e.getMessage());
            }
        } else {
            System.err.println("Invalid data format: " + input);
        }
        return null;
    }

    // Helper: Format a MedicalRecord object as a input for file storage
    private String format(MedicalRecord record) {
        StringBuilder sb = new StringBuilder();
        sb.append(record.getApptId()).append(",")
                .append(record.getPatientId()).append(",")
                .append(record.getDoctorId()).append(",")
                .append(record.getAppointmentDate().format(DATE_FORMATTER)).append(",")
                .append(record.getServiceProvided()).append(",")
                .append(record.getDiagnoses()).append(",");

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
