package UserSystem;

import java.util.*;

/**
 * The {@code PasswordChanger} class allows users to change their passwords.
 * It ensures that the new password meets specific security criteria defined in {@link PasswordVerifier}.
 */
public class PasswordChanger {

    private final Map<String, User> users;
    private final UserSaver userSaver;
    private final UserLoader userLoader;
    private final PasswordVerifier passwordVerifier;

    /**
     * Constructs a {@code PasswordChanger} object, initializing the user data by loading
     * the initial set of users.
     */
    public PasswordChanger() {
        this.users = new HashMap<>();
        this.userLoader = new UserLoader(this.users);
        this.userSaver = new UserSaver(this.users);
        this.passwordVerifier = new PasswordVerifier();

        userLoader.loadInitialUsers();
    }

    /**
     * Allows a user to change their password. Prompts the user to enter a new password and verifies
     * it using the {@link PasswordVerifier}. The password is saved if it meets the required criteria.
     *
     * @param loginId the login ID of the user whose password is being changed.
     */
    public void passwordChanger(String loginId) {
        Scanner sc = new Scanner(System.in);
        User user = users.get(loginId);
        String newPassword = "";
        boolean validPassword = false;

        while (!validPassword) {
            System.out.print("Enter New Password: ");
            newPassword = sc.nextLine();
            validPassword = passwordVerifier.passwordVerifier(newPassword);
        }

        user.setPassword(newPassword);
        userSaver.saveUsers();
        System.out.println("Password set.");
    }
}
