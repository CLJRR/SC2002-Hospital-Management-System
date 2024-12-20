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
 * The {@code UserService} class provides functionality for managing user data, 
 * including loading from a file, saving to a file, and converting between string 
 * and object representations. It implements the {@link Load}, {@link Format}, {@link Save}, 
 * {@link Write}, and {@link toObject} interfaces.
 */
public class UserService implements Load, Format, Save, Write, toObject {

    private static final String FILENAME = "./data/users.txt";

    /**
     * Loads user data from a file and converts each line into a {@link User} object.
     *
     * @return a list of {@link User} objects loaded from the file.
     * @throws IOException if an error occurs while reading the file.
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
     * Saves a list of objects to a file by formatting each object as a string.
     *
     * @param list the list of objects to save (must contain {@link User} objects).
     * @throws IOException if an error occurs while writing to the file or if the list 
     *                     contains non-{@link User} objects.
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
     * Writes a list of strings to the specified file.
     *
     * @param data the list of strings to write.
     * @throws IOException if an error occurs while writing to the file.
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
     * Formats a {@link User} object into a string representation suitable for storage.
     *
     * @param object the {@link User} object to format.
     * @return a string representation of the {@link User} object.
     * @throws IOException if the provided object is not a {@link User}.
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
     * Converts a string representation of a user into a {@link User} object.
     *
     * @param string the string to convert.
     * @return a {@link User} object created from the string.
     * @throws IOException if the string format is invalid or contains incorrect data.
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
            Integer age = "null".equalsIgnoreCase(parts[3]) ? null : Integer.parseInt(parts[3]);

            // Handle nullable dateOfBirth field
            LocalDate dateOfBirth = "null".equalsIgnoreCase(parts[4]) ? null : LocalDate.parse(parts[4]);

            // Parse enum values with error handling
            Gender gender = Gender.valueOf(parts[5].toUpperCase());
            String phoneNumber = "null".equalsIgnoreCase(parts[6]) ? null : parts[6];
            String email = "null".equalsIgnoreCase(parts[7]) ? null : parts[7];
            String bloodType = "null".equalsIgnoreCase(parts[8]) ? null : parts[8];
            Role role = Role.valueOf(parts[9].trim().toUpperCase());

            return new User(id, name, password, age, dateOfBirth, gender, phoneNumber, email, bloodType, role);

        } catch (NumberFormatException e) {
            throw new IOException("Invalid data format for age: " + parts[3], e);
        } catch (IllegalArgumentException e) {
            throw new IOException("Invalid enum value in Gender or Role field.", e);
        }
    }
}
