package MedicineInventory;


import java.util.HashMap;
import java.util.Map;

public class Inventory {
    private Map<Medicine, Integer> stock; // Aggregation (Inventory has multiple Medicine items with quantities)
    private Map<Medicine, Integer> lowStockAlertThresholds; // Stores specific low-stock thresholds for each medicine

    public Inventory() {
        this.stock = new HashMap<>();
        this.lowStockAlertThresholds = new HashMap<>();
    }

    // Add medicine to inventory with an initial stock level
    public void addMedicine(Medicine medicine, int initialStock, int lowStockAlertLevel) {
        stock.put(medicine, initialStock);
        lowStockAlertThresholds.put(medicine, lowStockAlertLevel);
        System.out.println("Medicine added: " + medicine.getName() + " | Initial Stock: " + initialStock);
    }

    // Dispense medicine (decrease stock level)
    public void dispenseMedicine(Medicine medicine, int quantity) {
        if (stock.containsKey(medicine) && stock.get(medicine) >= quantity) {
            stock.put(medicine, stock.get(medicine) - quantity);
            System.out.println("Medicine dispensed: " + medicine.getName() + " | Quantity: " + quantity);
            checkLowStock(medicine); // Check for low stock after dispensing
        } else {
            System.out.println("Insufficient stock for " + medicine.getName());
        }
    }

    // Update stock level (used when replenishing stock)
    public void updateStock(Medicine medicine, int quantity) {
        stock.put(medicine, stock.getOrDefault(medicine, 0) + quantity);
        System.out.println("Stock updated for " + medicine.getName() + " | Quantity added: " + quantity);
    }

    // Set a low stock alert level for a specific medicine
    public void setLowStockAlertLevel(Medicine medicine, int alertLevel) {
        if (stock.containsKey(medicine)) {
            lowStockAlertThresholds.put(medicine, alertLevel);
            System.out.println("Low stock alert level set for " + medicine.getName() + " | Threshold: " + alertLevel);
        } else {
            System.out.println("Medicine not found in inventory.");
        }
    }

    // Display the entire inventory with current stock levels
    public void displayInventory() {
        System.out.println("Inventory List:");
        for (Map.Entry<Medicine, Integer> entry : stock.entrySet()) {
            Medicine medicine = entry.getKey();
            int quantity = entry.getValue();
            System.out.println("Medicine: " + medicine.getName() + " | Stock: " + quantity +
                    " | Low Stock Alert Level: " + lowStockAlertThresholds.get(medicine));
        }
    }

    // Check if the stock level of a specific medicine is below the alert threshold
    private void checkLowStock(Medicine medicine) {
        int currentStock = stock.getOrDefault(medicine, 0);
        int alertThreshold = lowStockAlertThresholds.getOrDefault(medicine, 0);
        if (currentStock < alertThreshold) {
            System.out.println("Alert: Low stock for " + medicine.getName() +
                    " | Current stock: " + currentStock +
                    " | Low stock threshold: " + alertThreshold);
        }
    }

    // Check and display low stock alerts for all medicines
    public void checkLowStock() {
        System.out.println("Checking for low stock alerts...");
        for (Map.Entry<Medicine, Integer> entry : stock.entrySet()) {
            Medicine medicine = entry.getKey();
            checkLowStock(medicine); // Check each medicine for low stock
        }
    }

    // Get current stock level for a specific medicine
    public int getStockLevel(Medicine medicine) {
        return stock.getOrDefault(medicine, 0);
    }

    // Get low stock alert level for a specific medicine
    public int getLowStockAlertLevel(Medicine medicine) {
        return lowStockAlertThresholds.getOrDefault(medicine, 0);
    }
}
