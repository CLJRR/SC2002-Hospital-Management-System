package UserSystem;

import enums.*;
import java.util.*;

public class HospitalStaffUpdater {
    private Map<String, User> staffs;

    public HospitalStaffUpdater(Map<String, User> staffs) {
        this.staffs = staffs;
    }

    public void updateStaff(String staffId) {
        Scanner sc = new Scanner(System.in);
        User staff = staffs.get(staffId);
        if (staff != null) {
            boolean exit = false;
            while (!exit) {
                System.out.println("1) Update ID");
                System.out.println("2) Update Name");
                System.out.println("3) Update Gender");
                System.out.println("4) Update Age");
                System.out.println("5) Update Role");
                System.out.println("6) Quit");
                
                int option = sc.nextInt();

                switch(option) {
                    case 1 -> {
                        System.out.println("Enter new ID: ");
                        String newId = sc.nextLine();
                        staff.setId(newId);
                        break;
                    }
                    case 2 -> {
                        System.out.println("Enter new Name: ");
                        String newName = sc.nextLine();
                        staff.setName(newName);
                        break;
                    }
                    case 3 -> {
                        System.out.println("Enter new Gender: ");
                        Gender newGender = Gender.valueOf(sc.nextLine().trim().toUpperCase());
                        staff.setGender(newGender);
                        break;
                    }
                    case 4 -> {
                        System.out.println("Enter new Age: ");
                        Integer age = sc.nextInt();
                        staff.setAge(age);
                    }
                    case 5 -> {
                        System.out.println("Enter new Role: ");
                        Role newRole = Role.valueOf(sc.nextLine().trim().toUpperCase());
                        staff.setRole(newRole);
                        break;
                    }
                    case 6 -> {
                        exit = true;
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
