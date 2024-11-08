package testfile;


import FIleManager.*;
import enums.Gender;
import enums.Role;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StaffService implements Load, Format, Save, Write, toObject {

    @Override
    public List<Staff> load(String fileName) throws IOException {
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
                String formattedString = format(obj);
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
            return String.join(",",
                    staff.getId(),
                    staff.getName(),
                    staff.getPassword(),
                    String.valueOf(staff.getAge()),
                    staff.getGender().name(),
                    staff.getRole().name()
            );

        } else {
            throw new IOException("Invalid object type");
        }
    }

    @Override
    public Object toObject(String string) throws IOException {
        String[] parts = string.split(",");

        // Validate the format by checking the number of fields
        if (parts.length != 6) {
            throw new IOException("Invalid format.");
        }
        try {
            String id = parts[0];
            String name = parts[1];
            String password = parts[2];
            Integer age = Integer.parseInt(parts[3]);
            Gender gender = Gender.valueOf(parts[4].toUpperCase());
            Role role = Role.valueOf(parts[5].trim().toUpperCase());

            return new Staff(id, name, password, age, gender, role);

        } catch (NumberFormatException e) {
            throw new IOException("Invalid data format for age: " + parts[3], e);
        } catch (IllegalArgumentException e) {
            throw new IOException("Invalid enum value in Gender or Role field.", e);
        }
    }
}
