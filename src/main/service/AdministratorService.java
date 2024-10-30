package service;

import entity.Administrator;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AdministratorService {

    static String fileName = "./data/administrators.txt";

    // Load all administrators from a text file
    public static List<Administrator> loadAll() {
        List<Administrator> administrators = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 5) {
                    String id = data[0];
                    String name = data[1];
                    String gender = data[2];
                    Integer age = Integer.parseInt(data[3]);
                    Administrator administrator = new Administrator(id, name, gender, age);
                    administrators.add(administrator);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return administrators;
    }

    public static Administrator getById(String id) {
        List<Administrator> administrators = loadAll();

        for (Administrator administrator : administrators) {
            if (administrator.getId().equals(id)) {
                return administrator;  // Doctor found, return the object
            }
        }

        System.out.println("ID: " + id + " not found.");
        return null; // Return null if doctor not found

    }

    public void save(Administrator administrator) {

        List<Administrator> administrators = loadAll();

        // Check for duplicates by name
        for (Administrator existingAdministrator : administrators) {
            if (existingAdministrator.getId().equals(administrator.getId())) {
                System.out.println("ID: " + administrator.getId() + " already exists. Cannot add duplicate.");
                return;
            }
        }

        // If no duplicate, save the administrator
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(administrator.getId() + "," + administrator.getName() + "," + administrator.getGender() + "," + administrator.getAge() + "," + administrator.getPassword());
            writer.newLine();
            System.out.println("Administrator record saved: " + administrator);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public static void deleteById(String id) {
        List<Administrator> administrators = loadAll();
        boolean doctorFound = false;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Administrator administrator : administrators) {
                if (!administrator.getId().equals(id)) {
                    writer.write(administrator.getId() + "," + administrator.getName() + "," + administrator.getGender() + "," + administrator.getAge() + "," + administrator.getPassword());
                    writer.newLine();
                } else {
                    doctorFound = true;
                }
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }

        if (doctorFound) {
            System.out.println("ID: " + id + " has been deleted.");
        } else {
            System.out.println("ID: " + id + " not found.");
        }
    }

    // Function to change the password of a administrator by ID
    public static void changePassword(String id, String newPassword) {
        List<Administrator> administrators = loadAll();
        boolean administratorFound = false;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Administrator administrator : administrators) {
                if (administrator.getId().equals(id)) {
                    administrator.setPassword(newPassword);
                    administratorFound = true;
                }
                writer.write(administrator.getId() + "," + administrator.getName() + "," + administrator.getGender() + "," + administrator.getAge() + "," + administrator.getPassword());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }

        if (administratorFound) {
            System.out.println("Password updated successfully for ID: " + id);
        } else {
            System.out.println("ID: " + id + " not found.");
        }
    }
}
