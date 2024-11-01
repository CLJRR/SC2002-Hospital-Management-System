package service;

import entity.*;
import enums.*;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PatientService {

    private static final String FILE_NAME = "./data/patients.txt";

    // Function to load all patients from a text file
    public List<Patient> loadAll() {
        List<Patient> patients = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 9) {
                    String id = data[0];
                    String name = data[1];
                    String password = data[2];
                    Integer age = Integer.parseInt(data[3]);
                    LocalDate dateOfBirth = LocalDate.parse(data[4]);
                    Gender gender = Gender.valueOf(data[5].toUpperCase());
                    String phoneNumber = data[6];
                    String email = data[7];
                    String bloodType = data[8];
                    Patient patient = new Patient(id, name, password, age, dateOfBirth, gender, phoneNumber, email, bloodType);
                    patients.add(patient);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return patients;
    }

    //get Patient by ID
    public Patient getById(String id) {
        List<Patient> patients = loadAll();
        for (Patient patient : patients) {
            if (patient.getId().equals(id)) {
                return patient;  // Patient found, return the object
            }
        }
        System.out.println("ID: " + id + " not found.");
        return null;  // Return null if patient not found
    }

    // Function to save patient information to a text file
    public void save(Patient patient) {
        List<Patient> patients = loadAll();
        // Check for duplicates by ID
        for (Patient existingPatient : patients) {
            if (existingPatient.getId().equals(patient.getId())) {
                System.out.println("ID: " + patient.getId() + " already exists. Cannot add duplicate.");
                return;
            }
        }
        // If no duplicate, save the patient
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(patient.getId() + ","
                    + patient.getName() + ","
                    + patient.getPassword() + ","
                    + patient.getAge() + ","
                    + patient.getDateOfBirth().toString() + ","
                    + patient.getGender() + ","
                    + patient.getPhoneNumber() + ","
                    + patient.getEmail() + ","
                    + patient.getBloodType());
            writer.newLine();
            System.out.println("Patient record saved: " + patient);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public void deleteById(String id) {
        List<Patient> patients = loadAll();
        boolean found = false;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Patient patient : patients) {
                if (!patient.getId().equals(id)) {
                    writer.write(patient.getId() + ","
                            + patient.getName() + ","
                            + patient.getPassword() + ","
                            + patient.getAge() + ","
                            + patient.getDateOfBirth().toString() + ","
                            + patient.getGender() + ","
                            + patient.getPhoneNumber() + ","
                            + patient.getEmail() + ","
                            + patient.getBloodType());
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

    // Function to change the password of a patient by ID
    public void changePassword(String id, String password, String newPassword) {
        List<Patient> patients = loadAll();
        boolean found = false;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Patient patient : patients) {
                if (patient.getId().equals(id)) {
                    found = true;
                    if (patient.verifyPassword(password)) {
                        patient.setPassword(newPassword);
                        System.out.println("Password updated successfully for ID: " + id);
                    } else {
                        System.out.println("Incorrect Password enterd for " + id);
                    }
                }
                writer.write(patient.getId() + ","
                        + patient.getName() + ","
                        + patient.getPassword() + ","
                        + patient.getAge() + ","
                        + patient.getDateOfBirth().toString() + ","
                        + patient.getGender() + ","
                        + patient.getPhoneNumber() + ","
                        + patient.getEmail() + ","
                        + patient.getBloodType());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
        if (!found) {
            System.out.println("ID: " + id + " not found.");
        }
    }

    public void deleteAll() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, false))) {
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }

    }
}
