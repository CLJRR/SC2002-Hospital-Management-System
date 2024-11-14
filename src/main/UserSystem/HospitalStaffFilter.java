package UserSystem;

import enums.*;
import java.util.*;

public class HospitalStaffFilter {
    private Map<String, User> staffs;

    public HospitalStaffFilter(Map<String, User> staffs) {
        this.staffs = staffs;
    }

    public void filterStaff() {
        Scanner sc = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("Filter By: ");
            System.out.println("1) Role");
            System.out.println("2) Gender");
            System.out.println("3) Age");
            System.out.println("4) Exit");
            System.out.println("Select Option: ");
            while (!sc.hasNextInt()) { // Check if input is an integer
                System.out.println("Option not valid. Please try again:");
                sc.next(); // Clear the invalid input
            }

            int option = sc.nextInt();
            sc.nextLine(); // Consumes Newline
            switch (option) {
                case 1 -> {
                    Role role = Role.DOCTOR;
                    boolean validRole = false;
                    while (!validRole) {
                        try {
                            System.out.println("Enter Role: ");
                            role = Role.valueOf(sc.next().trim().toUpperCase());
                            if (role == Role.DOCTOR || role == Role.PHARMACIST) {
                                validRole = true;
                            }
                        } catch (Exception e) {
                            System.err.println("Role not valid. Please try again.");
                        }
                    }
                    for (User user : staffs.values()) {
                        if (user.getRole() == role) {
                            System.out.println("ID: " + user.getId() + ", Name: " + user.getName() + ", Gender: "
                                    + user.getGender() + ", Age: " + user.getAge() + ", Role: " + user.getRole());
                        }
                    }
                    System.out.println();
                    break;
                }
                case 2 -> {
                    Gender gender = Gender.FEMALE;
                    boolean validGender = false;
                    while (!validGender) {
                        try {
                            System.out.println("Enter Gender: ");
                            gender = Gender.valueOf(sc.next().trim().toUpperCase());
                            validGender = true;
                        } catch (Exception e) {
                            System.err.println("Gender not valid. Please try again.");
                        }
                    }
                    for (User user : staffs.values()) {
                        if (user.getGender() == gender) {
                            System.out.println("ID: " + user.getId() + ", Name: " + user.getName() + ", Gender: "
                                    + user.getGender() + ", Age: " + user.getAge() + ", Role: " + user.getRole());
                        }
                    }
                    System.out.println();
                    break;
                }
                case 3 -> {
                    Integer age = 0;
                    boolean validAge = false;
                    while (!validAge) {
                        try {
                            System.out.println("Enter Age: ");
                            age = Integer.valueOf(sc.nextLine());
                            validAge = true;
                        } catch (NumberFormatException e) {
                            System.err.println("Age not valid. Please try again.");
                        }
                    }
                    for (User user : staffs.values()) {
                        if (Objects.equals(user.getAge(), age)) {
                            System.out.println("ID: " + user.getId() + ", Name: " + user.getName() + ", Gender: "
                                    + user.getGender() + ", Age: " + user.getAge() + ", Role: " + user.getRole());
                        }
                    }
                    System.out.println();
                    break;
                }
                case 4 -> {
                    exit = true;
                    break;
                }
                default -> {
                    System.out.println("Invalid option. Please choose again.");
                }
            }
        }
    }
}
