/**
 * The {@code RequestLoader} class is responsible for loading request records from an external
 * data source into an in-memory map for processing and management.
 * It utilizes the {@code RequestService} to handle the loading operation.
 */
package RequestSystem;

import java.io.IOException;
import java.util.*;

public class RequestLoader {

    private final Map<String, Request> requestRecords;
    private final RequestService requestService;

    /**
     * Constructs a {@code RequestLoader} with a map of request records.
     *
     * @param requestRecords a map to store request records, keyed by their request IDs.
     */
    public RequestLoader(Map<String, Request> requestRecords) {
        this.requestRecords = requestRecords;
        this.requestService = new RequestService();
    }

    /**
     * Loads the initial set of request records from the external data source into the
     * in-memory {@code requestRecords} map. Each record is indexed by its request ID.
     * If loading fails, an error message is displayed.
     */
    public void loadInitialRequests() {
        try {
            @SuppressWarnings("unchecked")
            List<Request> records = (List<Request>) requestService.load(); // Load request records using the service
            for (Request request : records) {
                requestRecords.put(request.getRequestId(), request); // Store each record in the map
            }
            System.out.println("Requests loaded successfully.");
        } catch (IOException e) {
            System.err.println("Error loading requests: " + e.getMessage());
        }
    }
}
