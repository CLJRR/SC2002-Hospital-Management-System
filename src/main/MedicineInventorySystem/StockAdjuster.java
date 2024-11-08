package MedicineInventorySystem;

import java.util.*;

public class StockAdjuster {

    private Map<String, MedicationInventory> inventory;

    public StockAdjuster(Map<String, MedicationInventory> inventory) {
        this.inventory = inventory;
    }

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

    public void decreaseStock(String medicationName, int quantity) {
        MedicationInventory medication = inventory.get(medicationName);
        if (medication != null) {
            int currentStock = medication.getStock();
            if (currentStock >= quantity) {
                medication.setStock(currentStock - quantity);
                System.out.println("Stock decreased for " + medicationName + " | Quantity removed: " + quantity);
            } else {
                System.out.println("Insufficient stock to decrease " + medicationName + " by " + quantity
                        + " | Current stock: " + currentStock);
            }
        } else {
            System.out.println("Medication not found in inventory: " + medicationName);
        }
    }
}
