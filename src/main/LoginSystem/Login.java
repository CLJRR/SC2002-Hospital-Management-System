package LoginSystem;
import UserSystem.User;
import UserSystem.UserService;
import enums.*;
import java.io.IOException;
import java.util.List;

//comment
public class Login extends InputPrompt {
    private Role choice;


    public boolean checkAttempt(InputPrompt login) throws IOException {
        final String FILE_NAME = "./data/users.txt";
        UserService userService = new UserService();

        @SuppressWarnings("unchecked")
        List<User> users = (List<User>) userService.load(FILE_NAME);

        for (User user:users) {
            if (user.getId().equalsIgnoreCase(login.getLoginIDAttempt())) {
                if (user.getPassword().equals(login.getPasswordAttempt())) {
                    System.out.println("Login successfully.");
                    this.setChoice(user.getRole());
                    System.out.println("Welcome " + this.choice);
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
