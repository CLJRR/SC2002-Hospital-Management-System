/**
 * The {@code DoctorLeaveSetter} class provides functionality for managing leave
 * for doctors. It supports setting and canceling leave for specific timeslots
 * or entire days, ensuring no conflicts with existing non-canceled appointments
 * or leaves.
 */
package AppointmentSystem;

import enums.Flag;
import enums.Type;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class DoctorLeaveSetter {

    /**
     * A map containing all appointment records, keyed by their appointment ID.
     */
    private Map<String, Appointment> appointmentRecords;

    /**
     * Constructs a new {@code DoctorLeaveSetter} with the specified map of
     * appointment records.
     *
     * @param appointmentRecords the map to manage appointment and leave records
     */
    public DoctorLeaveSetter(Map<String, Appointment> appointmentRecords) {
        this.appointmentRecords = appointmentRecords;
    }

    /**
     * Sets leave for a specific timeslot for a doctor on a given date. Ensures
     * no conflicting non-canceled appointments or existing leave for the
     * specified timeslot.
     *
     * @param doctorId the ID of the doctor
     * @param date the date of the leave
     * @param timeslot the timeslot for which leave is being set
     */
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

    /**
     * Sets leave for all timeslots on a given date for a doctor. Ensures no
     * conflicting non-canceled appointments or leaves for any timeslot on the
     * specified date.
     *
     * @param doctorId the ID of the doctor
     * @param date the date of the leave
     */
    public void setLeaveForAllTimeslots(String doctorId, LocalDate date) {
        List<String> timeslots = Timeslot.getTimeslot();

        for (String timeslot : timeslots) {
            if (hasExistingNonCanceledAppointment(doctorId, date, timeslot)) {
                System.out.println("Cannot set leave for any timeslot: Existing non-canceled appointment or leave for Doctor " + doctorId + " on " + date);
                return;
            }
        }

        for (String timeslot : timeslots) {
            String leaveId = generateLeaveId();
            Appointment leaveAppointment = new Appointment(leaveId, null, doctorId, date, timeslot, Type.LEAVE, Flag.PENDING);
            appointmentRecords.put(leaveId, leaveAppointment);
        }
        System.out.println("All timeslots set to LEAVE for Doctor " + doctorId + " on " + date);
    }

    /**
     * Cancels leave for a specific timeslot for a doctor on a given date.
     *
     * @param doctorId the ID of the doctor
     * @param date the date of the leave
     * @param timeslot the timeslot for which leave is being canceled
     */
    public void cancelLeaveForTimeslot(String doctorId, LocalDate date, String timeslot) {
        for (Appointment appointment : appointmentRecords.values()) {
            if (appointment.getDoctorId().equalsIgnoreCase(doctorId)
                    && appointment.getDate().equals(date)
                    && appointment.getTimeSlot().equalsIgnoreCase(timeslot)
                    && appointment.getType() == Type.LEAVE
                    && appointment.getFlag() != Flag.CANCELLED) {
                appointment.setFlag(Flag.CANCELLED);
                System.out.println("Leave for Doctor " + doctorId + " on " + date + " at " + timeslot + " has been cancelled.");
                return;
            }
        }
        System.out.println("No existing leave found for Doctor " + doctorId + " on " + date + " at " + timeslot + " to cancel.");
    }

    /**
     * Cancels leave for all timeslots on a given date for a doctor.
     *
     * @param doctorId the ID of the doctor
     * @param date the date of the leave
     */
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

    /**
     * Checks if there is an existing non-canceled appointment or leave for a
     * doctor on a specific date and timeslot.
     *
     * @param doctorId the ID of the doctor
     * @param date the date of the appointment or leave
     * @param timeslot the timeslot of the appointment or leave
     * @return {@code true} if there is a conflict, {@code false} otherwise
     */
    private boolean hasExistingNonCanceledAppointment(String doctorId, LocalDate date, String timeslot) {
        for (Appointment appointment : appointmentRecords.values()) {
            if (appointment.getDoctorId().equalsIgnoreCase(doctorId)
                    && appointment.getDate().equals(date)
                    && appointment.getTimeSlot().equalsIgnoreCase(timeslot)
                    && appointment.getFlag() != Flag.CANCELLED) {
                return true;
            }
        }
        return false;
    }

    /**
     * Generates a unique leave ID in the format "Lxxx", where "xxx" is a
     * zero-padded number. The method counts the number of appointments flagged
     * as LEAVE and generates the next ID in the sequence.
     *
     * @return a unique leave ID in the format "Lxxx", where "xxx" is the next
     * available number based on the existing appointments flagged as LEAVE.
     */
    String generateLeaveId() {
        // Filter appointmentRecords to count only entries with Type LEAVE
        long leaveCount = appointmentRecords.values().stream()
                .filter(appointment -> appointment.getType() == Type.LEAVE)
                .count();

        // Generate the next leave ID based on the count of existing leave appointments
        return String.format("L%03d", leaveCount + 1);
    }

}
