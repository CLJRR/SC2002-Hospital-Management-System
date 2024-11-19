/**
 * The {@code AdminUI} class represents the user interface for administrators in the system.
 * It provides options to manage hospital staff, view and manage appointments, manage medication inventory,
 * and approve replenishment requests. This class interacts with various subsystems to perform administrative tasks.
 */
package Interfaces;

import AppointmentSystem.*;
import MedicineInventorySystem.*;
import RequestSystem.RequestController;
import SessionManager.Session;
import UserSystem.AdminHospitalStaff;
import java.io.IOException;
import java.util.Scanner;

public class AdminUI {

    /**
     * Launches the administrator user interface.
     * The administrator can perform tasks such as:
     * <ul>
     *   <li>Managing hospital staff</li>
     *   <li>Viewing appointment details</li>
     *   <li>Managing medication inventory</li>
     *   <li>Approving replenishment requests</li>
     * </ul>
     *
     * @throws IOException if an error occurs during input or interaction with subsystems
     */
    public void adminUI() throws IOException {
        Scanner sc = new Scanner(System.in);
        int option = 0;
        AdminInventory invSystem = new AdminInventory();
        AppointmentController appointmentController = new AppointmentController();
        AdminHospitalStaff staffSystem = new AdminHospitalStaff();
        RequestController requestController = new RequestController();

        while (option != 5) {
            System.out.println(Session.getName());
            System.out.println("1) View and Manage Hospital Staff");
            System.out.println("2) View Appointment details");
            System.out.println("3) View and Manage Medication Inventory");
            System.out.println("4) Approve Replenishment Requests");
            System.out.println("5) Logout");
            System.out.print("Please select an option: ");

            while (!sc.hasNextInt()) { // Check if input is an integer
                System.out.println("Option not valid. Please try again:");
                sc.next(); // Clear the invalid input
            }

            option = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (option) {
                case 1 -> {
                    staffSystem.adminHospitalStaff();
                    break;
                }
                case 2 -> {
                    appointmentController.adminViewAllRecords();
                    break;
                }
                case 3 -> {
                    invSystem.adminInventory();
                    break;
                }
                case 4 -> {
                    requestController.viewPendingRequests();
                    requestController.updateRequestFlag();
                    break;
                }
                case 5 -> {
                    System.out.println("Logged Out User " + Session.getName());
                    Session.logout();
                    break;
                }
                default -> System.out.println("Invalid choice. Please select a number between 1 and 5.");
            }
        }
    }
}
