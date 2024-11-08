package MedicineInventorySystem;

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
        inventoryService.saveInventory(inventoryList);
    }
}
