package ApptTest;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MainTest {

    public static void main(String[] args) {
        // Initialize appointmentRecords to store appointments
        Map<String, Appointment> appointmentRecords = new HashMap<>();

        // Instantiate ApptLoader and load appointments
        ApptLoader loader = new ApptLoader(appointmentRecords);
        loader.loadInitialAppointments();

        // Instantiate AppointmentService and AppointmentController with loaded appointments
        AppointmentService service = new AppointmentService();
        @SuppressWarnings("unused")
        AppointmentController controller = new AppointmentController();
        ApptViewer viewer = new ApptViewer(appointmentRecords);

        Scanner sc = new Scanner(System.in);

        // Main Test Menu
        while (true) {
            System.out.println("\n--- Appointment System Test Menu ---");
            System.out.println("1. Test Patient Functions");
            System.out.println("2. Test Doctor Functions");
            System.out.println("3. Admin View - All Appointment Records");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> testPatientFunctions(appointmentRecords, service);
                case 2 -> testDoctorFunctions(appointmentRecords, service);
                case 3 -> adminViewAppointments(viewer, sc);
                case 4 -> {
                    System.out.println("Exiting the system.");
                    sc.close();
                    return;
                }
                default -> System.out.println("Invalid choice, please try again.");
            }
        }
    }

    // Patient Functions Testing
    private static void testPatientFunctions(Map<String, Appointment> appointmentRecords, AppointmentService service) {
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Patient ID: ");
        String patientId = sc.nextLine();
        PatientAppointmentScheduler patientScheduler = new PatientAppointmentScheduler(appointmentRecords, patientId);

        while (true) {
            System.out.println("\n--- Patient Testing Menu ---");
            System.out.println("1. View Available Slots and Schedule");
            System.out.println("2. Reschedule Appointment");
            System.out.println("3. View Scheduled Appointments");
            System.out.println("4. Cancel Appointment");
            System.out.println("5. Back to Main Menu");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> patientScheduler.viewAndScheduleAppointment();
                case 2 -> patientScheduler.rescheduleAppointment();
                case 3 -> patientScheduler.viewAllScheduledAppointments();
                case 4 -> patientScheduler.cancelAppointment();
                case 5 -> {
                    System.out.println("Returning to Main Menu.");
                    return;
                }
                default -> System.out.println("Invalid choice, please try again.");
            }
        }
    }

    // Doctor Functions Testing
    private static void testDoctorFunctions(Map<String, Appointment> appointmentRecords, AppointmentService service) {
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Doctor ID: ");
        String doctorId = sc.nextLine();
        DoctorAppointmentScheduler doctorScheduler = new DoctorAppointmentScheduler(appointmentRecords, doctorId);

        while (true) {
            System.out.println("\n--- Doctor Testing Menu ---");
            System.out.println("1. Set Availability for Date");
            System.out.println("2. View Personal Schedule");
            System.out.println("3. Manage Appointment Requests");
            System.out.println("4. View Upcoming Appointments");
            System.out.println("5. Back to Main Menu");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> doctorScheduler.setAvailabilityForDate();
                case 2 -> doctorScheduler.viewPersonalSchedule();
                case 3 -> doctorScheduler.manageAppointmentRequests();
                case 4 -> doctorScheduler.viewUpcomingAppointments();
                case 5 -> {
                    System.out.println("Returning to Main Menu.");
                    return;
                }
                default -> System.out.println("Invalid choice, please try again.");
            }
        }
    }

    // Admin View for All Appointments
    private static void adminViewAppointments(ApptViewer viewer, Scanner sc) {
        while (true) {
            System.out.println("\n--- Admin View ---");
            System.out.println("1. View All Appointment Records");
            System.out.println("2. View Pending Appointment Records");
            System.out.println("3. View Appointment Records by ID");
            System.out.println("4. Back to Main Menu");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> viewer.viewAllRecords();
                case 2 -> {
                    try {
                        viewer.viewPendingRecords();
                    } catch (IOException e) {
                        System.err.println("Error viewing pending records: " + e.getMessage());
                    }
                }
                case 3 -> {
                    System.out.print("Enter Appointment or Patient ID to view records: ");
                    String id = sc.nextLine();
                    try {
                        viewer.viewRecordsById(id);
                    } catch (IOException e) {
                        System.err.println("Error viewing records by ID: " + e.getMessage());
                    }
                }
                case 4 -> {
                    System.out.println("Returning to Main Menu.");
                    return;
                }
                default -> System.out.println("Invalid choice, please try again.");
            }
        }
    }
}
