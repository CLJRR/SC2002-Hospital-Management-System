package entity;

import enums.Availability;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Appointment {

    private String apptId;
    private String doctorId;
    private String patientId;
    private LocalDate date;
    private String timeSlot;
    private Availability availability;

    private static final List<String> TIME_SLOTS = new ArrayList<>();

    // Initialize TIME_SLOTS from 9 AM to 5 PM
    static {
        int startHour = 9; // 9 AM
        int endHour = 17;  // 5 PM
        for (int hour = startHour; hour < endHour; hour++) {
            TIME_SLOTS.add(String.format("%02d:00", hour));
        }
    }

    public Appointment(String apptId, String patientId, String doctorId, LocalDate date, String timeSlot, Availability availability) {
        this.apptId = apptId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.date = date;
        this.timeSlot = timeSlot;
        this.availability = availability;
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

    public static List<String> getAvailableTimeSlots() {
        return TIME_SLOTS;
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
