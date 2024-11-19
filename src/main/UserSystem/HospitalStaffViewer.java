package UserSystem;

import enums.*;
import java.util.*;

/**
 * The {@code HospitalStaffViewer} class provides functionality to display
 * information about hospital staff members. This class specifically displays
 * all staff with roles such as "DOCTOR", "PHARMACIST", "ADMINISTRATOR" and
 * "RECEPTIONIST".
 */
public class HospitalStaffViewer {

    private Map<String, User> staffs;

    /**
     * Constructs a {@code HospitalStaffViewer} object with the specified staff
     * records.
     *
     * @param staffs a map of staff IDs to {@link User} objects representing the
     *               hospital staff members.
     */
    public HospitalStaffViewer(Map<String, User> staffs) {
        this.staffs = staffs;
    }

    /**
     * Displays the details of current hospital staff members.
     * <p>
     * This method filters and shows only staff members with roles other than
     * "PATIENT".
     * The details displayed for each staff member include:
     * <ul>
     * <li>Staff ID</li>
     * <li>Name</li>
     * <li>Gender</li>
     * <li>Age</li>
     * <li>Role</li>
     * </ul>
     * The output is formatted and printed to the console.
     */
    public void viewStaff() {
        System.out.println("Current Staff:");
        for (User user : staffs.values()) {
            if (user.getRole() != Role.PATIENT) {
                System.out.println("ID: " + user.getId() + ", Name: " + user.getName() +
                        ", Gender: " + user.getGender() + ", Age: " + user.getAge() +
                        ", Role: " + user.getRole());
            }
        }
        System.out.println(); // Adds a new line after the last staff entry
    }
}
