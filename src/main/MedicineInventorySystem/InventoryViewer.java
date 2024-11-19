/**
 * The {@code InventoryViewer} class is responsible for displaying the current
 * medication inventory. It provides methods to view inventory details in a user-friendly format.
 */
package MedicineInventorySystem;

import java.util.*;

public class InventoryViewer {

    private Map<String, MedicationInventory> inventory;

    /**
     * Constructs an {@code InventoryViewer} with the specified inventory map.
     *
     * @param inventory a {@code Map<String, MedicationInventory>} representing
     *                  the current inventory to be displayed.
     */
    public InventoryViewer(Map<String, MedicationInventory> inventory) {
        this.inventory = inventory;
    }

    /**
     * Displays the current inventory.
     *
     * <p>
     * This method iterates through the inventory map and prints the details of each
     * medication, including its name, stock quantity, and alert level.
     * </p>
     *
     * <p>
     * If the inventory is empty, no medications will be displayed.
     * </p>
     */
    public void viewInventory() {
        System.out.println("\nCurrent Inventory:");
        for (MedicationInventory med : inventory.values()) {
            System.out.println("Name: " + med.getName() + ", Stock: " + med.getStock() + ", Alert Level: " + med.getAlertlevel());
        }
        System.out.println(); // Adds a new line after the last medication
    }
}
