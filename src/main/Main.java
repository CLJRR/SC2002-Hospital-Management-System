
import LoginSystem.*;
import UserSystem.*;
import enums.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        // try {
        final String USER_FILE_NAME = "./data/users.txt";
        UserService userService = new UserService();
        // Cast the result of load() to List<Staff>
        @SuppressWarnings("unchecked")
        List<User> users = (List<User>) userService.load(USER_FILE_NAME);
        users.add(new User("P1002", "Bob Stone", LocalDate.parse("1975-11-22"), Gender.MALE, "123456789", "bob.stone@example.com", "B+", Role.PATIENT));
        users.add(new User("P1003", "Charlie White", LocalDate.parse("1990-07-08"), Gender.MALE, "123456789", "charlie.white@example.com", "O-", Role.PATIENT));
        users.add(new User("D001", "John Smith", Gender.MALE, 45, Role.DOCTOR));
        users.add(new User("D002", "Emily Clarke", Gender.FEMALE, 38, Role.DOCTOR));
// Pharmacist and Administrator with age, gender, and role
        users.add(new User("P001", "Mark Lee", Gender.MALE, 29, Role.PHARMACIST));
        users.add(new User("A001", "Sarah Lee", Gender.FEMALE, 40, Role.ADMINISTRATOR));

        // System.out.println("Please enter UserId");

        for (User user : users) {
            System.out.println(user.getId());
        }
        // userService.save(FILE_NAME, users);
        UserLogin a = new UserLogin();
        a.inputAttempt();
        Login test = new Login();
        boolean check = test.checkAttempt(a);
        System.out.println(check);
    }
}
