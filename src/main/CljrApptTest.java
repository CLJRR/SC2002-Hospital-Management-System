
import RequestSystem.RequestController;
import SessionManager.Session;

public class CljrApptTest {

    public static void main(String[] args) throws Exception {
        // AppointmentController appointmentController = new AppointmentController();
        Session.setLoginID("P001");
        // appointmentController.updateAppointmentFlag();
        RequestController x = new RequestController();
        x.updateRequestFlag();
        // x.createNewRequest();
        // x.updateRequestFlag();

    }
}
