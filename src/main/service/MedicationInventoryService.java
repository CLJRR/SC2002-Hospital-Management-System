package service;

import entity.MedicationInventory;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MedicationInventoryService {

    static String fileName = "./data/medicationInventory.txt";

    // Load all medication from file
    public static List<MedicationInventory> load() {
        List<MedicationInventory> medicationInventorys = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 5) {
                    String id = data[0];
                    String name = data[1];
                    String gender = data[2];
                    Integer age = Integer.parseInt(data[3]);
                    MedicationInventory medicationInventory = new MedicationInventory(id, name, gender, age);
                    medicationInventorys.add(medicationInventory);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return medicationInventorys;
    }

    public static MedicationInventory getAdministratorById(String id) {
        List<MedicationInventory> medicationInventorys = loadAdministratorsFromFile();

        for (MedicationInventory medicationInventory : medicationInventorys) {
            if (medicationInventory.getId().equals(id)) {
                return medicationInventory;  // Doctor found, return the object
            }
        }

        System.out.println("Pharmacist with ID: " + id + " not found.");
        return null; // Return null if doctor not found

    }

    public void saveAdministratorToFile(MedicationInventory medicationInventory) {

        List<MedicationInventory> medicationInventorys = loadAdministratorsFromFile();

        // Check for duplicates by name
        for (MedicationInventory existingAdministrator : medicationInventorys) {
            if (existingAdministrator.getId().equals(medicationInventory.getId())) {
                System.out.println("MedicationInventory with name: " + medicationInventory.getId() + " already exists. Cannot add duplicate.");
                return;
            }
        }

        // If no duplicate, save the medicationInventory
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(medicationInventory.getId() + "," + medicationInventory.getName() + "," + medicationInventory.getGender() + "," + medicationInventory.getAge() + "," + medicationInventory.getPassword());
            writer.newLine();
            System.out.println("MedicationInventory record saved: " + medicationInventory);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public static void deleteAdministratorById(String id) {
        List<MedicationInventory> medicationInventorys = loadAdministratorsFromFile();
        boolean doctorFound = false;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (MedicationInventory medicationInventory : medicationInventorys) {
                if (!medicationInventory.getId().equals(id)) {
                    writer.write(medicationInventory.getId() + "," + medicationInventory.getName() + "," + medicationInventory.getGender() + "," + medicationInventory.getAge() + "," + medicationInventory.getPassword());
                    writer.newLine();
                } else {
                    doctorFound = true;
                }
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }

        if (doctorFound) {
            System.out.println("Doctor with ID: " + id + " has been deleted.");
        } else {
            System.out.println("Doctor with ID: " + id + " not found.");
        }
    }

    // Function to change the password of a medicationInventory by ID
    public static void changeDoctorPassword(String id, String newPassword) {
        List<MedicationInventory> medicationInventorys = loadAdministratorsFromFile();
        boolean administratorFound = false;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (MedicationInventory medicationInventory : medicationInventorys) {
                if (medicationInventory.getId().equals(id)) {
                    medicationInventory.setPassword(newPassword);
                    administratorFound = true;
                }
                writer.write(medicationInventory.getId() + "," + medicationInventory.getName() + "," + medicationInventory.getGender() + "," + medicationInventory.getAge() + "," + medicationInventory.getPassword());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }

        if (administratorFound) {
            System.out.println("Password updated successfully for Doctor ID: " + id);
        } else {
            System.out.println("Doctor with ID: " + id + " not found.");
        }
    }
}
