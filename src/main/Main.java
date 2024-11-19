

import Interfaces.LoginUI;
import java.io.IOException;

/**
 * The {@code Main} class serves as the entry point to the application. It
 * continuously invokes the login user interface to allow users to log in.
 */
public class Main {

    /**
     * The main method initializes the application and enters a loop to display
     * the login UI.
     *
     * @param args command-line arguments (not used in this application).
     * @throws IOException if an input or output exception occurs during the
     * execution of the login UI.
     */
    public static void main(String[] args) throws IOException {
        LoginUI x = new LoginUI();
        while (true) {
            x.loginUI();
        }
    }
}
