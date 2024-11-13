package RequestSystem;

import java.util.*;

import MedicineInventorySystem.InventoryController;
import MedicineInventorySystem.Request;
import enums.Flag;

public class SHITRequestSystem {
    private Map<String, Request> requests;
    private InventoryController inventoryController;

    public SHITRequestSystem() {
        this.requests = new HashMap<>();
        this.inventoryController = new InventoryController();
    }

    public void submitReplenishmentRequest(String requestId, String pharmId, String medicationName, int increaseStockBy, String notes) {
        Request request = new Request(requestId, pharmId, medicationName, increaseStockBy, notes, Flag.PENDING);
        requests.put(requestId, request);
        System.out.println("Replenishment request submitted: " + request);
    }

    public void approveRequest(String requestId) {
        Request request = requests.get(requestId);
        if (request != null) {
            boolean success = inventoryController.increaseStock(request.getMedicationName(), request.getIncreaseStockBy());
            if (success) {
                request.setFlag(Flag.CONFIRMED);
                System.out.println("Request approved: " + request);
            } else {
                System.out.println("Failed to approve request: Medication not found or insufficient stock.");
            }
        } else {
            System.out.println("Request not found: " + requestId);
        }
    }
}
