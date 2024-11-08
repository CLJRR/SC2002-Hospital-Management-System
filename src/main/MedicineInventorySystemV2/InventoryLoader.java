package MedicineInventorySystemV2;

import TODO.MedicationInventory;
import java.util.*;

public class InventoryLoader {
    private Map<String, MedicationInventory> inventory;
    private MedicineInventoryService inventoryService;

    public InventoryLoader(Map<String, MedicationInventory> inventory) {
        this.inventory = inventory;
        this.inventoryService = new MedicineInventoryService();
    }

    public void loadInitialInventory() {
        List<MedicationInventory> medications = inventoryService.loadInventory();
        for (MedicationInventory medication : medications) {
            inventory.put(medication.getName(), medication);
        }
    }
}
