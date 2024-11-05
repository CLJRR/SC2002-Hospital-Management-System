package TODO;

import enums.*;
import java.time.LocalDate;

public class Appointment {

    private String apptId;
    private String doctorId;
    private String patientId;
    private LocalDate date;
    private String timeSlot;
    private Availability availability;
    private Flag flag;

    public Appointment(String apptId, String patientId, String doctorId, LocalDate date, String timeSlot, Availability availability, Flag flag) {
        this.apptId = apptId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.date = date;
        this.timeSlot = timeSlot;
        this.availability = availability;
        this.flag = flag;
    }

    public Appointment(String apptId, String patientId, String doctorId, LocalDate date, String timeSlot, Availability availability) {
        this.apptId = apptId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.date = date;
        this.timeSlot = timeSlot;
        this.availability = availability;
        this.flag = Flag.PENDING;
    }

    public Flag getFlag() {
        return flag;
    }

    public void setFlag(Flag flag) {
        this.flag = flag;
    }

    public String getApptId() {
        return apptId;
    }

    public String getPatientId() {
        return patientId;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public Availability getAvailability() {
        return availability;
    }

    @Override
    public String toString() {
        return "Appointment{"
                + "apptId='" + apptId + '\''
                + ", patientId='" + patientId + '\''
                + ", doctorId='" + doctorId + '\''
                + ", date=" + date
                + ", timeSlot='" + timeSlot + '\''
                + ", availability=" + availability
                + '}';
    }
}
