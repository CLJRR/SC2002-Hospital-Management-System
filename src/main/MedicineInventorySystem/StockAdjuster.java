package MedicineInventorySystem;

import java.util.*;  // Imports the entire java.util package, which includes the Map interface and other utility classes.

public class StockAdjuster {  // Declares the StockAdjuster class, responsible for adjusting the stock levels of medications in the inventory.
    private Map<String, MedicationInventory> inventory;  // Declares a Map that stores medication names (String) as keys and MedicationInventory objects as values.
    // Constructor that initializes the StockAdjuster with the provided inventory map.
    public StockAdjuster(Map<String, MedicationInventory> inventory) {
        this.inventory = inventory;  // Sets the inventory field to the provided Map, allowing the StockAdjuster to manage stock adjustments for the given inventory.
    }

    public boolean increaseStock(String medicationName, int quantity) {
        MedicationInventory medication = inventory.get(medicationName);
        if (medication != null) {
            int newStock = medication.getStock() + quantity;
            medication.setStock(newStock);
            System.out.println("\nStock increased for " + medicationName + " | Quantity added: " + quantity);
            return true;  // Return true to indicate successful stock increase.
        } else {
            System.out.println("\nMedication not found in inventory: " + medicationName);
            return false;
        }
    }
    

    // Method to decrease the stock of a medication by a specified quantity.
    public boolean decreaseStock(String medicationName, int quantity) {
        MedicationInventory medication = inventory.get(medicationName);  // Retrieves the MedicationInventory object associated with the medicationName from the inventory map.

        if (medication != null) {  // If the medication exists in the inventory...
            int currentStock = medication.getStock();  // Retrieves the current stock of the medication.

            if (currentStock >= quantity) {  // If there is enough stock to decrease by the specified quantity...
                medication.setStock(currentStock - quantity);  // Subtracts the specified quantity from the current stock and updates the medication’s stock.
                System.out.println("\nStock decreased for " + medicationName + " | Quantity removed: " + quantity);  // Prints a message indicating the stock decrease for the medication.

                return true;
            } else {
                // If there is insufficient stock, prints a message showing the current stock and the attempted decrease quantity.
                System.out.println("\nInsufficient stock to decrease " + medicationName + " by " + quantity
                        + " | Current stock: " + currentStock);
                return false;
            }
        } else {
            System.out.println("\nMedication not found in inventory: " + medicationName);  // If the medication is not found in the inventory, prints an error message.
            return false;
        }
    }
}
