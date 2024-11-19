package ReceptionistSystem;

import UserSystem.GetUser;
import UserSystem.User;
import UserSystem.UserService;
import enums.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class NewPatientCreator {

    private static final Scanner scanner = new Scanner(System.in);

    public void createNewPatient() throws IOException {
        UserService service = new UserService();
        List<User> users = new ArrayList<>();
        users = service.load();
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

        while (true) {
            System.out.println("Enter Patient Name (or type 'x' to go x):");
            name = scanner.nextLine().trim();
            if (name.equalsIgnoreCase("x")) {
                continue;
            }
            if (!name.isEmpty()) {
                break;
            }
            System.out.println("Error: Name cannot be empty. Please try again.");
        }

        while (true) {
            System.out.println("Enter Patient Date of Birth (yyyy-mm-dd) (or type 'x' to go x):");
            String dobInput = scanner.nextLine().trim();
            if (dobInput.equalsIgnoreCase("x")) {
                continue;
            }
            try {
                dateOfBirth = LocalDate.parse(dobInput);
                break;
            } catch (Exception e) {
                System.out.println("Invalid date format. Please enter in yyyy-mm-dd format.");
            }
        }

        while (true) {
            System.out.println("Enter Patient Gender (MALE / FEMALE / OTHER) (or type 'x' to go x):");
            String genderInput = scanner.nextLine().trim().toUpperCase();
            if (genderInput.equalsIgnoreCase("x")) {
                continue;
            }
            try {
                gender = Gender.valueOf(genderInput);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid gender. Please enter 'M' for Male or 'F' for Female.");
            }
        }

        while (true) {
            System.out.println("Enter Patient Phone Number (or type 'x' to go x):");
            String inputPhoneNumber = scanner.nextLine().trim(); // Use a separate variable for input
            if (inputPhoneNumber.equalsIgnoreCase("x")) {
                continue;
            }
            if (!inputPhoneNumber.matches("\\d{8,15}")) { // Validate phone number format
                System.out.println("Error: Invalid phone number format. Please try again.");
                continue;
            }
            // Use the inputPhoneNumber as final in lambda
            if (users.stream().anyMatch(user -> Objects.equals(inputPhoneNumber, user.getPhoneNumber()))) {
                System.out.println("Error: Phone number already exists in the system. Please try again.");
                continue;
            }
            phoneNumber = inputPhoneNumber; // Assign to the actual phoneNumber variable after validation
            break; // Valid phone number
        }

        while (true) {
            System.out.println("Enter Patient Email (or type 'back' to go back):");
            String inputEmail = scanner.nextLine().trim();
            if (inputEmail.equalsIgnoreCase("back")) {
                continue;
            }
            if (!inputEmail.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) { // Simple email validation
                System.out.println("Error: Invalid email format. Please try again.");
                continue;
            }
            if (users.stream().anyMatch(user -> inputEmail.equalsIgnoreCase(user.getEmail()))) {
                System.out.println("Error: Email already exists in the system. Please try again.");
                continue;
            }
            email = inputEmail; // Assign valid input to email
            break;
        }

        while (true) {
            System.out.println("Enter Patient Blood Type (or type 'x' to go x):");
            bloodType = scanner.nextLine().trim();
            if (bloodType.equalsIgnoreCase("x")) {
                continue;
            }
            if (!bloodType.isEmpty()) {
                break;
            }
            System.out.println("Error: Blood type cannot be empty.");
        }

        // Generate unique ID for the patient
        String id = generatePatientId();

        // Create a new patient object
        User newPatient = new User(id, name, dateOfBirth, gender, phoneNumber, email, bloodType, Role.PATIENT);

        // Confirm the patient's details before adding
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

    private String generatePatientId() {
        GetUser getUser = new GetUser();
        List<User> currentPatients = getUser.getAllPatients();
        int nextId = currentPatients.size() + 1;

        if (nextId > 999) {
            throw new IllegalStateException("Maximum patient ID limit reached (P1999). Cannot generate a new ID.");
        }

        // Format nextId to ensure it is zero-padded to 3 digits
        return String.format("P1%03d", nextId);
    }
}
