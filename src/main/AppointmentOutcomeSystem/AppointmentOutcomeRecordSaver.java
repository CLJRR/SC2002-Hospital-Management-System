/**
 * Responsible for saving appointment outcome records to a data source.
 */
package AppointmentOutcomeSystem;

import java.io.IOException;
import java.util.*;

public class AppointmentOutcomeRecordSaver {

    /**
     * A map storing appointment outcome records with appointment ID as the key.
     */
    private Map<String, AppointmentOutcomeRecord> appointmentOutcomeRecords;

    /**
     * Service responsible for saving appointment outcome records to a data source.
     */
    private AppointmentOutcomeRecordService appointmentOutcomeRecordService;

    /**
     * Constructs a new {@code AppointmentOutcomeRecordSaver} with the specified map
     * containing appointment outcome records to be saved.
     *
     * @param appointmentOutcomeRecords the map of appointment outcome records to save
     */
    public AppointmentOutcomeRecordSaver(Map<String, AppointmentOutcomeRecord> appointmentOutcomeRecords) {
        this.appointmentOutcomeRecords = appointmentOutcomeRecords;
        this.appointmentOutcomeRecordService = new AppointmentOutcomeRecordService();
    }

    /**
     * Saves all appointment outcome records from the map to the data source.
     * Converts the records to a list before saving.
     * If an error occurs during saving, it prints an error message to the console.
     */
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
