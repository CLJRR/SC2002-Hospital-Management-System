package RequestSystem;

import MedicineInventorySystem.InventoryController;
import enums.Flag;
import java.util.List;

public class ReplenishmentControllerTest {

    public static void main(String[] args) {
        // Create a mock InventoryController
        InventoryController mockInventoryController = new InventoryController() {
            @Override
            public boolean increaseStock(String medicationName, int quantity) {
                // Simulate increasing stock
                System.out.println("Mock increaseStock called for " + medicationName + " with quantity " + quantity);
                return true; // Simulate success
            }
        };

        // Initialize the ReplenishmentController with the mock InventoryController
        ReplenishmentController controller = new ReplenishmentController(mockInventoryController);

        // Test submitting a request
        System.out.println("=== Test: Submit Request ===");
        controller.submitRequest("Aspirin", 100, "Pharm123");
        
        // Verify that a request was added
        List<ReplenishmentRequest> requests = controller.getRequests(); // Use ReplenishmentRequest
        if (requests.size() == 1 && requests.get(0).getMedicationName().equals("Aspirin")) {
            System.out.println("Request submission test passed.");
        } else {
            System.out.println("Request submission test failed.");
        }

        // Test approving the request
        System.out.println("=== Test: Approve Request ===");
        boolean approveResult = controller.approveRequest(0);  // Approving the first request

        // Verify the request's status has been set to CONFIRMED
        ReplenishmentRequest approvedRequest = requests.get(0);  // Use ReplenishmentRequest
        if (approveResult && approvedRequest.getFlag() == Flag.CONFIRMED) {
            System.out.println("Request approval test passed.");
        } else {
            System.out.println("Request approval test failed.");
        }
    }
}
