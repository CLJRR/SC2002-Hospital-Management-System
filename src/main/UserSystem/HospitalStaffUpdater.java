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
                System.out.println("1) Update Name");
                System.out.println("2) Update Gender");
                System.out.println("3) Update Age");
                System.out.println("4) Quit");
                System.out.print("Select Option: ");

                while (!sc.hasNextInt()) { // Check if input is an integer
                    System.out.println("Option not valid. Please try again:");
                    sc.next(); // Clear the invalid input
                }

                int option = sc.nextInt();
                sc.nextLine(); // Consumes Newline

                switch (option) {
                    case 1 -> {
                        System.out.println("Enter new Name: ");
                        String newName = sc.nextLine();
                        staff.setName(newName);
                        break;
                    }
                    case 2 -> {
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
                    case 3 -> {
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
                    case 4 -> {
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
