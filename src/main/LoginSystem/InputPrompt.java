package LoginSystem;

import java.util.Scanner;

/**
 * The {@code InputPrompt} class provides functionality for capturing user login
 * details.
 * It includes methods to retrieve and set the login ID and password attempts
 * entered by the user.
 */

public class InputPrompt {
    private String loginIDAttempt;
    private String passwordAttempt;

    /**
     * Gets the login ID entered by the user.
     *
     * @return the login ID entered by the user.
     */
    public String getLoginIDAttempt() {
        return this.loginIDAttempt;
    }

    /**
     * Gets the password entered by the user.
     *
     * @return the password entered by the user.
     */
    public String getPasswordAttempt() {
        return this.passwordAttempt;
    }

    /**
     * Sets the login ID entered by the user.
     *
     * @param id_attempt the login ID entered by the user.
     */
    public void setLoginIDAttempt(String id_attempt) {
        this.loginIDAttempt = id_attempt;
    }

    /**
     * Sets the password entered by the user.
     *
     * @param pw_attempt the password entered by the user.
     */
    public void setPasswordAttempt(String pw_attempt) {
        this.passwordAttempt = pw_attempt;
    }

    /**
     * Captures the login ID and password attempts from the user.
     * <p>
     * Prompts the user to enter their login ID and password and sets these
     * values for future retrieval.
     */
    public void inputAttempt() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter login ID: ");
        String id = sc.nextLine();
        setLoginIDAttempt(id);

        System.out.println("Enter password: ");
        String pw = sc.nextLine();
        setPasswordAttempt(pw);
    }
}
