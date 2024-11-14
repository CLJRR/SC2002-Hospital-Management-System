package RequestSystem;

import enums.Flag;
import java.io.IOException;
import java.util.*;

public class RequestController {

    private Map<String, Request> requestRecords;
    private RequestLoader loader;
    private RequestSaver saver;
    private RequestViewer viewer;

    public RequestController() {
        this.requestRecords = new HashMap<>();
        this.loader = new RequestLoader(requestRecords);
        this.saver = new RequestSaver(requestRecords);
        this.viewer = new RequestViewer(requestRecords);
        loader.loadInitialRequests();
    }

    public void loadRequests() {
        loader.loadInitialRequests();
    }

    public void saveRequests() {
        saver.saveRequests();
    }

    public void viewAllRequests() {
        viewer.viewAllRequests();
    }

    public void viewPendingRequests() {
        viewer.viewPendingRequests();
    }

    public void updateRequestFlag(String requestId, Flag newFlag) {
        Request request = requestRecords.get(requestId);
        if (request != null) {
            request.setFlag(newFlag);
            System.out.println("Request " + requestId + " updated to " + newFlag);
            saveRequests();
        } else {
            System.out.println("Request not found.");
        }
    }
}
