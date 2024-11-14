package AppointmentOutcomeSystem;

import SessionManager.Session;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Scanner;

public class RecordAppointmentOutcomePrompts {

    private Map<String, AppointmentOutcomeRecord> appointmentOutcomeRecords;
    static final Scanner sc = new Scanner(System.in);

    public RecordAppointmentOutcomePrompts(Map<String, AppointmentOutcomeRecord> appointmentOutcomeRecords) {
        this.appointmentOutcomeRecords = appointmentOutcomeRecords;
    }

    public AppointmentOutcomeRecord prompts() {
        System.out.println("Enter AppointmentId: ");
        String apptId = sc.next();

        // Check for duplicate appointment ID
        if (appointmentOutcomeRecords.containsKey(apptId)) {
            System.out.println("A record with Appointment ID " + apptId + " already exists.");
            return null; // Exit without creating a new record
        }

        System.out.println("Enter Service Provided: ");
        String service = sc.next();

        System.out.println("Enter Diagnosis: ");
        String diagnosis = sc.next();

        System.out.println("Enter Medicine Prescribed: ");
        String medName = sc.next();

        System.out.println("Enter amount: ");
        Integer amt = sc.nextInt();

        System.out.println("Enter dosage: ");
        String dosage = sc.next();

        LocalDate appointmentDate = LocalDate.parse("2024-12-08", DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        // Create a Prescription object
        Prescription prescription = new Prescription(medName, amt, dosage);

        // Create and return an AppointmentOutcomeRecord instance
        return new AppointmentOutcomeRecord(
                apptId, // Appointment ID
                "patientIdapptIdToLoad", // Placeholder for Patient ID
                Session.getLoginID(), // Assuming this retrieves the logged-in doctor ID
                appointmentDate, // Appointment date
                service, // Service provided
                diagnosis, // Diagnosis
                prescription // Prescription object
        );
    }
}
