package UserSystem;

import enums.*;
import java.util.*;

/**
 * The {@code HospitalStaffFilter} class provides functionality to filter hospital staff 
 * based on specific criteria, such as role, gender, or age.
 */
public class HospitalStaffFilter {

    private Map<String, User> staffs;

    /**
     * Constructs a {@code HospitalStaffFilter} object.
     *
     * @param staffs a map of staff IDs to {@link User} objects.
     */
    public HospitalStaffFilter(Map<String, User> staffs) {
        this.staffs = staffs;
    }

    /**
     * Filters the hospital staff based on user-selected criteria.
     * The user can filter by role, gender, or age, and view the matching staff members.
     */
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

            while (!sc.hasNextInt()) { // Validate input as an integer
                System.out.println("Option not valid. Please try again:");
                sc.next(); // Clear invalid input
            }

            int option = sc.nextInt();
            sc.nextLine(); // Consume newline
            switch (option) {
                case 1 -> {
                    // Filter by role
                    Role role = Role.DOCTOR; // Default role
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
                    // Display staff with the selected role
                    for (User user : staffs.values()) {
                        if (user.getRole() == role) {
                            System.out.println("ID: " + user.getId() + ", Name: " + user.getName() + ", Gender: "
                                    + user.getGender() + ", Age: " + user.getAge() + ", Role: " + user.getRole());
                        }
                    }
                    System.out.println();
                }
                case 2 -> {
                    // Filter by gender
                    Gender gender = Gender.FEMALE; // Default gender
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
                    // Display staff with the selected gender
                    for (User user : staffs.values()) {
                        if (user.getGender() == gender) {
                            System.out.println("ID: " + user.getId() + ", Name: " + user.getName() + ", Gender: "
                                    + user.getGender() + ", Age: " + user.getAge() + ", Role: " + user.getRole());
                        }
                    }
                    System.out.println();
                }
                case 3 -> {
                    // Filter by age
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
                    // Display staff with the selected age
                    for (User user : staffs.values()) {
                        if (Objects.equals(user.getAge(), age)) {
                            System.out.println("ID: " + user.getId() + ", Name: " + user.getName() + ", Gender: "
                                    + user.getGender() + ", Age: " + user.getAge() + ", Role: " + user.getRole());
                        }
                    }
                    System.out.println();
                }
                case 4 -> {
                    // Exit the filter menu
                    exit = true;
                }
                default -> {
                    System.out.println("Invalid option. Please choose again.");
                }
            }
        }
    }
}
