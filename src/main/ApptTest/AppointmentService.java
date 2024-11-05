package ApptTest;

import enums.Availability;
import enums.Flag;
import java.time.LocalDate;
import java.util.*;


public class AppointmentService {
    private Map<String, Appointment> appointments = new HashMap<>();
    private int appointmentCounter = 1;

    public Appointment scheduleAppointment(String patientId, String doctorId, LocalDate date, String timeSlot) {
        String appointmentId = "APPT" + appointmentCounter++;
        Appointment appointment = new Appointment(appointmentId, patientId, doctorId, date, timeSlot, Availability.AVAILABLE, Flag.PENDING);
        appointments.put(appointmentId, appointment);
        return appointment;
    }

    public Appointment viewAppointment(String appointmentId) {
        return appointments.get(appointmentId);
    }

    public boolean rescheduleAppointment(String appointmentId, LocalDate newDate, String newTimeSlot) {
        Appointment appointment = appointments.get(appointmentId);
        if (appointment != null && appointment.getAvailability() == Availability.BOOKED) {
            appointment.setDate(newDate);
            appointment.setTimeSlot(newTimeSlot);
            return true;
        }
        return false;
    }

    public boolean cancelAppointment(String appointmentId) {
        Appointment appointment = appointments.get(appointmentId);
        if (appointment != null && appointment.getAvailability() == Availability.BOOKED) {
            appointment.setAvailability(Availability.CANCELLED);
            appointment.setFlag(Flag.PENDING);
            return true;
        }
        return false;
    }

    public boolean updateAppointmentOutcome(String appointmentId, String outcome, String prescribedMeds) {
        Appointment appointment = appointments.get(appointmentId);
        if (appointment != null && appointment.getAvailability() == Availability.BOOKED) {
            appointment.setOutcome(outcome);
            appointment.setPrescribedMeds(prescribedMeds);
            appointment.setFlag(Flag.COMPLETED);
            appointment.setAvailability(Availability.BOOKED);
            return true;
        }
        return false;
    }

    public String viewAppointmentOutcome(String appointmentId) {
        Appointment appointment = appointments.get(appointmentId);
        return (appointment != null) ? appointment.getOutcome() : "No outcome recorded.";
    }

    public List<Appointment> listAppointments() {
        return new ArrayList<>(appointments.values());
    }
}
