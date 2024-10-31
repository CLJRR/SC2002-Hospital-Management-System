package entity;

import enums.Gender;
import java.time.LocalDate;

public class Patient extends User {

    private Integer age;
    private LocalDate dateOfBirth;
    private Gender gender;
    private String phoneNumber;
    private String email;
    private String bloodType;
    
	public Patient(String id, String name, Integer age, LocalDate dateOfBirth, Gender gender, String phoneNumber,
			String email, String bloodType) {
		super(id, name);
		this.age = age;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.bloodType = bloodType;
	}
	public Patient(String id, String name, String password, Integer age, LocalDate dateOfBirth, Gender gender,
			String phoneNumber, String email, String bloodType) {
		super(id, name, password);
		this.age = age;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.bloodType = bloodType;
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
    
}
