package AppointmentSystem;

import enums.*;
import java.time.LocalDate;

/**
 * Represents an appointment in the appointment management system.
 * Includes details about the appointment ID, patient, doctor, date, time slot,
 * type, and status.
 */

public class Appointment {

    /**
     * The unique identifier for the appointment.
     */
    private String appointmentId;

    /**
     * The unique identifier for the patient.
     */
    private String patientId;

    /**
     * The unique identifier for the doctor.
     */
    private String doctorId;

    /**
     * The date of the appointment.
     */
    private LocalDate date;

    /**
     * The time slot for the appointment.
     */
    private String timeSlot;

    /**
     * The type of appointment (e.g., consultation, follow-up).
     */
    private Type type;

    /**
     * The current status of the appointment (e.g., CONFIRMED, PENDING).
     */
    private Flag flag;

    /**
     * Constructs a new {@code Appointment} with the specified attributes.
     *
     * @param appointmentId the unique identifier for the appointment
     * @param patientId     the unique identifier for the patient
     * @param doctorId      the unique identifier for the doctor
     * @param date          the date of the appointment
     * @param timeSlot      the time slot of the appointment
     * @param type          the type of appointment
     * @param flag          the current status of the appointment
     */
    public Appointment(String appointmentId, String patientId, String doctorId, LocalDate date, String timeSlot,
            Type type, Flag flag) {
        this.appointmentId = appointmentId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.date = date;
        this.timeSlot = timeSlot;
        this.type = type;
        this.flag = flag;
    }

    /**
     * Gets the appointment ID.
     *
     * @return the appointment ID
     */
    public String getAppointmentId() {
        return appointmentId;
    }

    /**
     * Sets the appointment ID.
     *
     * @param appointmentId the new appointment ID
     */
    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    /**
     * Gets the patient ID.
     *
     * @return the patient ID
     */
    public String getPatientId() {
        return patientId;
    }

    /**
     * Sets the patient ID.
     *
     * @param patientId the new patient ID
     */
    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    /**
     * Gets the doctor ID.
     *
     * @return the doctor ID
     */
    public String getDoctorId() {
        return doctorId;
    }

    /**
     * Sets the doctor ID.
     *
     * @param doctorId the new doctor ID
     */
    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    /**
     * Gets the date of the appointment.
     *
     * @return the date of the appointment
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Sets the date of the appointment.
     *
     * @param date the new date of the appointment
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * Gets the time slot of the appointment.
     *
     * @return the time slot of the appointment
     */
    public String getTimeSlot() {
        return timeSlot;
    }

    /**
     * Sets the time slot of the appointment.
     *
     * @param timeSlot the new time slot of the appointment
     */
    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }

    /**
     * Gets the type of the appointment.
     *
     * @return the type of the appointment
     */
    public Type getType() {
        return type;
    }

    /**
     * Sets the type of the appointment.
     *
     * @param type the new type of the appointment
     */
    public void setType(Type type) {
        this.type = type;
    }

    /**
     * Gets the status flag of the appointment.
     *
     * @return the current status flag of the appointment
     */
    public Flag getFlag() {
        return flag;
    }

    /**
     * Sets the status flag of the appointment.
     *
     * @param flag the new status flag of the appointment
     */
    public void setFlag(Flag flag) {
        this.flag = flag;
    }

    /**
     * Returns a string representation of the appointment object.
     *
     * @return a formatted string with the appointment details
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Appointment:");
        sb.append("\nappointmentId: ").append(appointmentId);
        sb.append("\npatientId: ").append(patientId);
        sb.append("\ndoctorId: ").append(doctorId);
        sb.append("\ndate: ").append(date);
        sb.append("\ntimeSlot: ").append(timeSlot);
        sb.append("\ntype: ").append(type);
        sb.append("\nflag: ").append(flag);
        return sb.toString();
    }
}
