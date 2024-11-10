package MedicineInventorySystem;

import java.util.*;  // Imports the entire java.util package, which includes the Map interface and other utility classes.

public class StockAdjuster {  // Declares the StockAdjuster class, responsible for adjusting the stock levels of medications in the inventory.
    private Map<String, MedicationInventory> inventory;  // Declares a Map that stores medication names (String) as keys and MedicationInventory objects as values.
    // Constructor that initializes the StockAdjuster with the provided inventory map.
    public StockAdjuster(Map<String, MedicationInventory> inventory) {
        this.inventory = inventory;  // Sets the inventory field to the provided Map, allowing the StockAdjuster to manage stock adjustments for the given inventory.
    }

    // Method to increase the stock of a medication by a specified quantity.
    public void increaseStock(String medicationName, int quantity) {
        MedicationInventory medication = inventory.get(medicationName);  // Retrieves the MedicationInventory object associated with the medicationName from the inventory map.

        if (medication != null) {
            int newStock = medication.getStock() + quantity;  // Calculates the new stock by adding the quantity to the current stock of the medication.
            medication.setStock(newStock);  // Updates the medication’s stock with the new value.
            System.out.println("Stock increased for " + medicationName + " | Quantity added: " + quantity);  // Prints a message indicating the stock increase for the medication.
        } else {
            System.out.println("Medication not found in inventory: " + medicationName);  // If the medication is not found in the inventory, prints an error message.
        }
    }

    // Method to decrease the stock of a medication by a specified quantity.
    public boolean decreaseStock(String medicationName, int quantity) {
        MedicationInventory medication = inventory.get(medicationName);  // Retrieves the MedicationInventory object associated with the medicationName from the inventory map.

        if (medication != null) {  // If the medication exists in the inventory...
            int currentStock = medication.getStock();  // Retrieves the current stock of the medication.

            if (currentStock >= quantity) {  // If there is enough stock to decrease by the specified quantity...
                medication.setStock(currentStock - quantity);  // Subtracts the specified quantity from the current stock and updates the medication’s stock.
                System.out.println("Stock decreased for " + medicationName + " | Quantity removed: " + quantity);  // Prints a message indicating the stock decrease for the medication.

                return true;
            } else {
                // If there is insufficient stock, prints a message showing the current stock and the attempted decrease quantity.
                System.out.println("Insufficient stock to decrease " + medicationName + " by " + quantity
                        + " | Current stock: " + currentStock);
                return false;
            }
        } else {
            System.out.println("Medication not found in inventory: " + medicationName);  // If the medication is not found in the inventory, prints an error message.
            return false;
        }
    }
}
