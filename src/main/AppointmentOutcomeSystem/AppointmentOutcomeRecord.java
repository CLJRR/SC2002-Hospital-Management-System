package AppointmentOutcomeSystem;

import enums.Flag;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the outcome of a medical appointment, including details of the
 * appointment,
 * diagnoses, and prescriptions provided.
 */

public class AppointmentOutcomeRecord {

    /**
     * The unique identifier for the appointment.
     */
    private String apptId;

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
    private LocalDate appointmentDate;

    /**
     * The service provided during the appointment.
     */
    private String serviceProvided;

    /**
     * The list of prescriptions provided during the appointment.
     */
    private List<Prescription> prescriptions;

    /**
     * The list of diagnoses made during the appointment.
     */
    private List<String> diagnoses;

    /**
     * Constructs a new {@code AppointmentOutcomeRecord} with the specified details.
     *
     * @param apptId          the unique identifier for the appointment
     * @param patientId       the unique identifier for the patient
     * @param doctorId        the unique identifier for the doctor
     * @param appointmentDate the date of the appointment
     * @param serviceProvided the service provided during the appointment
     * @param diagnoses       the list of diagnoses made during the appointment
     * @param prescriptions   the list of prescriptions provided during the
     *                        appointment
     */
    public AppointmentOutcomeRecord(String apptId, String patientId, String doctorId, LocalDate appointmentDate,
            String serviceProvided, List<String> diagnoses, List<Prescription> prescriptions) {
        this.apptId = apptId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.appointmentDate = appointmentDate;
        this.serviceProvided = serviceProvided;
        this.diagnoses = diagnoses != null ? new ArrayList<>(diagnoses) : new ArrayList<>();
        this.prescriptions = prescriptions != null ? new ArrayList<>(prescriptions) : new ArrayList<>();
    }

    /**
     * Gets the appointment ID.
     *
     * @return the appointment ID
     */
    public String getApptId() {
        return apptId;
    }

    /**
     * Sets the appointment ID.
     *
     * @param apptId the new appointment ID
     */
    public void setApptId(String apptId) {
        this.apptId = apptId;
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
     * Gets the appointment date.
     *
     * @return the appointment date
     */
    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    /**
     * Sets the appointment date.
     *
     * @param appointmentDate the new appointment date
     */
    public void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    /**
     * Gets the service provided during the appointment.
     *
     * @return the service provided
     */
    public String getServiceProvided() {
        return serviceProvided;
    }

    /**
     * Sets the service provided during the appointment.
     *
     * @param serviceProvided the new service provided
     */
    public void setServiceProvided(String serviceProvided) {
        this.serviceProvided = serviceProvided;
    }

    /**
     * Gets the list of diagnoses.
     *
     * @return a copy of the diagnoses list
     */
    public List<String> getDiagnoses() {
        return new ArrayList<>(diagnoses);
    }

    /**
     * Sets the list of diagnoses.
     *
     * @param diagnoses the new diagnoses list
     */
    public void setDiagnoses(List<String> diagnoses) {
        this.diagnoses = diagnoses != null ? new ArrayList<>(diagnoses) : new ArrayList<>();
    }

    /**
     * Gets the list of prescriptions.
     *
     * @return a copy of the prescriptions list
     */
    public List<Prescription> getPrescriptions() {
        return new ArrayList<>(prescriptions);
    }

    /**
     * Sets the list of prescriptions.
     *
     * @param prescriptions the new prescriptions list
     */
    public void setPrescriptions(List<Prescription> prescriptions) {
        this.prescriptions = prescriptions != null ? new ArrayList<>(prescriptions) : new ArrayList<>();
    }

    /**
     * Updates the {@code Flag} for all prescriptions in the record.
     *
     * @param flag the new flag to set
     */
    public void setFlag(Flag flag) {
        for (Prescription prescription : prescriptions) {
            prescription.setFlag(flag);
        }
    }

    /**
     * Returns a string representation of the appointment outcome record.
     *
     * @return a string representation of the record
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("AppointmentOutcomeRecord:");
        sb.append("\napptId: ").append(apptId);
        sb.append("\npatientId: ").append(patientId);
        sb.append("\ndoctorId: ").append(doctorId);
        sb.append("\nappointmentDate: ").append(appointmentDate);
        sb.append("\nserviceProvided: ").append(serviceProvided);
        sb.append("\ndiagnoses: ").append(String.join(", ", diagnoses));
        sb.append("\nprescriptions: ").append(formatPrescriptions());
        return sb.toString();
    }

    /**
     * Helper method to format prescriptions for the {@code toString} method.
     *
     * @return a formatted string of prescriptions
     */
    private String formatPrescriptions() {
        StringBuilder sb = new StringBuilder();
        for (Prescription prescription : prescriptions) {
            sb.append("\n  ").append(prescription.toString());
        }
        return sb.toString();
    }
}
