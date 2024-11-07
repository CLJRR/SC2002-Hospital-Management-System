package MedicineInventorySystem;

import TODO.MedicationInventory;
import java.io.*;
import java.util.*;

public class MedicineInventoryService {

    // Default file path for the inventory file
    private static final String DEFAULT_FILE_PATH = "src/main/data/medicine_inventory.txt";

    // Load medicines from the default file and return as a list of MedicationInventory objects
    public List<MedicationInventory> loadInventory() {
        List<MedicationInventory> inventoryList = new ArrayList<>();
        try (Scanner scanner = new Scanner(new FileInputStream(DEFAULT_FILE_PATH))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                MedicationInventory medicine = toObject(line);
                inventoryList.add(medicine);
            }
            System.out.println("Inventory loaded successfully from " + DEFAULT_FILE_PATH);
        } catch (IOException e) {
            System.err.println("Error loading inventory: " + e.getMessage());
        }
        return inventoryList;
    }

    // Save a list of MedicationInventory objects to the default file
    public void saveInventory(List<MedicationInventory> inventoryList) {
        List<String> data = new ArrayList<>();
        for (MedicationInventory medicine : inventoryList) {
            String formattedString = format(medicine);
            data.add(formattedString);
        }
        try {
            write(DEFAULT_FILE_PATH, data);
            System.out.println("Inventory saved successfully to " + DEFAULT_FILE_PATH);
        } catch (IOException e) {
            System.err.println("Error saving inventory: " + e.getMessage());
        }
    }

    // Write data to a file line-by-line
    private void write(String fileName, List<String> data) throws IOException {
        try (PrintWriter out = new PrintWriter(new FileWriter(fileName))) {
            for (String line : data) {
                out.println(line);
            }
        }
    }

    // Format a MedicationInventory object as a string for saving
    private String format(MedicationInventory medicine) {
        return String.join(",",
                medicine.getName(),
                String.valueOf(medicine.getStock()),
                String.valueOf(medicine.getAlertlevel())
        );
    }

    // Convert a line from the file into a MedicationInventory object
    private MedicationInventory toObject(String line) throws IOException {
        String[] parts = line.split(",");
        if (parts.length != 3) {
            throw new IOException("Invalid format. Each line must have exactly 3 fields.");
        }

        try {
            String name = parts[0];
            int stock = Integer.parseInt(parts[1]);
            int alertLevel = Integer.parseInt(parts[2]);
            return new MedicationInventory(name, stock, alertLevel);
        } catch (NumberFormatException e) {
            throw new IOException("Invalid number format in stock or alert level field.", e);
        }
    }
}
