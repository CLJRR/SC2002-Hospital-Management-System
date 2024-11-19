package UserSystem;

import enums.*;
import java.util.*;

/**
 * The {@code HospitalStaffViewer} class provides functionality to display information 
 * about hospital staff members. It specifically displays staff with roles such as 
 * "DOCTOR" and "PHARMACIST".
 */
public class HospitalStaffViewer {

    private Map<String, User> staffs;

    /**
     * Constructs a {@code HospitalStaffViewer} object.
     *
     * @param staffs a map of staff IDs to {@link User} objects representing the staff members.
     */
    public HospitalStaffViewer(Map<String, User> staffs) {
        this.staffs = staffs;
    }

    /**
     * Displays the details of current hospital staff members. Only staff with roles 
     * "DOCTOR" or "PHARMACIST" are shown.
     */
    public void viewStaff() {
        System.out.println("Current Staff:");
        for (User user : staffs.values()) {
            if (user.getRole() == Role.DOCTOR || user.getRole() == Role.PHARMACIST) {
                System.out.println("ID: " + user.getId() + ", Name: " + user.getName() + 
                                   ", Gender: " + user.getGender() + ", Age: " + user.getAge() + 
                                   ", Role: " + user.getRole());
            }
        }
        System.out.println(); // Adds a new line after the last staff entry
    }
}
