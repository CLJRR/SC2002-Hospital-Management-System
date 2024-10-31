package entity;

public class Staff extends User {

    public static enum Role {
        ADMINISTRATOR,
        PHARMACIST,
        DOCTOR
    }
    private String name;
    private String gender;
    private Integer age;
    private Role role;

    public Staff(String id, String name, String gender, Integer age, Role role) {
        super(id);
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.role = role;
    }

    public Staff(String id, String name, String gender, Integer age, Role role, String password) {
        super(id, password);
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.role = role;
    }

    // Getters and setters for the Doctor fields
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Staff [Id=" + getId() + ", name=" + name + ", gender=" + gender + ", age=" + age + ", role=" + role + ", Password=" + getPassword() + "]";
    }

}
