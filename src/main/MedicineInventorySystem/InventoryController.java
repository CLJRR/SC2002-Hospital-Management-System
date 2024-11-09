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
        inventoryViewer.viewInventory();
    }

    public void increaseStock(String medicationName, int quantity) {
        stockAdjuster.increaseStock(medicationName, quantity);
    }

    public boolean decreaseStock(String medicationName, int quantity) {
        if (stockAdjuster.decreaseStock(medicationName, quantity)) {

            return true;
        }
        return false;
    }

    public void checkLowStockAlerts() {
        lowStockChecker.checkLowStockAlerts();
    }

    public Map<String, MedicationInventory> getInventory() {
        return inventory;
    }
}
