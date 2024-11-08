package Interfaces;

import AppointmentOutcomeRecordSystem.UpdatePrescriptionStatus;
import ApptTest.AppointmentService;
import ApptTest.PharmacistController;
import MedicineInventorySystem.InventoryManager;
import SessionManager.Session;
import java.io.IOException;
import java.util.Scanner;

public class PharmacistUI {

    public void pharmacistUI() throws IOException {
        Scanner sc = new Scanner(System.in);
        UpdatePrescriptionStatus updatePrescriptionStatus = new UpdatePrescriptionStatus();
        InventoryManager inventoryManager = new InventoryManager();
        AppointmentService appointmentService = new AppointmentService();
        PharmacistController pharmacistController = new PharmacistController(appointmentService);

        int option = 0;
        while (option != 5) {
            System.out.println(Session.getName());
            System.out.println("1) View Appointment Outcome Record");
            System.out.println("2) Update Prescription Status");
            System.out.println("3) View Medication Inventory");
            System.out.println("4) Submit Replenishment Requests");
            System.out.println("5) Logout");
            option = sc.nextInt();
            switch (option) {
                case 1 -> {
                    System.out.print("Enter Appointment ID: ");
                    String appointmentId = sc.nextLine();
                    System.out.println("Outcome: " + pharmacistController.viewAppointmentOutcome(appointmentId));
                    break;
                }
                case 2 -> {
                    updatePrescriptionStatus.updatePrescriptionStatus();
                    break;
                }
                case 3 -> {
                    inventoryManager.viewInventory();
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
