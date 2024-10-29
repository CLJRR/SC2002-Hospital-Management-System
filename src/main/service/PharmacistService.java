package service;

import entity.Pharmacist;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PharmacistService {

    static String fileName = "./data/pharmacists.txt";

    // Load all pharmacists from a text file
    public static List<Pharmacist> loadPharmacistsFromFile() {
        List<Pharmacist> pharmacists = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 5) {
                    String id = data[0];
                    String name = data[1];
                    String gender = data[2];
                    Integer age = Integer.parseInt(data[3]);
                    Pharmacist pharmacist = new Pharmacist(id, name, gender, age);
                    pharmacists.add(pharmacist);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pharmacists;
    }

    public static Pharmacist getPharmacistById(String id) {
        List<Pharmacist> pharmacists = loadPharmacistsFromFile();

        for (Pharmacist pharmacist : pharmacists) {
            if (pharmacist.getId().equals(id)) {
                return pharmacist;  // Doctor found, return the object
            }
        }

        System.out.println("Pharmacist with ID: " + id + " not found.");
        return null;  // Return null if doctor not found
    }

    // Save a pharmacist to file with duplicate check
    public void savePharmacistToFile(Pharmacist pharmacist) {
        List<Pharmacist> pharmacists = loadPharmacistsFromFile();

        // Check for duplicates by ID
        for (Pharmacist existingPharmacist : pharmacists) {
            if (existingPharmacist.getId().equals(pharmacist.getId())) {
                System.out.println("Pharmacist with ID: " + pharmacist.getId() + " already exists. Cannot add duplicate.");
                return;
            }
        }
        // If no duplicate, save the pharmacist
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(pharmacist.getId() + "," + pharmacist.getName() + "," + pharmacist.getGender() + "," + pharmacist.getAge() + "," + pharmacist.getPassword());
            writer.newLine();
            System.out.println("Pharmacist record saved: " + pharmacist);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public static void deleteDoctorById(String id) {
        List<Pharmacist> pharmacists = loadPharmacistsFromFile();
        boolean pharmacistFound = false;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Pharmacist pharmacist : pharmacists) {
                if (!pharmacist.getId().equals(id)) {
                    writer.write(pharmacist.getId() + "," + pharmacist.getName() + "," + pharmacist.getGender() + "," + pharmacist.getAge() + "," + pharmacist.getPassword());
                    writer.newLine();
                } else {
                    pharmacistFound = true;
                }
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }

        if (pharmacistFound) {
            System.out.println("Pharmacist with ID: " + id + " has been deleted.");
        } else {
            System.out.println("Pharmacist with ID: " + id + " not found.");
        }
    }

    // Function to change the password of a doctor by ID
    public static void changeDoctorPassword(String id, String newPassword) {
        List<Pharmacist> pharmacists = loadPharmacistsFromFile();
        boolean pharmacistFound = false;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Pharmacist pharmacist : pharmacists) {
                if (pharmacist.getId().equals(id)) {
                    pharmacist.setPassword(newPassword);
                    pharmacistFound = true;
                }
                writer.write(pharmacist.getId() + "," + pharmacist.getName() + "," + pharmacist.getGender() + "," + pharmacist.getAge() + "," + pharmacist.getPassword());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }

        if (pharmacistFound) {
            System.out.println("Password updated successfully for Doctor ID: " + id);
        } else {
            System.out.println("Doctor with ID: " + id + " not found.");
        }
    }
}
