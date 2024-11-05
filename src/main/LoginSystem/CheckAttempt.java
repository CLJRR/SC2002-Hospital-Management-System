package LoginSystem;

import UserSystem.User;
import UserSystem.UserService;
import enums.*;
import data.users.txt;

import interfaces.Format;
import interfaces.Load;
import interfaces.Save;
import interfaces.Write;
import interfaces.toObject;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import javax.management.relation.Role;


public class CheckAttempt {
    // returns 1 if successful login, 0 if login failed
    public int checkAttempt(Role role) throws IOException {
        final String FILE_NAME = "./data/users.txt";
        UserService userService = new UserService();
        
        @SuppressWarnings("unchecked")
        List<User> users = (List<User>) userService.load(FILE_NAME);
        List<User> AccountList = new ArrayList<>();
        for (User user : users) {
            if (user.getRole() == role) {
                AccountList.add(user);
            }
        }

        for (int i = 0; i < AccountList.size(); i++)
        {
            if (AccountList.get(i).getLoginID().equals(this.loginIDAttempt))
            {
                if (AccountList.get(i).getPassword().equals(this.passwordAttempt))
                {
                    System.out.println("Login successfully.");
                    return 1;
                }
            }
            else
            {
                System.out.println("Incorrect Password.");
                return 0;
            }
        }
        System.out.println("LoginID not found.");
        return 0;
    }
}
