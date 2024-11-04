package entity;

import enums.*;

public class Staff extends User {

    private Gender gender;
    private Integer age;
    private Role role;

    public Staff(String id, String name, Gender gender, Integer age, Role role) {
        super(id, name);
        this.gender = gender;
        this.age = age;
        this.role = role;
    }

    public Staff(String id, String name, Gender gender, Integer age, Role role, String password) {
        super(id, name, password);
        this.gender = gender;
        this.age = age;
        this.role = role;
    }

    // Getters and setters for the Doctor fields
    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
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
        return "Staff [Id=" + getId() + ", name=" + getName() + ", gender=" + gender + ", age=" + age + ", role=" + role + ", Password=" + getPassword() + "]";
    }

}
