/**
 * The {@code MedicineInventoryService} class provides functionality for managing
 * medication inventory data, including loading, saving, formatting, writing,
 * and converting data to and from {@code MedicationInventory} objects.
 * This class interacts with a file to store inventory data persistently.
 */
package MedicineInventorySystem;

import FileManager.*;
import java.io.*;
import java.util.*;

public class MedicineInventoryService implements Load, Format, Save, Write, toObject {

    private static final String FILENAME = "./data/medicine_inventory.txt";

    /**
     * Loads medicines from the inventory file and returns them as a list of
     * {@code MedicationInventory} objects.
     *
     * @return a list of {@code MedicationInventory} objects loaded from the file.
     * @throws IOException if there is an issue reading the file or parsing its content.
     */
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

    /**
     * Saves a list of {@code MedicationInventory} objects to the inventory file.
     *
     * @param inventoryList the list of {@code MedicationInventory} objects to save.
     * @throws IOException if there is an issue writing to the file or if the list contains invalid objects.
     */
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

    /**
     * Writes a list of string data to the inventory file line-by-line.
     *
     * @param data the list of strings to write to the file.
     * @throws IOException if there is an issue writing to the file.
     */
    @Override
    public void write(List<String> data) throws IOException {
        try (PrintWriter out = new PrintWriter(new FileWriter(FILENAME))) {
            for (String line : data) {
                out.println(line);
            }
        }
    }

    /**
     * Formats a {@code MedicationInventory} object as a string suitable for saving to the file.
     *
     * @param obj the {@code MedicationInventory} object to format.
     * @return a string representation of the {@code MedicationInventory} object.
     * @throws IOException if the provided object is not of type {@code MedicationInventory}.
     */
    @Override
    public String format(Object obj) throws IOException {
        if (obj instanceof MedicationInventory medicine) {
            return String.join(",",
                    medicine.getName(),
                    String.valueOf(medicine.getStock()),
                    String.valueOf(medicine.getAlertlevel()));
        } else {
            throw new IOException("Invalid object type");
        }
    }

    /**
     * Converts a string line from the file into a {@code MedicationInventory} object.
     *
     * @param line the string line to convert.
     * @return a {@code MedicationInventory} object represented by the string.
     * @throws IOException if the line format is invalid or if there is an issue parsing fields.
     */
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
