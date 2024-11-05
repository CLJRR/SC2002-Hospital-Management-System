package MedicineInventory;

import entity.MedicationInventory;
import java.util.Scanner;

public class InventoryTest {
    public static void main(String[] args) {
        InventoryManager inventoryManager = new InventoryManager();
        Scanner scanner = new Scanner(System.in);

        // Create medications
        MedicationInventory aspirin = new MedicationInventory("Aspirin", 10, 20);
        MedicationInventory ibuprofen = new MedicationInventory("Ibuprofen", 50, 10);
        MedicationInventory paracetamol = new MedicationInventory("Paracetamol", 30, 5);

        // Add medications to the inventory
        inventoryManager.addMedication(aspirin);
        inventoryManager.addMedication(ibuprofen);
        inventoryManager.addMedication(paracetamol);

        // View inventory
        inventoryManager.viewInventory();

        // Check low stock alerts (should trigger for Aspirin)
        inventoryManager.checkLowStockAlerts();

        // Adjust stock using the new method
        String continueAdjusting;
        do {
            inventoryManager.adjustStock();
            System.out.print("Do you want to adjust stock for another medication? (yes/no): ");
            continueAdjusting = scanner.nextLine();
        } while (continueAdjusting.equalsIgnoreCase("yes"));

        // Final inventory view
        inventoryManager.viewInventory();
    }
}
