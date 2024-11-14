package Interfaces;

import LoginSystem.InputPrompt;
import LoginSystem.Login;
import UserSystem.GetUser;
import UserSystem.PasswordChanger;
import UserSystem.User;
import enums.Role;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class LoginUI {

    private final HashMap<Role, Runnable> roleActions = new HashMap<>();

    public LoginUI() {
        // Initialize the roleActions map with each role mapped to a specific UI action
        roleActions.put(Role.ADMINISTRATOR, () -> {
            try {
                AdminUI adminUI = new AdminUI();
                adminUI.adminUI();
            } catch (IOException e) {
                System.out.println("Error launching Admin UI: " + e.getMessage());
            }
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
            GetUser getUser = new GetUser();
            String userId = a.getLoginIDAttempt().toUpperCase();
            User user = getUser.getUser(userId);
            PasswordChanger passwordChanger = new PasswordChanger();
            if (user.getPassword().equals("password")) {
                passwordChanger.passwordChanger(userId);
                action.run();
            } else {
                Scanner sc = new Scanner(System.in);
                System.out.println("1) Login.");
                System.out.println("2) Change Password.");
                int option = 0;
                while (!(option == 1 || option == 2)) {
                    System.out.print("Please select option: ");
                    while (!sc.hasNextInt()) { // Check if input is an integer
                        System.out.println("Option not valid. Please try again:");
                        sc.next(); // Clear the invalid input
                    }

                    option = sc.nextInt();
                    sc.nextLine(); // Consumes Newline
                }
                if (option == 1)
                    action.run(); // Run the UI action associated with the role
                else if (option == 2) {
                    passwordChanger.passwordChanger(userId);
                }
            }
        } else {
            System.out.println("No action defined for role: " + role);
        }
    }
}
