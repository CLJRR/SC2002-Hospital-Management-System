package UserSystem;

import enums.*;
import java.util.*;

/**
 * The {@code HospitalStaffUpdater} class provides functionality to update the details 
 * of a hospital staff member, including their name, gender, and age.
 */
public class HospitalStaffUpdater {

    private Map<String, User> staffs;

    /**
     * Constructs a {@code HospitalStaffUpdater} object.
     *
     * @param staffs a map of staff IDs to {@link User} objects representing the staff members.
     */
    public HospitalStaffUpdater(Map<String, User> staffs) {
        this.staffs = staffs;
    }

    /**
     * Updates the details of a specific staff member.
     *
     * @param staffId the unique ID of the staff member to update.
     */
    public void updateStaff(String staffId) {
        Scanner sc = new Scanner(System.in);
        User staff = staffs.get(staffId);

        if (staff != null) {
            boolean exit = false;
            while (!exit) {
                System.out.println("1) Update Name");
                System.out.println("2) Update Gender");
                System.out.println("3) Update Age");
                System.out.println("4) Quit");
                System.out.print("Select Option: ");

                while (!sc.hasNextInt()) { // Validate input as an integer
                    System.out.println("Option not valid. Please try again:");
                    sc.next(); // Clear invalid input
                }

                int option = sc.nextInt();
                sc.nextLine(); // Consume newline

                switch (option) {
                    case 1 -> {
                        // Update Name
                        System.out.println("Enter new Name: ");
                        String newName = sc.nextLine();
                        staff.setName(newName);
                        System.out.println("Name updated successfully.");
                    }
                    case 2 -> {
                        // Update Gender
                        Gender newGender = Gender.OTHER; // Default gender
                        boolean validGender = false;
                        while (!validGender) {
                            try {
                                System.out.println("Enter new Gender: ");
                                newGender = Gender.valueOf(sc.nextLine().trim().toUpperCase());
                                validGender = true;
                                System.out.println("Gender updated successfully.");
                            } catch (Exception e) {
                                System.err.println("Gender not valid. Please try again.");
                            }
                        }
                        staff.setGender(newGender);
                    }
                    case 3 -> {
                        // Update Age
                        Integer age = 0;
                        boolean validAge = false;
                        while (!validAge) {
                            try {
                                System.out.println("Enter new Age: ");
                                age = Integer.valueOf(sc.nextLine());
                                validAge = true;
                                System.out.println("Age updated successfully.");
                            } catch (NumberFormatException e) {
                                System.err.println("Age not valid. Please try again.");
                            }
                        }
                        staff.setAge(age);
                    }
                    case 4 -> {
                        // Quit
                        exit = true;
                        System.out.println("Exiting update menu.");
                    }
                    default -> {
                        System.out.println("Invalid option. Please choose again.");
                    }
                }
            }
        } else {
            System.out.println("Staff member not found.");
        }
    }
}
