package RequestSystem;

import SessionManager.Session;
import java.util.HashMap;

public class RequestController {

    private HashMap<String, Request> requestRecords;
    private RequestLoader loader;
    private RequestSaver saver;
    private RequestViewer viewer;
    private NewRequestCreator newRequestCreator;
    private RequestFlagUpdater requestFlagUpdater;

    public RequestController() {
        this.requestRecords = new HashMap<>();
        this.loader = new RequestLoader(requestRecords);
        this.saver = new RequestSaver(requestRecords);
        this.viewer = new RequestViewer(requestRecords);
        this.newRequestCreator = new NewRequestCreator(requestRecords);
        this.requestFlagUpdater = new RequestFlagUpdater(requestRecords);
        loader.loadInitialRequests();
    }

    public void loadRequests() {
        loader.loadInitialRequests();
    }

    public void saveRequests() {
        saver.saveRequests();
    }

    public void viewAllRequests() {
        loadRequests();
        viewer.viewAllRequests();
    }

    public void viewPendingRequests() {
        loadRequests();
        viewer.viewPendingRequests();
    }

    // Method for pharm to create a new request by calling NewRequestCreator
    public void createNewRequest() {
        loadRequests();
        newRequestCreator.createNewRequest(Session.getLoginID());
        saveRequests(); // Save the updated records after adding the new request
    }
    //pharm view his requests:

    public void PharmViewRequests() {
        loadRequests();
        viewer.pharmViewPendingRequestsById(Session.getLoginID());
    }

    public void updateRequestFlag() {
        loadRequests();
        requestFlagUpdater.updateRequestFlagPrompt();
        saveRequests();
    }
}
