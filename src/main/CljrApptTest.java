
import AppointmentSystem.*;
import SessionManager.Session;

public class CljrApptTest {

    public static void main(String[] args) throws Exception {
        AppointmentController appointmentController = new AppointmentController();
        Session.setLoginID("D001");
        appointmentController.updateAppointmentFlag();

    }
}
