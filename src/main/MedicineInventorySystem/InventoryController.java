package MedicineInventorySystem;

import java.util.*;

public class InventoryController {

    private Map<String, MedicationInventory> inventory;
    private InventoryLoader inventoryLoader;
    private InventorySaver inventorySaver;
    private InventoryViewer inventoryViewer;
    private StockAdjuster stockAdjuster;
    private LowStockChecker lowStockChecker;

    // Constructor
    public InventoryController() {
        this.inventory = new HashMap<>();
        this.inventoryLoader = new InventoryLoader(this.inventory);
        this.inventorySaver = new InventorySaver(this.inventory);
        this.stockAdjuster = new StockAdjuster(this.inventory);
        this.inventoryViewer = new InventoryViewer(this.inventory);
        this.lowStockChecker = new LowStockChecker(this.inventory);

        // Load initial inventory
        inventoryLoader.loadInitialInventory();
    }

    public void saveInventory() {
        inventorySaver.saveInventory();
    }

    public void addMedication(MedicationInventory medication) {
        inventory.put(medication.getName(), medication);
    }

    public void viewInventory() {
        inventoryLoader.loadInitialInventory();
        inventoryViewer.viewInventory();

    }

    public boolean increaseStock(String medicationName, int quantity) {
        // Removed inventoryLoader.loadInitialInventory() to prevent overwriting recent changes.
        inventoryLoader.loadInitialInventory();
        if (stockAdjuster.increaseStock(medicationName, quantity)) {
            inventorySaver.saveInventory();  // Save only if stock increase is successful.
            return true;
        } else {
            return false;
        }
    }

    public boolean decreaseStock(String medicationName, int quantity) {
        inventoryLoader.loadInitialInventory();
        if (stockAdjuster.decreaseStock(medicationName, quantity)) {
            inventorySaver.saveInventory();
            return true;
        } else {

            return false;
        }

    }

    public void checkLowStockAlerts() {
        lowStockChecker.checkLowStockAlerts();
    }

    public Map<String, MedicationInventory> getInventory() {
        return inventory;
    }
}
