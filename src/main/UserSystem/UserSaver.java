package UserSystem;

import java.io.IOException;
import java.util.*;

/**
 * This class is responsible for saving user data from the system to an external
 * storage.
 * It interacts with the UserService to persist user information.
 */

public class UserSaver {
    private final Map<String, User> users;
    private final UserService userService;

    /**
     * Constructs a UserSaver instance, initializing it with a user map and
     * a UserService for persisting user data.
     *
     * @param users A map containing user data to be saved, keyed by their unique
     *              IDs.
     */

    public UserSaver(Map<String, User> users) {
        this.users = users;
        this.userService = new UserService();
    }

    /**
     * Saves all user data from the user map to the external storage.
     * Converts the user map to a list of users and uses the UserService
     * to persist the data. Handles any IOExceptions that may occur during the
     * process.
     */

    public void saveUsers() {
        List<User> userList = new ArrayList<>(users.values());
        try {
            userService.save(userList);
        } catch (IOException e) {
            System.err.println("Error saving staffs: " + e.getMessage());
        }
    }
}