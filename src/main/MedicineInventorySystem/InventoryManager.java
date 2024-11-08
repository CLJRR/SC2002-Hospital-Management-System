package MedicineInventorySystem;

import java.util.*;

public class InventoryManager {

    private Map<String, MedicationInventory> inventory;
    private InventoryLoader inventoryLoader;
    private InventorySaver inventorySaver;
    private InventoryViewer inventoryViewer;
    private StockAdjuster stockAdjuster;
    private LowStockChecker lowStockChecker;

    // Constructor
    public InventoryManager() {
        this.inventory = new HashMap<>();
        this.inventoryLoader = new InventoryLoader(this.inventory);
        this.inventorySaver = new InventorySaver(this.inventory);
        this.inventoryViewer = new InventoryViewer(this.inventory);
        this.stockAdjuster = new StockAdjuster(this.inventory);
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

    public void decreaseStock(String medicationName, int quantity) {
        stockAdjuster.decreaseStock(medicationName, quantity);
    }

    public void checkLowStockAlerts() {
        lowStockChecker.checkLowStockAlerts();
    }

    public Map<String, MedicationInventory> getInventory() {
        return inventory;
    }
}
