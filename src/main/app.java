
import entity.*;
import service.*;

public class app {

    public static void main(String[] args) {

        DoctorService docservice = new DoctorService();
        AdministratorService adminervice = new AdministratorService();
        PharmacistService pharmservice = new PharmacistService();
        docservice.saveDoctorToFile(new Doctor("D02", "John Smith", "Male", 45));
        adminervice.saveAdministratorToFile(new Administrator("D02", "John Smith", "Male", 45));
        pharmservice.savePharmacistToFile(new Pharmacist("D02", "John Smith", "Male", 45));
    }
}
