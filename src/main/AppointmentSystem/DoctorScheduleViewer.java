package AppointmentSystem;

import enums.Flag;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class DoctorScheduleViewer {

    private Map<String, Appointment> appointmentRecords;
    private final Scanner sc = new Scanner(System.in);
    public DoctorScheduleViewer(Map<String, Appointment> appointmentRecords) {
        this.appointmentRecords = appointmentRecords;
    }

    public void viewDoctorScheduleForNextThreeDays(String doctorId, LocalDate startDate) {
        // Loop over the specified date and the next two days
        for (int i = 0; i < 3; i++) {
            LocalDate date = startDate.plusDays(i);
            viewDoctorScheduleForDate(doctorId, date);
            System.out.println(); // Add spacing between days
        }
        System.out.println("Press Enter to go back");
        sc.nextLine();
    }

    public void viewDoctorScheduleForDate(String doctorId, LocalDate date) {
        List<String> timeslots = Timeslot.getTimeslot();
        Map<String, String> schedule = new HashMap<>();

        // Initialize all timeslots to "AVAILABLE"
        for (String timeslot : timeslots) {
            schedule.put(timeslot, "AVAILABLE");
        }
        // Check appointments and leaves for the specified doctor and date
        for (Appointment appointment : appointmentRecords.values()) {
            if (appointment.getDoctorId().equalsIgnoreCase(doctorId) && appointment.getDate().equals(date) && appointment.getFlag() != Flag.CANCELLED && appointment.getFlag() != Flag.REJECTED) {
                String timeSlot = appointment.getTimeSlot();
                String details;

                if (appointment.getType() == null) {
                    details = "AVAILABLE";
                } else {
                    switch (appointment.getType()) {
                        case APPOINTMENT:
                            details = String.format("APPOINTMENT - ID: %s, Patient ID: %s, Flag: %s",
                                    appointment.getAppointmentId(), appointment.getPatientId(), appointment.getFlag());
                            break;
                        case LEAVE:
                            details = String.format("LEAVE - Flag: %s", appointment.getFlag());
                            break;
                        default:
                            details = "AVAILABLE";
                            break;
                    }
                }
                schedule.put(timeSlot, details);
            }
        }

        // Display the schedule with additional details for the specified date
        System.out.println("Doctor's Schedule for " + doctorId + " on " + date + ":");
        for (String timeslot : timeslots) {
            System.out.println(timeslot + "\t" + schedule.get(timeslot));
        }
    }
}
