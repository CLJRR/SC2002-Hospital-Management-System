package RequestSystem;

import MedicineInventorySystem.InventoryController;
import SessionManager.Session;
import enums.Flag;
import java.util.HashMap;
import java.util.Scanner;

public class RequestFlagUpdater {

    private HashMap<String, Request> requestRecords;
    private InventoryController invController;
    private static final Scanner scanner = new Scanner(System.in);

    public RequestFlagUpdater(HashMap<String, Request> requestRecords) {
        this.requestRecords = requestRecords;
        this.invController = new InventoryController();
    }

    public void updateRequestFlagPrompt() {
        String requestId;

        // Validate Request ID
        while (true) {
            System.out.println("Enter Request ID (or type 'x' to quit):");
            requestId = scanner.nextLine().toUpperCase();

            if (requestId.equalsIgnoreCase("x")) {
                System.out.println("Operation canceled.");
                return; // Exit the method
            }

            // Check if request ID exists and the flag is PENDING
            if (requestRecords.containsKey(requestId)) {
                Request request = requestRecords.get(requestId);
                if (request.getFlag() == Flag.PENDING) {
                    break; // Valid ID and flag is PENDING, proceed
                } else {
                    System.out.println("Request ID " + requestId + " has already been processed with flag: " + request.getFlag());
                }
            } else {
                System.out.println("Request ID " + requestId + " not found. Please try again.");
            }
        }

        System.out.println("Select the new flag status:");
        System.out.println("1. Approved");
        System.out.println("2. Rejected");

        int choice;
        Flag newFlag;

        // Validate Flag Choice
        while (true) {
            System.out.print("Enter your choice (1 or 2, or type 'x' to quit): ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("x")) {
                System.out.println("Operation canceled.");
                return; // Exit the method
            }

            try {
                choice = Integer.parseInt(input);
                if (choice == 1) {
                    newFlag = Flag.APPROVED;
                    break;
                } else if (choice == 2) {
                    newFlag = Flag.REJECTED;
                    break;
                } else {
                    System.out.println("Invalid choice. Please enter 1 or 2.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number (1 or 2).");
            }
        }

        // Update the request flag
        updateRequestFlag(requestId, newFlag);
    }

    // Method to update the flag of a request
    public void updateRequestFlag(String requestId, Flag newFlag) {
        Request request = requestRecords.get(requestId);
        if (request != null) {
            // Attempt to increase stock in inventory if flag is APPROVED
            if (newFlag == Flag.APPROVED && !invController.increaseStock(request.getMedicationName(), request.getIncreaseStockBy())) {
                System.out.println("Unable to update stock for request " + requestId + ".");
                return;
            }
            request.setApprovedBy(Session.getLoginID());
            request.setFlag(newFlag);
            System.out.println("Request " + requestId + " updated to " + newFlag);
        } else {
            System.out.println("Request " + requestId + " not found.");
        }
    }
}
