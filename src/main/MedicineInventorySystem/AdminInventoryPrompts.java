/**
 * The {@code AdminInventory} class provides an interface for administrators to manage the medication inventory.
 * It allows administrators to view inventory, add new medications, modify stock levels, and save changes.
 */
package MedicineInventorySystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AdminInventoryPrompts {

    /**
     * Launches the inventory management menu for administrators. This menu provides options to:
     * <ul>
     *     <li>View inventory</li>
     *     <li>Add new medications</li>
     *     <li>Increase or decrease stock levels</li>
     *     <li>Save changes and exit</li>
     * </ul>
     */
    public void adminInventory() {
        InventoryController inventoryManager = new InventoryController();
        Scanner scanner = new Scanner(System.in);

        // View inventory loaded from file
        inventoryManager.viewInventory();

        // Check for any low stock alerts
        inventoryManager.checkLowStockAlerts();

        System.out.println("\nPress enter to view menu");
        scanner.nextLine(); // Wait for Enter to proceed

        boolean exit = false;

        while (!exit) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Add a new medication");
            System.out.println("2. Increase stock of an existing medication");
            System.out.println("3. Decrease stock of an existing medication");
            System.out.println("4. View inventory");
            System.out.println("5. Save and exit");

            while (!scanner.hasNextInt()) { // Check if input is an integer
                System.out.println("Option not valid. Please try again:");
                scanner.next(); // Clear the invalid input
            }

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the leftover newline after nextInt()

            switch (choice) {
                case 1 -> addMedication(scanner, inventoryManager);
                case 2, 3 -> modifyStock(scanner, inventoryManager, choice);
                case 4 -> viewInventoryAndAlerts(scanner, inventoryManager);
                case 5 -> {
                    inventoryManager.saveInventory();
                    System.out.println("\nPress enter to confirm exit inventory menu");
                    scanner.nextLine(); // Wait for Enter
                    exit = true;
                }
                default -> System.out.println("\nInvalid option. Please choose again.");
            }
        }
    }

    /**
     * Handles adding a new medication to the inventory.
     *
     * @param scanner           the {@code Scanner} for user input
     * @param inventoryManager  the {@code InventoryController} to manage inventory
     */
    private void addMedication(Scanner scanner, InventoryController inventoryManager) {
        String name;
        do {
            System.out.print("Enter medication name to add: ");
            name = scanner.nextLine();
            if (name.trim().isEmpty()) {
                System.out.println("Medication name cannot be empty. Please try again.");
            }
        } while (name.trim().isEmpty());

        System.out.print("Enter stock quantity: ");
        int stock = getValidInteger(scanner, "Quantity not valid. Please try again:");

        System.out.print("Enter alert level: ");
        int alertLevel = getValidInteger(scanner, "Alert level not valid. Please try again:");

        MedicationInventory newMedication = new MedicationInventory(name, stock, alertLevel);
        inventoryManager.addMedication(newMedication);
        System.out.println("Medication added: " + name);

        System.out.println("\nPress enter to return to menu");
        scanner.nextLine(); // Wait for Enter
    }

    /**
     * Handles increasing or decreasing the stock of an existing medication.
     *
     * @param scanner           the {@code Scanner} for user input
     * @param inventoryManager  the {@code InventoryController} to manage inventory
     * @param choice            the choice of action (2 for increase, 3 for decrease)
     */
    private void modifyStock(Scanner scanner, InventoryController inventoryManager, int choice) {
        List<String> medicationNames = new ArrayList<>(inventoryManager.getInventory().keySet());

        if (medicationNames.isEmpty()) {
            System.out.println("\nNo medications available in the inventory.");
            return;
        }

        String action = (choice == 2) ? "increase" : "decrease";
        System.out.println("\nSelect medication to " + action + " stock:");

        for (int i = 0; i < medicationNames.size(); i++) {
            String medicationName = medicationNames.get(i);
            int currentStock = inventoryManager.getInventory().get(medicationName).getStock();
            System.out.println((i + 1) + ". " + medicationName + " | Current stock: " + currentStock);
        }

        System.out.print("\nEnter the number corresponding to the medication: ");
        int selectedChoice = getValidInteger(scanner, "Option not valid. Please try again:");

        if (selectedChoice > 0 && selectedChoice <= medicationNames.size()) {
            String medicationName = medicationNames.get(selectedChoice - 1);
            System.out.print("Enter quantity to " + action + ": ");
            int quantity = getValidInteger(scanner, "Quantity not valid. Please try again:");

            if (choice == 2) {
                inventoryManager.increaseStock(medicationName, quantity);
            } else {
                inventoryManager.decreaseStock(medicationName, quantity);
            }
        } else {
            System.out.println("\nInvalid selection. Please try again.");
        }

        System.out.println("\nPress enter to return to menu");
        scanner.nextLine();
    }

    /**
     * Displays the current inventory and low stock alerts.
     *
     * @param scanner           the {@code Scanner} for user input
     * @param inventoryManager  the {@code InventoryController} to manage inventory
     */
    private void viewInventoryAndAlerts(Scanner scanner, InventoryController inventoryManager) {
        inventoryManager.viewInventory();
        inventoryManager.checkLowStockAlerts();

        System.out.println("\nPress enter to return to menu");
        scanner.nextLine(); // Wait for Enter
    }

    /**
     * Reads a valid integer input from the user.
     *
     * @param scanner   the {@code Scanner} for user input
     * @param errorMsg  the error message to display for invalid input
     * @return the valid integer entered by the user
     */
    private int getValidInteger(Scanner scanner, String errorMsg) {
        while (!scanner.hasNextInt()) {
            System.out.println(errorMsg);
            scanner.next(); // Clear the invalid input
        }
        int value = scanner.nextInt();
        scanner.nextLine(); // Consume the leftover newline
        return value;
    }
}
