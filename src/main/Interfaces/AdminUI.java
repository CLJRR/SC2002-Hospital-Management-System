package Interfaces;

import ApptTest.*;
import MedicineInventorySystem.*;
import SessionManager.Session;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class AdminUI {

    public void adminUI() {
        Scanner sc = new Scanner(System.in);
        int option = 0;
        AdminInventory invSystem = new AdminInventory();
        AppointmentService appService = new AppointmentService();
        AdminController adminController = new AdminController(appService);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-mm-dd");

        while (option != 5) {
            System.out.println(Session.getName());
            System.out.println("1) View and Manage Hospital Staff");
            System.out.println("2) View Appointment details");
            System.out.println("3) View and Manage Medication Inventory");
            System.out.println("4) Approve Replenishment Requests");
            System.out.println("5) Logout");
            option = sc.nextInt();
            switch (option) {
                case 1 -> {
                    break;
                }
                case 2 -> {
                    System.out.print("1) View All Appointments 2) Reschedule Appointment\nSelect option: ");
                    int adminChoice = sc.nextInt();
                    sc.nextLine();

                    if (adminChoice == 1) {
                        adminController.listAllAppointments();
                    } else if (adminChoice == 2) {
                        System.out.print("Enter Appointment ID: ");
                        String appointmentId = sc.nextLine();
                        System.out.print("Enter New Date (yyyy-MM-dd): ");
                        LocalDate newDate = LocalDate.parse(sc.nextLine(), formatter);
                        System.out.print("Enter New Time Slot: ");
                        String newTimeSlot = sc.nextLine();

                        boolean rescheduled = adminController.updateScheduledAppointment(appointmentId, newDate, newTimeSlot);
                        System.out.println(rescheduled ? "Appointment rescheduled successfully" : "Failed to reschedule appointment");
                    }
                    break;
                }
                case 3 -> {
                    invSystem.adminInventory();
                    break;
                }
                case 4 -> {
                    break;
                }
                case 5 -> {
                    Session.logout();
                    break;
                }
            }
        }
    }
}
