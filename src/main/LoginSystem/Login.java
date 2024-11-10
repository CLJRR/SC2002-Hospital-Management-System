package LoginSystem;

import SessionManager.Session;
import UserSystem.*;
import enums.*;
import java.io.IOException;
import java.util.List;
//comment

public class Login extends InputPrompt {

    private Role choice;

    public boolean checkAttempt(InputPrompt login) throws IOException {

        UserService userService = new UserService();

        @SuppressWarnings("unchecked")
        List<User> users = (List<User>) userService.load();

        for (User user : users) {
            if (user.getId().equalsIgnoreCase(login.getLoginIDAttempt())) {
                if (user.getPassword().equals(login.getPasswordAttempt())) {
                    System.out.println("Login successfully.");
                    Session.setLoginID(user.getId());
                    Session.setRole(user.getRole());
                    Session.setName(user.getName());
                    this.setChoice(user.getRole());
                    System.out.println("Welcome " + this.choice + " " + user.getName());
                    return true;
                } else {
                    System.out.println("Incorrect Password.");
                    return false;
                }
            }
        }
        System.out.println("LoginID not found.");
        return false;
    }

    public Role getChoice() {
        return this.choice;
    }

    public void setChoice(Role choice) {
        this.choice = choice;
    }

}
