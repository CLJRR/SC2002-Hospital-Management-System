package ApptTest;

import enums.Availability;
import enums.Flag;
import java.time.LocalDate;

public class Appointment {

    private String appointmentId;
    private String patientId;
    private String doctorId;
    private LocalDate date;
    private String timeSlot;
    private Availability availability;
    private Flag flag;

    // Constructor with all attributes
    public Appointment(String appointmentId, String patientId, String doctorId, LocalDate date, String timeSlot, Availability availability, Flag flag) {
        this.appointmentId = appointmentId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.date = date;
        this.timeSlot = timeSlot;
        this.availability = availability;
        this.flag = flag;
    }

    // Getters and setters
    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }

    public Availability getAvailability() {
        return availability;
    }

    public void setAvailability(Availability availability) {
        this.availability = availability;
    }

    public Flag getFlag() {
        return flag;
    }

    public void setFlag(Flag flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Appointment:");
        sb.append("\nappointmentId: ").append(appointmentId);
        sb.append("\npatientId: ").append(patientId);
        sb.append("\ndoctorId: ").append(doctorId);
        sb.append("\ndate: ").append(date);
        sb.append("\ntimeSlot: ").append(timeSlot);
        sb.append("\navailability: ").append(availability);
        sb.append("\nflag: ").append(flag);

        return sb.toString();
    }
}
