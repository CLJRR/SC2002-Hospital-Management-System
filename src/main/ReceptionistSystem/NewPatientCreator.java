package ReceptionistSystem;

import UserSystem.GetUser;
import UserSystem.User;
import UserSystem.UserService;
import enums.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

/**
 * The {@code NewPatientCreator} class provides functionality to create a new
 * patient in the system.
 * It gathers input from the user, validates the input, and adds the patient to
 * the database.
 * The class ensures unique identifiers and checks for duplicate entries before
 * saving.
 */

public class NewPatientCreator {

    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Creates a new patient by prompting the receptionist for details such as name,
     * date of birth, gender,
     * phone number, email, and blood type. Validates input and ensures that no
     * duplicate records are added.
     *
     * @throws IOException if there is an error reading from or writing to the user
     *                     database.
     */
    public void createNewPatient() throws IOException {
        UserService service = new UserService();
        List<User> users = service.load();

        if (users == null) {
            System.out.println("Error: Unable to load users. Operation canceled.");
            return;
        }

        String name = null;
        LocalDate dateOfBirth = null;
        Gender gender = null;
        String phoneNumber = null;
        String email = null;
        String bloodType = null;

        // Step-by-step input and validation
        while (true) {
            System.out.println("Enter Patient Name (or type 'x' to cancel):");
            name = scanner.nextLine().trim();
            if (name.equalsIgnoreCase("x")) {
                return;
            }
            if (!name.isEmpty()) {
                break;
            }
            System.out.println("Error: Name cannot be empty. Please try again.");
        }

        // Input and validate date of birth
        while (true) {
            System.out.println("Enter Patient Date of Birth (yyyy-mm-dd) (or type 'x' to cancel):");
            String dobInput = scanner.nextLine().trim();
            if (dobInput.equalsIgnoreCase("x")) {
                return;
            }
            try {
                dateOfBirth = LocalDate.parse(dobInput);
                break;
            } catch (Exception e) {
                System.out.println("Invalid date format. Please enter in yyyy-mm-dd format.");
            }
        }

        // Input and validate gender
        while (true) {
            System.out.println("Enter Patient Gender (MALE / FEMALE / OTHER) (or type 'x' to cancel):");
            String genderInput = scanner.nextLine().trim().toUpperCase();
            if (genderInput.equalsIgnoreCase("x")) {
                return;
            }
            try {
                gender = Gender.valueOf(genderInput);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid gender. Please enter 'MALE', 'FEMALE', or 'OTHER'.");
            }
        }

        // Input and validate phone number
        while (true) {
            System.out.println("Enter Patient Phone Number (or type 'x' to cancel):");
            String inputPhoneNumber = scanner.nextLine().trim();
            if (inputPhoneNumber.equalsIgnoreCase("x")) {
                return;
            }
            if (!inputPhoneNumber.matches("\\d{8,15}")) {
                System.out.println("Error: Invalid phone number format. Please try again.");
                continue;
            }
            if (users.stream().anyMatch(user -> Objects.equals(inputPhoneNumber, user.getPhoneNumber()))) {
                System.out.println("Error: Phone number already exists in the system. Please try again.");
                return;
            }
            phoneNumber = inputPhoneNumber;
            break;
        }

        // Input and validate email
        while (true) {
            System.out.println("Enter Patient Email (or type 'back' to go back):");
            String inputEmail = scanner.nextLine().trim();
            if (inputEmail.equalsIgnoreCase("back")) {
                continue;
            }
            if (!inputEmail.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
                System.out.println("Error: Invalid email format. Please try again.");
                continue;
            }
            if (users.stream().anyMatch(user -> inputEmail.equalsIgnoreCase(user.getEmail()))) {
                System.out.println("Error: Email already exists in the system. Please try again.");
                continue;
            }
            email = inputEmail;
            break;
        }

        // Input blood type
        while (true) {
            System.out.println("Enter Patient Blood Type (or type 'x' to cancel):");
            bloodType = scanner.nextLine().trim();
            if (bloodType.equalsIgnoreCase("x")) {
                continue;
            }
            if (!bloodType.isEmpty()) {
                break;
            }
            System.out.println("Error: Blood type cannot be empty.");
        }

        // Generate a unique patient ID
        String id = generatePatientId();

        // Create a new User object for the patient
        User newPatient = new User(id, name, dateOfBirth, gender, phoneNumber, email, bloodType, Role.PATIENT);

        // Confirm the details before saving
        System.out.println("New Patient Details:");
        System.out.println("ID: " + newPatient.getId());
        System.out.println("Name: " + newPatient.getName());
        System.out.println("Date of Birth: " + newPatient.getDateOfBirth());
        System.out.println("Gender: " + newPatient.getGender());
        System.out.println("Phone Number: " + newPatient.getPhoneNumber());
        System.out.println("Email: " + newPatient.getEmail());
        System.out.println("Blood Type: " + newPatient.getBloodType());
        System.out.println("Confirm to save this patient? (y/others)");

        String confirmation = scanner.nextLine().trim().toLowerCase();
        if (confirmation.equals("y")) {
            users.add(newPatient);
            service.save(users);
            System.out.println("Patient saved successfully.");
        } else {
            System.out.println("Patient creation canceled.");
        }
    }

    /**
     * Generates a unique patient ID in the format "P1XXX", where "XXX" is a
     * zero-padded sequential number.
     *
     * @return a unique patient ID.
     */
    private String generatePatientId() {
        GetUser getUser = new GetUser();
        List<User> currentPatients = getUser.getAllPatients();
        int nextId = currentPatients.size() + 1;

        if (nextId > 999) {
            throw new IllegalStateException("Maximum patient ID limit reached (P1999). Cannot generate a new ID.");
        }

        return String.format("P1%03d", nextId);
    }
}
