package RequestSystem;

import MedicineInventorySystem.InventoryController;
import SessionManager.Session;
import enums.Flag;

import java.util.ArrayList;
import java.util.List;

public class ReplenishmentController {
    private InventoryController inventoryController;
    private List<ReplenishmentRequest> replenishmentRequests;

    // Constructor
    public ReplenishmentController(InventoryController inventoryController) {
        this.inventoryController = inventoryController;
        this.replenishmentRequests = new ArrayList<>();
    }

    // Submit a replenishment request
    public void submitRequest(String medicationName, int quantity, String pharmacistId) {
        // Automatically set the pharmacist ID from the session
        if (pharmacistId == null || pharmacistId.isEmpty()) {
            System.out.println("Error: Pharmacist ID cannot be null or empty.");
            return;
        }

        // Create a new replenishment request with the pharmacist ID
        ReplenishmentRequest request = new ReplenishmentRequest(medicationName, quantity, pharmacistId);
        
        // Add the request to the list of replenishment requests
        replenishmentRequests.add(request);
        System.out.println("Replenishment request submitted for " + medicationName + " with quantity " + quantity);
    }

    // Retrieve all replenishment requests
    public List<ReplenishmentRequest> getRequests() {
        return replenishmentRequests;
    }

    // Approve a replenishment request (example method)
    public boolean approveRequest(int requestIndex) {
        if (requestIndex < 0 || requestIndex >= replenishmentRequests.size()) {
            System.out.println("Invalid request index.");
            return false;
        }
        
        ReplenishmentRequest request = replenishmentRequests.get(requestIndex);
        request.setFlag(Flag.CONFIRMED);
        System.out.println("Replenishment request for " + request.getMedicationName() + " has been approved.");
        return true;
    }
}
