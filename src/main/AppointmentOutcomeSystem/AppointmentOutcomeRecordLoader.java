package AppointmentOutcomeSystem;

import java.io.IOException;
import java.util.*;

public class AppointmentOutcomeRecordLoader {

    private final Map<String, AppointmentOutcomeRecord> appointmentOutcomeRecords;
    private final AppointmentOutcomeRecordService appointmentOutcomeRecordService;

    public AppointmentOutcomeRecordLoader(Map<String, AppointmentOutcomeRecord> appointmentOutcomeRecords) {
        this.appointmentOutcomeRecords = appointmentOutcomeRecords;
        this.appointmentOutcomeRecordService = new AppointmentOutcomeRecordService();
    }

    public void loadInitialAppointmentOutcomes() {
        try {
            @SuppressWarnings("unchecked")
            List<AppointmentOutcomeRecord> records = (List<AppointmentOutcomeRecord>) appointmentOutcomeRecordService
                    .load(); // Load method to get records
            for (AppointmentOutcomeRecord record : records) {
                appointmentOutcomeRecords.put(record.getApptId(), record); // Put each record into the map using apptId
            }
        } catch (IOException e) {
            System.err.println("Error loading appointments: " + e.getMessage());
        }
    }
}
