package UserSystem;

import enums.*;
import java.time.LocalDate;

/**
 * This class represents a User in the system. Users can be either staff or
 * patients.
 * It contains personal and role-specific details such as ID, name, age, gender,
 * contact information, bloody type, and role.
 */

public class User {

    private String id;
    private String password;
    private String name;
    private Integer age;
    private LocalDate dateOfBirth;
    private Gender gender;
    private String phoneNumber;
    private String email;
    private String bloodType;
    private Role role;

    /**
     * Constructor for creating a patient with default password.
     *
     * @param id          The unique ID of the user.
     * @param name        The name of the user.
     * @param dateOfBirth The date of birth of the user.
     * @param gender      The gender of the user.
     * @param phoneNumber The user's phone number.
     * @param email       The user's email address.
     * @param bloodType   The user's blood type.
     * @param role        The role of the user (e.g., PATIENT).
     */

    /**
     * Constructor for creating a patient with a specified password.
     *
     * @param id          The unique ID of the user.
     * @param name        The name of the user.
     * @param password    The password for the user's account.
     * @param dateOfBirth The date of birth of the user.
     * @param gender      The gender of the user.
     * @param phoneNumber The user's phone number.
     * @param email       The user's email address.
     * @param bloodType   The user's blood type.
     * @param role        The role of the user (e.g., PATIENT).
     */

    public User(String id, String name, LocalDate dateOfBirth, Gender gender, String phoneNumber,
            String email, String bloodType, Role role) {
        this.id = id;
        this.name = name;
        this.password = "password";
        this.gender = gender;
        this.age = null;
        this.role = role;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.bloodType = bloodType;
    }

    /**
     * Constructor for creating a patient with a specified password.
     *
     * @param id          The unique ID of the user.
     * @param name        The name of the user.
     * @param password    The password for the user's account.
     * @param dateOfBirth The date of birth of the user.
     * @param gender      The gender of the user.
     * @param phoneNumber The user's phone number.
     * @param email       The user's email address.
     * @param bloodType   The user's blood type.
     * @param role        The role of the user (e.g., PATIENT).
     */

    public User(String id, String name, String password, LocalDate dateOfBirth, Gender gender,
            String phoneNumber, String email, String bloodType, Role role) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.gender = gender;
        this.age = null;
        this.role = role;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.bloodType = bloodType;
    }

    /**
     * Constructor for creating a staff member with a default password.
     *
     * @param id     The unique ID of the user.
     * @param name   The name of the user.
     * @param gender The gender of the user.
     * @param age    The age of the user.
     * @param role   The role of the user (e.g., DOCTOR, PHARMACIST).
     */

    public User(String id, String name, Gender gender, Integer age, Role role) {
        this.id = id;
        this.name = name;
        this.password = "password";
        ;
        this.gender = gender;
        this.age = age;
        this.role = role;
        this.dateOfBirth = null;
        this.phoneNumber = null;
        this.email = null;
        this.bloodType = null;
    }

    /**
     * Constructor for creating a staff member with a specified password.
     *
     * @param id       The unique ID of the user.
     * @param name     The name of the user.
     * @param gender   The gender of the user.
     * @param age      The age of the user.
     * @param role     The role of the user (e.g., DOCTOR, PHARMACIST).
     * @param password The password for the user's account.
     */

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

    /**
     * Constructor for creating a user with complete details.
     *
     * @param id          The unique ID of the user.
     * @param name        The name of the user.
     * @param password    The password for the user's account.
     * @param age         The age of the user.
     * @param dateOfBirth The date of birth of the user.
     * @param gender      The gender of the user.
     * @param phoneNumber The user's phone number.
     * @param email       The user's email address.
     * @param bloodType   The user's blood type.
     * @param role        The role of the user (e.g., PATIENT, DOCTOR, PHARMACIST).
     */

    public User(String id, String name, String password, Integer age, LocalDate dateOfBirth, Gender gender,
            String phoneNumber, String email, String bloodType, Role role) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.age = age;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.bloodType = bloodType;
        this.role = role;
    }

    // Getters and Setters

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

    /**
     * Verifies if the entered password matches the user's password.
     *
     * @param enteredPassword The password to be verified.
     * @return true if the password matches, false otherwise.
     */

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
