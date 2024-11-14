package Interfaces;

import AppointmentOutcomeSystem.AppointmentOutcomeRecordController;
import AppointmentSystem.AppointmentController;
import SessionManager.Session;
import UserSystem.*;
import java.io.IOException;
import java.util.Scanner;

public class DoctorUI {

    public void doctorUI() throws IOException {
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
            sc.nextLine(); // Consumes Newline

            switch (option) {
                case 1 -> {
                    doctorMedicalRecordController.viewRecords();
                    break;
                }
                case 2 -> {
                    System.out.println("Enter Appointment ID to update: ");
                    String apptId = sc.nextLine();
                    doctorMedicalRecordController.updateRecords(apptId);
                    break;
                }
                case 3 -> {
                    appointmentController.doctorScheduleViewer();
                    break;
                }
                case 4 -> {
                    appointmentController.doctorCancelLeave();
                    appointmentController.doctorCancelLeaveByTimeslot();
                    appointmentController.doctorSetLeave();
                    appointmentController.doctorSetLeaveByTimeslot();
                    break;
                }
                case 5 -> {
                    appointmentController.viewPendingRecords(); // prints pending reecords for tht doctor
                    // choice 2: update by ID
                    appointmentController.updateAppointmentFlag();

                    break;
                }
                case 6 -> {
                    appointmentController.doctorScheduleViewerByDay();

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
            }
        }
    }
}
