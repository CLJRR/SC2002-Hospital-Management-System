package Interfaces;
import AppointmentOutcomeRecordSystem.ViewAppointmentOutcomeRecords;
import ApptTest.AppointmentService;
import ApptTest.DoctorController;
import SessionManager.Session;
import java.io.IOException;
import java.util.Scanner;

public class DoctorUI {
    public void doctorUI() throws IOException {
        Scanner sc = new Scanner(System.in);
        ViewAppointmentOutcomeRecords viewRecord = new ViewAppointmentOutcomeRecords();
        int option = 0;
        AppointmentService appointmentService = new AppointmentService();
        DoctorController doctorController = new DoctorController(appointmentService);
        
        while (option != 8)
        {
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
            switch(option)
            {
                case 1 -> {
                }
                case 2 -> {
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
                    System.out.print("Enter Appointment ID: ");
                    String appointmentId = sc.nextLine();
                    System.out.print("Enter Outcome: ");
                    String outcome = sc.nextLine();
                    System.out.print("Enter Prescribed Medication: ");
                    String meds = sc.nextLine();

                    boolean outcomeUpdated = doctorController.updateAppointmentOutcome(appointmentId, outcome, meds);
                    System.out.println(outcomeUpdated ? "Outcome updated successfully" : "Failed to update outcome");
                    break;
                }
                case 8 -> {
                    Session.logout();
                    break;
                }
            }
        }
    }
}
