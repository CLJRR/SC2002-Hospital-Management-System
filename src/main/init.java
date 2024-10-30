
import entity.*;
import service.*;

public class init {
    public static void main(String[] args) {
        DoctorService docservice = new DoctorService();
        AdministratorService adminervice = new AdministratorService();
        PharmacistService pharmservice = new PharmacistService();
        docservice.save(new Doctor("D001", "John Smith", "Male", 45));
        docservice.save(new Doctor("D002", "Emily Clarke", "Female", 38));
        adminervice.save(new Administrator("A001", "Sarah Lee", "Female", 40));
        pharmservice.save(new Pharmacist("P001", "Mark Lee", "Male", 29));
    }
}
