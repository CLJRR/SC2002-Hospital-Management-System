package UserSystem;

import enums.*;
import java.io.IOException;
import java.util.*;

/**
 * Class for managing hospital staff operations such as viewing, adding,
 * removing, updating, filtering and saving staff records by admin. The
 * {@code AdminHospitalStaff} class provides functionality for managing hospital
 * staff It interacts with the {@link HospitalStaffController} to view, add,
 * remove, update, filter, and save staff information.
 */
public class AdminHospitalStaff {

    /**
     * Starts the admin panel for hospital staff management. Provides options to
     * view, add, remove, update, filter and save staff records.
     */
    public void adminHospitalStaff() {
        HospitalStaffController hospitalStaffController = new HospitalStaffController();

        UserService userService = new UserService();
        List<User> userList = new ArrayList<>();
        try {
            userList = userService.load();
        } catch (IOException e) {
            System.err.println("Users not loaded successfully.");
        }

        Scanner sc = new Scanner(System.in);

        // Display initial list of staff on startup
        hospitalStaffController.viewStaff();

        boolean exit = false;

        while (!exit) {
            // Display menu options
            System.out.println("\nChoose an option: ");
            System.out.println("1) View Staff");
            System.out.println("2) Add Staff");
            System.out.println("3) Remove Staff");
            System.out.println("4) Update Staff");
            System.out.println("5) Filter Staff");
            System.out.println("6) Save and exit");

            // Validate menu option input
            while (!sc.hasNextInt()) { // Check if input is an integer
                System.out.println("Option not valid. Please try again:");
                sc.next(); // Clear the invalid input
            }

            int choice = sc.nextInt();
            sc.nextLine(); // Consumes Newline

            // Handle menu option selection
            switch (choice) {
                case 1:
                    // View all staff members
                    hospitalStaffController.viewStaff();
                    break;

                case 2:
                    // Add a new staff member

                    System.out.print("Enter Staff Name to add: ");
                    String name = sc.nextLine();

                    // Get valid gender input
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

                    // Get valid age input
                    boolean validAge = false;
                    Integer age = 0;
                    while (validAge == false) {
                        try {
                            System.out.print("Enter Staff Age to add (between 18 and 100): ");
                            age = Integer.valueOf(sc.nextLine());
                            if (age >= 18 && age <= 100) {
                                validAge = true;
                            } else {
                                System.out.println("Age must be between 18 and 100.");
                            }
                        } catch (NumberFormatException e) {
                            System.err.println("Age not valid. Please try again.");
                        }
                    }

                    // Get valid role input
                    boolean validRole = false;
                    Role role = null;
                    while (validRole == false) {
                        try {
                            System.out.print("Enter Staff Role to add: ");
                            role = Role.valueOf(sc.nextLine().trim().toUpperCase());
                            validRole = true;
                        } catch (Exception e) {
                            System.err.println("Role not valid. Please try again.");
                        }
                    }
                    GetUser getUser = new GetUser();
                    String staffId = "";
                    if (null != role) {
                        switch (role) {
                            case DOCTOR: {
                                List<User> doctors = getUser.getAllDoctors();
                                List<String> doctorids = new ArrayList<>();
                                for (User doctor : doctors) {
                                    doctorids.add(doctor.getId());
                                }
                                int num = doctors.size() + 1;
                                staffId = String.format("D%03d", num);
                                while (doctorids.contains(staffId)) {
                                    num++;
                                    staffId = String.format("D%03d", num);
                                }
                                break;
                            }
                            case PHARMACIST: {
                                List<User> pharmacists = getUser.getAllPharmacists();
                                List<String> pharmacistids = new ArrayList<>();
                                for (User doctor : pharmacists) {
                                    pharmacistids.add(doctor.getId());
                                }
                                int num = pharmacists.size() + 1;
                                staffId = String.format("P%03d", num);

                                while (pharmacistids.contains(staffId)) {
                                    num++;
                                    staffId = String.format("P%03d", num);
                                }
                                break;
                            }
                            case RECEPTIONIST: {
                                List<User> receptionists = getUser.getAllReceptionist();
                                List<String> pharmacistids = new ArrayList<>();
                                for (User doctor : receptionists) {
                                    pharmacistids.add(doctor.getId());
                                }
                                int num = receptionists.size() + 1;
                                staffId = String.format("R%03d", num);
                                while (pharmacistids.contains(staffId)) {
                                    num++;
                                    staffId = String.format("R%03d", num);
                                }
                                break;
                            }
                            case ADMINISTRATOR: {
                                List<User> receptionists = getUser.getAllAdministrators();
                                List<String> pharmacistids = new ArrayList<>();
                                for (User doctor : receptionists) {
                                    pharmacistids.add(doctor.getId());
                                }
                                int num = receptionists.size() + 1;
                                staffId = String.format("A%03d", num);
                                while (pharmacistids.contains(staffId)) {
                                    num++;
                                    staffId = String.format("A%03d", num);
                                }
                                break;
                            }
                            default:
                                break;
                        }

                    }

                    // Create and add staff member
                    User staff = new User(staffId, name, gender, age, role);
                    hospitalStaffController.addStaff(staff);
                    break;

                case 3:
                case 4:
                    // Retrieve staff for removal or update
                    Map<String, User> staffs = hospitalStaffController.getStaffs();

                    if (staffs.isEmpty()) {
                        System.out.println("No staffs available in system.");
                        break;
                    }
                    String action = (choice == 3) ? "remove" : "update";

                    System.out.print("Enter Staff ID to " + action + ": ");
                    staffId = sc.nextLine().toUpperCase();

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
                    // Filter staff based on criteria
                    hospitalStaffController.filterStaff();
                    break;

                case 6:
                    // Save staff data and exit the program
                    hospitalStaffController.saveUsers();
                    System.out.println("Staffs saved to file. Exiting program.");
                    exit = true;
                    break;

                default:
                    // Handle invalid menu option
                    System.out.println("Invalid option. Please choose again.");
            }
        }
    }
}
