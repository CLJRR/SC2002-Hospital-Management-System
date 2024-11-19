/**
 * The {@code InventoryController} class serves as the central controller for managing medication inventory.
 * It provides methods to load, view, update, and save inventory, as well as to handle stock adjustments and
 * low stock alerts.
 */
package MedicineInventorySystem;

import java.util.*;

public class InventoryController {

    private Map<String, MedicationInventory> inventory;
    private InventoryLoader inventoryLoader;
    private InventorySaver inventorySaver;
    private InventoryViewer inventoryViewer;
    private StockAdjuster stockAdjuster;
    private LowStockChecker lowStockChecker;

    /**
     * Constructs an {@code InventoryController} and initializes the inventory system components.
     * Automatically loads the initial inventory from the file.
     */
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

    /**
     * Saves the current inventory state to the file.
     */
    public void saveInventory() {
        inventorySaver.saveInventory();
    }

    /**
     * Adds a new medication to the inventory.
     *
     * @param medication the {@code MedicationInventory} object representing the new medication.
     */
    public void addMedication(MedicationInventory medication) {
        inventory.put(medication.getName(), medication);
    }

     /**
     * Removes a medication from the inventory.
     *
     * @param medicationName the name of the medication to remove.
     * @return {@code true} if the medication was successfully removed, {@code false} if the medication was not found.
     */
    public boolean removeMedication(String medicationName) {
        if (inventory.containsKey(medicationName)) {
            inventory.remove(medicationName);
            inventorySaver.saveInventory(); // Save changes after removal
            System.out.println("\nMedication removed: " + medicationName);
            return true;
        } else {
            System.out.println("\nMedication not found in inventory: " + medicationName);
            return false;
        }
    }

    /**
     * Displays the current inventory.
     * Ensures that the latest inventory is loaded before viewing.
     */
    public void viewInventory() {
        inventoryLoader.loadInitialInventory();
        inventoryViewer.viewInventory();
    }

    /**
     * Increases the stock of a specified medication.
     *
     * @param medicationName the name of the medication to increase stock for.
     * @param quantity       the quantity to add to the current stock.
     * @return {@code true} if the stock increase is successful, {@code false} otherwise.
     */
    public boolean increaseStock(String medicationName, int quantity) {
        inventoryLoader.loadInitialInventory(); // Ensure the inventory is up-to-date
        if (stockAdjuster.increaseStock(medicationName, quantity)) {
            inventorySaver.saveInventory(); // Save changes after a successful update
            return true;
        } else {
            return false;
        }
    }

    /**
     * Decreases the stock of a specified medication.
     *
     * @param medicationName the name of the medication to decrease stock for.
     * @param quantity       the quantity to subtract from the current stock.
     * @return {@code true} if the stock decrease is successful, {@code false} otherwise.
     */
    public boolean decreaseStock(String medicationName, int quantity) {
        inventoryLoader.loadInitialInventory(); // Ensure the inventory is up-to-date
        if (stockAdjuster.decreaseStock(medicationName, quantity)) {
            inventorySaver.saveInventory(); // Save changes after a successful update
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks and displays any low stock alerts for medications in the inventory.
     */
    public void checkLowStockAlerts() {
        lowStockChecker.checkLowStockAlerts();
    }

    /**
     * Returns the current inventory map.
     *
     * @return a {@code Map<String, MedicationInventory>} representing the current inventory.
     */
    public Map<String, MedicationInventory> getInventory() {
        return inventory;
    }
}
