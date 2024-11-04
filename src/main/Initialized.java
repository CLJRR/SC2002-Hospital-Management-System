
import entity.*;
import enums.Gender;
import enums.Role;
import java.io.IOException;
import java.util.List;
import service.*;

public class Initialized {

    public static void main(String[] args) throws IOException {
        // try {
        final String FILE_NAME = "./data/users.txt";
        UserService userService = new UserService();
        // Cast the result of load() to List<Staff>

        @SuppressWarnings("unchecked")
        List<User> users = (List<User>) userService.load(FILE_NAME);
        User x = new User("D001", "John Smith", Gender.MALE, 45, Role.DOCTOR);
        users.add(x);
        for (User user : users) {
            System.out.println(user.getId());
        }
        userService.save(FILE_NAME, users);
    }
}
