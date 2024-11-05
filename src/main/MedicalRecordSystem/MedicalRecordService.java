package MedicalRecordSystem;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import FIleManager.Format;
import FIleManager.Load;
import FIleManager.Save;
import FIleManager.Write;
import FIleManager.toObject;
import UserSystem.User;
import enums.Gender;
import enums.Role;

public class MedicalRecordService implements Load, Format, Save, Write, toObject {

    @Override
    public List<?> load(String fileName) throws IOException {
        List<User> data = new ArrayList<>();
        try (Scanner scanner = new Scanner(new FileInputStream(fileName))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                User medicalRecord = (User) toObject(line);
                data.add(medicalRecord);
            }
        }
        return data;
    }

    @Override
    public String format(Object object) throws IOException {
        if (object instanceof MedicalRecord medicalRecord) {
            return String.join(",",
                    medicalRecord.getId(),
                    medicalRecord.getName(),
                    medicalRecord.getPassword(),
                    String.valueOf(medicalRecord.getAge()),
                    medicalRecord.getDateOfBirth() == null ? "null" : medicalRecord.getDateOfBirth().toString(),
                    medicalRecord.getGender().name(),
                    medicalRecord.getPhoneNumber() == null ? "null" : medicalRecord.getPhoneNumber(),
                    medicalRecord.getEmail() == null ? "null" : medicalRecord.getEmail(),
                    medicalRecord.getBloodType() == null ? "null" : medicalRecord.getBloodType(),
                    medicalRecord.getRole().name()
            );

        } else {
            throw new IOException("Invalid object type");
        }
    }

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
            Integer age = Integer.parseInt(parts[3]);
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
