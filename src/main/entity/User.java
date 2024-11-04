package entity;

import enums.*;
import java.time.LocalDate;

public class User {

    private Integer age;
    private LocalDate dateOfBirth;
    private Gender gender;
    private String phoneNumber;
    private String email;
    private String bloodType;
    private Role role;
    private String id;
    private String password;
    private String name;

    public User(String id, String name, Integer age, LocalDate dateOfBirth, Gender gender, String phoneNumber,
            String email, String bloodType, Role role) {
        this.id = id;
        this.name = name;
        this.password = "password";
        this.gender = gender;
        this.age = age;
        this.role = role;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.bloodType = bloodType;
    }

    public User(String id, String name, String password, Integer age, LocalDate dateOfBirth, Gender gender,
            String phoneNumber, String email, String bloodType, Role role) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.gender = gender;
        this.age = age;
        this.role = role;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.bloodType = bloodType;
    }

    public User(String id, String name, Gender gender, Integer age, Role role) {
        this.id = id;
        this.name = name;
        this.password = "password";;
        this.gender = gender;
        this.age = age;
        this.role = role;
        this.dateOfBirth = null;
        this.phoneNumber = null;
        this.email = null;
        this.bloodType = null;
    }

    public User(String id, String name, Gender gender, Integer age, Role role, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.gender = gender;
        this.age = age;
        this.role = role;
        this.dateOfBirth = null;
        this.phoneNumber = null;
        this.email = null;
        this.bloodType = null;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
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
}
