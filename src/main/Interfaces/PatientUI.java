package Interfaces;

import AppointmentOutcomeRecordSystem.ViewAppointmentOutcomeRecords;
import ApptTest.Appointment;
import ApptTest.AppointmentService;
import ApptTest.ApptOutcome;
import ApptTest.PatientController;
import SessionManager.Session;
import UserSystem.UpdateInformation;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class PatientUI {

    public void patientUI() throws IOException {
        String patientId = Session.getLoginID();
        ViewAppointmentOutcomeRecords viewApptRecord = new ViewAppointmentOutcomeRecords();

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
            option = sc.nextInt();

            ApptOutcome apptOutcome = new ApptOutcome();
            AppointmentService appointmentService = new AppointmentService();
            PatientController patientController = new PatientController(appointmentService);
            UpdateInformation updateInformation = new UpdateInformation();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-mm-dd");

            switch (option) {
                case 1 -> {

                    break;
                }
                case 2 -> {
                try {
                    updateInformation.updateInformation(patientId);
                } catch (IOException ex) {
                }
                    break;
                }
                case 3 -> {
                    break;
                }
                case 4 -> {
                    System.out.print("Enter Doctor ID: ");
                    String doctorId = sc.nextLine();
                    System.out.print("Enter Appointment Date (yyyy-MM-dd): ");
                    LocalDate date = LocalDate.parse(sc.nextLine(), formatter);
                    System.out.print("Enter Time Slot: ");
                    String timeSlot = sc.nextLine();

                    Appointment appointment = patientController.scheduleAppointment(patientId, doctorId, date, timeSlot);
                    System.out.println("Scheduled: " + appointment);
                    break;
                }
                case 5 -> {
                    System.out.print("Enter Appointment ID: ");
                    String appointmentId = sc.nextLine();
                    System.out.print("Enter New Date (yyyy-MM-dd): ");
                    LocalDate newDate = LocalDate.parse(sc.nextLine(), formatter);
                    System.out.print("Enter New Time Slot: ");
                    String newTimeSlot = sc.nextLine();

                    boolean rescheduled = patientController.rescheduleAppointment(appointmentId, newDate, newTimeSlot);
                    System.out.println(rescheduled ? "Rescheduled successfully" : "Failed to reschedule");
                    break;
                }
                case 6 -> {
                    System.out.print("Enter Appointment ID: ");
                    String appointmentId = sc.nextLine();
                    boolean cancelled = patientController.cancelAppointment(appointmentId);
                    System.out.println(cancelled ? "Cancelled successfully" : "Failed to cancel");
                    break;
                }
                case 7 -> {
                    break;
                }
                case 8 -> {
                    viewApptRecord.viewRecordsById(Session.getLoginID());
                    apptOutcome.viewPastAppointmentOutcomes(patientId);
                    break;
                }
                case 9 -> {
                    Session.logout();
                    break;
                }
            }
        }
    }
}
