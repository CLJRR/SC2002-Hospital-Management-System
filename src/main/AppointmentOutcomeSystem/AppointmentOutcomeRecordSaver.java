package AppointmentOutcomeSystem;

import java.io.IOException;
import java.util.*;

public class AppointmentOutcomeRecordSaver {

    private Map<String, AppointmentOutcomeRecord> appointmentOutcomeRecords;
    private AppointmentOutcomeRecordService appointmentOutcomeRecordService;

    public AppointmentOutcomeRecordSaver(Map<String, AppointmentOutcomeRecord> appointmentOutcomeRecords) {
        this.appointmentOutcomeRecords = appointmentOutcomeRecords;
        this.appointmentOutcomeRecordService = new AppointmentOutcomeRecordService();
    }

    public void saveRecords() {
        List<AppointmentOutcomeRecord> recordList = new ArrayList<>(appointmentOutcomeRecords.values());
        try {
            appointmentOutcomeRecordService.save(recordList); // Ensure `save` method exists in `AppointmentOutcomeRecordService`
            System.out.println("Appointment outcomes saved successfully.");
        } catch (IOException e) {
            System.err.println("Error saving appointment outcomes: " + e.getMessage());
        }
    }
}
