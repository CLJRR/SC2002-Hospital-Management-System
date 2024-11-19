/**
 * The {@code ApptLoader} class is responsible for loading appointment records from a data source
 * and populating a map of appointment records.
 */
package AppointmentSystem;

import java.io.IOException;
import java.util.*;

public class ApptLoader {

    /**
     * A map to store appointment records, keyed by their appointment ID.
     */
    private final Map<String, Appointment> appointmentRecords;

    /**
     * Service responsible for managing the serialization and deserialization of appointment records.
     */
    private final AppointmentService appointmentService;

    /**
     * Constructs a new {@code ApptLoader} with the specified map of appointment records.
     *
     * @param appointmentRecords the map to store loaded appointment records
     */
    public ApptLoader(Map<String, Appointment> appointmentRecords) {
        this.appointmentRecords = appointmentRecords;
        this.appointmentService = new AppointmentService();
    }

    /**
     * Loads initial appointment records from the data source and populates the {@code appointmentRecords} map.
     * If an error occurs during loading, an error message is displayed.
     */
    public void loadInitialAppointments() {
        try {
            @SuppressWarnings("unchecked")
            List<Appointment> records = (List<Appointment>) appointmentService.load(); // Load method to get records
            for (Appointment record : records) {
                appointmentRecords.put(record.getAppointmentId(), record); // Put each record into the map using appointmentId
            }
        } catch (IOException e) {
            System.err.println("Error loading appointments: " + e.getMessage());
        }
    }
}
