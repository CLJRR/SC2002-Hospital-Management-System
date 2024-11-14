package AppointmentSystem;

import enums.Flag;
import enums.Type;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class DoctorLeaveSetter {

    private Map<String, Appointment> appointmentRecords;

    public DoctorLeaveSetter(Map<String, Appointment> appointmentRecords) {
        this.appointmentRecords = appointmentRecords;
    }

    // Method to set a specific timeslot to LEAVE
    public void setLeaveForTimeslot(String doctorId, LocalDate date, String timeslot) {
        if (hasExistingNonCanceledAppointment(doctorId, date, timeslot)) {
            System.out.println("Cannot set leave: Existing non-canceled appointment or leave for Doctor " + doctorId + " on " + date + " at " + timeslot);
            return;
        }

        String leaveId = generateLeaveId();
        Appointment leaveAppointment = new Appointment(leaveId, null, doctorId, date, timeslot, Type.LEAVE, Flag.PENDING);
        appointmentRecords.put(leaveId, leaveAppointment);
        System.out.println("Leave set for Doctor " + doctorId + " on " + date + " at " + timeslot);
    }

    // Method to set all timeslots of a day to LEAVE
    public void setLeaveForAllTimeslots(String doctorId, LocalDate date) {
        List<String> timeslots = Timeslot.getTimeslot();

        // First, check if there’s an existing non-canceled appointment or leave for any timeslot
        for (String timeslot : timeslots) {
            if (hasExistingNonCanceledAppointment(doctorId, date, timeslot)) {
                System.out.println("Cannot set leave for any timeslot: Existing non-canceled appointment or leave for Doctor " + doctorId + " on " + date);
                return;
            }
        }

        // If no conflicts, set all timeslots to LEAVE
        for (String timeslot : timeslots) {
            String leaveId = generateLeaveId();
            Appointment leaveAppointment = new Appointment(leaveId, null, doctorId, date, timeslot, Type.LEAVE, Flag.PENDING);
            appointmentRecords.put(leaveId, leaveAppointment);
        }
        System.out.println("All timeslots set to LEAVE for Doctor " + doctorId + " on " + date);
    }

    // Method to cancel leave for a specific timeslot
    public void cancelLeaveForTimeslot(String doctorId, LocalDate date, String timeslot) {
        for (Appointment appointment : appointmentRecords.values()) {
            if (appointment.getDoctorId().equalsIgnoreCase(doctorId)
                    && appointment.getDate().equals(date)
                    && appointment.getTimeSlot().equals(timeslot)
                    && appointment.getType() == Type.LEAVE
                    && appointment.getFlag() != Flag.CANCELLED) {
                appointment.setFlag(Flag.CANCELLED);
                System.out.println("Leave for Doctor " + doctorId + " on " + date + " at " + timeslot + " has been cancelled.");
                return;
            }
        }
        System.out.println("No existing leave found for Doctor " + doctorId + " on " + date + " at " + timeslot + " to cancel.");
    }

    // Method to cancel leave for all timeslots of a day
    public void cancelLeaveForAllTimeslots(String doctorId, LocalDate date) {
        boolean leaveFound = false;
        for (Appointment appointment : appointmentRecords.values()) {
            if (appointment.getDoctorId().equalsIgnoreCase(doctorId)
                    && appointment.getDate().equals(date)
                    && appointment.getType() == Type.LEAVE
                    && appointment.getFlag() != Flag.CANCELLED) {
                appointment.setFlag(Flag.CANCELLED);
                leaveFound = true;
                System.out.println("Leave for Doctor " + doctorId + " on " + date + " at " + appointment.getTimeSlot() + " has been cancelled.");
            }
        }
        if (!leaveFound) {
            System.out.println("No existing leave found for Doctor " + doctorId + " on " + date + " to cancel.");
        }
    }

    // Helper method to check for an existing non-canceled appointment or leave
    private boolean hasExistingNonCanceledAppointment(String doctorId, LocalDate date, String timeslot) {
        for (Appointment appointment : appointmentRecords.values()) {
            if (appointment.getDoctorId().equalsIgnoreCase(doctorId)
                    && appointment.getDate().equals(date)
                    && appointment.getTimeSlot().equals(timeslot)
                    && appointment.getFlag() != Flag.CANCELLED) {
                return true;
            }
        }
        return false;
    }

    // Helper method to generate a unique leave ID
    private String generateLeaveId() {
        int nextId = appointmentRecords.size() + 1;
        return "L" + nextId;
    }
}
