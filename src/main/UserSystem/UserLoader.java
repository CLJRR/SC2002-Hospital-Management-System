package UserSystem;

import java.io.IOException;
import java.util.*;

/**
 * The {@code UserLoader} class is responsible for loading user data into a given map.
 * It utilizes the {@link UserService} to fetch user data from an external source.
 */
public class UserLoader {
    private Map<String, User> users;
    private UserService userService;

    /**
     * Constructs a {@code UserLoader} object.
     *
     * @param users a map where the loaded user data will be stored, with user IDs as keys
     *              and {@link User} objects as values.
     */
    public UserLoader(Map<String, User> users) {
        this.users = users;
        this.userService = new UserService();
    }

    /**
     * Loads the initial set of users from the {@link UserService}.
     * The loaded users are stored in the provided map.
     * If an error occurs during the loading process, an error message is printed to the console.
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
