package RequestSystem;

import java.util.HashMap;
import java.util.Scanner;

public class NewRequestCreator {

    private HashMap<String, Request> requestRecords;
    private static final Scanner scanner = new Scanner(System.in);

    public NewRequestCreator(HashMap<String, Request> requestRecords) {
        this.requestRecords = requestRecords;
    }

    // Method to create a new request
    public void createNewRequest(String pharmId) {

        System.out.println("Enter Medication Name:");
        String medicationName = scanner.nextLine();

        System.out.println("Enter Quantity to Increase Stock By:");
        int increaseStockBy;
        while (true) {
            try {
                increaseStockBy = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number for the quantity.");
            }
        }
        System.out.println("Enter any notes:");
        String notes = scanner.nextLine();

        // Generate unique request ID
        String requestId = generateRequestId();

        // Create the new request with default flag as PENDING
        Request newRequest = new Request(requestId, pharmId, medicationName, increaseStockBy, notes);

        // Add the new request to the HashMap
        requestRecords.put(requestId, newRequest);
        System.out.println("New request created with ID: " + requestId);
    }

    // Method to generate a unique request ID
    private String generateRequestId() {
        return "REQ" + (requestRecords.size() + 1);
    }
}
