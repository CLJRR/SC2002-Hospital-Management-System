package UserSystem;

import enums.Role;
import java.util.*;

public class GetUser {

    private Map<String, User> users;
    private UserLoader userLoader;

    public GetUser() {
        this.users = new HashMap<>();
        this.userLoader = new UserLoader(this.users);

        userLoader.loadInitialUsers();
    }

    public User getUser(String UserId) {
        User user = users.get(UserId);
        return user;
    }

    public List<User> getAllDoctors() {
        List<User> doctors = new ArrayList<>();
        for (User user : users.values()) {
            if (user.getRole() == Role.DOCTOR) {
                doctors.add(user); // Add user to the list if their role is DOCTOR
            }
        }
        return doctors;
    }
}
