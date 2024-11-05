package UserSystem;

import enums.*;
import UserSystem.UserService;

import FIleManager.*;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UpdateInformation {
    public void updateInformation(String UserId) {
        final String FILE_NAME = "./data/users.txt";
        UserService userService = new UserService();
        
        @SuppressWarnings("unchecked")
        List<User> users = (List<User>) userService.load(FILE_NAME);
        List<User> AccountList = new ArrayList<>();

        for (user : users) {
            if (user.getRole == Role.PATIENT) {
                if (user.getId == UserId) {
                    Scanner sc = new Scanner(System.in);
                    int option;
                    int valid = 0;
                    while (valid == 0) {
                        System.out.println("Select option: ");
                        System.out.println("[1] Update PhoneNo. ");
                        System.out.println("[2] Update email ");
                        option = sc.nextInt();
                        if (option == 1 || option == 2) {
                            valid = 1;
                            break;
                        }
                    }
                    switch(option)
                    {
                        case 1:
                            System.out.println("Enter new PhoneNo. : ");
                            String newPhoneNo = sc.nextLine();
                            
                    }
                }
            }
        }
    }
}
