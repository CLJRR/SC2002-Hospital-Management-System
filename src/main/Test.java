
import SessionManager.Session;
import SessionManager.SessionManager;

public class Test {
    public static void main(String[] args) {
        // Instantiate the SessionManager
        SessionManager sessionManager = new SessionManager();

        // Create a new session for a user
        String sessionId = sessionManager.createSession("user1");

        // Check if the session is valid
        System.out.println("Is valid session: " + sessionManager.isValidSession(sessionId));

        // Retrieve and print session details
        Session session = sessionManager.getSession(sessionId);
        System.out.println(session);

        // // Remove the session (log out the user)
        // sessionManager.removeSession(sessionId);

        // // Check session validity after removal
        // System.out.println("Is valid session: " + sessionManager.isValidSession(sessionId));
    }
}
