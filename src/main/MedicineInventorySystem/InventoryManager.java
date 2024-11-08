package MedicineInventorySystem;

import TODO.MedicationInventory;
import java.util.*;

public class InventoryManager {
    private Map<String, MedicationInventory> inventory;
    private MedicineInventoryService inventoryService;

    // Constructor
    public InventoryManager() {
        inventoryService = new MedicineInventoryService();
        this.inventory = new HashMap<>();
        loadInitialInventory(); // Load inventory at startup
    }

    // Load initial inventory from the file
    private void loadInitialInventory() {
        List<MedicationInventory> medications = inventoryService.loadInventory();
        for (MedicationInventory medication : medications) {
            inventory.put(medication.getName(), medication);
        }
    }

    // Save inventory back to the file
    public void saveInventory() {
        List<MedicationInventory> inventoryList = new ArrayList<>(inventory.values());
        inventoryService.saveInventory(inventoryList);
    }

    // Add a medication to the inventory
    public void addMedication(MedicationInventory medication) {
        inventory.put(medication.getName(), medication);
    }

    // View the entire inventory
    public void viewInventory() {
        System.out.println("Current Inventory:");
        for (MedicationInventory med : inventory.values()) {
            System.out.println("Name: " + med.getName() + ", Stock: " + med.getStock() + ", Alert Level: " + med.getAlertlevel() + "\n");
        }
    }

    // Increase stock level for a specific medication
    public void increaseStock(String medicationName, int quantity) {
        MedicationInventory medication = inventory.get(medicationName);
        if (medication != null) {
            int newStock = medication.getStock() + quantity;
            medication.setStock(newStock);
            System.out.println("Stock increased for " + medicationName + " | Quantity added: " + quantity);
        } else {
            System.out.println("Medication not found in inventory: " + medicationName);
        }
    }

    // Decrease stock level for a specific medication
    public void decreaseStock(String medicationName, int quantity) {
        MedicationInventory medication = inventory.get(medicationName);
        if (medication != null) {
            int currentStock = medication.getStock();
            if (currentStock >= quantity) {
                medication.setStock(currentStock - quantity);
                System.out.println("Stock decreased for " + medicationName + " | Quantity removed: " + quantity);
            } else {
                System.out.println("Insufficient stock to decrease " + medicationName + " by " + quantity +
                        " | Current stock: " + currentStock);
            }
        } else {
            System.out.println("Medication not found in inventory: " + medicationName);
        }
    }

    // Check low stock alerts
    public void checkLowStockAlerts() {
        System.out.println("Checking for low stock alerts...");
        for (MedicationInventory medication : inventory.values()) {
            if (medication.getStock() < medication.getAlertlevel()) {
                System.out.println("Alert: Low stock for " + medication.getName() +
                        " | Current stock: " + medication.getStock() +
                        " | Alert level: " + medication.getAlertlevel());
            }
        }
    }
}
