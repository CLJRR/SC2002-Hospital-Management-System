package UI;

import java.util.Scanner;

public class PatientLoginUI {
    private Scanner scanner;

    public PatientLoginUI(Scanner scanner) {
        this.scanner = scanner;
    }

    // Method to prompt user for ID and simulate login
    public void login() {
        System.out.print("Please enter your ID: ");
        String patientID = scanner.nextLine();

        // Save patientID in Session after successful login
        Session.setPatientID(patientID);
        System.out.println("Login successful for patient ID: " + patientID);
    }
}
