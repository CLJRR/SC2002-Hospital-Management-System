package RequestSystem;

import java.io.IOException;
import java.util.*;

public class RequestSaver {

    private Map<String, Request> requestRecords;
    private RequestService requestService;

    public RequestSaver(Map<String, Request> requestRecords) {
        this.requestRecords = requestRecords;
        this.requestService = new RequestService();
    }

    public void saveRequests() {
        List<Request> recordList = new ArrayList<>(requestRecords.values());
        try {
            requestService.save(recordList);
            System.out.println("Requests saved successfully.");
        } catch (IOException e) {
            System.err.println("Error saving requests: " + e.getMessage());
        }
    }
}
