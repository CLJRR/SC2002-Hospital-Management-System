/**
 * The {@code RequestController} class manages the workflow of handling replenishment requests for medications.
 * It provides functionalities for loading, saving, viewing, creating, and updating request records.
 */
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

    /**
     * Constructs a {@code RequestController} and initializes the components for managing requests.
     * Loads initial request records from storage.
     */
    public RequestController() {
        this.requestRecords = new HashMap<>();
        this.loader = new RequestLoader(requestRecords);
        this.saver = new RequestSaver(requestRecords);
        this.viewer = new RequestViewer(requestRecords);
        this.newRequestCreator = new NewRequestCreator(requestRecords);
        this.requestFlagUpdater = new RequestFlagUpdater(requestRecords);
        loader.loadInitialRequests();
    }

    /**
     * Loads request records from persistent storage into the system.
     */
    public void loadRequests() {
        loader.loadInitialRequests();
    }

    /**
     * Saves the current state of request records to persistent storage.
     */
    public void saveRequests() {
        saver.saveRequests();
    }

    /**
     * Displays all requests in the system.
     * Loads the latest request records before displaying.
     */
    public void viewAllRequests() {
        loadRequests();
        viewer.viewAllRequests();
    }

    /**
     * Displays all pending requests in the system.
     * Loads the latest request records before displaying.
     */
    public void viewPendingRequests() {
        loadRequests();
        viewer.viewPendingRequests();
    }

    /**
     * Allows a pharmacist to create a new replenishment request.
     * The pharmacist's ID is automatically attached to the request.
     * Saves the updated request records after creation.
     */
    public void createNewRequest() {
        loadRequests();
        newRequestCreator.createNewRequest(Session.getLoginID());
        saveRequests(); // Save the updated records after adding the new request
    }

    /**
     * Allows a pharmacist to view their own pending requests.
     * Filters requests by the pharmacist's ID and displays them.
     */
    public void PharmViewRequests() {
        loadRequests();
        viewer.pharmViewPendingRequestsById(Session.getLoginID());
    }

    /**
     * Prompts an administrator to update the flag (status) of a request.
     * Saves the updated request records after the operation.
     */
    public void updateRequestFlag() {
        loadRequests();
        requestFlagUpdater.updateRequestFlagPrompt();
        saveRequests();
    }
}
