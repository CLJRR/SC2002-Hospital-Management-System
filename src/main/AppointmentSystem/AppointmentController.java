package AppointmentSystem;

import SessionManager.Session;
import enums.Flag;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class AppointmentController {

    private Map<String, Appointment> appointmentRecords;
    private ApptLoader loader;
    private ApptSaver saver;
    private ApptRecordViewer viewer;
    private DoctorScheduleViewer doctorScheduleViewer;
    private DoctorLeaveSetter doctorLeaveSetter;
    private AppointmentFlagUpdater appointmentFlagUpdater;
    private PatientApptViewer patientApptViewer;
    private PatientAppointmentScheduler patientAppointmentScheduler;
    static final Scanner sc = new Scanner(System.in);

    public AppointmentController() {
        this.appointmentRecords = new HashMap<>();
        this.loader = new ApptLoader(appointmentRecords);
        this.saver = new ApptSaver(appointmentRecords);
        this.viewer = new ApptRecordViewer(appointmentRecords);
        this.doctorScheduleViewer = new DoctorScheduleViewer(appointmentRecords);
        this.doctorLeaveSetter = new DoctorLeaveSetter(appointmentRecords);
        this.appointmentFlagUpdater = new AppointmentFlagUpdater(appointmentRecords);
        this.patientApptViewer = new PatientApptViewer(appointmentRecords);
        this.patientAppointmentScheduler = new PatientAppointmentScheduler(appointmentRecords);
        loader.loadInitialAppointments();
    }

    // Method to load appointment records
    public void loadRecords() {
        loader.loadInitialAppointments();
    }

    // Method to save appointment records
    public void saveRecords() {
        saver.saveRecords();
    }

    //ADMIN
    public void adminViewAllRecords() throws IOException {
        loader.loadInitialAppointments();
        System.out.println(" ");
        viewer.adminViewAllRecords();
        saver.saveRecords();

    }

    //DOCTOR
    //view Schedule for 3 days
    public void doctorScheduleViewer() {
        loader.loadInitialAppointments();
        System.out.println("Please enter date in yyyy-mm-dd format");
        String dateInput = sc.next();
        LocalDate date;
        try {
            date = LocalDate.parse(dateInput);
        } catch (Exception e) {
            System.out.println("Invalid date format. Please enter in yyyy-mm-dd format.");
            return;
        }
        doctorScheduleViewer.viewDoctorScheduleForNextThreeDays(Session.getLoginID(), date);
        saver.saveRecords();
        System.out.println("Press Enter to go back");
        sc.nextLine();
    }

    //view Schedule for day
    public void doctorScheduleViewerByDay() {
        loader.loadInitialAppointments();
        System.out.println("Please enter date in yyyy-mm-dd format");
        String dateInput = sc.next();
        LocalDate date;
        try {
            date = LocalDate.parse(dateInput);
        } catch (Exception e) {
            System.out.println("Invalid date format. Please enter in yyyy-mm-dd format.");
            return;
        }
        doctorScheduleViewer.viewDoctorScheduleForDate(Session.getLoginID(), date);
        saver.saveRecords();
    }

    public void doctorViewConfirmedAppt() {
        loader.loadInitialAppointments(); // Load all appointments

        // Fetch and filter appointments for the logged-in doctor with CONFIRMED flag
        List<Appointment> filteredAppointments = appointmentRecords.values().stream()
                .filter(appt -> appt.getDoctorId().equalsIgnoreCase(Session.getLoginID()) // Match logged-in doctor
                && appt.getFlag() == Flag.CONFIRMED) // Only confirmed appointments
                .collect(Collectors.toList());

        // Display filtered appointments
        if (filteredAppointments.isEmpty()) {
            System.out.println("No confirmed appointments for the logged-in doctor.");
        } else {
            System.out.println("Confirmed Appointments for Doctor " + Session.getLoginID() + ":");
            for (Appointment appt : filteredAppointments) {
                System.out.println("Appointment ID: " + appt.getAppointmentId());
                System.out.println("Patient ID: " + appt.getPatientId());
                System.out.println("Date: " + appt.getDate()); // Print the appointment date
                System.out.println("Time: " + appt.getTimeSlot());
                System.out.println("--------------------------------------------");
            }
        }

        saver.saveRecords(); // Save updated records if needed
        System.out.println("Press Enter to go back");
        sc.nextLine();
    }

    //update Personal Schedule
    // set leave for all time slots of a day
    public void doctorSetLeave() {
        loader.loadInitialAppointments();
        System.out.println("Please enter date in yyyy-mm-dd format");
        String dateInput = sc.next();
        LocalDate date;

        try {
            date = LocalDate.parse(dateInput);
        } catch (Exception e) {
            System.out.println("Invalid date format. Please enter in yyyy-mm-dd format.");
            return;
        }
        doctorLeaveSetter.setLeaveForAllTimeslots(Session.getLoginID(), date);
        saver.saveRecords();

    }

    // cancels leave for all time slots of a day
    public void doctorCancelLeave() {
        loader.loadInitialAppointments();
        System.out.println("Please enter date in yyyy-mm-dd format");
        String dateInput = sc.next();
        LocalDate date;

        try {
            date = LocalDate.parse(dateInput);
        } catch (Exception e) {
            System.out.println("Invalid date format. Please enter in yyyy-mm-dd format.");
            return;
        }
        doctorLeaveSetter.cancelLeaveForAllTimeslots(Session.getLoginID(), date);
        saver.saveRecords();

    }

    // set leave for a specefic time slot
    public void doctorSetLeaveByTimeslot() {
        loader.loadInitialAppointments();
        System.out.println("Please enter date in yyyy-mm-dd format");
        String dateInput = sc.next();
        LocalDate date;

        try {
            date = LocalDate.parse(dateInput);
        } catch (Exception e) {
            System.out.println("Invalid date format. Please enter in yyyy-mm-dd format.");
            return;
        }
        //getting timeslots
        List<String> availableTimeslots = Timeslot.getTimeslot();
        System.out.println("Available timeslots:");
        for (int i = 0; i < availableTimeslots.size(); i++) {
            System.out.println((i + 1) + ". " + availableTimeslots.get(i));
        }

        System.out.println("Choose a timeslot number to set as leave: ");
        int timeslotChoice;

        try {
            timeslotChoice = Integer.parseInt(sc.next()) - 1;
            if (timeslotChoice < 0 || timeslotChoice >= availableTimeslots.size()) {
                System.out.println("Invalid choice. Please select a valid timeslot.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input.");
            return;
        }

        doctorLeaveSetter.setLeaveForTimeslot(Session.getLoginID(), date, availableTimeslots.get(timeslotChoice));
        saver.saveRecords();
    }

    // cancel leave for a specific time slot
    public void doctorCancelLeaveByTimeslot() {
        loader.loadInitialAppointments();
        //    public void cancelLeaveForTimeslot(String doctorId, LocalDate date, String timeslot) {

        System.out.println("Please enter date in yyyy-mm-dd format:");
        String dateInput = sc.nextLine();
        LocalDate date;

        try {
            date = LocalDate.parse(dateInput);
        } catch (Exception e) {
            System.out.println("Invalid date format. Please enter in yyyy-MM-dd format.");
            return;
        }

        // Display available timeslots and prompt 
        List<String> availableTimeslots = Timeslot.getTimeslot();
        System.out.println("Available timeslots:");
        for (int i = 0; i < availableTimeslots.size(); i++) {
            System.out.println((i + 1) + ". " + availableTimeslots.get(i));
        }

        System.out.print("Choose a timeslot number to cancel leave: ");
        int timeslotChoice;
        try {
            timeslotChoice = Integer.parseInt(sc.nextLine()) - 1;
            if (timeslotChoice < 0 || timeslotChoice >= availableTimeslots.size()) {
                System.out.println("Invalid choice. Please select a valid timeslot.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input.");
            return;
        }

        doctorLeaveSetter.cancelLeaveForTimeslot(Session.getLoginID(), date, availableTimeslots.get(timeslotChoice));
        saver.saveRecords();
    }

    public void updateAppointmentFlag() {
        loader.loadInitialAppointments();
        appointmentFlagUpdater.promptUpdateAppointmentFlag(Session.getLoginID());
        saver.saveRecords();
    }

    public Appointment getAppointmentById(String Id) throws IOException {
        loader.loadInitialAppointments();
        return viewer.viewRecordsById(Id);
    }

    public void viewAllRecords() throws IOException {
        loader.loadInitialAppointments();
        viewer.viewAllRecords();
    }

    //Doctor
    public void viewPendingRecords() throws IOException {
        loader.loadInitialAppointments();
        viewer.viewPendingRecords(Session.getLoginID());

    }

    //PATIENT
    //view all available appointment slots
    public void viewAllAvailableAppointments() {
        loader.loadInitialAppointments();
        System.out.print("Please enter date in yyyy-mm-dd format: ");
        String dateInput = sc.next();
        LocalDate date;
        try {
            date = LocalDate.parse(dateInput);
        } catch (Exception e) {
            System.out.println("Invalid date format. Please enter in yyyy-MM-dd format.");
            return;
        }
        patientApptViewer.viewpatientViewScheduleForNextThreeDays(date);
        saver.saveRecords();
    }

    //Patient Appointment Scheduler
    public void patientScheduleAppointment() {
        loader.loadInitialAppointments();
        patientAppointmentScheduler.scheduleAppointment(Session.getLoginID());
        saver.saveRecords();
    }

    //Patient Re-Schedule Appointment
    public void patientReScheduleAppointment() {
        loader.loadInitialAppointments();
        patientApptViewer.viewAllScheduledAppointments(Session.getLoginID());
        patientAppointmentScheduler.rescheduleAppointment(Session.getLoginID());
        saver.saveRecords();
    }

    //Patient Cancel Appointment
    public void patientCancelAppointment() {
        loader.loadInitialAppointments();
        patientApptViewer.viewAllScheduledAppointments(Session.getLoginID());
        patientAppointmentScheduler.cancelAppointment(Session.getLoginID());
        saver.saveRecords();
    }

    //view all the scheduled appointments by patient
    public void viewAllScheduledAppointments() {
        loader.loadInitialAppointments();
        patientApptViewer.viewAllScheduledAppointments(Session.getLoginID());
        saver.saveRecords();
    }

}
