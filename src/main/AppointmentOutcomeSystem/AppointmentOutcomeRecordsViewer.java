package AppointmentOutcomeSystem;

import enums.Flag;
import java.io.IOException;
import java.util.*;

public class AppointmentOutcomeRecordsViewer {

    private Map<String, AppointmentOutcomeRecord> appointmentOutcomeRecords;
    static final Scanner sc = new Scanner(System.in);

    public AppointmentOutcomeRecordsViewer(Map<String, AppointmentOutcomeRecord> appointmentOutcomeRecords) {
        this.appointmentOutcomeRecords = appointmentOutcomeRecords;
    }

    public void viewAllRecords() {
        System.out.println("All Appointment Outcome Records:");
        for (AppointmentOutcomeRecord record : appointmentOutcomeRecords.values()) {
            System.out.println(record.toString());
        }
        System.out.println(); // Adds a new line after the last record
    }

    public void viewPendingRecords() throws IOException {
        for (AppointmentOutcomeRecord record : appointmentOutcomeRecords.values()) {
            if (record.getPrescription().getStatus() == Flag.PENDING) {
                System.out.println(record.getApptId() + " " + record.getPrescription());
            }
        }
    }

    public void viewRecordsById(String Id) throws IOException {
        // List<MedicalOutcomeRecord> medicalRecords = new ArrayList<MedicalOutcomeRecord>();
        // 
        System.out.println("For Id " + Id);
        System.out.println("----------------------------------------------------------------------------------------------------------------");
        for (AppointmentOutcomeRecord medicalRecord : appointmentOutcomeRecords.values()) {
            //by appt id 
            if (Id.equalsIgnoreCase((medicalRecord.getApptId()))) {
                System.out.println(medicalRecord.toString());
                return;
            }
            //by patient id 
            if (Id.equalsIgnoreCase((medicalRecord.getPatientId()))) {
                System.out.println(medicalRecord.toString());
                System.out.println("----------------------------------------------------------------------------------------------------------------");
            }
        }
        System.out.println("Press Enter to go back");
        sc.nextLine();
    }
}
