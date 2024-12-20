package Interfaces;

import AppointmentOutcomeSystem.*;
import AppointmentSystem.AppointmentController;
import SessionManager.Session;
import UserSystem.PatientMedicalRecord;
import UserSystem.UpdateInformation;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * The {@code PatientUI} class provides the user interface for patients in the
 * system.
 * Patients can interact with their medical records, manage personal
 * information, and handle appointments.
 */

public class PatientUI {

    /**
     * Launches the patient user interface.
     * <p>
     * The patient can perform the following tasks:
     * <ul>
     * <li>View their medical records</li>
     * <li>Update personal information</li>
     * <li>View available appointment slots</li>
     * <li>Schedule, reschedule, or cancel appointments</li>
     * <li>View scheduled appointments</li>
     * <li>Access past appointment outcome records</li>
     * </ul>
     *
     * @throws IOException if an error occurs during interaction with subsystems
     */
    public void patientUI() throws IOException {
        String patientId = Session.getLoginID();

        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        int option = 0;
        while (option != 9) {
            System.out.println(Session.getName());
            System.out.println("1) View Medical Records");
            System.out.println("2) Update Personal Information");
            System.out.println("3) View Available Appointment Slots");
            System.out.println("4) Schedule an Appointment");
            System.out.println("5) Reschedule an Appointment");
            System.out.println("6) Cancel an Appointment");
            System.out.println("7) View Scheduled Appointments");
            System.out.println("8) View Past Appointment Outcome Records");
            System.out.println("9) Logout");
            while (!sc.hasNextInt()) { // Check if input is an integer
                System.out.println("Option not valid. Please try again:");
                sc.next(); // Clear the invalid input
            }

            option = sc.nextInt();
            sc.nextLine(); // Consumes newline
            AppointmentOutcomeRecordController appointmentOutcomeRecordController = new AppointmentOutcomeRecordController();
            UpdateInformation updateInformation = new UpdateInformation();
            PatientMedicalRecord patientMedicalRecord = new PatientMedicalRecord(patientId);
            AppointmentController appointmentController = new AppointmentController();

            @SuppressWarnings("unused")
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            switch (option) {
                case 1 -> {
                    patientMedicalRecord.patientMedicalRecord();
                    break;
                }
                case 2 -> {
                    updateInformation.updateInformation(patientId);
                    break;
                }
                case 3 -> {
                    appointmentController.viewAllAvailableAppointments();
                    break;
                }
                case 4 -> {
                    appointmentController.patientScheduleAppointment();
                    break;
                }
                case 5 -> {
                    appointmentController.patientReScheduleAppointment();
                    break;
                }
                case 6 -> {
                    appointmentController.patientCancelAppointment();
                    break;
                }
                case 7 -> {
                    appointmentController.viewAllScheduledAppointments();
                    break;
                }
                case 8 -> {
                    appointmentOutcomeRecordController.patientViewPastRecords();
                    break;
                }
                case 9 -> {
                    System.out.println("Logged Out User " + Session.getName());
                    Session.logout();
                    break;
                }
                default -> System.out.println("Invalid choice. Please select a number between 1 and 9.");
            }
        }
    }
}
