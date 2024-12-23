
package Interfaces;

import AppointmentOutcomeSystem.AppointmentOutcomeRecordController;
import AppointmentSystem.AppointmentController;
import SessionManager.Session;
import UserSystem.*;
import java.io.IOException;
import java.util.Scanner;

/**
 * The {@code DoctorUI} class represents the user interface for doctors in the
 * system.
 * It provides options to manage medical records, view schedules, set
 * availability, handle appointment requests,
 * and record appointment outcomes. This class interacts with various
 * controllers to facilitate these tasks.
 */

public class DoctorUI {

    /**
     * Launches the doctor user interface. The doctor can perform tasks such as:
     * <ul>
     * <li>Viewing and updating patient medical records</li>
     * <li>Viewing personal schedules</li>
     * <li>Setting or canceling availability for appointments</li>
     * <li>Accepting or declining appointment requests</li>
     * <li>Viewing upcoming appointments</li>
     * <li>Recording appointment outcomes</li>
     * </ul>
     *
     * @throws IOException if an error occurs during input or interaction with
     *                     subsystems
     */
    public void doctorUI() throws IOException {
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        AppointmentOutcomeRecordController appointmentOutcomeRecordController = new AppointmentOutcomeRecordController();
        int option = 0;

        AppointmentController appointmentController = new AppointmentController();
        DoctorMedicalRecordController doctorMedicalRecordController = new DoctorMedicalRecordController(
                Session.getLoginID());

        while (option != 8) {
            System.out.println(Session.getName());
            System.out.println("1) View Patient Medical Records");
            System.out.println("2) Update Patient Medical Records");
            System.out.println("3) View Personal Schedule");
            System.out.println("4) Set Availability for Appointments");
            System.out.println("5) Accept or Decline Appointment Requests");
            System.out.println("6) View Upcoming Appointments");
            System.out.println("7) Record Appointment Outcome");
            System.out.println("8) Logout");
            while (!sc.hasNextInt()) { // Check if input is an integer
                System.out.println("Option not valid. Please try again:");
                sc.next(); // Clear the invalid input
            }

            option = sc.nextInt();
            sc.nextLine(); // Consumes newline

            switch (option) {
                case 1 -> {
                    while (true) {
                        System.out.println("Menu:");
                        System.out.println("1. View all medical records");
                        System.out.println("2. View medical records by patient ID");
                        System.out.println("3. Exit");
                        System.out.print("Enter your choice: ");

                        int choice = -1; // Initialize with an invalid value
                        while (true) {
                            System.out.print("Enter your choice: ");
                            if (sc.hasNextInt()) {
                                choice = sc.nextInt();
                                sc.nextLine(); // Consume newline
                                break; // Exit the loop if a valid integer is entered
                            } else {
                                System.out.println("Invalid input. Please enter an integer.");
                                sc.next(); // Clear the invalid input
                            }
                        }

                        switch (choice) {
                            case 1 -> {
                                System.out.println("Fetching all medical records...");
                                doctorMedicalRecordController.viewRecords(); // View all records
                            }
                            case 2 -> {
                                System.out.print("Enter patient ID to view records: ");
                                String patientId = sc.nextLine().toUpperCase();
                                doctorMedicalRecordController.viewRecordsById(patientId); // View records by patient ID
                            }
                            case 3 -> {
                                System.out.println("Exiting...");
                                break; // Exit the menu
                            }
                            default ->
                                System.out.println("Invalid choice. Please try again.");
                        }

                        // Exit the loop if the user selects option 3
                        if (choice == 3) {
                            break;
                        }

                        System.out.println(); // Add a line break for better readability
                    }

                    break; // Exit the main case
                }
                case 2 -> {
                    appointmentOutcomeRecordController.loadRecords();
                    System.out.println("Enter Appointment ID to update: ");
                    String apptId = sc.nextLine().toUpperCase();
                    doctorMedicalRecordController.updateRecords(apptId);
                    break;
                }
                case 3 -> {
                    appointmentController.doctorScheduleViewer();
                    break;
                }
                case 4 -> {
                    System.out.println("1) Set Leave for the day");
                    System.out.println("2) Cancel Leave for the day");
                    System.out.println("3) Set Leave for one timeslot");
                    System.out.println("4) Cancel Leave for one timeslot");

                    while (!sc.hasNextInt()) { // Check if input is an integer
                        System.out.println("Option not valid. Please try again:");
                        sc.next(); // Clear the invalid input
                    }
                    option = sc.nextInt();
                    sc.nextLine(); // Consumes newline
                    if (option == 1) {
                        appointmentController.doctorSetLeave();
                    }
                    if (option == 2) {
                        appointmentController.doctorCancelLeave();
                    }
                    if (option == 3) {
                        appointmentController.doctorSetLeaveByTimeslot();
                    }
                    if (option == 4) {
                        appointmentController.doctorCancelLeaveByTimeslot();
                    }
                    if (option < 1 || option > 4) {
                        System.out.println("Invalid Input.");
                    }
                    break;
                }
                case 5 -> {
                    appointmentController.viewPendingRecords(); // prints pending records for the doctor
                    appointmentController.updateAppointmentFlag();
                    break;
                }
                case 6 -> {
                    appointmentController.doctorViewConfirmedAppt();
                    break;
                }
                case 7 -> {
                    appointmentOutcomeRecordController.recordAppointmentOutcome();
                    break;
                }
                case 8 -> {
                    System.out.println("Logged Out User " + Session.getName());
                    Session.logout();
                    break;
                }

                default ->
                    System.out.println("Invalid choice. Please select a number between 1 and 8.");
            }
        }
    }
}
