package MedicineInventory;

import entity.MedicationInventory;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class InventoryManager {
    private Map<String, MedicationInventory> inventory; // Stores medications with their names as keys

    // Constructor
    public InventoryManager() {
        this.inventory = new HashMap<>();
    }

    // Add a medication to the inventory
    public void addMedication(MedicationInventory medication) {
        inventory.put(medication.getName(), medication);
        //System.out.println("Medication added: " + medication.getName() + " | Initial Stock: " + medication.getStock());
    }

    // View the entire inventory
    public void viewInventory() {
        System.out.println("Current Inventory:");
        for (MedicationInventory med : inventory.values()) {
            System.out.println("Name: " + med.getName() + ", Stock: " + med.getStock() + ", Alert Level: " + med.getAlertlevel());
        }
    }

    // Method to show all medications available for stock adjustment
    public void showAvailableMedications() {
        System.out.println("Available Medications:");
        for (MedicationInventory med : inventory.values()) {
            System.out.println("Name: " + med.getName() + ", Current Stock: " + med.getStock());
        }
    }

    // Method to adjust stock based on user input
    public void adjustStock() {
        Scanner scanner = new Scanner(System.in);
        showAvailableMedications();

        System.out.print("Enter the name of the medication you want to adjust: ");
        String medicationName = scanner.nextLine();
        
        MedicationInventory medication = inventory.get(medicationName);
        if (medication != null) {
            System.out.print("Enter the quantity to adjust (use negative value to decrease): ");
            int quantity = scanner.nextInt();
            if (quantity < 0) {
                decreaseStock(medicationName, -quantity); // Pass positive quantity for decrease
            } else {
                increaseStock(medicationName, quantity); // Pass quantity for increase
            }
        } else {
            System.out.println("Medication not found in inventory.");
        }
    }

    // Decrease stock level when medication is dispensed
    public void decreaseStock(String medicationName, int quantity) {
        MedicationInventory medication = inventory.get(medicationName);
        if (medication != null) {
            int newStock = medication.getStock() - quantity;
            if (newStock >= 0) {
                medication.setStock(newStock);
                System.out.println("Stock decreased for " + medicationName + " | Quantity removed: " + quantity);
            } else {
                System.out.println("Insufficient stock to dispense " + medicationName);
            }
        } else {
            System.out.println("Medication not found in inventory: " + medicationName);
        }
    }

    // Increase stock level when new stock comes in
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

    // Check and display all low stock alerts
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
