package UserSystem;

import java.io.IOException;
import java.util.*;

public class HospitalStaffLoader {
    private Map<String, User> users;
    private UserService userService;

    public HospitalStaffLoader(Map<String, User> users) {
        this.users = users;
        this.userService = new UserService();
    }

    public void loadInitialUsers() {
        try {
            List<User> userList = userService.load();
            for (User user : userList) {
                users.put(user.getId(), user);
            }
        } catch (IOException e) {
            System.err.println("Error loading staffs: " + e.getMessage()) ;
        }
    }
}
