package MedicineInventorySystem;

import TODO.MedicationInventory;
import java.util.Scanner;

public class InventoryTest {
    public static void main(String[] args) {
        InventoryManager inventoryManager = new InventoryManager();
        Scanner scanner = new Scanner(System.in);

        // View inventory loaded from file
        inventoryManager.viewInventory();

        // Check for any low stocks
        inventoryManager.checkLowStockAlerts();

        boolean exit = false;

        while (!exit) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Add a new medication");
            System.out.println("2. Increase stock of an existing medication");
            System.out.println("3. Decrease stock of an existing medication");
            System.out.println("4. View inventory");
            System.out.println("5. Save and exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    // Add a new medication to test saving
                    System.out.print("Enter medication name to add: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter stock quantity: ");
                    int stock = scanner.nextInt();
                    System.out.print("Enter alert level: ");
                    int alertLevel = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    MedicationInventory newMedication = new MedicationInventory(name, stock, alertLevel);
                    inventoryManager.addMedication(newMedication);
                    System.out.println("Medication added: " + name);
                    break;

                case 2:
                    // Increase stock level for a specific medication
                    System.out.print("Enter medication name to increase stock: ");
                    String increaseName = scanner.nextLine();
                    System.out.print("Enter quantity to increase: ");
                    int increaseQuantity = scanner.nextInt();
                    inventoryManager.increaseStock(increaseName, increaseQuantity);
                    break;

                case 3:
                    // Decrease stock level for a specific medication
                    System.out.print("Enter medication name to decrease stock: ");
                    String decreaseName = scanner.nextLine();
                    System.out.print("Enter quantity to decrease: ");
                    int decreaseQuantity = scanner.nextInt();
                    inventoryManager.decreaseStock(decreaseName, decreaseQuantity);
                    break;

                case 4:
                    // View inventory
                    inventoryManager.viewInventory();
                    break;

                case 5:
                    // Save and exit
                    inventoryManager.saveInventory();
                    System.out.println("Inventory saved to file. Exiting program.");
                    exit = true;
                    break;

                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        }
    }
}
