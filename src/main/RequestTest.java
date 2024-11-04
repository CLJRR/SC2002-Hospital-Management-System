
import entity.Request;
import enums.Flag;
import java.util.List;
import service.RequestService;

public class RequestTest {

    public static void main(String[] args) {
        RequestService requestService = new RequestService();
        requestService.deleteAll();
        // Test 1: Load all requests (Should initially be empty)
        System.out.println("Test 1: Load all requests");
        List<Request> requests = requestService.loadAll();
        System.out.println("No of req: " + requests.size()); // Expect 0

        // Test 2: Save a new request
        System.out.println("\nTest 2: Save a new request");
        Request newRequest = new Request("REQ001", "PHARM001", "MedicationA", 10, "Urgent restock", Flag.PENDING);
        requestService.save(newRequest);
        requestService.save(new Request("REQ002", "PHARM002", "MedicationB", 15, "Regular restock", Flag.PENDING));
        requestService.save(new Request("REQ003", "PHARM003", "MedicationC", 20, "Special restock", Flag.PENDING));
        requestService.save(newRequest); // Should indicate duplicate ID
        requests = requestService.loadAll();
        System.out.println("No of req: " + requests.size()); // Expect 3
        // Test 4: Delete the request by ID
        System.out.println("\nTest 4: Delete the request by ID");
        requestService.deleteByRequestId("REQ001");
        System.out.println("\nTest 5: Delete a non-existent request");
        requestService.deleteByRequestId("REQ999"); // Should indicate ID not found

        //print out request list
        System.out.println("");
        requests = requestService.loadAll();
        for (Request request : requests) {
            System.out.println(request.toString());
        }
    }
}
