package service;

import entity.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DoctorService {

    static String fileName = "./data/doctors.txt";

    // Function to load all doctors from a text file
    public static List<Doctor> loadDoctorsFromFile() {
        List<Doctor> doctors = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 5) {
                    String id = data[0];
                    String name = data[1];
                    String gender = data[2];
                    Integer age = Integer.parseInt(data[3]);
                    Doctor doctor = new Doctor(id, name, gender, age);
                    doctors.add(doctor);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return doctors;
    }

    //get Doctor by ID
    public static Doctor getDoctorById(String id) {
        List<Doctor> doctors = loadDoctorsFromFile();

        for (Doctor doctor : doctors) {
            if (doctor.getId().equals(id)) {
                return doctor;  // Doctor found, return the object
            }
        }

        System.out.println("Doctor with ID: " + id + " not found.");
        return null;  // Return null if doctor not found
    }

    // Function to save doctor information to a text file
    public static void saveDoctorToFile(Doctor doctor) {
        List<Doctor> doctors = loadDoctorsFromFile();

        // Check for duplicates by ID
        for (Doctor existingDoctor : doctors) {
            if (existingDoctor.getId().equals(doctor.getId())) {
                System.out.println("Doctor with ID: " + doctor.getId() + " already exists. Cannot add duplicate.");
                return;
            }
        }

        // If no duplicate, save the doctor
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(doctor.getId() + "," + doctor.getName() + "," + doctor.getGender() + "," + doctor.getAge() + "," + doctor.getPassword());
            writer.newLine();
            System.out.println("Doctor record saved: " + doctor);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public static void deleteDoctorById(String id) {
        List<Doctor> doctors = loadDoctorsFromFile();
        boolean doctorFound = false;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Doctor doctor : doctors) {
                if (!doctor.getId().equals(id)) {
                    writer.write(doctor.getId() + "," + doctor.getName() + "," + doctor.getGender() + "," + doctor.getAge() + "," + doctor.getPassword());
                    writer.newLine();
                } else {
                    doctorFound = true;
                }
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }

        if (doctorFound) {
            System.out.println("Doctor with ID: " + id + " has been deleted.");
        } else {
            System.out.println("Doctor with ID: " + id + " not found.");
        }
    }

    // Function to change the password of a doctor by ID
    public static void changeDoctorPassword(String id, String newPassword) {
        List<Doctor> doctors = loadDoctorsFromFile();
        boolean doctorFound = false;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Doctor doctor : doctors) {
                if (doctor.getId().equals(id)) {
                    doctor.setPassword(newPassword);
                    doctorFound = true;
                }
                writer.write(doctor.getId() + "," + doctor.getName() + "," + doctor.getGender() + "," + doctor.getAge() + "," + doctor.getPassword());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }

        if (doctorFound) {
            System.out.println("Password updated successfully for Doctor ID: " + id);
        } else {
            System.out.println("Doctor with ID: " + id + " not found.");
        }
    }
}
