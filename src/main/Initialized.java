
import entity.*;
import enums.Gender;
import enums.Role;
import java.io.IOException;
import java.util.List;
import service.*;

public class Initialized {

    public static void main(String[] args) throws IOException {
        // try {
        PatientService patientService = new PatientService();
        // Cast the result of load() to List<Staff>

        @SuppressWarnings("unchecked")
        List<Patient> patients = (List<Patient>) patientService.load("./data/patients.txt");
        Patient x = new Patient("D001", "John Smith", Gender.MALE, 45, Role.DOCTOR);
        patients.add(x);
        for (Patient patient : patients) {
            System.out.println(patient);
        }
        patientService.save("./data/patients.txt", patients);
    }
}
