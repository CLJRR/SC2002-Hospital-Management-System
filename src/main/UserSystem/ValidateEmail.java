package UserSystem;

import java.util.regex.Pattern;

/**
 * This class provides functionality to validate email addresses.
 * It uses a regular expression to ensure the email conforms to
 * standard formatting rules.
 */

public class ValidateEmail {

    /**
     * Validates an email address using a regular expression pattern.
     * The email must:
     * <ul>
     * <li>Start with alphanumeric characters or allowed special characters (_ + & *
     * -).</li>
     * <li>Contain a valid domain format with a top-level domain of 2 to 7
     * characters.</li>
     * <li>Allow dots (.) in both local and domain parts, provided they are not
     * consecutive or at the edges.</li>
     * </ul>
     *
     * @param email The email address to validate.
     * @return true if the email address matches the pattern; false otherwise.
     */

    public boolean validateEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }
}
