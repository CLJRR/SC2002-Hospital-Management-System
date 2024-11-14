package UserSystem;

import enums.*;
import java.util.*;

public class AdminHospitalStaff {
    public void adminHospitalStaff() {
        HospitalStaffController hospitalStaffController = new HospitalStaffController();
        Scanner sc = new Scanner(System.in);

        hospitalStaffController.viewStaff();

        boolean exit = false;

        while (!exit) {
            System.out.println("\nChoose an option: ");
            System.out.println("1) View Staff");
            System.out.println("2) Add Staff");
            System.out.println("3) Remove Staff");
            System.out.println("4) Update Staff");
            System.out.println("5) Filter Staff");
            System.out.println("6) Save and exit");

            while (!sc.hasNextInt()) { // Check if input is an integer
                System.out.println("Option not valid. Please try again:");
                sc.next(); // Clear the invalid input
            }

            int choice = sc.nextInt();
            sc.nextLine(); // Consumes Newline

            switch (choice) {
                case 1:
                    hospitalStaffController.viewStaff();
                    break;
                case 2:
                    System.out.print("Enter Staff ID to add: ");
                    String staffId = sc.nextLine();
                    System.out.print("Enter Staff Name to add: ");
                    String name = sc.nextLine();
                    boolean validGender = false;
                    Gender gender = Gender.OTHER;
                    while (validGender == false) {
                        try {
                            System.out.print("Enter Staff Gender to add: ");
                            gender = Gender.valueOf(sc.nextLine().trim().toUpperCase());
                            validGender = true;
                        } catch (Exception e) {
                            System.err.println("Gender not valid. Please try again.");
                        }
                    }
                    boolean validAge = false;
                    Integer age = 0;
                    while (validAge == false) {
                        try {
                            System.out.print("Enter Staff Age to add: ");
                            age = Integer.valueOf(sc.nextLine());
                            validAge = true;
                        } catch (NumberFormatException e) {
                            System.err.println("Age not valid. Please try again.");
                        }
                    }
                    boolean validRole = false;
                    Role role = Role.DOCTOR;
                    while (validRole == false) {
                        try {
                            System.out.print("Enter Staff Role to add: ");
                            role = Role.valueOf(sc.nextLine().trim().toUpperCase());
                            validRole = true;
                        } catch (Exception e) {
                            System.err.println("Role not valid. Please try again.");
                        }
                    }
                    User staff = new User(staffId, name, gender, age, role);
                    hospitalStaffController.addStaff(staff);
                    break;
                case 3:
                case 4:
                    Map<String, User> staffs = hospitalStaffController.getStaffs();

                    if (staffs.isEmpty()) {
                        System.out.println("No staffs available in system.");
                        break;
                    }
                    String action = (choice == 3) ? "remove" : "update";

                    System.out.print("Enter Staff ID to " + action + ": ");
                    staffId = sc.nextLine();

                    if (staffs.get(staffId) == null) {
                        System.out.println("Invalid selection. Please try again.");
                    } else {
                        staff = staffs.get(staffId);
                        if (choice == 3) {
                            hospitalStaffController.removeStaff(staff);
                        } else {
                            hospitalStaffController.updateStaff(staff);
                        }
                    }
                    break;
                case 5:
                    hospitalStaffController.filterStaff();
                    break;
                case 6:
                    hospitalStaffController.saveUsers();
                    System.out.println("Staffs saved to file. Exiting program.");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        }
    }
}
