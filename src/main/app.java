
// import static entity.Staff.Role.PHARMACIST;
import entity.*;
import java.util.ArrayList;
import java.util.List;
import service.*;

public class app {

    public static void main(String[] args) {
        // DoctorService docservice = new DoctorService();
        // AdministratorService adminervice = new AdministratorService();
        // PharmacistService pharmservice = new PharmacistService();
        // List<Doctor> doctors = new ArrayList<>();
        // doctors = docservice.loadAll();
        // for (Doctor doctor : doctors) {
        //     // String name, String gender, Integer age
        //     System.out.println(doctor.getId() + doctor.getName() + doctor.getGender() + doctor.getAge() + doctor.getPassword());

        // }
        StaffService s = new StaffService();
        List<Staff> staffs = new ArrayList<>();
        staffs = s.loadAll();
        for (Staff staff : staffs) {
            System.out.println(staff.getId() + staff.getName() + staff.getGender() + staff.getAge() + staff.getRole() + staff.getPassword());
        }
        Staff test = new Staff("test", "name", "gender", 12, Staff.Role.DOCTOR, "password");
        System.out.println(test.getId() + test.getName() + test.getGender() + test.getAge() + test.getRole() + test.getPassword());

    }
}
