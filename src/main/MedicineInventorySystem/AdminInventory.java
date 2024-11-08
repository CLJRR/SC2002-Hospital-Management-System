package MedicineInventorySystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AdminInventory {

    public void adminInventory() {
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
                case 3:
                    // Display available medications and allow selection by number
                    List<String> medicationNames = new ArrayList<>(inventoryManager.getInventory().keySet());

                    if (medicationNames.isEmpty()) {
                        System.out.println("No medications available in the inventory.");
                        break;
                    }

                    String action = (choice == 2) ? "increase" : "decrease";
                    System.out.println("Select medication to " + action + " stock:");

                    for (int i = 0; i < medicationNames.size(); i++) {
                        System.out.println((i + 1) + ". " + medicationNames.get(i));
                    }

                    System.out.print("Enter the number corresponding to the medication: ");
                    int selectedChoice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    // Validate the choice and get medication name
                    if (selectedChoice > 0 && selectedChoice <= medicationNames.size()) {
                        String medicationName = medicationNames.get(selectedChoice - 1);
                        System.out.print("Enter quantity to " + action + ": ");
                        int quantity = scanner.nextInt();
                        scanner.nextLine(); // Consume newline

                        if (choice == 2) {
                            inventoryManager.increaseStock(medicationName, quantity);
                        } else {
                            inventoryManager.decreaseStock(medicationName, quantity);
                        }
                    } else {
                        System.out.println("Invalid selection. Please try again.");
                    }
                    break;

                case 4:
                    // View inventory
                    inventoryManager.viewInventory();
                    // Check for any low stocks
                    inventoryManager.checkLowStockAlerts();
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
