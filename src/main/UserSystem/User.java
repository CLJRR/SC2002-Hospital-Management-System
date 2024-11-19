package UserSystem;

import enums.*;
import java.time.LocalDate;

/**
 * The {@code User} class represents a user in the system. It can be used to represent
 * both patients and staff members, providing various attributes such as name, ID,
 * gender, role, and contact information.
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
     * Constructs a {@code User} object representing a patient with default password.
     *
     * @param id          the ID of the patient.
     * @param name        the name of the patient.
     * @param dateOfBirth the date of birth of the patient.
     * @param gender      the gender of the patient.
     * @param phoneNumber the phone number of the patient.
     * @param email       the email of the patient.
     * @param bloodType   the blood type of the patient.
     * @param role        the role of the patient (e.g., PATIENT).
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
     * Constructs a {@code User} object representing a patient with a specified password.
     *
     * @param id          the ID of the patient.
     * @param name        the name of the patient.
     * @param password    the password of the patient.
     * @param dateOfBirth the date of birth of the patient.
     * @param gender      the gender of the patient.
     * @param phoneNumber the phone number of the patient.
     * @param email       the email of the patient.
     * @param bloodType   the blood type of the patient.
     * @param role        the role of the patient (e.g., PATIENT).
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
     * Constructs a {@code User} object representing a staff member with default password.
     *
     * @param id     the ID of the staff.
     * @param name   the name of the staff.
     * @param gender the gender of the staff.
     * @param age    the age of the staff.
     * @param role   the role of the staff (e.g., DOCTOR, PHARMACIST).
     */
    public User(String id, String name, Gender gender, Integer age, Role role) {
        this.id = id;
        this.name = name;
        this.password = "password";
        this.gender = gender;
        this.age = age;
        this.role = role;
        this.dateOfBirth = null;
        this.phoneNumber = null;
        this.email = null;
        this.bloodType = null;
    }

    /**
     * Constructs a {@code User} object representing a staff member with a specified password.
     *
     * @param id       the ID of the staff.
     * @param name     the name of the staff.
     * @param gender   the gender of the staff.
     * @param age      the age of the staff.
     * @param role     the role of the staff (e.g., DOCTOR, PHARMACIST).
     * @param password the password of the staff.
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
     * Constructs a {@code User} object with full attributes.
     *
     * @param id          the ID of the user.
     * @param name        the name of the user.
     * @param password    the password of the user.
     * @param age         the age of the user.
     * @param dateOfBirth the date of birth of the user.
     * @param gender      the gender of the user.
     * @param phoneNumber the phone number of the user.
     * @param email       the email of the user.
     * @param bloodType   the blood type of the user.
     * @param role        the role of the user (e.g., PATIENT, DOCTOR, PHARMACIST).
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
