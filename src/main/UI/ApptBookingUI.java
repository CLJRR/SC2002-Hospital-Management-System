package UI;

import java.util.Scanner;

public class ApptBookingUI {
    private Scanner scanner;

    public ApptBookingUI(Scanner scanner) {
        this.scanner = scanner;
    }

    public void bookAppointment() {
        if (!Session.isLoggedIn()) {
            System.out.println("You must log in before booking an appointment.");
            return;
        }

        System.out.print("Please enter appointment date (YYYY-MM-DD): ");
        String date = scanner.nextLine();
        System.out.print("Please enter time (HH:MM): ");
        String time = scanner.nextLine();

        // Use the patientID from Session
        String patientID = Session.getPatientID();
        System.out.println("Appointment booked for patient ID " + patientID + " on " + date + " at " + time);
    }
}
