package UserSystem;

import enums.*;
import java.util.*;

public class HospitalStaffViewer {
    private Map<String, User> staffs;

    public HospitalStaffViewer(Map<String, User> staffs) {
        this.staffs = staffs;
    }

    public void viewStaff() {
        System.out.println("Current Staff:");
        for (User user : staffs.values() ) {
            if (user.getRole() == Role.DOCTOR || user.getRole() == Role.PHARMACIST) {
                System.out.println("ID: " + user.getId() + ", Name: " + user.getName() + ", Gender: " + user.getGender() + ", Age: " + user.getAge() + ", Role: " + user.getRole());
            }
        }
        System.out.println(); // Adds new line after last staff
    }
}
