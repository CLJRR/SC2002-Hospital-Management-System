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

    public void loadUser() {
        userLoader.loadInitialUsers();
    }

    public User getUser(String UserId) {
        loadUser();
        User user = users.get(UserId);
        return user;
    }

    public List<User> getAllPatients() {
        loadUser();
        List<User> patients = new ArrayList<>();
        for (User user : users.values()) {
            if (user.getRole() == Role.PATIENT) {
                patients.add(user); // Add user to the list if their role is DOCTOR
            }
        }
        return patients;
    }

    public List<User> getAllDoctors() {
        loadUser();
        List<User> doctors = new ArrayList<>();
        for (User user : users.values()) {
            if (user.getRole() == Role.DOCTOR) {
                doctors.add(user); // Add user to the list if their role is DOCTOR
            }
        }
        return doctors;
    }

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
    
    public List<User> getAllAdministrators() {
        loadUser();
        List<User> adminstrators = new ArrayList<>();
        for (User user : users.values()) {
            if (user.getRole() == Role.ADMINISTRATOR) {
                adminstrators.add(user);
            }
        }
        return adminstrators;
    }
}
