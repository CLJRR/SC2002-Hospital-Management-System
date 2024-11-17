package UserSystem;

import java.io.IOException;
import java.util.*;

/**
 * This class provides functionality to update a user's contact information,
 * specifically their phone number or email address. It includes validation
 * mechanisms to ensure the new information meets required standards.
 */

public class UpdateInformation {
    private final Map<String, User> users;
    private final UserLoader userLoader;
    private final UserSaver userSaver;

    /**
     * Constructs an UpdateInformation instance, initialising it with a user map
     * and associated loaders and savers to manage user data.
     */

    public UpdateInformation() {
        this.users = new HashMap<>();
        this.userLoader = new UserLoader(this.users);
        this.userSaver = new UserSaver(this.users);

        // Load initial user data
        userLoader.loadInitialUsers();
    }

    /**
     * Updates a user's phone number or email address based on their ID.
     * Prompts the user to choose which field to update and validates the input
     * before saving the updated information.
     * 
     * @param UserId The unique ID of the user whose information is to be updated.
     * @throws IOException If there is an error saving the updated user data.
     */

    public void updateInformation(String UserId) throws IOException {
        User user = users.get(UserId);
        if (user != null) {
            Scanner sc = new Scanner(System.in);
            int option = 0;

            // Prompt user to choose an option until a valid one is chosem
            while (!(option == 1 || option == 2)) {
                System.out.println("Select option: ");
                System.out.println("[1] Update PhoneNo. ");
                System.out.println("[2] Update email ");
                while (!sc.hasNextInt()) { // Check if input is an integer
                    System.out.println("Option not valid. Please try again:");
                    sc.next(); // Clear the invalid input
                }

                option = sc.nextInt();
                sc.nextLine(); // Consumes Newline
            }

            switch (option) {
                case 1 -> {
                    System.out.println("Enter new PhoneNo.: ");
                    String newPhoneNo = sc.nextLine();
                    ValidatePhoneNo vPhoneNo = new ValidatePhoneNo();
                    boolean isValid = vPhoneNo.validatePhoneNo(newPhoneNo);

                    // Validate phone number until a valid one is provided
                    while (isValid == false) {
                        System.out.println("Enter valid PhoneNo.: ");
                        newPhoneNo = sc.nextLine();
                        isValid = vPhoneNo.validatePhoneNo(newPhoneNo);
                    }

                    user.setPhoneNumber(newPhoneNo);
                    userSaver.saveUsers();
                    System.out.println("New Phone Number set to " + user.getPhoneNumber());
                }
                case 2 -> {
                    System.out.println("Enter new email: ");
                    String newEmail = sc.nextLine();
                    ValidateEmail vemail = new ValidateEmail();
                    boolean temp = vemail.validateEmail(newEmail);

                    // Validate email until a valid one is provided
                    while (temp == false) {
                        System.out.println("Enter valid email: ");
                        newEmail = sc.nextLine();
                        temp = vemail.validateEmail(newEmail);
                    }

                    user.setEmail(newEmail);
                    userSaver.saveUsers();
                    System.out.println("New Email set to " + user.getEmail());
                }
            }
        } else {
            System.out.println("User with ID " + UserId + " not found.");
        }
    }
}
