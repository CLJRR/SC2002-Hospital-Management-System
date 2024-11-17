package UserSystem;

import enums.*;
import java.util.*;

/**
 * This class provides functionality for viewing hospital staff details.
 * It displays information about staff members, including their ID, name,
 * gender, age, and role, filtering for relevant roles such as DOCTOR and
 * PHAMACIST.
 */

public class HospitalStaffViewer {
    private final Map<String, User> staffs;

    /**
     * Constructs a HospitalStaffViewer instance, initialising it with a map of
     * staff members.
     * 
     * @param staffs A map containing staff members, keyed by their unique IDs.
     */

    public HospitalStaffViewer(Map<String, User> staffs) {
        this.staffs = staffs;
    }

    /**
     * Displays the details of all staff members currently in the system.
     * Only staff with roles of DOCTOR or PHARMACIST are shown. The details include
     * ID, name, gender, age, and role.
     */

    public void viewStaff() {
        System.out.println("Current Staff:");
        for (User user : staffs.values()) {
            if (user.getRole() == Role.DOCTOR || user.getRole() == Role.PHARMACIST) {
                System.out.println("ID: " + user.getId() + ", Name: " + user.getName() + ", Gender: " + user.getGender()
                        + ", Age: " + user.getAge() + ", Role: " + user.getRole());
            }
        }
        System.out.println(); // Adds a new line after listing all the staff
    }
}
