package UserSystem;

import java.util.*;

/**
 * This class provides functionality for changing user passwords.
 * It includes password validation and ensures updated passwords are saved
 * persistently.
 */

public class PasswordChanger {
    private final Map<String, User> users;
    private final UserSaver userSaver;
    private final UserLoader userLoader;
    private final PasswordVerifier passwordVerifier;

    /**
     * Constructs a PasswordChanger instance, initialising it with a user map and
     * associated loaders and savers. This ensures users and their data are loaded
     * and saved correctly.
     */
    public PasswordChanger() {
        this.users = new HashMap<>();
        this.userLoader = new UserLoader(this.users);
        this.userSaver = new UserSaver(this.users);
        this.passwordVerifier = new PasswordVerifier();

        // Load the initial set of users
        userLoader.loadInitialUsers();
    }

    /**
     * Changes the password for a specific user identified by their login ID.
     * Ensures the new password meets validation requirements before updating the
     * user's password and saving the updated data.
     * 
     * @param loginId The unique login ID of the user whose password is to be
     *                changed.
     */

    public void passwordChanger(String loginId) {
        Scanner sc = new Scanner(System.in);
        User user = users.get(loginId);

        if (user != null) {
            String newPassword;
            boolean validPassword = false;

            // Prompt the user for a valid password
            while (!validPassword) {
                System.out.print("Enter New Password: ");
                newPassword = sc.nextLine();
                validPassword = passwordVerifier.passwordVerifier(newPassword);

                if (validPassword) {
                    user.setPassword(newPassword); // Update the user's password
                    userSaver.saveUsers(); // Persist the changes
                    System.out.println("Password set successfully.");
                }
            }
        } else {
            System.out.println("User not found. Please check the login ID.");
        }
    }
}