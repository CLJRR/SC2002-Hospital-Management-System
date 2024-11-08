package SessionManager;

import enums.Role;

public class Session {

    private static String loginID;
    private static Role role;
    private static String name;

    public static String getLoginID() {
        return loginID;
    }

    public static void setLoginID(String id) {
        Session.loginID = id;
    }

    public static Role getRole() {
        return role;
    }

    public static void setRole(Role role) {
        Session.role = role;
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        Session.name = name;
    }

    public static void logout() {
        loginID = null;
        role = null;
        name = null;
    }
}
