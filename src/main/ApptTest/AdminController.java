package ApptTest;

import java.time.LocalDate;


public class AdminController {
    private AppointmentService appointmentService;
    private PatientAppointmentScheduler patientAppointmentScheduler;

    public AdminController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    public boolean updateScheduledAppointment(String appointmentId, LocalDate newDate, String newTimeSlot) {
        return patientAppointmentScheduler.rescheduleAppointment(appointmentId, newDate, newTimeSlot);
    }

    public void listAllAppointments() {
        for (Appointment appointment : appointmentService.listAppointments()) {
            System.out.println(appointment);
        }
    }
}
