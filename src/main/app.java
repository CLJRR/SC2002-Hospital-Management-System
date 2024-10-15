
import entity.*;
import service.*;

public class app {

    public static void main(String[] args) {

        DoctorFileHandler regdoc = new DoctorFileHandler();
        regdoc.saveDoctorToFile(new Doctor("D01", "John Smith", "Male", 45));
        // regdoc.deleteDoctorById("D03");
        // regdoc.deleteDoctorById("D02");

    }
}
