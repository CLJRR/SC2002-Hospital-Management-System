
import MedicalRecordSystem.*;
import UserSystem.*;
import enums.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Cljr {

    public static void main(String[] args) throws IOException {
        // try {
        final String FILENAME = "./data/users.txt";
        UserService userService = new UserService();
        // Cast the result of load() to List<Staff>
        @SuppressWarnings("unchecked")
        // List<User> users = (List<User>) userService.load(FILENAME);
        List<User> users = new ArrayList<User>();
        users.add(new User("P1002", "Bob Stone", LocalDate.parse("1975-11-22"), Gender.MALE, "123456789", "bob.stone@example.com", "B+", Role.PATIENT));
        users.add(new User("P1003", "Charlie White", LocalDate.parse("1990-07-08"), Gender.MALE, "123456789", "charlie.white@example.com", "O-", Role.PATIENT));
        users.add(new User("D001", "John Smith", Gender.MALE, 45, Role.DOCTOR));
        users.add(new User("D002", "Emily Clarke", Gender.FEMALE, 38, Role.DOCTOR));
// Pharmacist and Administrator with age, gender, and role
        users.add(new User("P001", "Mark Lee", Gender.MALE, 29, Role.PHARMACIST));
        users.add(new User("A001", "Sarah Lee", Gender.FEMALE, 40, Role.ADMINISTRATOR));
        userService.save(FILENAME, users);


        final String FILENAMEE = "./data/medicalRecords.txt";
        MedicalRecordService medicalrecordService = new MedicalRecordService();

        @SuppressWarnings("unchecked")
        List<MedicalRecord> medicalRecords = (List<MedicalRecord>) userService.load(FILENAMEE);
        // List<MedicalRecord> medicalRecords = new ArrayList<MedicalRecord>();

        Prescription prescription = new Prescription("Ibuprofen", 30, "200mg twice daily");
        // Creating a MedicalRecord object
        MedicalRecord record1 = new MedicalRecord("A001", "P1001", "D001", LocalDate.of(2024, 10, 10), "General Checkup", "fever", prescription);
        MedicalRecord record2 = new MedicalRecord("A002", "P1001", "D001", LocalDate.of(2024, 10, 10), "General Checkup", "flu", prescription);
        // Saving the MedicalRecord
        medicalRecords.add(record1);
        medicalRecords.add(record2);
        medicalrecordService.save(FILENAMEE, medicalRecords);

    }
}
