package UserSystem;

import enums.Role;
import java.util.*;

/**
 * This class provides functionality to retrive user information to perform
 * role-specific queries, such as fetching all doctors. It initialises the user
 * data by loading from the storage system into memory.
 */

public class GetUser {

    private final Map<String, User> users;
    private final UserLoader userLoader;

    /**
     * Constructs a GetUser instance and inialises the user data by loading it into
     * a map of user IDs to user objects.
     */

    public GetUser() {
        this.users = new HashMap<>();
        this.userLoader = new UserLoader(this.users);

        // Load the initial set of users into the system
        userLoader.loadInitialUsers();
    }

    /**
     * Retrieves a user object based on the provided user ID.
     * 
     * @param UserId The unique ID for the user to be retrieved
     * @return The user object associated with the given ID, or null if no user is
     *         found.
     */

    public User getUser(String UserId) {
        User user = users.get(UserId);
        return user;
    }

    /**
     * Retrieves a list of all users who have the role of DOCTOR.
     * 
     * @return A list of User objects with the role DOCTOR.
     */

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
