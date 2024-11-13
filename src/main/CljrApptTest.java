
import AppointmentSystem.*;
import SessionManager.Session;

public class CljrApptTest {

    public static void main(String[] args) throws Exception {
        AppointmentController x = new AppointmentController();
        Session.setLoginID("P001");
        x.viewAllRecords();
    }
}
