package UserSystem;

import java.io.IOException;
import java.util.*;

/**
 * The {@code UserSaver} class is responsible for saving user data to an external source.
 * It utilizes the {@link UserService} to persist user data.
 */
public class UserSaver {
    private Map<String, User> users;
    private UserService userService;

    /**
     * Constructs a {@code UserSaver} object.
     *
     * @param users a map containing user data, with user IDs as keys and {@link User} objects as values.
     */
    public UserSaver(Map<String, User> users) {
        this.users = users;
        this.userService = new UserService();
    }

    /**
     * Saves the current user data to an external source using {@link UserService}.
     * Converts the map of users into a list and attempts to persist the data.
     * If an error occurs during the save operation, an error message is printed to the console.
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
