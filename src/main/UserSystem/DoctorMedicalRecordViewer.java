package UserSystem;

import AppointmentOutcomeSystem.AppointmentOutcomeRecord;
import java.util.*;

public class DoctorMedicalRecordViewer {
    private User doctor;
    private GetUser getUser;
    private Map<String, AppointmentOutcomeRecord> appointmentOutcomeRecords;
    
    public DoctorMedicalRecordViewer(String UserId, Map<String, AppointmentOutcomeRecord> appointmentOutcomeRecords) {
        this.appointmentOutcomeRecords = appointmentOutcomeRecords;
        this.getUser = new GetUser();
        this.doctor = getUser.getUser(UserId);
    }

    public void viewMedicalRecords() {
        for (AppointmentOutcomeRecord record : appointmentOutcomeRecords.values()) {
            if (record.getDoctorId().equals(doctor.getId())) {
                System.out.println(record.toString());
            }
        }
    }
}
