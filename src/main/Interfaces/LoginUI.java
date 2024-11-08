package Interfaces;

import LoginSystem.InputPrompt;
import LoginSystem.Login;
import enums.Role;
import java.io.IOException;
import java.util.HashMap;

public class LoginUI {

    private final HashMap<Role, Runnable> roleActions = new HashMap<>();

    public LoginUI() {
        // Initialize the roleActions map with each role mapped to a specific UI action
        roleActions.put(Role.ADMINISTRATOR, () -> {
            AdminUI adminUI = new AdminUI();
            adminUI.adminUI();
        });
        roleActions.put(Role.DOCTOR, () -> {
            DoctorUI doctorUI = new DoctorUI();
            doctorUI.doctorUI();
        });
        roleActions.put(Role.PATIENT, () -> {
            PatientUI patientUI = new PatientUI();
            patientUI.patientUI();
        });
        roleActions.put(Role.PHARMACIST, () -> {
            PharmacistUI pharmacistUI = new PharmacistUI();
<<<<<<< HEAD
            try {
                pharmacistUI.pharmacistUI();
            } catch (IOException ex) {
            }
=======
            //pharmacistUI.pharmacistUI();
>>>>>>> a569badf926e61ee7fdc77443261889be09959df
        });
    }

    public void loginUI() throws IOException {
        InputPrompt a = new InputPrompt();
        a.inputAttempt();
        Login login = new Login();
        boolean check = login.checkAttempt(a);
        while (!check) {
            a.inputAttempt();
            check = login.checkAttempt(a);
        }

        // Retrieve the role and execute the associated action
        Role role = login.getChoice();
        Runnable action = roleActions.get(role);

        if (action != null) {
            action.run(); // Run the UI action associated with the role
        } else {
            System.out.println("No action defined for role: " + role);
        }
    }
}
