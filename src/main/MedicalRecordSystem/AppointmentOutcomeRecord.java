package MedicalRecordSystem;

import java.time.LocalDate;

public class AppointmentOutcomeRecord {

    private String apptId;
    private String patientId;
    private String doctorId;
    private LocalDate appointmentDate;
    private String serviceProvided;
    private Prescription prescription;
    private String diagnoses;

    public AppointmentOutcomeRecord(String apptId, String patientId, String doctorId, LocalDate appointmentDate,
            String serviceProvided, String diagnoses, Prescription prescription) {
        this.apptId = apptId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.appointmentDate = appointmentDate;
        this.serviceProvided = serviceProvided;
        this.prescription = prescription;
        this.diagnoses = diagnoses;
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

    public String getApptId() {
        return apptId;
    }

    public void setApptId(String apptId) {
        this.apptId = apptId;
    }

    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getServiceProvided() {
        return serviceProvided;
    }

    public void setServiceProvided(String serviceProvided) {
        this.serviceProvided = serviceProvided;
    }

    public Prescription getPrescription() {
        return prescription;
    }

    public void setPrescription(Prescription prescription) {
        this.prescription = prescription;
    }

    public String getDiagnoses() {
        return diagnoses;
    }

    public void setDiagnoses(String diagnoses) {
        this.diagnoses = diagnoses;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("MedicalOutcomeRecord:");
        sb.append("\napptId: ").append(apptId);
        sb.append("\npatientId: ").append(patientId);
        sb.append("\ndoctorId: ").append(doctorId);
        sb.append("\nappointmentDate: ").append(appointmentDate);
        sb.append("\nserviceProvided: ").append(serviceProvided);
        sb.append("\ndiagnoses: ").append(diagnoses);
        sb.append("\nprescription: ").append(prescription);

        return sb.toString();
    }

}
