package MedicineInventorySystem;

import java.util.*;

public class InventoryViewer {

    private Map<String, MedicationInventory> inventory;

    public InventoryViewer(Map<String, MedicationInventory> inventory) {
        this.inventory = inventory;
    }

    public void viewInventory() {
        System.out.println("Current Inventory:");
        for (MedicationInventory med : inventory.values()) {
            System.out.println("Name: " + med.getName() + ", Stock: " + med.getStock() + ", Alert Level: " + med.getAlertlevel());
        }
        System.out.println(); // Adds a new line after the last medication
    }
}
