package ApptTest;

import java.io.IOException;
import java.util.*;

public class ApptSaver {

    private Map<String, Appointment> appointmentRecords;
    private AppointmentService appointmentService;

    public ApptSaver(Map<String, Appointment> appointmentRecords) {
        this.appointmentRecords = appointmentRecords;
        this.appointmentService = new AppointmentService();
    }

    public void saveRecords() {
        List<Appointment> recordList = new ArrayList<>(appointmentRecords.values());
        try {
            appointmentService.save(recordList); // Ensure `save` method exists in `AppointmentService`
            System.out.println("Appointments saved successfully.");
        } catch (IOException e) {
            System.err.println("Error saving appointments: " + e.getMessage());
        }
    }
}
