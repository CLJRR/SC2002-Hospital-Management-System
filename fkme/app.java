
import UI.*;
import java.util.Scanner;

public class app {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PatientLoginUI loginUI = new PatientLoginUI(scanner);
        ApptBookingUI appointmentBookingUI = new ApptBookingUI(scanner);

        while (true) {
            System.out.println("1. Login");
            System.out.println("2. Book Appointment");
            System.out.println("3. Logout");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // consume the newline

            switch (choice) {
                case 1:
                    loginUI.login();
                    break;
                case 2:
                    appointmentBookingUI.bookAppointment();
                    break;
                case 3:
                    Session.logout();
                    System.out.println("Logged out successfully.");
                    break;
                case 4:
                    System.out.println("Exiting application.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
