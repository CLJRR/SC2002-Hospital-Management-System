package UserSystem;

/**
 * The {@code PasswordVerifier} class provides functionality to verify the validity of a password.
 * It checks for specific criteria, such as minimum length, presence of an uppercase character,
 * a digit, and a special character.
 */
public class PasswordVerifier {

    /**
     * Verifies whether a given password meets the required criteria:
     * <ul>
     *   <li>At least 8 characters long</li>
     *   <li>Contains at least one uppercase letter</li>
     *   <li>Contains at least one digit</li>
     *   <li>Contains at least one special character (!@#$%^&*()-+)</li>
     * </ul>
     *
     * @param password the password to be verified.
     * @return {@code true} if the password meets all criteria, otherwise {@code false}.
     */
    public boolean passwordVerifier(String password) {
        if (password.length() < 8) {
            System.out.println("Password must have at least 8 characters.");
            return false;
        }

        boolean hasUpper = false;
        boolean hasDigit = false;
        boolean hasSpecial = false;

        // Check each character of the password
        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                hasUpper = true;
            } else if (Character.isDigit(c)) {
                hasDigit = true;
            } else if ("!@#$%^&*()-+".contains(String.valueOf(c))) {
                hasSpecial = true;
            }
        }

        // Provide feedback if criteria are not met
        if (!hasUpper) {
            System.out.println("Password must contain at least one uppercase character.");
        }
        if (!hasDigit) {
            System.out.println("Password must contain at least one number.");
        }
        if (!hasSpecial) {
            System.out.println("Password must contain at least one of the following special characters:");
            System.out.println("!@#$%^&*()-+");
        }

        return (hasDigit && hasSpecial && hasUpper);
    }
}
