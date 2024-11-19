package UserSystem;

import enums.Role;
import java.util.*;

/**
 * The {@code GetUser} class provides functionality to retrieve user information
 * from a preloaded collection of users. It supports fetching individual users,
 * as well as filtering users by their roles such as patients, doctors,
 * pharmacists, receptionists, and administrators.
 */
public class GetUser {

    private Map<String, User> users;
    private UserLoader userLoader;

    /**
     * Constructs a {@code GetUser} object and initializes the user data by loading
     * initial user information.
     */
    public GetUser() {
        this.users = new HashMap<>();
        this.userLoader = new UserLoader(this.users);

        // Load initial users into the system
        userLoader.loadInitialUsers();
    }

    /**
     * Reloads the user data by loading it from the {@link UserLoader}.
     */
    public void loadUser() {
        userLoader.loadInitialUsers();
    }

    /**
     * Retrieves a user by their unique ID.
     *
     * @param UserId the unique ID of the user to retrieve.
     * @return the {@link User} object associated with the given ID, or {@code null} if not found.
     */
    public User getUser(String UserId) {
        loadUser();
        return users.get(UserId);
    }

    /**
     * Retrieves all users with the role of "PATIENT".
     *
     * @return a list of all patients.
     */
    public List<User> getAllPatients() {
        loadUser();
        List<User> patients = new ArrayList<>();
        for (User user : users.values()) {
            if (user.getRole() == Role.PATIENT) {
                patients.add(user);
            }
        }
        return patients;
    }

    /**
     * Retrieves all users with the role of "DOCTOR".
     *
     * @return a list of all doctors.
     */
    public List<User> getAllDoctors() {
        loadUser();
        List<User> doctors = new ArrayList<>();
        for (User user : users.values()) {
            if (user.getRole() == Role.DOCTOR) {
                doctors.add(user);
            }
        }
        return doctors;
    }

    /**
     * Retrieves all users with the role of "PHARMACIST".
     *
     * @return a list of all pharmacists.
     */
    public List<User> getAllPharmacists() {
        loadUser();
        List<User> pharmacists = new ArrayList<>();
        for (User user : users.values()) {
            if (user.getRole() == Role.PHARMACIST) {
                pharmacists.add(user);
            }
        }
        return pharmacists;
    }

    /**
     * Retrieves all users with the role of "RECEPTIONIST".
     *
     * @return a list of all receptionists.
     */
    public List<User> getAllReceptionist() {
        loadUser();
        List<User> receptionists = new ArrayList<>();
        for (User user : users.values()) {
            if (user.getRole() == Role.RECEPTIONIST) {
                receptionists.add(user);
            }
        }
        return receptionists;
    }

    /**
     * Retrieves all users with the role of "ADMINISTRATOR".
     *
     * @return a list of all administrators.
     */
    public List<User> getAllAdministrators() {
        loadUser();
        List<User> administrators = new ArrayList<>();
        for (User user : users.values()) {
            if (user.getRole() == Role.ADMINISTRATOR) {
                administrators.add(user);
            }
        }
        return administrators;
    }
}
