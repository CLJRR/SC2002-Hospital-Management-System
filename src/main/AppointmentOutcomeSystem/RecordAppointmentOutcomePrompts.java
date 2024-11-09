package AppointmentOutcomeSystem;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import SessionManager.Session;

public class RecordAppointmentOutcomePrompts {

    static final Scanner sc = new Scanner(System.in);

    public AppointmentOutcomeRecord prompts() {
        System.out.println("Enter AppointmentId: ");
        String apptId = sc.next();
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

        //    public AppointmentOutcomeRecord(String apptId, String patientId, String doctorId, LocalDate appointmentDate,String serviceProvided, String diagnoses, Prescription prescription) {
        Prescription prescription = new Prescription(medName, amt, dosage);

        // Create an AppointmentOutcomeRecord instance
        AppointmentOutcomeRecord newRecord = new AppointmentOutcomeRecord(
                apptId, // Appointment ID
                "patientIdapptIdToLoad", // Patient ID
                Session.getLoginID(), // Assuming this retrieves the logged-in doctor ID
                appointmentDate, // to load
                service, // Service provided
                diagnosis,// Diagnoses
                prescription // Prescription object
        );
        return newRecord;
    }
}
