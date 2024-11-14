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

    // Method to prompt user for requestId and newFlag, then update the flag
    public void updateRequestFlagPrompt() {
        System.out.println("Enter Request ID:");
        String requestId = scanner.nextLine().toUpperCase();

        System.out.println("Select the new flag status:");
        System.out.println("1. Approved");
        System.out.println("2. Rejected");

        int choice;
        Flag newFlag;

        while (true) {
            System.out.print("Enter your choice (1 or 2): ");
            String input = scanner.nextLine();
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
