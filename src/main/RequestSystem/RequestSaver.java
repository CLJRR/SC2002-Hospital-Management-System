
package RequestSystem;

import java.io.IOException;
import java.util.*;
/**
 * The {@code RequestSaver} class is responsible for saving request records
 * from an in-memory map to an external data source. It utilizes the
 * {@code RequestService} to handle the save operation.
 */
public class RequestSaver {

    private Map<String, Request> requestRecords;
    private RequestService requestService;

    /**
     * Constructs a {@code RequestSaver} with a map of request records.
     *
     * @param requestRecords a map containing the request records to be saved, keyed by their request IDs.
     */
    public RequestSaver(Map<String, Request> requestRecords) {
        this.requestRecords = requestRecords;
        this.requestService = new RequestService();
    }

    /**
     * Saves the request records from the in-memory {@code requestRecords} map
     * to an external data source. If saving fails, an error message is displayed.
     */
    public void saveRequests() {
        List<Request> recordList = new ArrayList<>(requestRecords.values()); // Convert map values to a list
        try {
            requestService.save(recordList); // Save request records using the service
            System.out.println("Requests saved successfully.");
        } catch (IOException e) {
            System.err.println("Error saving requests: " + e.getMessage());
        }
    }
}
