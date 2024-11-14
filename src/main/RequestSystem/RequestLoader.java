package RequestSystem;

import java.io.IOException;
import java.util.*;

public class RequestLoader {

    private final Map<String, Request> requestRecords;
    private final RequestService requestService;

    public RequestLoader(Map<String, Request> requestRecords) {
        this.requestRecords = requestRecords;
        this.requestService = new RequestService();
    }

    public void loadInitialRequests() {
        try {
            List<Request> records = (List<Request>) requestService.load();
            for (Request request : records) {
                requestRecords.put(request.getRequestId(), request);
            }
            System.out.println("Requests loaded successfully.");
        } catch (IOException e) {
            System.err.println("Error loading requests: " + e.getMessage());
        }
    }
}
