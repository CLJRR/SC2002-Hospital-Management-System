package entity;

import java.time.LocalDate;

public class MedicalRecord {

    private String apptId;
    private String patientId;
    private String doctorId;
    private LocalDate appointmentDate;
    private String serviceProvided;
    private Prescription prescription;
    private String diagnoses;

    public MedicalRecord(String apptId, String patientId, String doctorId, LocalDate appointmentDate,
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
        sb.append("MedicalRecord{");
        sb.append("apptId= ").append(apptId);
        sb.append(", patientId= ").append(patientId);
        sb.append(", doctorId= ").append(doctorId);
        sb.append(", appointmentDate= ").append(appointmentDate);
        sb.append(", serviceProvided= ").append(serviceProvided);
        sb.append(", diagnoses= ").append(diagnoses);
        sb.append(", prescription= ").append(prescription);
        sb.append('}');
        return sb.toString();
    }

}
