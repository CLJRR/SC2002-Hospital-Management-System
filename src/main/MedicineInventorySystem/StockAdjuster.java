package MedicineInventorySystem;

import java.util.*;

/**
 * The {@code StockAdjuster} class is responsible for managing stock adjustments
 * (increase or decrease) of medications in the inventory.
 * It interacts with a {@code Map} that stores medication names as keys and
 * {@code MedicationInventory} objects as values.
 */

public class StockAdjuster {

    private Map<String, MedicationInventory> inventory;

    /**
     * Constructs a {@code StockAdjuster} with the provided inventory map.
     *
     * @param inventory a {@code Map} where keys are medication names and values
     *                  are {@code MedicationInventory} objects.
     */
    public StockAdjuster(Map<String, MedicationInventory> inventory) {
        this.inventory = inventory;
    }

    /**
     * Increases the stock of a medication by a specified quantity.
     *
     * @param medicationName the name of the medication to update.
     * @param quantity       the amount to add to the current stock.
     * @return {@code true} if the stock was successfully increased, {@code false}
     *         if the medication was not found or if the quantity is invalid.
     */
    public boolean increaseStock(String medicationName, int quantity) {
        if (quantity < 0) {
            System.out.println("\nInvalid quantity: Quantity cannot be negative.");
            return false;
        }

        MedicationInventory medication = inventory.get(medicationName);
        if (medication != null) {
            int newStock = medication.getStock() + quantity;
            medication.setStock(newStock);
            System.out.println("\nStock increased for " + medicationName + " | Quantity added: " + quantity);
            return true;
        } else {
            System.out.println("\nMedication not found in inventory: " + medicationName);
            return false;
        }
    }

    /**
     * Decreases the stock of a medication by a specified quantity.
     *
     * @param medicationName the name of the medication to update.
     * @param quantity       the amount to subtract from the current stock.
     * @return {@code true} if the stock was successfully decreased, {@code false}
     *         if the medication was not found, if the stock was insufficient, or if
     *         the quantity is invalid.
     */
    public boolean decreaseStock(String medicationName, int quantity) {
        if (quantity < 0) {
            System.out.println("\nInvalid quantity: Quantity cannot be negative.");
            return false;
        }

        MedicationInventory medication = inventory.get(medicationName);

        if (medication != null) {
            int currentStock = medication.getStock();

            if (currentStock >= quantity) {
                medication.setStock(currentStock - quantity);
                System.out.println("\nStock decreased for " + medicationName + " | Quantity removed: " + quantity);
                return true;
            } else {
                System.out.println("\nInsufficient stock to decrease " + medicationName + " by " + quantity
                        + " | Current stock: " + currentStock);
                return false;
            }
        } else {
            System.out.println("\nMedication not found in inventory: " + medicationName);
            return false;
        }
    }
}
