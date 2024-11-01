package service;

import entity.*;
import enums.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StaffService {

    private static final String FILE_NAME = "./data/staffs.txt";

    // Function to load all staffs from a text file
    public List<Staff> loadAll() {
        List<Staff> staffs = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 6) {
                    String id = data[0];
                    String name = data[1];
                    String gender = data[2];
                    Integer age = Integer.parseInt(data[3]);
                    Role role = Role.valueOf(data[4].toUpperCase());
                    String password = data[5];
                    Staff staff = new Staff(id, name, gender, age, role, password);
                    staffs.add(staff);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return staffs;
    }

    //get Staff by ID
    public Staff getById(String id) {
        List<Staff> staffs = loadAll();
        for (Staff staff : staffs) {
            if (staff.getId().equals(id)) {
                return staff;  // Staff found, return the object
            }
        }
        System.out.println("ID: " + id + " not found.");
        return null;  // Return null if staff not found
    }

    // Function to save staff information to a text file
    public void save(Staff staff) {
        List<Staff> staffs = loadAll();
        // Check for duplicates by ID
        for (Staff existingDoctor : staffs) {
            if (existingDoctor.getId().equals(staff.getId())) {
                System.out.println("ID: " + staff.getId() + " already exists. Cannot add duplicate.");
                return;
            }
        }
        // If no duplicate, save the staff
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(format(staff));
            writer.newLine();
            System.out.println("Staff record saved: " + staff);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public void deleteById(String id) {
        List<Staff> staffs = loadAll();
        boolean found = false;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Staff staff : staffs) {
                if (!staff.getId().equals(id)) {
                    writer.write(format(staff));
                    writer.newLine();
                } else {
                    found = true;
                }
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
        if (found) {
            System.out.println("ID: " + id + " has been deleted.");
        } else {
            System.out.println("ID: " + id + " not found.");
        }
    }

    // Function to change the password of a staff by ID
    public void changePassword(String id, String password, String newPassword) {
        List<Staff> staffs = loadAll();
        boolean found = false;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Staff staff : staffs) {
                if (staff.getId().equals(id)) {
                    found = true;
                    if (staff.verifyPassword(password)) {
                        staff.setPassword(newPassword);
                        System.out.println("Password updated successfully for ID: " + id);
                    } else {
                        System.out.println("Incorrect Password enterd for " + id);
                    }
                }
                writer.write(format(staff));
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
        if (!found) {
            System.out.println("ID: " + id + " not found.");
        }
    }

    private String format(Staff staff) {
        return staff.getId() + ","
                + staff.getName() + ","
                + staff.getGender() + ","
                + staff.getAge() + ","
                + staff.getRole() + ","
                + staff.getPassword();
    }

    public void deleteAll() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, false))) {
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }

    }
}
