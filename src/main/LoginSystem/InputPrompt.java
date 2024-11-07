package LoginSystem;

import java.util.Scanner;

public class InputPrompt {
    private String loginIDAttempt;
    private String passwordAttempt;

    public String getLoginIDAttempt() {
        return this.loginIDAttempt;
    }

    public String getPasswordAttempt() {
        return this.passwordAttempt;
    }

    public void setLoginIDAttempt(String id_attempt) {
        this.loginIDAttempt = id_attempt;
    }

    public void setPasswordAttempt(String pw_attempt) {
        this.passwordAttempt = pw_attempt;
    }

    public void inputAttempt() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter login ID: ");
        String id = sc.nextLine();
        setLoginIDAttempt(id);

        System.out.println("Enter password: ");
        String pw = sc.nextLine();
        setPasswordAttempt(pw);
    }
}