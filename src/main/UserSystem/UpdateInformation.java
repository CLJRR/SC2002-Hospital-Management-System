package UserSystem;

import java.io.IOException;
import java.util.*;

public class UpdateInformation {

    private final Map<String, User> users;
    private final UserLoader userLoader;
    private final UserSaver userSaver;

    public UpdateInformation() {
        this.users = new HashMap<>();
        this.userLoader = new UserLoader(this.users);
        this.userSaver = new UserSaver(this.users);

        userLoader.loadInitialUsers();
    }

    public void updateInformation(String userId) throws IOException {
        User user = users.get(userId);
        if (user != null) {
            Scanner sc = new Scanner(System.in);
            ValidatePhoneNo validatePhoneNo = new ValidatePhoneNo();
            ValidateEmail validateEmail = new ValidateEmail();
            boolean exit = false;

            while (!exit) {
                System.out.println("Select option: ");
                System.out.println("[1] Update PhoneNo.");
                System.out.println("[2] Update Email");
                System.out.println("[3] Exit");

                int option = getValidatedOption(sc);

                switch (option) {
                    case 1 ->
                        updatePhoneNumber(sc, user, validatePhoneNo);
                    case 2 ->
                        updateEmail(sc, user, validateEmail);
                    case 3 ->
                        exit = true;
                    default ->
                        System.out.println("Invalid option. Please try again.");
                }
            }
        } else {
            System.out.println("User not found.");
        }
    }

    private int getValidatedOption(Scanner sc) {
        while (!sc.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number:");
            sc.next(); // Clear invalid input
        }
        int option = sc.nextInt();
        sc.nextLine(); // Consume newline
        return option;
    }

    private void updatePhoneNumber(Scanner sc, User user, ValidatePhoneNo validatePhoneNo) throws IOException {
        System.out.println("Enter new PhoneNo.: ");
        String newPhoneNo = sc.nextLine();
        while (!validatePhoneNo.validatePhoneNo(newPhoneNo)) {
            System.out.println("Invalid PhoneNo. Please try again: ");
            newPhoneNo = sc.nextLine();
        }
        user.setPhoneNumber(newPhoneNo);
        userSaver.saveUsers();
        System.out.println("Phone number updated to: " + user.getPhoneNumber());
    }

    private void updateEmail(Scanner sc, User user, ValidateEmail validateEmail) throws IOException {
        System.out.println("Enter new email: ");
        String newEmail = sc.nextLine();
        while (!validateEmail.validateEmail(newEmail)) {
            System.out.println("Invalid email. Please try again: ");
            newEmail = sc.nextLine();
        }
        user.setEmail(newEmail);
        userSaver.saveUsers();
        System.out.println("Email updated to: " + user.getEmail());
    }
}
