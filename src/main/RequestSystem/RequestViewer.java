package RequestSystem;

import enums.Flag;
import java.util.Map;

public class RequestViewer {

    private Map<String, Request> requestRecords;

    public RequestViewer(Map<String, Request> requestRecords) {
        this.requestRecords = requestRecords;
    }

    public void viewAllRequests() {
        System.out.println("All Requests:");
        for (Request request : requestRecords.values()) {
            System.out.println(request.toString());
        }
        System.out.println();
    }

    public void viewPendingRequests() {
        System.out.println("Pending Requests:");
        for (Request request : requestRecords.values()) {
            if (request.getFlag() == Flag.PENDING) {
                System.out.println(request.toString());
            }
        }
        System.out.println();
    }
}
