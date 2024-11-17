package UserSystem;

import enums.*;
import java.util.*;

/**
 * This class provides functionality to filter hospital staff based on specific
 * criteria such as role, gender, and age. The filtering option allows the user
 * to view subsets of staff matching the desired conditions.
 */

public class HospitalStaffFilter {
    private final Map<String, User> staffs;

    /**
     * Constructs a HospitalStaffFilter instance, initialising it with a map of
     * staff members.
     * 
     * @param staffs A map containing staff members, keyed by their unique IDs.
     */

    public HospitalStaffFilter(Map<String, User> staffs) {
        this.staffs = staffs;
    }

    /**
     * Provides an interactive menu for filtering staff members based on their role,
     * gender, or age. The filtered results are displayed to the user.
     */

    public void filterStaff() {
        Scanner sc = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            // Display filter options
            System.out.println("Filter By: ");
            System.out.println("1) Role");
            System.out.println("2) Gender");
            System.out.println("3) Age");
            System.out.println("4) Exit");
            System.out.println("Select Option: ");

            // Validate option input
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

                    // Prompt user for a valid role
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

                    // Display staff members with the selected role
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

                    // Prompt user for a valid gender
                    while (!validGender) {
                        try {
                            System.out.println("Enter Gender: ");
                            gender = Gender.valueOf(sc.next().trim().toUpperCase());
                            validGender = true;
                        } catch (Exception e) {
                            System.err.println("Gender not valid. Please try again.");
                        }
                    }

                    // Display staff members with the selected gender
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

                    // Prompt user for a valid age
                    while (!validAge) {
                        try {
                            System.out.println("Enter Age: ");
                            age = Integer.valueOf(sc.nextLine());
                            validAge = true;
                        } catch (NumberFormatException e) {
                            System.err.println("Age not valid. Please try again.");
                        }
                    }

                    // Display staff members with the selected age
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
                    exit = true; // Exit the filter menu
                    break;
                }
                default -> {
                    System.out.println("Invalid option. Please choose again.");
                }
            }
        }
    }
}
