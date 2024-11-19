/**
 * The {@code LowStockChecker} class is responsible for monitoring and alerting 
 * about medications in the inventory that are below their defined alert levels.
 */
package MedicineInventorySystem;

import java.util.*;

public class LowStockChecker {

    private Map<String, MedicationInventory> inventory;

    /**
     * Constructs a {@code LowStockChecker} with the specified inventory map.
     *
     * @param inventory a {@code Map<String, MedicationInventory>} representing
     *                  the current medication inventory to be monitored.
     */
    public LowStockChecker(Map<String, MedicationInventory> inventory) {
        this.inventory = inventory;
    }

    /**
     * Checks the inventory for medications that have low stock levels and prints alerts.
     *
     * <p>
     * This method iterates through the inventory and compares each medication's stock
     * level with its alert level. If the stock is below the alert level, a warning message 
     * is printed with details about the medication.
     * </p>
     *
     * <p>
     * If no low-stock medications are found, it displays a message indicating all medications
     * are sufficiently stocked.
     * </p>
     */
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
