/**
 * The {@code ApptRecordViewer} class provides functionalities to view appointment records.
 * It supports operations for administrators, doctors, and general users to display records based on specific criteria.
 */
package AppointmentSystem;

import AppointmentOutcomeSystem.AppointmentOutcomeRecordController;
import enums.Flag;
import enums.Type;
import java.io.IOException;
import java.util.*;

public class ApptRecordViewer {

    /**
     * A map containing all appointment records, keyed by their appointment ID.
     */
    private Map<String, Appointment> appointmentRecords;

    /**
     * Scanner for user input.
     */
    static final Scanner sc = new Scanner(System.in);

    /**
     * Controller to access and manage appointment outcome records.
     */
    private AppointmentOutcomeRecordController outcomeController = new AppointmentOutcomeRecordController();

    /**
     * Constructs a new {@code ApptRecordViewer} with the specified map of appointment records.
     *
     * @param appointmentRecords the map to manage and view appointment records
     */
    public ApptRecordViewer(Map<String, Appointment> appointmentRecords) {
        this.appointmentRecords = appointmentRecords;
    }

    /**
     * Displays all appointment records stored in the {@code appointmentRecords} map.
     */
    public void viewAllRecords() {
        System.out.println("All Appointment Records:");
        System.out.println(" ");
        for (Appointment record : appointmentRecords.values()) {
            System.out.println(record.toString());
            System.out.println(
                    "----------------------------------------------------------------------------------------------------------------");
        }
        System.out.println(); // Adds a new line after the last record
    }

    /**
     * Displays all appointment records for administrative purposes.
     * Only includes appointments of type {@link Type#APPOINTMENT}.
     * Also displays related outcome records for each appointment.
     *
     * @throws IOException if an error occurs while accessing outcome records
     */
    public void adminViewAllRecords() throws IOException {
        System.out.println("All Appointment Records:");
        for (Appointment record : appointmentRecords.values()) {
            if (record.getType().equals(Type.APPOINTMENT)) {
                System.out.println("----------------------------------------------------------------------------------------------------------------");
                System.out.println(record.toString());
                System.out.println(" ");
                outcomeController.adminViewRecords(record.getAppointmentId());
            }
        }
        System.out.println(); // Adds a new line after the last record
        System.out.println(" ");
    }

    /**
     * Displays all pending appointment records for a specific doctor.
     * Excludes appointments of type {@link Type#LEAVE}.
     *
     * @param doctorId the ID of the doctor whose pending records should be displayed
     * @throws IOException if an error occurs during record access
     */
    public void viewPendingRecords(String doctorId) throws IOException {
        for (Appointment record : appointmentRecords.values()) {
            if (doctorId.equalsIgnoreCase(record.getDoctorId())
                    && record.getFlag() == Flag.PENDING
                    && record.getType() != Type.LEAVE) {
                System.out.println(record.getAppointmentId() + " " + record.getFlag());
            }
        }
    }

    /**
     * Displays appointment records by a specific ID.
     * The ID can be an appointment ID or a patient ID.
     *
     * @param Id the ID to filter appointment records
     * @return the {@link Appointment} object if found by appointment ID, otherwise {@code null}
     * @throws IOException if an error occurs while accessing records
     */
    public Appointment viewRecordsById(String Id) throws IOException {
        System.out.println("For ID " + Id);
        System.out.println(
                "----------------------------------------------------------------------------------------------------------------");
        for (Appointment appointment : appointmentRecords.values()) {
            // Match by appointment ID
            if (Id.equalsIgnoreCase(appointment.getAppointmentId())) {
                System.out.println(appointment.toString());
                return appointment;
            }
            // Match by patient ID
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
