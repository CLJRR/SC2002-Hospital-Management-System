package UserSystem;

import FileManager.*;
import enums.Gender;
import enums.Role;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The `UserService` class provides functionality for loading, saving, and
 * formatting user data. It implements interfaces for handling file operations
 * and object serialization/deserialization. Users are stored in a text file
 * and managed as `User` objects.
 */

public class UserService implements Load, Format, Save, Write, toObject {

    private static final String FILENAME = "./data/users.txt";

    /**
     * Loads user data from a text file, parses each line into `User` objects,
     * and returns a list of these users.
     *
     * @return A list of `User` objects loaded from the file.
     * @throws IOException If an error occurs while reading the file.
     */

    @Override
    public List<User> load() throws IOException {
        List<User> data = new ArrayList<>();
        try (Scanner scanner = new Scanner(new FileInputStream(FILENAME))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                User user = (User) toObject(line);
                data.add(user);
            }
        }
        return data;
    }

    /**
     * Saves a list of users to the text file. Converts each `User` object into
     * a formatted string and writes it to the file.
     *
     * @param list A list of objects to be saved, which must be instances of `User`.
     * @throws IOException If an error occurs during the saving process or if the
     *                     list contains invalid objects.
     */

    @Override
    public void save(List<?> list) throws IOException {
        List<String> data = new ArrayList<>();
        for (Object obj : list) {
            if (obj instanceof User) {
                String formattedString = format(obj); // Use format method
                data.add(formattedString);
            } else {
                throw new IOException("List contains incorrect objects.");
            }
        }
        write(data);

    }

    /**
     * Writes a list of formatted strings to the text file.
     *
     * @param data A list of strings to be written to the file.
     * @throws IOException If an error occurs while writing to the file.
     */

    @Override
    public void write(List<String> data) throws IOException {
        try (PrintWriter out = new PrintWriter(new FileWriter(FILENAME))) {
            for (String line : data) {
                out.println(line);
            }
        }
    }

    /**
     * Formats a `User` object into a comma-separated string suitable for storage.
     *
     * @param object The `User` object to be formatted.
     * @return A string representation of the `User` object.
     * @throws IOException If the object is not an instance of `User`.
     */

    @Override
    public String format(Object object) throws IOException {
        if (object instanceof User user) {
            return String.join(",",
                    user.getId(),
                    user.getName(),
                    user.getPassword(),
                    String.valueOf(user.getAge()),
                    user.getDateOfBirth() == null ? "null" : user.getDateOfBirth().toString(),
                    user.getGender().name(),
                    user.getPhoneNumber() == null ? "null" : user.getPhoneNumber(),
                    user.getEmail() == null ? "null" : user.getEmail(),
                    user.getBloodType() == null ? "null" : user.getBloodType(),
                    user.getRole().name());

        } else {
            throw new IOException("Invalid object type");
        }
    }

    /**
     * Converts a comma-separated string into a `User` object.
     *
     * @param string The string to be parsed.
     * @return A `User` object created from the string data.
     * @throws IOException If the string is improperly formatted or contains invalid
     *                     data.
     */

    @Override
    public Object toObject(String string) throws IOException {
        String[] parts = string.split(",");

        // Validate the format by checking the number of fields
        if (parts.length != 10) {
            throw new IOException("Invalid format.");
        }
        try {
            String id = parts[0];
            String name = parts[1];
            String password = parts[2];
            // Integer age = Integer.parseInt(parts[3]);
            Integer age = "null".equals(parts[3]) ? null : Integer.parseInt(parts[3]);

            // Handle nullable dateOfBirth field
            LocalDate dateOfBirth = "null".equals(parts[4]) ? null : LocalDate.parse(parts[4]);
            // Parse enum values with error handling
            Gender gender = Gender.valueOf(parts[5].toUpperCase());
            String phoneNumber = "null".equals(parts[6]) ? null : parts[6];
            String email = "null".equals(parts[7]) ? null : parts[7];
            String bloodType = "null".equals(parts[8]) ? null : parts[8];
            Role role = Role.valueOf(parts[9].trim().toUpperCase());

            return new User(id, name, password, age, dateOfBirth, gender, phoneNumber, email, bloodType, role);

        } catch (NumberFormatException e) {
            throw new IOException("Invalid data format for age: " + parts[3], e);
        } catch (IllegalArgumentException e) {
            throw new IOException("Invalid enum value in Gender or Role field.", e);
        }
    }
}
