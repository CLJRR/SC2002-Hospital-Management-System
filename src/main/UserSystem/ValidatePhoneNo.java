package UserSystem;

/**
 * This class provides functionality to validate phone numbers.
 * It ensures that the phone number starts with specific digits and
 * has the required length.
 */

public class ValidatePhoneNo {

    /**
     * Validates a phone number based on the following criteria:
     * <ul>
     * <li>The phone number must start with '8' or '9'.</li>
     * <li>The phone number must be exactly 8 digits long.</li>
     * </ul>
     *
     * @param PhoneNo The phone number to validate.
     * @return true if the phone number meets the criteria; false otherwise.
     */

    public boolean validatePhoneNo(String PhoneNo) {
        // Check if the phone number starts with '8' or '9' and has exactly 8 digits
        if (PhoneNo.charAt(0) == '8' || PhoneNo.charAt(0) == '9') {
            return (PhoneNo.length() == 8);
        } else
            return false;
    }
}
