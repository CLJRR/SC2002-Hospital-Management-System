package ReceptionistSystem;

import java.io.IOException;
import java.util.Scanner;

/**
 * The {@code ReceptionistController} class provides the interface and
 * functionality for a receptionist
 * to manage system operations such as adding new patients.
 * It provides a menu-driven interface with input validation for user actions.
 */

public class ReceptionistController {

    private static final Scanner scanner = new Scanner(System.in);
    private final NewPatientCreator newPatientCreator;

    /**
     * Constructs a new {@code ReceptionistController} and initializes the
     * {@code NewPatientCreator}.
     */
    public ReceptionistController() {
        this.newPatientCreator = new NewPatientCreator();
    }

    /**
     * Starts the receptionist system, displaying a menu for the receptionist to add
     * new patients or exit.
     * Input is validated to ensure valid choices are processed.
     *
     * @throws IOException if an I/O error occurs during patient creation.
     */
    public void startReceptionistSystem() throws IOException {
        while (true) {
            int choice = -1; // Initialize with an invalid value

            // Input validation loop
            while (choice == -1) {
                System.out.println("Receptionist Menu:");
                System.out.println("1. Add New Patient");
                System.out.println("2. Logout");
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
                    newPatientCreator.createNewPatient(); // Call to create new patient
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
