/**
 * Responsible for loading appointment outcome records from a data source 
 * and storing them in a provided map.
 */
package AppointmentOutcomeSystem;

import java.io.IOException;
import java.util.*;

public class AppointmentOutcomeRecordLoader {

    /**
     * A map storing appointment outcome records with appointment ID as the key.
     */
    private final Map<String, AppointmentOutcomeRecord> appointmentOutcomeRecords;

    /**
     * Service responsible for loading appointment outcome records from a data source.
     */
    private final AppointmentOutcomeRecordService appointmentOutcomeRecordService;

    /**
     * Constructs a new {@code AppointmentOutcomeRecordLoader} with the specified map
     * to store appointment outcome records.
     *
     * @param appointmentOutcomeRecords the map to store loaded appointment outcome records
     */
    public AppointmentOutcomeRecordLoader(Map<String, AppointmentOutcomeRecord> appointmentOutcomeRecords) {
        this.appointmentOutcomeRecords = appointmentOutcomeRecords;
        this.appointmentOutcomeRecordService = new AppointmentOutcomeRecordService();
    }

    /**
     * Loads the initial set of appointment outcome records from the data source 
     * and populates the provided map.
     * If an error occurs during loading, it prints an error message to the console.
     */
    public void loadInitialAppointmentOutcomes() {
        try {
            @SuppressWarnings("unchecked")
            List<AppointmentOutcomeRecord> records = (List<AppointmentOutcomeRecord>) appointmentOutcomeRecordService.load(); // Load method to get records
            for (AppointmentOutcomeRecord record : records) {
                appointmentOutcomeRecords.put(record.getApptId(), record); // Put each record into the map using apptId
            }
        } catch (IOException e) {
            System.err.println("Error loading inventory: " + e.getMessage());
        }
    }
}
