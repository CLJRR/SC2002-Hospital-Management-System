package UserSystem;

import java.util.regex.Pattern;

/**
 * The {@code ValidateEmail} class provides functionality for validating email addresses
 * using a regular expression pattern.
 */
public class ValidateEmail {

    /**
     * Validates the given email address based on a predefined regular expression pattern.
     * The pattern ensures the email format is correct, including characters, domain, and extension.
     *
     * @param email the email address to validate.
     * @return {@code true} if the email address is valid, {@code false} otherwise.
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
