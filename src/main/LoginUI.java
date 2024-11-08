
import LoginSystem.InputPrompt;
import LoginSystem.Login;
import enums.Role;
import java.io.IOException;

public class LoginUI {

    public static void main(String[] args) throws IOException {
        InputPrompt a = new InputPrompt();
        a.inputAttempt();
        Login login = new Login();
        boolean check = login.checkAttempt(a);
        while (check == false) {
            a.inputAttempt();
            check = login.checkAttempt(a);
        }

        Role role = login.getChoice();
        switch (role) {
            case ADMINISTRATOR -> {
                AdminUI.main(args);
                break;
            }
            case PHARMACIST -> {
                PharmacistUI.main(args);
                break;
            }
            case DOCTOR -> {
                DoctorUI.main(args);
                break;
            }
            case PATIENT -> {
                PatientUI.main(args);
                break;
            }
        }
    }
}
