package AdminSytem;


import enums.Gender;
import enums.Role;

public class Staff {

    private String id;
    private String password;
    private String name;
    private Integer age;
    private Gender gender;
    private Role role;

    // Constructor with all fields
    public Staff(String id, String name, String password, Integer age, Gender gender, Role role) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.age = age;
        this.gender = gender;
        this.role = role;
    }

    // Constructor with only required fields (password set to a default value)
    public Staff(String id, String name, Integer age, Gender gender, Role role) {
        this.id = id;
        this.name = name;
        this.password = "defaultPassword";
        this.age = age;
        this.gender = gender;
        this.role = role;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    // Method to verify password
    public boolean verifyPassword(String enteredPassword) {
        return this.password.equals(enteredPassword);
    }

    // toString method for easy display of staff details
    @Override
    public String toString() {
        return "Staff{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                ", role=" + role +
                '}';
    }

    public class Test {
    public static void main(String[] args) {
        Staff staff = new Staff("S001", "Alice", "password123", 30, Gender.FEMALE, Role.ADMINISTRATOR);
        System.out.println(staff);  // This will use the toString method in Staff
    }
    }
}
