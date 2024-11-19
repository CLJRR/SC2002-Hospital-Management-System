/**
 * The {@code AppointmentController} class is responsible for managing appointment records,
 * providing functionalities for administrators, doctors, and patients.
 * It supports viewing, scheduling, rescheduling, canceling, and updating appointments.
 */
package AppointmentSystem;

import SessionManager.Session;
import enums.Flag;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class AppointmentController {

    /**
     * A map containing all appointment records, keyed by their appointment ID.
     */
    private Map<String, Appointment> appointmentRecords;

    /**
     * Loader for appointment records.
     */
    private AppointmentLoader loader;

    /**
     * Saver for appointment records.
     */
    private AppointmentSaver saver;

    /**
     * Viewer for appointment records.
     */
    private AppointmentRecordViewer viewer;

    /**
     * Handles viewing the doctor's schedule.
     */
    private DoctorScheduleViewer doctorScheduleViewer;

    /**
     * Handles setting and canceling leave for doctors.
     */
    private DoctorLeaveSetter doctorLeaveSetter;

    /**
     * Handles updating appointment flags.
     */
    private AppointmentFlagUpdater appointmentFlagUpdater;

    /**
     * Viewer for patient appointments.
     */
    private PatientAppointmentViewer PatientAppointmentViewer;

    /**
     * Scheduler for patient appointments.
     */
    private PatientAppointmentScheduler patientAppointmentScheduler;

    /**
     * Scanner for user input.
     */
    static final Scanner sc = new Scanner(System.in);

    /**
     * Constructs a new {@code AppointmentController}.
     * Initializes all components and loads initial appointment records.
     */
    public AppointmentController() {
        this.appointmentRecords = new HashMap<>();
        this.loader = new AppointmentLoader(appointmentRecords);
        this.saver = new AppointmentSaver(appointmentRecords);
        this.viewer = new AppointmentRecordViewer(appointmentRecords);
        this.doctorScheduleViewer = new DoctorScheduleViewer(appointmentRecords);
        this.doctorLeaveSetter = new DoctorLeaveSetter(appointmentRecords);
        this.appointmentFlagUpdater = new AppointmentFlagUpdater(appointmentRecords);
        this.PatientAppointmentViewer = new PatientAppointmentViewer(appointmentRecords);
        this.patientAppointmentScheduler = new PatientAppointmentScheduler(appointmentRecords);
        loader.loadInitialAppointments();
    }

    /**
     * Loads all appointment records from storage.
     */
    public void loadRecords() {
        loader.loadInitialAppointments();
    }

    /**
     * Saves all appointment records to storage.
     */
    public void saveRecords() {
        saver.saveRecords();
    }

    /**
     * Views all appointment records (Admin functionality).
     *
     * @throws IOException if an error occurs while accessing records
     */
    public void adminViewAllRecords() throws IOException {
        loader.loadInitialAppointments();
        System.out.println(" ");
        viewer.adminViewAllRecords();
        saver.saveRecords();
    }

    /**
     * Views the doctor's schedule for the next three days starting from a given date.
     */
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

    /**
     * Views the doctor's schedule for a specific date.
     */
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

    /**
     * Views all confirmed appointments for the logged-in doctor.
     */
    public void doctorViewConfirmedAppt() {
        loader.loadInitialAppointments();
        List<Appointment> filteredAppointments = appointmentRecords.values().stream()
                .filter(appt -> appt.getDoctorId().equalsIgnoreCase(Session.getLoginID())
                        && appt.getFlag() == Flag.CONFIRMED)
                .collect(Collectors.toList());

        if (filteredAppointments.isEmpty()) {
            System.out.println("No confirmed appointments for the logged-in doctor.");
        } else {
            System.out.println("Confirmed Appointments for Doctor " + Session.getLoginID() + ":");
            for (Appointment appt : filteredAppointments) {
                System.out.println("Appointment ID: " + appt.getAppointmentId());
                System.out.println("Patient ID: " + appt.getPatientId());
                System.out.println("Date: " + appt.getDate());
                System.out.println("Time: " + appt.getTimeSlot());
                System.out.println("--------------------------------------------");
            }
        }
        saver.saveRecords();
        System.out.println("Press Enter to go back");
        sc.nextLine();
    }

    /**
     * Sets leave for all timeslots on a specific day for the logged-in doctor.
     */
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

    /**
     * Cancels leave for all timeslots on a specific day for the logged-in doctor.
     */
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

    /**
     * Sets leave for a specific timeslot on a specific day for the logged-in doctor.
     */
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

    /**
     * Cancels leave for a specific timeslot on a specific day for the logged-in doctor.
     */
    public void doctorCancelLeaveByTimeslot() {
        loader.loadInitialAppointments();
        System.out.println("Please enter date in yyyy-mm-dd format:");
        String dateInput = sc.nextLine();
        LocalDate date;

        try {
            date = LocalDate.parse(dateInput);
        } catch (Exception e) {
            System.out.println("Invalid date format. Please enter in yyyy-MM-dd format.");
            return;
        }

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

    /**
     * Updates the flag of appointments for the logged-in doctor.
     */
    public void updateAppointmentFlag() {
        loader.loadInitialAppointments();
        appointmentFlagUpdater.promptUpdateAppointmentFlag(Session.getLoginID());
        saver.saveRecords();
    }

    /**
     * Retrieves an appointment by its ID.
     *
     * @param Id the appointment ID
     * @return the appointment corresponding to the given ID
     * @throws IOException if an error occurs while accessing records
     */
    public Appointment getAppointmentById(String Id) throws IOException {
        loader.loadInitialAppointments();
        return viewer.viewRecordsById(Id);
    }

    /**
     * Views all appointment records.
     *
     * @throws IOException if an error occurs while accessing records
     */
    public void viewAllRecords() throws IOException {
        loader.loadInitialAppointments();
        viewer.viewAllRecords();
    }

    /**
     * Views all pending appointment records for the logged-in doctor.
     *
     * @throws IOException if an error occurs while accessing records
     */
    public void viewPendingRecords() throws IOException {
        loader.loadInitialAppointments();
        viewer.viewPendingRecords(Session.getLoginID());
    }

    /**
     * Views all available appointment slots for the next three days starting from a specific date (Patient functionality).
     */
    public void viewAllAvailableAppointments() {
        loader.loadInitialAppointments();
        PatientAppointmentViewer.patientViewSchedule();
        saver.saveRecords();
    }

    /**
     * Schedules a new appointment for the logged-in patient.
     */
    public void patientScheduleAppointment() {
        loader.loadInitialAppointments();
        patientAppointmentScheduler.scheduleAppointment(Session.getLoginID());
        saver.saveRecords();
    }

    /**
     * Reschedules an existing appointment for the logged-in patient.
     */
    public void patientReScheduleAppointment() {
        loader.loadInitialAppointments();
        PatientAppointmentViewer.viewAllScheduledAppointments(Session.getLoginID());
        patientAppointmentScheduler.rescheduleAppointment(Session.getLoginID());
        saver.saveRecords();
    }

    /**
     * Cancels a scheduled appointment for the logged-in patient.
     */
    public void patientCancelAppointment() {
        loader.loadInitialAppointments();
        PatientAppointmentViewer.viewAllScheduledAppointments(Session.getLoginID());
        patientAppointmentScheduler.cancelAppointment(Session.getLoginID());
        saver.saveRecords();
    }

    /**
     * Views all scheduled appointments for the logged-in patient.
     */
    public void viewAllScheduledAppointments() {
        loader.loadInitialAppointments();
        PatientAppointmentViewer.viewAllScheduledAppointments(Session.getLoginID());
        saver.saveRecords();
    }
}
