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
                System.out.print("Select Option: ");

                while (!sc.hasNextInt()) { // Check if input is an integer
                    System.out.println("Option not valid. Please try again:");
                    sc.next(); // Clear the invalid input
                }

                int option = sc.nextInt();
                sc.nextLine(); // Consumes Newline

                switch (option) {
                    case 1 -> {
                        System.out.println("Enter new ID: ");
                        String newId = sc.nextLine().toUpperCase();
                        while (staffs.containsKey(newId)) {
                            System.out.println("Staff already exists. Please try again.");
                            System.out.println("Enter new ID: ");
                            newId = sc.nextLine();
                        }
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
                        Gender newGender = Gender.OTHER;
                        boolean validGender = false;
                        while (!validGender) {
                            try {
                                System.out.println("Enter new Gender: ");
                                newGender = Gender.valueOf(sc.nextLine().trim().toUpperCase());
                                validGender = true;
                            } catch (Exception e) {
                                System.err.println("Gender not valid. Please try again.");
                            }
                        }
                        staff.setGender(newGender);
                        break;
                    }
                    case 4 -> {
                        Integer age = 0;
                        boolean validAge = false;
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
                        break;
                    }
                    case 5 -> {
                        Role newRole = Role.DOCTOR;
                        boolean validRole = false;
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
