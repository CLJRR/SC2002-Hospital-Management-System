package LoginSystem;
import enums.*;
import java.util.Scanner;
//comment
public class Login {
    private Role choice;

    public void login() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to HMS");
        System.out.println("Select option: ");
        System.out.println("[1] Administrator");
        System.out.println("[2] Pharmacist");
        System.out.println("[3] Doctor");
        System.out.println("[4] Patient");
        int option;
        int valid = 0;
        option = 0;
        while (valid != 1) {
            System.out.println("Enter an option: ");
            option = sc.nextInt();
            if (option == 1 || option == 2 || option == 3 || option == 4) {
                valid = 1;
                break;
            }
            System.out.println("Error! Please enter either [1] or [2] or [3] or [4]");
        }

        switch (option) {
            case 1 -> {
                setChoice(Role.ADMINISTRATOR);
                System.out.println("Welcome Administrator");
            }
            case 2 -> {
                setChoice(Role.PHARMACIST);
                System.out.println("Welcome Pharmacist");
            }
            case 3 -> {
                setChoice(Role.DOCTOR);
                System.out.println("Welcome Doctor");
            }
            case 4 -> {
                setChoice(Role.PATIENT);
                System.out.println("Welcome Patient");
            }
        }
    }

    public Role getChoice() {
        return this.choice;
    }

    public void setChoice(Role choice) {
        this.choice = choice;
    }
}
