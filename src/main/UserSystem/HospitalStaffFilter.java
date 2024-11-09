package UserSystem;

import enums.*;
import java.util.*;

public class HospitalStaffFilter {
    private Map<String, User> staffs;

    public HospitalStaffFilter(Map<String, User> staffs) {
        this.staffs = staffs;
    }

    public void filterStaff() {
        Scanner sc = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("Filter By: ");
            System.out.println("1) Role");
            System.out.println("2) Gender");
            System.out.println("3) Age");
            System.out.println("4) Exit");
            System.out.println("Select Option: ");
            int option = sc.nextInt();
            switch (option) {
                case 1 -> {
                    System.out.println("Enter Role: ");
                    Role role = Role.valueOf(sc.next().trim().toUpperCase());
                    for (User user : staffs.values()) {
                        if (user.getRole() == role) {
                            System.out.println("ID: " + user.getId() + ", Name: " + user.getName() + ", Gender: " + user.getGender() + ", Age: " + user.getAge() + ", Role: " + user.getRole());
                        }
                    }
                    System.out.println();
                    break;
                }
                case 2 -> {
                    System.out.println("Enter Gender: ");
                    Gender gender = Gender.valueOf(sc.next().trim().toUpperCase());
                    for (User user : staffs.values()) {
                        if (user.getGender() == gender) {
                            System.out.println("ID: " + user.getId() + ", Name: " + user.getName() + ", Gender: " + user.getGender() + ", Age: " + user.getAge() + ", Role: " + user.getRole());
                        }
                    }
                    System.out.println();
                    break;
                }
                case 3 -> {
                    System.out.println("Enter Age: ");
                    Integer age = sc.nextInt();
                    for (User user : staffs.values()) {
                        if (Objects.equals(user.getAge(), age)) {
                            System.out.println("ID: " + user.getId() + ", Name: " + user.getName() + ", Gender: " + user.getGender() + ", Age: " + user.getAge() + ", Role: " + user.getRole());
                        }
                    }
                    System.out.println();
                    break;
                }
                case 4 -> {
                    exit = true;
                    break;
                }
                default -> {
                    System.out.println("Invalid option. Please choose again.");
                }
            }
        }
    }
}
