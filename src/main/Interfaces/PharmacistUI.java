package Interfaces;

import AppointmentOutcomeSystem.AppointmentOutcomeRecordController;
import MedicineInventorySystem.InventoryController;
import RequestSystem.RequestController;
import SessionManager.Session;
import java.io.IOException;
import java.util.Scanner;

public class PharmacistUI {

    public void pharmacistUI() throws IOException {
        Scanner sc = new Scanner(System.in);
        InventoryController inventoryManager = new InventoryController();
        AppointmentOutcomeRecordController appointmentOutcomeRecordController = new AppointmentOutcomeRecordController();
        RequestController requestController = new RequestController();

        int option = 0;
        while (option != 5) {
            System.out.println(Session.getName());
            System.out.println("1) View Appointment Outcome Record");
            System.out.println("2) Update Prescription Flag");
            System.out.println("3) View Medication Inventory");
            System.out.println("4) Submit Replenishment Requests");
            System.out.println("5) Logout");
            while (!sc.hasNextInt()) { // Check if input is an integer
                System.out.println("Option not valid. Please try again:");
                sc.next(); // Clear the invalid input
            }

            option = sc.nextInt();
            sc.nextLine(); // Consumes Newline
            switch (option) {
                case 1 -> {
                    appointmentOutcomeRecordController.viewPendingRecords();
                    break;
                }
                case 2 -> {
                    appointmentOutcomeRecordController.updatePrescriptionFlag();
                    break;
                }
                case 3 -> {
                    inventoryManager.viewInventory();
                    System.out.println("\nPress enter to return to menu");
                    sc.nextLine();
                    break;
                }
                case 4 -> {
                    requestController.PharmViewRequests();
                    requestController.createNewRequest();
                    break;

                }
                case 5 -> {
                    System.out.println("Logged Out User " + Session.getName());
                    Session.logout();
                    break;
                }
            }
        }
    }
}
