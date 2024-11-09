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

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    hospitalStaffController.viewStaff();
                    break;
                case 2:
                    System.out.print("Enter Staff ID to add: ");
                    String staffId = sc.nextLine();
                    System.out.print("Enter Staff Name to add: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Staff Gender to add: ");
                    Gender gender = Gender.valueOf(sc.nextLine().trim().toUpperCase());
                    System.out.print("Enter Staff Age to add: ");
                    Integer age = sc.nextInt();
                    sc.nextLine(); // consumes new line
                    System.out.print("Enter Staff Role to add: ");
                    Role role = Role.valueOf(sc.nextLine().trim().toUpperCase());

                    User staff = new User(staffId, name, gender, age, role);
                    hospitalStaffController.addStaff(staff);
                    System.out.println("Staff added: " + staffId);
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
                    }
                    else {
                        staff = staffs.get(staffId);
                        if (choice == 3) {
                            hospitalStaffController.removeStaff(staff);
                        }
                        else {
                            hospitalStaffController.updateStaff(staff);
                        }
                    }
                    break;
                case 5:
                    hospitalStaffController.filterStaff();
                    break;
                case 6:
                    hospitalStaffController.saveStaff();
                    System.out.println("Staffs saved to file. Exiting program.");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        }
    }
}
