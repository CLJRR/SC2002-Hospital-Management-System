package ApptTest;

import enums.Flag;
import java.io.IOException;
import java.util.*;

public class ApptViewer {

    private Map<String, Appointment> appointmentRecords;
    static final Scanner sc = new Scanner(System.in);

    public ApptViewer(Map<String, Appointment> appointmentRecords) {
        this.appointmentRecords = appointmentRecords;
    }

    public void viewAllRecords() {
        System.out.println("All Appointment Records:");
        for (Appointment record : appointmentRecords.values()) {
            System.out.println(record.toString());
        }
        System.out.println(); // Adds a new line after the last record
    }

    public void viewPendingRecords() throws IOException {
        for (Appointment record : appointmentRecords.values()) {
            if (record.getFlag() == Flag.PENDING) {
                System.out.println(record.getAppointmentId() + " " + record.getFlag());
            }
        }
    }

    public void viewRecordsById(String Id) throws IOException {
        System.out.println("For ID " + Id);
        System.out.println("----------------------------------------------------------------------------------------------------------------");
        for (Appointment appointment : appointmentRecords.values()) {
            // by appointment ID
            if (Id.equalsIgnoreCase(appointment.getAppointmentId())) {
                System.out.println(appointment.toString());
                return;
            }
            // by patient ID
            if (Id.equalsIgnoreCase(appointment.getPatientId())) {
                System.out.println(appointment.toString());
                System.out.println("----------------------------------------------------------------------------------------------------------------");
            }
        }
        System.out.println("Press Enter to go back");
        sc.nextLine();
    }
}
