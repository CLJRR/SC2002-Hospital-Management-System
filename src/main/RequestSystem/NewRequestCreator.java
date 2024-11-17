package RequestSystem;

import MedicineInventorySystem.InventoryController;
import MedicineInventorySystem.MedicationInventory;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class NewRequestCreator {

    private HashMap<String, Request> requestRecords;
    private static final Scanner sc = new Scanner(System.in);
    private InventoryController inventoryController;
    private Map<String, MedicationInventory> inventory;

    public NewRequestCreator(HashMap<String, Request> requestRecords) {
        this.requestRecords = requestRecords;
        this.inventoryController = new InventoryController();
        this.inventory = new HashMap<>();
        this.inventory = inventoryController.getInventory();
    }

    public void createNewRequest(String pharmId) {
        String prescriptionName = null;
        while (true) {
            inventoryController.viewInventory();
            System.out.println("Enter prescription Name (or type 'x' to quit): ");
            prescriptionName = sc.nextLine().trim();

            if (prescriptionName.equalsIgnoreCase("x")) {
                System.out.println("Request creation canceled.");
                return; // Exit the method
            }

            if (inventory.containsKey(prescriptionName)) { // Check if medication exists
                break; // Exit loop if the medication exists in inventory
            }

            System.out.println("Error: Medication " + prescriptionName + " is not available in inventory. Please try again.");
        }

        System.out.println("Enter Quantity to Increase Stock By (or type 'x' to quit):");
        int increaseStockBy;
        while (true) {
            String input = sc.nextLine().trim();
            if (input.equalsIgnoreCase("x")) {
                System.out.println("Request creation canceled.");
                return; // Exit the method
            }
            try {
                increaseStockBy = Integer.parseInt(input);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number for the quantity.");
            }
        }

        System.out.println("Enter any notes (or type 'x' to quit):");
        String notes = sc.nextLine().trim();
        if (notes.equalsIgnoreCase("x")) {
            System.out.println("Request creation canceled.");
            return; // Exit the method
        }

        // Generate unique request ID
        String requestId = generateRequestId();

        // Create the new request with default flag as PENDING
        Request newRequest = new Request(requestId, pharmId, prescriptionName, increaseStockBy, notes);

        // Add the new request to the HashMap
        requestRecords.put(requestId, newRequest);
        System.out.println("New request created with ID: " + requestId);
    }

    // Method to generate a unique request ID
    private String generateRequestId() {
        return "REQ" + (requestRecords.size() + 1);
    }
}
