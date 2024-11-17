package MedicineInventorySystem;

import java.util.*;

public class LowStockChecker {

    private Map<String, MedicationInventory> inventory;

    public LowStockChecker(Map<String, MedicationInventory> inventory) {
        this.inventory = inventory;
    }

    public void checkLowStockAlerts() {
        System.out.println("Checking for low stock alerts...");
        boolean lowStockFound = false;

        for (MedicationInventory medication : inventory.values()) {
            if (medication.getStock() < medication.getAlertlevel()) {
                System.out.println("Alert: Low stock for " + medication.getName()
                        + " | Current stock: " + medication.getStock()
                        + " | Alert level: " + medication.getAlertlevel());
                lowStockFound = true;
            }
        }

        if (!lowStockFound) {
            System.out.println("All medicine up to stock");
        }

    }
}
