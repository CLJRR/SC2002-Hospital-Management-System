package UserSystem;

import java.util.*;

public class GetUser {
    private Map<String, User> users;
    private UserLoader userLoader;

    public GetUser() {
        this.users = new HashMap<>();
        this.userLoader = new UserLoader(this.users);
        
        userLoader.loadInitialUsers();        
    }

    public User getUser(String UserId) {
        User user = users.get(UserId);
        return user;
    }
}
