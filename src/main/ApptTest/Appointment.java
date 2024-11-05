package ApptTest;

import enums.Availability;
import enums.Flag;
import java.time.LocalDate;

public class Appointment {

    private String apptId;
    private String doctorId;
    private String patientId;
    private LocalDate date;
    private String timeSlot;
    private Availability availability;
    private Flag flag;
    private String outcome;
    private String prescribedMeds;

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
        this(apptId, patientId, doctorId, date, timeSlot, availability, Flag.PENDING);
    }

    // Getters and Setters
    public String getApptId() { return apptId; }
    public String getDoctorId() { return doctorId; }
    public String getPatientId() { return patientId; }
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
    public String getTimeSlot() { return timeSlot; }
    public void setTimeSlot(String timeSlot) { this.timeSlot = timeSlot; }
    public Availability getAvailability() { return availability; }
    public void setAvailability(Availability availability) { this.availability = availability; }
    public Flag getFlag() { return flag; }
    public void setFlag(Flag flag) { this.flag = flag; }
    
    public String getOutcome() { return outcome; }
    public void setOutcome(String outcome) { this.outcome = outcome; }
    public String getPrescribedMeds() { return prescribedMeds; }
    public void setPrescribedMeds(String prescribedMeds) { this.prescribedMeds = prescribedMeds; }

    @Override
    public String toString() {
        return "Appointment{" +
                "apptId='" + apptId + '\'' +
                ", doctorId='" + doctorId + '\'' +
                ", patientId='" + patientId + '\'' +
                ", date=" + date +
                ", timeSlot='" + timeSlot + '\'' +
                ", availability=" + availability +
                ", flag=" + flag +
                ", outcome='" + outcome + '\'' +
                ", prescribedMeds='" + prescribedMeds + '\'' +
                '}';
    }
}
