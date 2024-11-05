package ApptTest;

import java.time.LocalDate;


public class PatientController {
    private AppointmentService appointmentService;

    public PatientController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    public Appointment scheduleAppointment(String patientId, String doctorId, LocalDate date, String timeSlot) {
        return appointmentService.scheduleAppointment(patientId, doctorId, date, timeSlot);
    }

    public Appointment viewAppointment(String appointmentId) {
        return appointmentService.viewAppointment(appointmentId);
    }

    public boolean rescheduleAppointment(String appointmentId, LocalDate newDate, String newTimeSlot) {
        return appointmentService.rescheduleAppointment(appointmentId, newDate, newTimeSlot);
    }

    public boolean cancelAppointment(String appointmentId) {
        return appointmentService.cancelAppointment(appointmentId);
    }

    public String viewAppointmentOutcome(String appointmentId) {
        return appointmentService.viewAppointmentOutcome(appointmentId);
    }
}
