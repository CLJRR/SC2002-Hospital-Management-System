package MedicineInventorySystem;

import java.io.IOException;
import java.util.*;

public class InventoryLoader {
    private Map<String, MedicationInventory> inventory;
    private MedicineInventoryService inventoryService;

    public InventoryLoader(Map<String, MedicationInventory> inventory) {
        this.inventory = inventory;
        this.inventoryService = new MedicineInventoryService();
    }

    public void loadInitialInventory() {
        try {
            List<MedicationInventory> medications = inventoryService.load(); // Ensure `load` method exists in `MedicineInventoryService`
            for (MedicationInventory medication : medications) {
                inventory.put(medication.getName(), medication);
            }
            //System.out.println("Inventory loaded successfully.");
        } catch (IOException e) {
            System.err.println("Error loading inventory: " + e.getMessage());
        }
    }
}
