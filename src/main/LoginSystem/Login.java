package LoginSystem;
import UserSystem.User;
import UserSystem.UserService;
import enums.*;
import java.io.IOException;
import java.util.List;

//comment
public class Login {
    private Role choice;

    public Role getChoice() {
        return this.choice;
    }

    public void setChoice(Role choice) {
        this.choice = choice;
    }
    
    public boolean checkAttempt(UserLogin login) throws IOException {
        final String FILE_NAME = "./data/users.txt";
        UserService userService = new UserService();

        @SuppressWarnings("unchecked")
        List<User> users = (List<User>) userService.load(FILE_NAME);

        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equals(login.getLoginIDAttempt())) {
                if (users.get(i).getPassword().equals(login.getPasswordAttempt())) {
                    System.out.println("Login successfully.");
                    this.setChoice(users.get(i).getRole());
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
}
