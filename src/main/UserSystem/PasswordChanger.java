package UserSystem;

import java.util.*;

public class PasswordChanger {
    private final Map<String, User> users;
    private final UserSaver userSaver;
    private final UserLoader userLoader;
    private final PasswordVerifier passwordVerifier;

    public PasswordChanger() {
        this.users = new HashMap<>();
        this.userLoader = new UserLoader(this.users);
        this.userSaver = new UserSaver(this.users);
        this.passwordVerifier = new PasswordVerifier();

        userLoader.loadInitialUsers();
    }

    public void passwordChanger(String loginId) {
        Scanner sc = new Scanner(System.in);
        User user = users.get(loginId);
        String newPassword = "";
        boolean validPassword = false;

        while (!validPassword) {
            System.out.print("Enter New Password: ");
            newPassword = sc.nextLine();
            validPassword = passwordVerifier.passwordVerifier(newPassword);
        }

        user.setPassword(newPassword);
        userSaver.saveUsers();
        System.out.println("Password set.");
    }
}
