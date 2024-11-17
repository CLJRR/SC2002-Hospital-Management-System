package MedicineInventorySystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AdminInventory {

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
            System.out.println("4. View inventory");
            System.out.println("5. Save and exit");

            while (!scanner.hasNextInt()) { // Check if input is an integer
                System.out.println("Option not valid. Please try again:");
                scanner.next(); // Clear the invalid input
            }

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the leftover newline after nextInt()

            switch (choice) {
                case 1:
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
                    List<String> medicationNames = new ArrayList<>(inventoryManager.getInventory().keySet());

                    if (medicationNames.isEmpty()) {
                        System.out.println("\nNo medications available in the inventory.");
                        break;
                    }

                    String action = (choice == 2) ? "increase" : "decrease";
                    System.out.println("\nSelect medication to " + action + " stock:");

                    for (int i = 0; i < medicationNames.size(); i++) {
                        String medicationName = medicationNames.get(i);
                        int currentStock = inventoryManager.getInventory().get(medicationName).getStock(); // Get stock for each medication
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
                    inventoryManager.viewInventory();
                    inventoryManager.checkLowStockAlerts();

                    // Wait for Enter to return to the menu
                    System.out.println("\nPress enter to return to menu");
                    scanner.nextLine();
                    break;

                case 5:
                    inventoryManager.saveInventory();
                    System.out.println("\nPress enter to confirm exit inventory menu");

                    // Wait for Enter to exit
                    //System.out.println("\nPress enter to confirm exit inventory menu");
                    scanner.nextLine();
                    exit = true;
                    break;

                default:
                    System.out.println("\nInvalid option. Please choose again.");
            }
        }
    }
}
