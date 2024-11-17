package AppointmentOutcomeSystem;

import enums.Flag;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AppointmentOutcomeRecord {

    private String apptId;
    private String patientId;
    private String doctorId;
    private LocalDate appointmentDate;
    private String serviceProvided;
    private List<Prescription> prescriptions;
    private List<String> diagnoses;

    // Constructor
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

    // Getters and Setters
    public String getApptId() {
        return apptId;
    }

    public void setApptId(String apptId) {
        this.apptId = apptId;
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

    public List<String> getDiagnoses() {
        return new ArrayList<>(diagnoses); // Return a copy to maintain encapsulation
    }

    public void setDiagnoses(List<String> diagnoses) {
        this.diagnoses = diagnoses != null ? new ArrayList<>(diagnoses) : new ArrayList<>();
    }

    public List<Prescription> getPrescriptions() {
        return new ArrayList<>(prescriptions); // Return a copy to maintain encapsulation
    }

    public void setPrescriptions(List<Prescription> prescriptions) {
        this.prescriptions = prescriptions != null ? new ArrayList<>(prescriptions) : new ArrayList<>();
    }

    // Update the flag for all prescriptions
    public void setFlag(Flag flag) {
        for (Prescription prescription : prescriptions) {
            prescription.setFlag(flag);
        }
    }

    // Override toString for detailed printing
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

    // Helper method to format prescriptions
    private String formatPrescriptions() {
        StringBuilder sb = new StringBuilder();
        for (Prescription prescription : prescriptions) {
            sb.append("\n  ").append(prescription.toString());
        }
        return sb.toString();
    }
}
