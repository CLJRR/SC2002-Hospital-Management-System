package AppointmentSystem;

import AppointmentOutcomeSystem.AppointmentOutcomeRecordController;
import enums.Flag;
import enums.Type;

import java.io.IOException;
import java.util.*;

public class ApptRecordViewer {

    private Map<String, Appointment> appointmentRecords;
    static final Scanner sc = new Scanner(System.in);
    private AppointmentOutcomeRecordController outcomeController = new AppointmentOutcomeRecordController();

    public ApptRecordViewer(Map<String, Appointment> appointmentRecords) {
        this.appointmentRecords = appointmentRecords;
    }

    public void viewAllRecords() {
        System.out.println("All Appointment Records:");
        for (Appointment record : appointmentRecords.values()) {
            System.out.println(record.toString());
            System.out.println(
                    "----------------------------------------------------------------------------------------------------------------");
        }
        System.out.println(); // Adds a new line after the last record
    }

    public void adminViewAllRecords() throws IOException {
        System.out.println("All Appointment Records:");
        for (Appointment record : appointmentRecords.values()) {
            System.out.println(record.toString());
            System.out.println(" ");
            outcomeController.adminViewRecords(record.getAppointmentId());
        }
        System.out.println(); // Adds a new line after the last record
    }

    // For doctor to view pending records
    public void viewPendingRecords(String doctorId) throws IOException {
        for (Appointment record : appointmentRecords.values()) {
            if (doctorId.equalsIgnoreCase(record.getDoctorId()) && record.getFlag() == Flag.PENDING && record.getType() != Type.LEAVE) {
                System.out.println(record.getAppointmentId() + " " + record.getFlag());
            }
        }
    }

    public Appointment viewRecordsById(String Id) throws IOException {
        System.out.println("For ID " + Id);
        System.out.println(
                "----------------------------------------------------------------------------------------------------------------");
        for (Appointment appointment : appointmentRecords.values()) {
            // by appointment ID
            if (Id.equalsIgnoreCase(appointment.getAppointmentId())) {
                System.out.println(appointment.toString());
                return appointment;
            }
            // by patient ID
            if (Id.equalsIgnoreCase(appointment.getPatientId())) {
                System.out.println(appointment.toString());
                System.out.println(
                        "----------------------------------------------------------------------------------------------------------------");
            }
        }
        System.out.println("Press Enter to go back");
        sc.nextLine();
        return null;
    }

}
