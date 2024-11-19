/**
 * The {@code ApptSaver} class is responsible for saving appointment records to a data source.
 * It ensures that the records are sorted by their appointment IDs before saving.
 */
package AppointmentSystem;

import java.io.IOException;
import java.util.*;

public class AppointmentSaver {

    /**
     * A map containing all appointment records, keyed by their appointment ID.
     */
    private Map<String, Appointment> appointmentRecords;

    /**
     * Service responsible for managing the serialization of appointment records.
     */
    private AppointmentService appointmentService;

    /**
     * Constructs a new {@code ApptSaver} with the specified map of appointment records.
     *
     * @param appointmentRecords the map of appointment records to be saved
     */
    public AppointmentSaver(Map<String, Appointment> appointmentRecords) {
        this.appointmentRecords = appointmentRecords;
        this.appointmentService = new AppointmentService();
    }

    /**
     * Saves all appointment records to a data source.
     * The records are first sorted by their appointment IDs to ensure consistent ordering.
     * If an error occurs during the save process, an error message is displayed.
     */
    public void saveRecords() {
        List<Appointment> recordList = new ArrayList<>(appointmentRecords.values());
        recordList.sort(Comparator.comparing(Appointment::getAppointmentId));

        try {
            appointmentService.save(recordList); // Save the sorted records using `AppointmentService`
            System.out.println("Appointments saved successfully.");
        } catch (IOException e) {
            System.err.println("Error saving appointments: " + e.getMessage());
        }
    }
}
