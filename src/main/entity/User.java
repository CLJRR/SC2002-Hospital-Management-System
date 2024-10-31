package entity;

public class User {

    private String id;
    private String password;
    private String name;

    public User(String id, String name) {
        this.id = id;
        this.name = name;
        this.password = "password";
    }

    public User(String id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean verifyPassword(String enteredPassword) {
        return this.password.equals(enteredPassword);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", password=" + password + "]";
    }
}
