package MedicineInventorySystem;

import java.io.IOException;
import java.util.*;

public class InventorySaver {
    private Map<String, MedicationInventory> inventory;
    private MedicineInventoryService inventoryService;

    public InventorySaver(Map<String, MedicationInventory> inventory) {
        this.inventory = inventory;
        this.inventoryService = new MedicineInventoryService();
    }

    public void saveInventory() {
        List<MedicationInventory> inventoryList = new ArrayList<>(inventory.values());
        try {
            inventoryService.save(inventoryList); // Ensure `save` method exists in `MedicineInventoryService`
            System.out.println("Inventory saved successfully.");
        } catch (IOException e) {
            System.err.println("Error saving inventory: " + e.getMessage());
        }
    }
}
