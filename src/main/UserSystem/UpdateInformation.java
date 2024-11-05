package UserSystem;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class UpdateInformation {

    public void updateInformation(List<User> AccountList, String UserId) throws IOException {
        for (int i = 0; i < AccountList.size(); i++) {
            if (AccountList.get(i).getId().equals(UserId)) {
                Scanner sc = new Scanner(System.in);
                int option = 0;
                int valid = 0;
                while (valid == 0) {
                    System.out.println("Select option: ");
                    System.out.println("[1] Update PhoneNo. ");
                    System.out.println("[2] Update email ");
                    option = sc.nextInt();
                    @SuppressWarnings("unused")
                    String temp = sc.nextLine();
                    if (option == 1 || option == 2) {
                        valid = 1;
                        break;
                    }
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
                        AccountList.get(i).setPhoneNumber(newPhoneNo);
                        System.out.println("New Phone Number set to " + AccountList.get(i).getPhoneNumber());
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
                        AccountList.get(i).setEmail(newEmail);
                        System.out.println("New Email set to " + AccountList.get(i).getEmail());
                    }
                }
            }
        }
    }
}
