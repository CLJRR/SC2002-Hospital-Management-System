package service;

import entity.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MedicationInventoryService {

    private static final String FILE_NAME = "./data/medicationInventory.txt";

    // Function to load all MedicationInventory from a text file
    public List<MedicationInventory> loadAll() {
        List<MedicationInventory> inventoryList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String input;
            while ((input = reader.readLine()) != null) {
                MedicationInventory inventory = toObject(input);
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

    // Helper method to convert text input to MedicationInventory object
    private MedicationInventory toObject(String input) {
        String[] data = input.split(",");
        if (data.length == 3) {
            try {
                String name = data[0];
                int stock = Integer.parseInt(data[1]);
                int alertLevel = Integer.parseInt(data[2]);
                return new MedicationInventory(name, stock, alertLevel);
            } catch (NumberFormatException e) {
                System.err.println("Error parsing medication data: " + input);
            }
        }
        return null;
    }

    // Helper method to format MedicationInventory object to text input
    private String format(MedicationInventory inventory) {
        return inventory.getName() + "," + inventory.getStock() + "," + inventory.getAlertlevel();
    }
}
