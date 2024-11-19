package UserSystem;

/**
 * The {@code ValidatePhoneNo} class provides functionality for validating phone numbers.
 * It ensures that the phone number meets specific criteria such as length and starting digit.
 */
public class ValidatePhoneNo {

    /**
     * Validates the given phone number based on the following criteria:
     * <ul>
     *   <li>The phone number must start with '8' or '9'.</li>
     *   <li>The phone number must be exactly 8 digits long.</li>
     * </ul>
     *
     * @param PhoneNo the phone number to validate.
     * @return {@code true} if the phone number meets the criteria, {@code false} otherwise.
     */
    public boolean validatePhoneNo(String PhoneNo) {
        if (PhoneNo.charAt(0) == '8' || PhoneNo.charAt(0) == '9') {
            if (PhoneNo.length() == 8) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
