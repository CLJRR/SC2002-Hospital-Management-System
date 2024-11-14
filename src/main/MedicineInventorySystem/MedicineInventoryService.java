package MedicineInventorySystem;

import FileManager.Format;
import FileManager.Load;
import FileManager.Save;
import FileManager.Write;
import FileManager.toObject;
import java.io.*;
import java.util.*;

public class MedicineInventoryService implements Load, Format, Save, Write, toObject {
    private static final String FILENAME = "./data/medicine_inventory.txt";
    

    // Load medicines from the file and return as a list of MedicationInventory objects
    @Override
    public List<MedicationInventory> load() throws IOException {
        List<MedicationInventory> inventoryList = new ArrayList<>();
        try (Scanner scanner = new Scanner(new FileInputStream(FILENAME))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                MedicationInventory medicine = (MedicationInventory) toObject(line);
                inventoryList.add(medicine);
            }
        }
        return inventoryList;
    }

    // Save a list of MedicationInventory objects to the file
    @Override
    public void save(List<?> inventoryList) throws IOException {
        List<String> data = new ArrayList<>();
        for (Object obj : inventoryList) {
            if (!(obj instanceof MedicationInventory)) {
                throw new IOException("List contains incorrect objects.");
            }
            String formattedString = format(obj);
            data.add(formattedString);
        }
        write(data);
    }

    // Write data to the file line-by-line
    @Override
    public void write(List<String> data) throws IOException {
        try (PrintWriter out = new PrintWriter(new FileWriter(FILENAME))) {
            for (String line : data) {
                out.println(line);
            }
        }
    }

    // Format a MedicationInventory object as a string for saving
    @Override
    public String format(Object obj) throws IOException {
        if (obj instanceof MedicationInventory medicine) {
            return String.join(",",
                    medicine.getName(),
                    String.valueOf(medicine.getStock()),
                    String.valueOf(medicine.getAlertlevel())
            );
        } else {
            throw new IOException("Invalid object type");
        }
    }

    // Convert a line from the file into a MedicationInventory object
    @Override
    public Object toObject(String line) throws IOException {
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
