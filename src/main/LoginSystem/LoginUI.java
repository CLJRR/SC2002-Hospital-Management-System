package LoginSystem;

import enums.Role;
import java.io.IOException;

public class LoginUI {
public static void main(String[] args) throws IOException {
    InputPrompt a = new InputPrompt();
    a.inputAttempt();
    Login login = new Login();
    boolean check = login.checkAttempt(a);
    System.out.println(check);
    //scanner
    Role role = login.getChoice();
    switch(role){
        case ADMINISTRATOR -> {
            break;
        }
        case PHARMACIST -> {
            break;
        }
        case DOCTOR -> {
            break;
        }
        case PATIENT -> {
            break;
        }
        }
    }
}