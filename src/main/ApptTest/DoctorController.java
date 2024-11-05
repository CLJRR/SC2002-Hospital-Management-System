package ApptTest;

public class DoctorController {
    private AppointmentService appointmentService;

    public DoctorController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    public boolean updateAppointmentOutcome(String appointmentId, String outcome, String prescribedMeds) {
        return appointmentService.updateAppointmentOutcome(appointmentId, outcome, prescribedMeds);
    }

    public Appointment viewAppointment(String appointmentId) {
        return appointmentService.viewAppointment(appointmentId);
    }
}
