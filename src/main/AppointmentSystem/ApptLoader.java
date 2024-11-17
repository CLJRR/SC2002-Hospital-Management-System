package AppointmentSystem;

import java.io.IOException;
import java.util.*;

public class ApptLoader {

    private final Map<String, Appointment> appointmentRecords;
    private final AppointmentService appointmentService;

    public ApptLoader(Map<String, Appointment> appointmentRecords) {
        this.appointmentRecords = appointmentRecords;
        this.appointmentService = new AppointmentService();
    }

    public void loadInitialAppointments() {
        try {
            @SuppressWarnings("unchecked")
            List<Appointment> records = (List<Appointment>) appointmentService.load(); // Load method to get records
            for (Appointment record : records) {
                appointmentRecords.put(record.getAppointmentId(), record); // Put each record into the map using
                                                                           // appointmentId
            }
        } catch (IOException e) {
            System.err.println("Error loading appointments: " + e.getMessage());
        }
    }
}
