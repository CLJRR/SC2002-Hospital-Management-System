package service;

import entity.Staff;
import enums.*;
import interfaces.*;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StaffService implements Load, Format, Save, Write, toObject {

    @Override
    public List<?> load(String fileName) throws IOException {
        List<Staff> data = new ArrayList<>();
        try (Scanner scanner = new Scanner(new FileInputStream(fileName))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Staff staff = (Staff) toObject(line);
                data.add(staff);
            }
        }
        return data;
    }



    @Override
    public void save(String filename, List<?> list) throws IOException {
        List<String> data = new ArrayList<>();
        for (Object obj : list) {
            if (obj instanceof Staff) {
                String formattedString = format(obj); // Use format method
                data.add(formattedString);
            } else {
                throw new IOException("List contains incorrect objects.");
            }
        }
        write(filename, data);

    }

    @Override
    public void write(String fileName, List<String> data) throws IOException {
        try (PrintWriter out = new PrintWriter(new FileWriter(fileName))) {
            for (String line : data) {
                out.println(line);
            }
        }
    }
    @Override
    public String format(Object object) throws IOException {
        if (object instanceof Staff staff) {
            return staff.getId() + ","
                    + staff.getName() + ","
                    + staff.getGender() + ","
                    + staff.getAge() + ","
                    + staff.getRole() + ","
                    + staff.getPassword();
        } else {
            throw new IOException("Invalid object type");
        }
    }
    @Override
    public Object toObject(String string) throws IOException {
        String[] parts = string.split(",");

        // Validate the format by checking the number of fields
        if (parts.length != 6) {
            throw new IOException("Invalid format");
        }

        try {
            // Parse each part and create a Staff object
            String id = parts[0].trim();
            String name = parts[1].trim();
            Gender gender = Gender.valueOf(parts[2].trim().toUpperCase());
            Integer age = Integer.parseInt(parts[3].trim());
            Role role = Role.valueOf(parts[4].trim().toUpperCase());
            String password = parts[5].trim();
            return new Staff(id, name, gender, age, role, password);

        } catch (NumberFormatException e) {
            throw new IOException("Invalid data format for age: " + parts[3], e);
        }
    }
}
