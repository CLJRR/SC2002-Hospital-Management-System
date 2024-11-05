public package LoginSystem;

import UserSystem.User;
import UserSystem.UserService;
import enums.*;
import data.users.txt;

import interfaces.Format;
import interfaces.Load;
import interfaces.Save;
import interfaces.Write;
import interfaces.toObject;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class UserLogin {
    private String loginIDAttempt;
    private String passwordAttempt;

    public String getLoginIDAttempt() {
        return this.loginIDAttempt;

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