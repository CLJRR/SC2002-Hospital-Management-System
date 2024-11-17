package UserSystem;

import java.io.IOException;
import java.util.*;

/**
 * This class is responsible for loading user data into the system.
 * It initializes the user map with data fetched from an external source
 * through the UserService.
 */

public class UserLoader {
    private final Map<String, User> users;
    private final UserService userService;

    /**
     * Constructs a UserLoader instance, initializing it with a user map and
     * a UserService for retrieving user data.
     *
     * @param users A map to store the loaded user data, keyed by their unique IDs.
     */

    public UserLoader(Map<String, User> users) {
        this.users = users;
        this.userService = new UserService();
    }

    /**
     * Loads initial user data into the system.
     * Fetches data using the UserService and populates the user map.
     * In case of an error during data loading, it logs an error message.
     */

    public void loadInitialUsers() {
        try {
            List<User> userList = userService.load();
            for (User user : userList) {
                users.put(user.getId(), user);
            }
        } catch (IOException e) {
            System.err.println("Error loading staffs: " + e.getMessage());
        }
    }
}
