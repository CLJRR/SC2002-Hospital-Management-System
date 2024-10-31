
import entity.*;
import enums.Gender;
import enums.Role;
import java.time.LocalDate;
import service.*;

public class init {

    public static void main(String[] args) {
        StaffService staffService = new StaffService();
        staffService.deleteAll();
        staffService.save(new Staff("D001", "John Smith", "Male", 45, Role.DOCTOR));
        staffService.save(new Staff("D002", "Emily Clarke", "Female", 38, Role.DOCTOR));
        staffService.save(new Staff("A001", "Sarah Lee", "Female", 40, Role.ADMINISTRATOR));
        staffService.save(new Staff("P001", "Mark Lee", "Male", 29, Role.PHARMACIST));
        PatientService patientService = new PatientService();
        patientService.deleteAll();
        patientService.save(new Patient("P1001", "Alice Brown", 42, LocalDate.parse("1980-05-14"), Gender.FEMALE, "96810518", "alice.brown@example.com", "A+"));
        patientService.save(new Patient("P1002", "Bob Stone", 47, LocalDate.parse("1975-11-22"), Gender.MALE, "88386868", "bob.stone@example.com", "B+"));
        patientService.save(new Patient("P1003", "Charlie White", 32, LocalDate.parse("1990-07-08"), Gender.MALE, "94315338", "charlie.white@example.com", "O-"));

    }
}
