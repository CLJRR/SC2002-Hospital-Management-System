package service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import entity.*;

public class MedicationInventoryService {

    private static final String FILE_NAME = "./data/MedicationInventory.txt";

    // Function to load all MedicationInventory from a text file
    public List<MedicationInventory> loadAll() {
        List<MedicationInventory> inventoryList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                MedicationInventory inventory = textToMedicationInventory(line);
                if (inventory != null) {
                    inventoryList.add(inventory);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return inventoryList;
    }

    // Function to get MedicationInventory by name
    public MedicationInventory getByName(String name) {
        List<MedicationInventory> inventoryList = loadAll();
        for (MedicationInventory inventory : inventoryList) {
            if (inventory.getName().equalsIgnoreCase(name)) {
                return inventory;
            }
        }
        System.out.println("Medication not found: " + name);
        return null;
    }

    // Function to save MedicationInventory to a text file
    public boolean save(MedicationInventory inventory) {
        List<MedicationInventory> inventoryList = loadAll();
        // Check if medication with the same name already exists
        for (MedicationInventory existingInventory : inventoryList) {
            if (existingInventory.getName().equalsIgnoreCase(inventory.getName())) {
                System.out.println("Medication already exists: " + inventory.getName());
                return false;
            }
        }
        // Save new medication to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(format(inventory));
            writer.newLine();
            System.out.println("Medication saved successfully: " + inventory.getName());
            return true;
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
            return false;
        }
    }

    // Function to delete MedicationInventory by name from a text file
    public boolean deleteByName(String name) {
        List<MedicationInventory> inventoryList = loadAll();
        boolean found = false;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (MedicationInventory inventory : inventoryList) {
                if (!inventory.getName().equalsIgnoreCase(name)) {
                    writer.write(format(inventory));
                    writer.newLine();
                } else {
                    found = true;
                }
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
            return false;
        }
        if (found) {
            System.out.println("Medication deleted: " + name);
        } else {
            System.out.println("Medication not found: " + name);
        }
        return found;
    }

    // Helper function to delete all MedicationInventory records
    public void deleteAll() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, false))) {
            System.out.println("All medication records deleted.");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    // Helper method to convert text line to MedicationInventory object
    private MedicationInventory textToMedicationInventory(String line) {
        String[] data = line.split(",");
        if (data.length == 3) {
            try {
                String name = data[0];
                int stock = Integer.parseInt(data[1]);
                int alertLevel = Integer.parseInt(data[2]);
                return new MedicationInventory(name, stock, alertLevel);
            } catch (NumberFormatException e) {
                System.err.println("Error parsing medication data: " + line);
            }
        }
        return null;
    }

    // Helper method to format MedicationInventory object to text line
    private String format(MedicationInventory inventory) {
        return inventory.getName() + "," + inventory.getStock() + "," + inventory.getAlertlevel();
    }
}
