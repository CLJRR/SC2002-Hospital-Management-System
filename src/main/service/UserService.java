package service;

import entity.Patient;
import enums.Gender;
import enums.Role;
import interfaces.*;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserService implements Load, Format, Save, Write, toObject {

    private static final String FILE_NAME = "./data/users.txt";

    @Override
    public List<?> load(String fileName) throws IOException {
        List<Patient> data = new ArrayList<>();
        try (Scanner scanner = new Scanner(new FileInputStream(fileName))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Patient patient = (Patient) toObject(line);
                data.add(patient);
            }
        }
        return data;
    }

    @Override
    public void save(String filename, List<?> list) throws IOException {
        List<String> data = new ArrayList<>();
        for (Object obj : list) {
            if (obj instanceof Patient) {
                String formattedString = format(obj); // Use format method
                data.add(formattedString);
            } else {
                throw new IOException("List contains incorrect objects.");
            }
        }
        write(filename, data);

    }

    @Override
    public void write(String fileName, List<String> data) throws IOException {
        try (PrintWriter out = new PrintWriter(new FileWriter(fileName))) {
            for (String line : data) {
                out.println(line);
            }
        }
    }

    @Override
    public String format(Object object) throws IOException {
        if (object instanceof Patient patient) {
            if (patient.getDateOfBirth() == null) {
                return String.join(",",
                        patient.getId(),
                        patient.getName(),
                        patient.getPassword(),
                        String.valueOf(patient.getAge()),
                        null,
                        patient.getGender().name(),
                        patient.getPhoneNumber(),
                        patient.getEmail(),
                        patient.getBloodType(),
                        patient.getRole().name()
                );
            } else {

                return String.join(",",
                        patient.getId(),
                        patient.getName(),
                        patient.getPassword(),
                        String.valueOf(patient.getAge()),
                        patient.getDateOfBirth().toString(),
                        patient.getGender().name(),
                        patient.getPhoneNumber(),
                        patient.getEmail(),
                        patient.getBloodType(),
                        patient.getRole().name()
                );

            }
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
            LocalDate dateOfBirth = null;
            if (!"null".equals(parts[4])) {
                dateOfBirth = LocalDate.parse(parts[4]);
            }
            Gender gender = Gender.valueOf(parts[5].toUpperCase());
            String phoneNumber = parts[6];
            String email = parts[7];
            String bloodType = parts[8];
            Role role = Role.valueOf(parts[9].trim().toUpperCase());
            return new Patient(id, name, password, age, dateOfBirth, gender, phoneNumber, email, bloodType, role);

        } catch (NumberFormatException e) {
            throw new IOException("Invalid data format for age: " + parts[3], e);
        }
    }
    // // Save a Patient to the file if ID is not duplicate
    // public boolean save(Patient patient) {
    //     List<Patient> patients = loadAll();
    //     // Check for duplicates by ID
    //     if (isDuplicateId(patient.getId(), patients)) {
    //         System.out.println("ID: " + patient.getId() + " already exists. Cannot add duplicate.");
    //         return false;
    //     }
    //     // If no duplicate, save the patient
    //     try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
    //         writer.write(format(patient));
    //         writer.newLine();
    //         System.out.println("Patient record saved: " + patient);
    //         return true;
    //     } catch (IOException e) {
    //         System.out.println("Error writing to file: " + e.getMessage());
    //         return false;
    //     }
    // }
    // // Delete a Patient by ID
    // public void deleteById(String id) {
    //     List<Patient> patients = loadAll();
    //     boolean found = false;
    //     try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
    //         for (Patient patient : patients) {
    //             if (!patient.getId().equals(id)) {
    //                 writer.write(format(patient));
    //                 writer.newLine();
    //             } else {
    //                 found = true;
    //             }
    //         }
    //     } catch (IOException e) {
    //         System.out.println("Error writing to file: " + e.getMessage());
    //     }
    //     if (found) {
    //         System.out.println("ID: " + id + " has been deleted.");
    //     } else {
    //         System.out.println("ID: " + id + " not found.");
    //     }
    // }
    // // Delete all Patient records
    // public void deleteAll() {
    //     try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, false))) {
    //         System.out.println("All patient records have been deleted.");
    //     } catch (IOException e) {
    //         System.out.println("Error writing to file: " + e.getMessage());
    //     }
    // }
    // // Helper method to convert a input of text to a Patient object
    // private Patient toObject(String input) {
    //     String[] data = input.split(",");
    //     if (data.length == 9) {
    //         try {
    //             String id = data[0];
    //             String name = data[1];
    //             String password = data[2];
    //             Integer age = Integer.parseInt(data[3]);
    //             LocalDate dateOfBirth = LocalDate.parse(data[4]);
    //             Gender gender = Gender.valueOf(data[5].toUpperCase());
    //             String phoneNumber = data[6];
    //             String email = data[7];
    //             String bloodType = data[8];
    //             return new Patient(id, name, password, age, dateOfBirth, gender, phoneNumber, email, bloodType);
    //         } catch (Exception e) {
    //             System.err.println("Error parsing patient data: " + input + " - " + e.getMessage());
    //         }
    //     } else {
    //         System.err.println("Invalid data format: " + input);
    //     }
    //     return null;
    // }
    // // Helper method to format Patient object as a input for file storage
    // private String format(Patient patient) {
    //     return String.join(",",
    //             patient.getId(),
    //             patient.getName(),
    //             patient.getPassword(),
    //             String.valueOf(patient.getAge()),
    //             patient.getDateOfBirth().toString(),
    //             patient.getGender().name(),
    //             patient.getPhoneNumber(),
    //             patient.getEmail(),
    //             patient.getBloodType()
    //     );
    // }
    // // Helper method to check if a Patient ID is duplicate
    // private boolean isDuplicateId(String id, List<Patient> patients) {
    //     return patients.stream().anyMatch(p -> p.getId().equals(id));
    // }
}
