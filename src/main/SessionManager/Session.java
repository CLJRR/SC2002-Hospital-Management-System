/**
 * The {@code Session} class manages session-related information for a logged-in user.
 * This includes their login ID, role, name, and any appointment-related data.
 * It also provides functionality to reset the session upon logout.
 */
package SessionManager;

import enums.Role;

public class Session {

    private static String loginID; // The login ID of the currently logged-in user
    private static Role role; // The role of the currently logged-in user
    private static String name; // The name of the currently logged-in user
    private static String AppointmentId; // The ID of the appointment being handled by the session

    /**
     * Retrieves the login ID of the current session.
     *
     * @return the login ID of the user
     */
    public static String getLoginID() {
        return loginID;
    }

    /**
     * Sets the login ID for the current session.
     *
     * @param id the login ID to set
     */
    public static void setLoginID(String id) {
        Session.loginID = id.toUpperCase(); // Converts to uppercase for consistency
    }

    /**
     * Retrieves the role of the current session.
     *
     * @return the {@code Role} of the user
     */
    public static Role getRole() {
        return role;
    }

    /**
     * Sets the role for the current session.
     *
     * @param role the {@code Role} to set
     */
    public static void setRole(Role role) {
        Session.role = role;
    }

    /**
     * Retrieves the name of the current session.
     *
     * @return the name of the user
     */
    public static String getName() {
        return name;
    }

    /**
     * Sets the name for the current session.
     *
     * @param name the name to set
     */
    public static void setName(String name) {
        Session.name = name;
    }

    /**
     * Retrieves the appointment ID associated with the current session.
     *
     * @return the appointment ID
     */
    public static String getAppointmentId() {
        return AppointmentId;
    }

    /**
     * Sets the appointment ID for the current session.
     *
     * @param appointmentId the appointment ID to set
     */
    public static void setAppointmentId(String appointmentId) {
        AppointmentId = appointmentId;
    }

    /**
     * Resets the session by clearing all stored session data.
     */
    public static void logout() {
        loginID = null;
        role = null;
        name = null;
        AppointmentId = null;
    }
}
