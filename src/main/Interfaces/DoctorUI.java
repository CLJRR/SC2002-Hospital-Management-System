package Interfaces;

import AppointmentOutcomeSystem.AppointmentOutcomeRecordController;
import ApptTest.AppointmentService;
import ApptTest.DoctorController;
import SessionManager.Session;
import UserSystem.*;
import java.io.IOException;
import java.util.Scanner;

public class DoctorUI {

    public void doctorUI() throws IOException {
        Scanner sc = new Scanner(System.in);
        AppointmentOutcomeRecordController appointmentOutcomeRecordController = new AppointmentOutcomeRecordController();
        int option = 0;
        AppointmentService appointmentService = new AppointmentService();
        DoctorController doctorController = new DoctorController(appointmentService);
        DoctorMedicalRecordController doctorMedicalRecordController = new DoctorMedicalRecordController(Session.getLoginID());

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
            option = sc.nextInt();
            sc.nextLine(); // Consumes NewLine
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
                    break;
                }
                case 4 -> {
                    break;
                }
                case 5 -> {
                    break;
                }
                case 6 -> {
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
