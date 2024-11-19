package MedicineInventorySystem;

import java.io.IOException;
import java.util.*;

/**
 * The {@code InventoryLoader} class is responsible for loading medication
 * inventory data
 * into the system. It retrieves the data from a persistent storage using the
 * {@link MedicineInventoryService} and populates the inventory map.
 */

public class InventoryLoader {
    private Map<String, MedicationInventory> inventory;
    private MedicineInventoryService inventoryService;

    /**
     * Constructs an {@code InventoryLoader} with the specified inventory map.
     *
     * @param inventory a {@code Map<String, MedicationInventory>} representing
     *                  the inventory to be populated.
     */
    public InventoryLoader(Map<String, MedicationInventory> inventory) {
        this.inventory = inventory;
        this.inventoryService = new MedicineInventoryService();
    }

    /**
     * Loads the initial inventory data from the persistent storage and populates
     * the inventory map.
     *
     * <p>
     * This method uses the {@code MedicineInventoryService} to fetch a list of
     * {@link MedicationInventory} objects and adds them to the inventory map. If
     * an error occurs during the loading process, it is logged to the standard
     * error output.
     * </p>
     */
    public void loadInitialInventory() {
        try {
            // Retrieve the list of medications from the inventory service
            List<MedicationInventory> medications = inventoryService.load();

            // Populate the inventory map with medication data
            for (MedicationInventory medication : medications) {
                inventory.put(medication.getName(), medication);
            }
            // Uncomment for debugging: System.out.println("Inventory loaded
            // successfully.");
        } catch (IOException e) {
            System.err.println("Error loading inventory: " + e.getMessage());
        }
    }
}
