/**
 * The {@code RequestViewer} class provides methods for displaying different subsets 
 * of requests, including all requests, pending requests, and requests specific to a pharmacist.
 */
package RequestSystem;

import enums.Flag;
import java.util.Map;
/**
 * The {@code RequestViewer} class provides methods for displaying different subsets 
 * of requests, including all requests, pending requests, and requests specific to a pharmacist.
 */
public class RequestViewer {

    private Map<String, Request> requestRecords;

    /**
     * Constructs a {@code RequestViewer} with a specified map of request records.
     *
     * @param requestRecords a {@code Map} containing {@code Request} objects, keyed by their request IDs.
     */
    public RequestViewer(Map<String, Request> requestRecords) {
        this.requestRecords = requestRecords;
    }

    /**
     * Displays all requests in the system.
     */
    public void viewAllRequests() {
        System.out.println("All Requests:");
        for (Request request : requestRecords.values()) {
            System.out.println(request.toString());
        }
        System.out.println(); // Adds a new line after the list
    }

    /**
     * Displays all requests with the {@code PENDING} flag.
     */
    public void viewPendingRequests() {
        System.out.println("Pending Requests:");
        for (Request request : requestRecords.values()) {
            if (request.getFlag() == Flag.PENDING) {
                System.out.println(request.toString());
            }
        }
        System.out.println(); // Adds a new line after the list
    }

    /**
     * Displays all requests made by a specific pharmacist, identified by their ID.
     *
     * @param PharmId the ID of the pharmacist whose requests are to be displayed.
     */
    public void pharmViewPendingRequestsById(String PharmId) {
        System.out.println("Requests made by you:");
        for (Request request : requestRecords.values()) {
            if (request.getPharmId().equalsIgnoreCase(PharmId)) {
                System.out.println(request.toString());
            }
        }
        System.out.println(); // Adds a new line after the list
    }
}
