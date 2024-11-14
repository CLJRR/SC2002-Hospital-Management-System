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

    public void updateInformation(String UserId) throws IOException {
        User user = users.get(UserId);
        if (user != null) {
            Scanner sc = new Scanner(System.in);
            int option = 0;
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
                    boolean v = vPhoneNo.validatePhoneNo(newPhoneNo);
                    while (v == false) {
                        System.out.println("Enter valid PhoneNo.: ");
                        newPhoneNo = sc.nextLine();
                        v = vPhoneNo.validatePhoneNo(newPhoneNo);
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
        }
    }
}
