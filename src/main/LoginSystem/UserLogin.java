package LoginSystem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import UserSystem.User;
import UserSystem.UserService;

public class UserLogin {
    private String loginIDAttempt;
    private String passwordAttempt;

    public String getLoginIDAttempt() {
        return this.loginIDAttempt;
    }

    public String getPasswordAttempt() {
        return this.passwordAttempt;
    }

    public void setLoginIDAttempt(String id_attempt) {
        this.loginIDAttempt = id_attempt;
    }

    public void setPasswordAttempt(String pw_attempt) {
        this.passwordAttempt = pw_attempt;
    }

    public void inputAttempt() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter login ID: ");
        String id = sc.nextLine();
        setLoginIDAttempt(id);

        System.out.println("Enter password: ");
        String pw = sc.nextLine();
        setPasswordAttempt(pw);
    }
    
    public boolean checkAttempt(Login login) throws IOException {
        final String FILE_NAME = "./data/users.txt";
        UserService userService = new UserService();
        
        @SuppressWarnings("unchecked")
        List<User> users = (List<User>) userService.load(FILE_NAME);
        List<User> AccountList = new ArrayList<>();
        for (User user : users) {
            if (user.getRole() == login.getChoice()) {
                AccountList.add(user);
            }
        }

        for (int i = 0; i < AccountList.size(); i++)
        {
            if (AccountList.get(i).getId().equals(this.loginIDAttempt))
            {
                if (AccountList.get(i).getPassword().equals(this.passwordAttempt))
                {
                    System.out.println("Login successfully.");
                    return true;
                }
            }
            else
            {
                System.out.println("Incorrect Password.");
                return false;
            }
        }
        System.out.println("LoginID not found.");
        return false;
    }
}