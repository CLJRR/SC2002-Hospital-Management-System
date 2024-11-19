package ReceptionistSystem;

import java.io.IOException;
import java.util.Scanner;

public class ReceptionistController {

    private static final Scanner scanner = new Scanner(System.in);
    private final NewPatientCreator newPatientCreator;

    public ReceptionistController() {
        this.newPatientCreator = new NewPatientCreator();

    }

    public void startReceptionistSystem() throws IOException {
        while (true) {
            int choice = -1; // Initialize with an invalid value

            // Input validation loop
            while (choice == -1) {
                System.out.println("Receptionist Menu:");
                System.out.println("1. Add New Patient");
                System.out.println("2. Exit");
                System.out.print("Choose an option: ");

                if (scanner.hasNextInt()) {
                    choice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    if (choice < 1 || choice > 2) {
                        System.out.println("Invalid input. Please enter 1 or 2.");
                        choice = -1; // Reset choice to keep looping
                    }
                } else {
                    System.out.println("Invalid input. Please enter a number between 1 and 2.");
                    scanner.next(); // Clear the invalid input
                }
            }

            // Process valid input
            switch (choice) {
                case 1 -> {
                    try {
                        newPatientCreator.createNewPatient(); // Call to create new patient
                    } catch (Exception e) {
                        System.out.println("An error occurred while creating a new patient. Please try again.");
                        e.printStackTrace();
                    }
                }
                case 2 -> {
                    System.out.println("Exiting Receptionist System.");
                    return;
                }
                default ->
                    System.out.println("Unexpected error. Returning to main menu.");
            }
        }
    }

}
