package UserSystem;

public class PasswordVerifier {
    public boolean passwordVerifier(String password) {
        if (password.length() < 8) {
            System.out.println("Password must have at least 8 characters.");
            return false;
        }

        boolean hasUpper = false;
        boolean hasDigit = false;
        boolean hasSpecial = false;

        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                hasUpper = true;
            } else if (Character.isDigit(c)) {
                hasDigit = true;
            } else if ("!@#$%^&*()-+".contains(String.valueOf(c))) {
                hasSpecial = true;
            }
        }

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
        return (hasDigit && hasSpecial && hasUpper);
    }
}
