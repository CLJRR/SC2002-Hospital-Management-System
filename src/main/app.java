
import entity.*;
import java.util.ArrayList;
import java.util.List;
import service.*;

public class app {

    public static void main(String[] args) {
        DoctorService docservice = new DoctorService();
        // AdministratorService adminervice = new AdministratorService();
        // PharmacistService pharmservice = new PharmacistService();
        List<Doctor> doctors = new ArrayList<>();
        doctors = docservice.loadAll();
        for (Doctor doctor : doctors) {
            // String name, String gender, Integer age
            System.out.println(doctor.getId() + doctor.getName() + doctor.getGender() + doctor.getAge() + doctor.getPassword());

        }
    }
}
