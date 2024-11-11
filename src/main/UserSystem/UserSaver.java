package UserSystem;

import java.io.IOException;
import java.util.*;

public class UserSaver {
    private Map<String, User> users;
    private UserService userService;

    public UserSaver(Map<String, User> users) {
        this.users = users;
        this.userService = new UserService();
    }

    public void saveUsers() {
        List<User> userList = new ArrayList<>(users.values());
        try {
            userService.save(userList);
            System.out.println("Staffs saved successfully.");
        } catch (IOException e) {
            System.err.println("Error saving staffs: " + e.getMessage());
        }
    }
}