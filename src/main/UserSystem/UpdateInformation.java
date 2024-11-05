package UserSystem;

import enums.*;

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

        for (User user : users) {
            if (user.getRole() == Role.PATIENT) {
                if (user.getId() == UserId) {
                    Scanner sc = new Scanner(System.in);
                    int option = 0;
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
                            System.out.println("Enter new PhoneNo.: ");
                            String newPhoneNo = sc.nextLine();
                            ValidatePhoneNo vPhoneNo = new ValidatePhoneNo();
                            boolean v = false;
                            while (v == false)
                            {  
                                System.out.println("Enter valid PhoneNo.: ");
                                newPhoneNo = sc.nextLine();
                                v = vPhoneNo.validatePhoneNo(newPhoneNo);
                                if (v == true)
                                {
                                    break;
                                }
                            }
                            user.setPhoneNumber(newPhoneNo);
                            System.out.println("New Phone Number set");
                            break;
                        case 2:
                            System.out.println("Enter new email: ");
                            String newEmail = sc.nextLine();
                            ValidateEmail vemail = new ValidateEmail();
                            boolean temp = false;
                            while (temp == false)
                            {
                                System.out.println("Enter valid email: ");
                                newEmail = sc.nextLine();
                                temp = vemail.validateEmail(newEmail);
                                if (temp == true)
                                {
                                    break;
                                }
                            }
                            user.setEmail(newEmail);
                            System.out.println("New Email set");
                            break;
                    }
                }
            }
        }
    }
}
