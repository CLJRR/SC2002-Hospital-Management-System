package UserSystem;

import enums.*;
import java.util.*;

/**
 * This class provides functionality for updating hospital staff details.
 * It allows modifications to staff attributes such as ID, name, gender, age,
 * and role.
 */

public class HospitalStaffUpdater {
    private final Map<String, User> staffs;

    /**
     * Constructs a HospitalStaffUpdater instance, initialising it with a map of
     * staff members.
     * 
     * @param staffs A map containing staff members, keyed by their unique IDs.
     */

    public HospitalStaffUpdater(Map<String, User> staffs) {
        this.staffs = staffs;
    }

    /**
     * Updates the details of a specific staff member based on user input.
     * Provides an interactive menu to modify the staff's attributes, including ID,
     * name, gender, age, and role. Ensures unique ID when updating the staff ID.
     * 
     * @param staffId The unique ID of the staff member to be updated.
     */

    public void updateStaff(String staffId) {
        Scanner sc = new Scanner(System.in);
        User staff = staffs.get(staffId);

        if (staff != null) {
            boolean exit = false;
            while (!exit) {
                // Display update options
                System.out.println("1) Update ID");
                System.out.println("2) Update Name");
                System.out.println("3) Update Gender");
                System.out.println("4) Update Age");
                System.out.println("5) Update Role");
                System.out.println("6) Quit");
                System.out.print("Select Option: ");

                // Validate menu option input
                while (!sc.hasNextInt()) { // Check if input is an integer
                    System.out.println("Option not valid. Please try again:");
                    sc.next(); // Clear the invalid input
                }

                int option = sc.nextInt();
                sc.nextLine(); // Consumes Newline

                switch (option) {
                    case 1 -> {
                        System.out.println("Enter new ID: ");
                        String newId = sc.nextLine().trim();

                        // Ensure the new ID is unique
                        while (staffs.containsKey(newId)) {
                            System.out.println("ID already exists. Please try again.");
                            System.out.print("Enter new ID: ");
                            newId = sc.nextLine().trim();
                        }

                        // Update the ID in the map and the User object
                        staffs.remove(staffId); // Remove the old entry
                        staff.setId(newId); // Update the ID in the User object
                        staffs.put(newId, staff); // Add the updated entry with the new ID
                        staffId = newId; // Update the reference for further updates
                        System.out.println("ID updated successfully.");
                        break;
                    }
                    case 2 -> {
                        System.out.println("Enter new Name: ");
                        String newName = sc.nextLine();
                        staff.setName(newName);
                        System.out.println("Name updated successfully.");
                        break;
                    }
                    case 3 -> {
                        Gender newGender = Gender.OTHER;
                        boolean validGender = false;

                        // Prompt for a valid gender
                        while (!validGender) {
                            try {
                                System.out.println("Enter new Gender: ");
                                newGender = Gender.valueOf(sc.nextLine().trim().toUpperCase());
                                validGender = true;
                            } catch (IllegalArgumentException e) {
                                System.err.println("Gender not valid. Please try again.");
                            }
                        }
                        staff.setGender(newGender);
                        System.out.println("Gender updated successfully.");
                        break;
                    }
                    case 4 -> {
                        Integer age = 0;
                        boolean validAge = false;

                        // Prompt for a valid age
                        while (!validAge) {
                            try {
                                System.out.println("Enter new Age: ");
                                age = Integer.valueOf(sc.nextLine());
                                validAge = true;
                            } catch (NumberFormatException e) {
                                System.err.println("Age not valid. Please try again.");
                            }
                        }
                        staff.setAge(age);
                        System.out.println("Age updated successfully.");
                        break;
                    }
                    case 5 -> {
                        Role newRole = Role.DOCTOR;
                        boolean validRole = false;

                        // Prompt for a valid role
                        while (!validRole) {
                            try {
                                System.out.println("Enter new Role: ");
                                newRole = Role.valueOf(sc.nextLine().trim().toUpperCase());
                                if (newRole == Role.DOCTOR || newRole == Role.PHARMACIST) {
                                    validRole = true;
                                }
                            } catch (Exception e) {
                                System.err.println("Role not valid. Please try again. ");
                            }
                        }
                        staff.setRole(newRole);
                        System.out.println("Role updated successfully.");
                        break;
                    }
                    case 6 -> {
                        exit = true; // Exit the menu
                        break;
                    }
                    default -> {
                        System.out.println("Invalid option. Please choose again.");
                        break;
                    }
                }
            }
        }
    }
}
