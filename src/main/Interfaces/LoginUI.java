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
            // try {
            AdminUI adminUI = new AdminUI();
            adminUI.adminUI();
            // } catch (IOException e) {
            //     System.out.println("Error launching Admin UI: " + e.getMessage());
            // }
        });
        roleActions.put(Role.DOCTOR, () -> {
            try {
            DoctorUI doctorUI = new DoctorUI();
            doctorUI.doctorUI();
            } catch (IOException e) {
                System.out.println("Error launching Doctor UI: " + e.getMessage());
            }
        });
        roleActions.put(Role.PATIENT, () -> {
            try {
            PatientUI patientUI = new PatientUI();
            patientUI.patientUI();
            } catch (IOException e) {
                System.out.println("Error launching Patient UI: " + e.getMessage());
            }
        });
        roleActions.put(Role.PHARMACIST, () -> {
            try {
                PharmacistUI pharmacistUI = new PharmacistUI();
                pharmacistUI.pharmacistUI();
            } catch (IOException e) {
                System.out.println("Error launching Pharmacist UI: " + e.getMessage());
            }
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
