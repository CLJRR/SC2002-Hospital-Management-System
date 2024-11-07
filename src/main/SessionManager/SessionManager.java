package SessionManager;

import java.io.*;
import java.util.HashMap;
import java.util.UUID;

public class SessionManager {

    private HashMap<String, Session> sessions;
    private static final String FILE_NAME = "sessions.txt"; // The file where session IDs will be stored

    public SessionManager() {
        sessions = new HashMap<>();
        loadSessionsFromFile(); // Load sessions from file when starting
    }

    // Method to create a new session for a user
    public String createSession(String username) {
        String sessionId = UUID.randomUUID().toString(); // Generate a unique session ID
        Session session = new Session(sessionId, username);
        sessions.put(sessionId, session);
        System.out.println("Session created for user: " + username);
        saveSessionsToFile(); // Save sessions to file after adding a new one
        return sessionId;
    }

    // Method to retrieve a session by session ID
    public Session getSession(String sessionId) {
        return sessions.get(sessionId);
    }

    // Method to remove a session by session ID (logout)
    public void removeSession(String sessionId) {
        if (sessions.containsKey(sessionId)) {
            Session session = sessions.remove(sessionId);
            System.out.println("Session ended for user: " + session.getUsername());
            saveSessionsToFile(); // Save sessions to file after removing one
        } else {
            System.out.println("Session ID not found: " + sessionId);
        }
    }

    // Method to validate if a session exists
    public boolean isValidSession(String sessionId) {
        return sessions.containsKey(sessionId);
    }

    // Method to save sessions to a file
    private void saveSessionsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Session session : sessions.values()) {
                writer.write(session.getSessionId() + "," + session.getUsername() + "," + session.getLoginTime());
                writer.newLine();
            }
            System.out.println("Sessions saved to file.");
        } catch (IOException e) {
            System.err.println("Error saving sessions to file: " + e.getMessage());
        }
    }

    // Method to load sessions from a file
    private void loadSessionsFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return; // No file to load from if it doesn't exist
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String sessionId = parts[0];
                    String username = parts[1];
                    long loginTime = Long.parseLong(parts[2]);

                    // Recreate session and add it to the map
                    Session session = new Session(sessionId, username, loginTime);
                    sessions.put(sessionId, session);
                }
            }
            System.out.println("Sessions loaded from file.");
        } catch (IOException e) {
            System.err.println("Error loading sessions from file: " + e.getMessage());
        }
    }
}
