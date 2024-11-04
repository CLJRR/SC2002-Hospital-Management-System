package service;

import entity.Patient;
import enums.Gender;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PatientService {

    private static final String FILE_NAME = "./data/patients.txt";

    // Load all Patient records from the file
    public List<Patient> loadAll() {
        List<Patient> patients = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String input;
            while ((input = reader.readLine()) != null) {
                Patient patient = toObject(input);
                if (patient != null) {
                    patients.add(patient);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return patients;
    }

    // Get Patient by ID
    public Patient getById(String id) {
        List<Patient> patients = loadAll();
        for (Patient patient : patients) {
            if (patient.getId().equals(id)) {
                return patient;
            }
        }
        System.out.println("ID: " + id + " not found.");
        return null;
    }

    // Save a Patient to the file if ID is not duplicate
    public void save(Patient patient) {
        List<Patient> patients = loadAll();

        // Check for duplicates by ID
        if (isDuplicateId(patient.getId(), patients)) {
            System.out.println("ID: " + patient.getId() + " already exists. Cannot add duplicate.");
            return;
        }

        // If no duplicate, save the patient
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(format(patient));
            writer.newLine();
            System.out.println("Patient record saved: " + patient);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    // Delete a Patient by ID
    public void deleteById(String id) {
        List<Patient> patients = loadAll();
        boolean found = false;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Patient patient : patients) {
                if (!patient.getId().equals(id)) {
                    writer.write(format(patient));
                    writer.newLine();
                } else {
                    found = true;
                }
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
        if (found) {
            System.out.println("ID: " + id + " has been deleted.");
        } else {
            System.out.println("ID: " + id + " not found.");
        }
    }

    // Delete all Patient records
    public void deleteAll() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, false))) {
            System.out.println("All patient records have been deleted.");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    // Helper method to convert a input of text to a Patient object
    private Patient toObject(String input) {
        String[] data = input.split(",");
        if (data.length == 9) {
            try {
                String id = data[0];
                String name = data[1];
                String password = data[2];
                Integer age = Integer.parseInt(data[3]);
                LocalDate dateOfBirth = LocalDate.parse(data[4]);
                Gender gender = Gender.valueOf(data[5].toUpperCase());
                String phoneNumber = data[6];
                String email = data[7];
                String bloodType = data[8];
                return new Patient(id, name, password, age, dateOfBirth, gender, phoneNumber, email, bloodType);
            } catch (Exception e) {
                System.err.println("Error parsing patient data: " + input + " - " + e.getMessage());
            }
        } else {
            System.err.println("Invalid data format: " + input);
        }
        return null;
    }

    // Helper method to format Patient object as a input for file storage
    private String format(Patient patient) {
        return String.join(",",
                patient.getId(),
                patient.getName(),
                patient.getPassword(),
                String.valueOf(patient.getAge()),
                patient.getDateOfBirth().toString(),
                patient.getGender().name(),
                patient.getPhoneNumber(),
                patient.getEmail(),
                patient.getBloodType()
        );
    }

    // Helper method to check if a Patient ID is duplicate
    private boolean isDuplicateId(String id, List<Patient> patients) {
        return patients.stream().anyMatch(p -> p.getId().equals(id));
    }
}
