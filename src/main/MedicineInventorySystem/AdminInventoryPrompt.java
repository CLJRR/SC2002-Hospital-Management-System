package MedicineInventorySystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The {@code AdminInventory} class provides an interface for administrators to manage the medication inventory.
 * Administrators can:
 * <ul>
 *     <li>Add new medications</li>
 *     <li>Increase or decrease stock of existing medications</li>
 *     <li>Remove medications</li>
 *     <li>View inventory and low stock alerts</li>
 *     <li>Save changes and exit the menu</li>
 * </ul>
 */

public class AdminInventoryPrompt {

    /**
     * Launches the inventory management menu for administrators.
     * This method provides options for administrators to interact with the inventory system.
     */
    public void adminInventory() {
        InventoryController inventoryManager = new InventoryController();
        Scanner scanner = new Scanner(System.in);

        // View inventory loaded from file
        inventoryManager.viewInventory();

        // Check for any low stocks
        inventoryManager.checkLowStockAlerts();

        System.out.println("\nPress enter to view menu");
        scanner.nextLine(); // Wait for Enter to proceed

        boolean exit = false;

        while (!exit) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Add a new medication");
            System.out.println("2. Increase stock of an existing medication");
            System.out.println("3. Decrease stock of an existing medication");
            System.out.println("4. Remove a medication");
            System.out.println("5. View inventory");
            System.out.println("6. Save and exit");

            while (!scanner.hasNextInt()) { // Check if input is an integer
                System.out.println("Option not valid. Please try again:");
                scanner.next(); // Clear the invalid input
            }

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the leftover newline after nextInt()

            switch (choice) {
                case 1:
                    // Add a new medication
                    String name;
                    do {
                        System.out.print("Enter medication name to add: ");
                        name = scanner.nextLine();
                        if (name.trim().isEmpty()) {
                            System.out.println("Medication name cannot be empty. Please try again.");
                        }
                    } while (name.trim().isEmpty());

                    System.out.print("Enter stock quantity: ");
                    while (!scanner.hasNextInt()) {
                        System.out.println("Quantity not valid. Please try again:");
                        scanner.next(); // Clear the invalid input
                    }
                    int stock = scanner.nextInt();
                    scanner.nextLine(); // Consume the leftover newline

                    System.out.print("Enter alert level: ");
                    while (!scanner.hasNextInt()) {
                        System.out.println("Alert level not valid. Please try again:");
                        scanner.next(); // Clear the invalid input
                    }

                    int alertLevel = scanner.nextInt();
                    scanner.nextLine(); // Consume the leftover newline

                    MedicationInventory newMedication = new MedicationInventory(name, stock, alertLevel);
                    inventoryManager.addMedication(newMedication);
                    System.out.println("Medication added: " + name);

                    // Wait for Enter to return to the menu
                    System.out.println("\nPress enter to return to menu");
                    scanner.nextLine(); // Wait for Enter
                    break;

                case 2:
                case 3:
                    // Modify stock of an existing medication
                    List<String> medicationNames = new ArrayList<>(inventoryManager.getInventory().keySet());

                    if (medicationNames.isEmpty()) {
                        System.out.println("\nNo medications available in the inventory.");
                        break;
                    }

                    String action = (choice == 2) ? "increase" : "decrease";
                    System.out.println("\nSelect medication to " + action + " stock:");

                    for (int i = 0; i < medicationNames.size(); i++) {
                        String medicationName = medicationNames.get(i);
                        int currentStock = inventoryManager.getInventory().get(medicationName).getStock();
                        System.out.println((i + 1) + ". " + medicationName + " | Current stock: " + currentStock);
                    }

                    System.out.print("\nEnter the number corresponding to the medication: ");
                    while (!scanner.hasNextInt()) {
                        System.out.println("Option not valid. Please try again:");
                        scanner.next(); // Clear the invalid input
                    }

                    int selectedChoice = scanner.nextInt();
                    scanner.nextLine(); // Consume the leftover newline

                    if (selectedChoice > 0 && selectedChoice <= medicationNames.size()) {
                        String medicationName = medicationNames.get(selectedChoice - 1);
                        System.out.print("Enter quantity to " + action + ": ");
                        while (!scanner.hasNextInt()) {
                            System.out.println("Option not valid. Please try again:");
                            scanner.next(); // Clear the invalid input
                        }

                        int quantity = scanner.nextInt();
                        scanner.nextLine(); // Consume the leftover newline

                        if (choice == 2) {
                            inventoryManager.increaseStock(medicationName, quantity);
                        } else {
                            inventoryManager.decreaseStock(medicationName, quantity);
                        }
                    } else {
                        System.out.println("\nInvalid selection. Please try again.");
                    }

                    // Wait for Enter to return to the menu
                    System.out.println("\nPress enter to return to menu");
                    scanner.nextLine();
                    break;

                case 4:
                    // Remove a medication
                    List<String> medsToRemove = new ArrayList<>(inventoryManager.getInventory().keySet());

                    if (medsToRemove.isEmpty()) {
                        System.out.println("\nNo medications available in the inventory.");
                        break;
                    }

                    System.out.println("\nSelect medication to remove:");
                    for (int i = 0; i < medsToRemove.size(); i++) {
                        System.out.println((i + 1) + ". " + medsToRemove.get(i));
                    }

                    System.out.print("\nEnter the number corresponding to the medication: ");
                    while (!scanner.hasNextInt()) {
                        System.out.println("Option not valid. Please try again:");
                        scanner.next(); // Clear the invalid input
                    }

                    int removeChoice = scanner.nextInt();
                    scanner.nextLine(); // Consume the leftover newline

                    if (removeChoice > 0 && removeChoice <= medsToRemove.size()) {
                        String medicationName = medsToRemove.get(removeChoice - 1);
                        boolean removed = inventoryManager.removeMedication(medicationName);

                        if (removed) {
                            System.out.println("Medication removed successfully: " + medicationName);
                        }
                    } else {
                        System.out.println("\nInvalid selection. Please try again.");
                    }

                    // Wait for Enter to return to the menu
                    System.out.println("\nPress enter to return to menu");
                    scanner.nextLine();
                    break;

                case 5:
                    // View inventory
                    inventoryManager.viewInventory();
                    inventoryManager.checkLowStockAlerts();

                    // Wait for Enter to return to the menu
                    System.out.println("\nPress enter to return to menu");
                    scanner.nextLine();
                    break;

                case 6:
                    // Save and exit
                    inventoryManager.saveInventory();
                    System.out.println("\nPress enter to confirm exit inventory menu");

                    // Wait for Enter to exit
                    scanner.nextLine();
                    exit = true;
                    break;

                default:
                    System.out.println("\nInvalid option. Please choose again.");
            }
        }
    }
}
