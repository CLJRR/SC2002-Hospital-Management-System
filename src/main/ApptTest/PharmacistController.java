package ApptTest;

public class PharmacistController {
    private AppointmentService appointmentService;

    public PharmacistController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    public String viewAppointmentOutcome(String appointmentId) {
        return appointmentService.viewAppointmentOutcome(appointmentId);
    }
}
