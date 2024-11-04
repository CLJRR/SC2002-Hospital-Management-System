package UI;

public class Session {

    private static String patientID;

    // Get the currently logged-in patientID
    public static String getPatientID() {
        return patientID;
    }

    // Set the patientID upon successful login
    public static void setPatientID(String id) {
        patientID = id;
    }

    // Check if a patient is logged in
    public static boolean isLoggedIn() {
        return patientID != null;
    }

    // Log out the current patient by clearing the patientID
    public static void logout() {
        patientID = null;
    }
}

