/**
 * The {@code InventorySaver} class handles saving medication inventory data
 * from the system to persistent storage. It uses the {@link MedicineInventoryService}
 * to save inventory records.
 */
package MedicineInventorySystem;

import java.io.IOException;
import java.util.*;

public class InventorySaver {
    private Map<String, MedicationInventory> inventory;
    private MedicineInventoryService inventoryService;

    /**
     * Constructs an {@code InventorySaver} with the specified inventory map.
     *
     * @param inventory a {@code Map<String, MedicationInventory>} representing
     *                  the current inventory to be saved.
     */
    public InventorySaver(Map<String, MedicationInventory> inventory) {
        this.inventory = inventory;
        this.inventoryService = new MedicineInventoryService();
    }

    /**
     * Saves the current inventory data to persistent storage.
     *
     * <p>
     * This method collects all {@link MedicationInventory} objects from the
     * inventory map, converts them into a list, and uses the
     * {@link MedicineInventoryService} to save the data. If an error occurs during
     * the saving process, it logs the error message to the standard error output.
     * </p>
     */
    public void saveInventory() {
        // Convert the inventory map values to a list
        List<MedicationInventory> inventoryList = new ArrayList<>(inventory.values());
        try {
            // Use the inventory service to save the inventory list
            inventoryService.save(inventoryList);
            System.out.println("Inventory saved successfully.");
        } catch (IOException e) {
            // Handle any IO exceptions during the save operation
            System.err.println("Error saving inventory: " + e.getMessage());
        }
    }
}
