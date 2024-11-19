/**
 * The {@code Login} class extends {@code InputPrompt} and provides functionality for verifying
 * user login credentials against a stored user database. It uses the {@code UserService} to
 * authenticate users and manages session details upon successful login.
 */
package LoginSystem;

import SessionManager.Session;
import UserSystem.*;
import enums.*;
import java.io.IOException;
import java.util.List;

public class Login extends InputPrompt {

    private Role choice;

    /**
     * Checks the login attempt credentials entered by the user.
     * <p>
     * This method compares the login ID and password entered by the user with the
     * stored credentials. If a match is found, it initializes the user session and sets
     * the user's role and name in the session.
     *
     * @param login the {@code InputPrompt} object containing the login ID and password entered by the user.
     * @return {@code true} if the credentials are correct; {@code false} otherwise.
     * @throws IOException if an error occurs while loading user data.
     */
    public boolean checkAttempt(InputPrompt login) throws IOException {

        UserService userService = new UserService();

        @SuppressWarnings("unchecked")
        List<User> users = (List<User>) userService.load();

        for (User user : users) {
            if (user.getId().equalsIgnoreCase(login.getLoginIDAttempt())) {
                if (user.getPassword().equals(login.getPasswordAttempt())) {
                    System.out.println("Login successfully.");
                    Session.setLoginID(user.getId());
                    Session.setRole(user.getRole());
                    Session.setName(user.getName());
                    this.setChoice(user.getRole());
                    System.out.println("Welcome " + this.choice + " " + user.getName());
                    return true;
                } else {
                    System.out.println("Incorrect Password.");
                    return false;
                }
            }
        }
        System.out.println("LoginID not found.");
        return false;
    }

    /**
     * Retrieves the role of the logged-in user.
     *
     * @return the role of the user as a {@code Role} enum.
     */
    public Role getChoice() {
        return this.choice;
    }

    /**
     * Sets the role of the logged-in user.
     *
     * @param choice the role of the user to set.
     */
    public void setChoice(Role choice) {
        this.choice = choice;
    }
}
