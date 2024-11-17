package UserSystem;

/**
 * This class provides functionality for verifying the strength of a password.
 * It ensures the password meets specific criteria, such as minimum length,
 * inclusion of uppercase letters, digits, and special characters.
 */

public class PasswordVerifier {

    /**
     * Verifies whether a password meets the specified security criteria:
     * - At least 8 characters in length.
     * - Contains at least one uppercase letter.
     * - Contains at least one digit.
     * - Contains at least one special character (!@#$%^&*()-+).
     * 
     * @param password The password to be verified.
     * @return true if the password meets all criteria, false otherwise.
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

        // Provide feedback for missing criteria
        if (!hasUpper) {
            System.out.println("Password must contain at least one uppercase character.");
        }
        if (!hasDigit) {
            System.out.println("Password must contain at least one number.");
        }
        if (!hasSpecial) {
            System.out.println("Password must contain at least one of the following special characters: ");
            System.out.println("!@#$%^&*()-+");
        }

        // Return true only if all criteria are met
        return (hasDigit && hasSpecial && hasUpper);
    }
}
