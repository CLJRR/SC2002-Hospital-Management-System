package UserSystem;

import enums.*;
import java.io.IOException;
import java.util.*;

public class HospitalStaffLoader {
    private Map<String, User> users;
    private UserService userService;

    public HospitalStaffLoader(Map<String, User> users) {
        this.users = users;
        this.userService = new UserService();
    }

    public void loadInitialStaff() {
        try {
            List<User> userList = userService.load();
            for (User user : userList) {
                if (user.getRole() == Role.DOCTOR || user.getRole() == Role.PHARMACIST) {
                    users.put(user.getId(), user);
                }
            }
            System.out.println("Staffs loaded successfully.");
        } catch (IOException e) {
            System.err.println("Error loading staffs: " + e.getMessage()) ;
        }
    }
}
