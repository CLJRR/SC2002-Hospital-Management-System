package ApptTest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class MainTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AppointmentService appointmentService = new AppointmentService();
        
        PatientController patientController = new PatientController(appointmentService);
        DoctorController doctorController = new DoctorController(appointmentService);
        PharmacistController pharmacistController = new PharmacistController(appointmentService);
        AdminController adminController = new AdminController(appointmentService);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        while (true) {
            System.out.println("Select a role: 1) Patient 2) Doctor 3) Pharmacist 4) Admin 5) Exit");
            int role = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            if (role == 5) break;

            switch (role) {
                case 1: // Patient actions
                    System.out.println("1) Schedule Appointment 2) View Appointment 3) Reschedule Appointment 4) Cancel Appointment 5) View Outcome");
                    int patientChoice = scanner.nextInt();
                    scanner.nextLine();

                    if (patientChoice == 1) {
                        System.out.print("Enter Patient ID: ");
                        String patientId = scanner.nextLine();
                        System.out.print("Enter Doctor ID: ");
                        String doctorId = scanner.nextLine();
                        System.out.print("Enter Appointment Date (yyyy-MM-dd): ");
                        LocalDate date = LocalDate.parse(scanner.nextLine(), formatter);
                        System.out.print("Enter Time Slot: ");
                        String timeSlot = scanner.nextLine();

                        Appointment appointment = patientController.scheduleAppointment(patientId, doctorId, date, timeSlot);
                        System.out.println("Scheduled: " + appointment);
                    } else if (patientChoice == 2) {
                        System.out.print("Enter Appointment ID: ");
                        String appointmentId = scanner.nextLine();
                        System.out.println("Appointment: " + patientController.viewAppointment(appointmentId));
                    } else if (patientChoice == 3) {
                        System.out.print("Enter Appointment ID: ");
                        String appointmentId = scanner.nextLine();
                        System.out.print("Enter New Date (yyyy-MM-dd): ");
                        LocalDate newDate = LocalDate.parse(scanner.nextLine(), formatter);
                        System.out.print("Enter New Time Slot: ");
                        String newTimeSlot = scanner.nextLine();

                        boolean rescheduled = patientController.rescheduleAppointment(appointmentId, newDate, newTimeSlot);
                        System.out.println(rescheduled ? "Rescheduled successfully" : "Failed to reschedule");
                    } else if (patientChoice == 4) {
                        System.out.print("Enter Appointment ID: ");
                        String appointmentId = scanner.nextLine();
                        boolean cancelled = patientController.cancelAppointment(appointmentId);
                        System.out.println(cancelled ? "Cancelled successfully" : "Failed to cancel");
                    } else if (patientChoice == 5) {
                        System.out.print("Enter Appointment ID: ");
                        String appointmentId = scanner.nextLine();
                        System.out.println("Outcome: " + patientController.viewAppointmentOutcome(appointmentId));
                    }
                    break;

                case 2: // Doctor actions
                    System.out.print("Enter Appointment ID: ");
                    String appointmentId = scanner.nextLine();
                    System.out.print("Enter Outcome: ");
                    String outcome = scanner.nextLine();
                    System.out.print("Enter Prescribed Medication: ");
                    String meds = scanner.nextLine();

                    boolean outcomeUpdated = doctorController.updateAppointmentOutcome(appointmentId, outcome, meds);
                    System.out.println(outcomeUpdated ? "Outcome updated successfully" : "Failed to update outcome");
                    break;

                case 3: // Pharmacist actions
                    System.out.print("Enter Appointment ID: ");
                    appointmentId = scanner.nextLine();
                    System.out.println("Outcome: " + pharmacistController.viewAppointmentOutcome(appointmentId));
                    break;

                case 4: // Admin actions
                    System.out.print("1) View All Appointments 2) Reschedule Appointment\nSelect option: ");
                    int adminChoice = scanner.nextInt();
                    scanner.nextLine();

                    if (adminChoice == 1) {
                        adminController.listAllAppointments();
                    } else if (adminChoice == 2) {
                        System.out.print("Enter Appointment ID: ");
                        appointmentId = scanner.nextLine();
                        System.out.print("Enter New Date (yyyy-MM-dd): ");
                        LocalDate newDate = LocalDate.parse(scanner.nextLine(), formatter);
                        System.out.print("Enter New Time Slot: ");
                        String newTimeSlot = scanner.nextLine();

                        boolean rescheduled = adminController.updateScheduledAppointment(appointmentId, newDate, newTimeSlot);
                        System.out.println(rescheduled ? "Appointment rescheduled successfully" : "Failed to reschedule appointment");
                    }
                    break;

                default:
                    System.out.println("Invalid selection.");
            }
        }

        scanner.close();
    }
}
