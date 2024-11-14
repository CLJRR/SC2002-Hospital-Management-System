package AppointmentSystem;

import SessionManager.Session;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

public class AppointmentController {

    private Map<String, Appointment> appointmentRecords;
    private ApptLoader loader;
    private ApptSaver saver;
    private ApptViewer viewer;
    private DoctorScheduleViewer doctorScheduleViewer;
    private DoctorLeaveSetter doctorLeaveSetter;
    private AppointmentFlagUpdater appointmentFlagUpdater;
    static final Scanner sc = new Scanner(System.in);

    public AppointmentController() {
        this.appointmentRecords = new HashMap<>();
        this.loader = new ApptLoader(appointmentRecords);
        this.saver = new ApptSaver(appointmentRecords);
        this.viewer = new ApptViewer(appointmentRecords);
        this.doctorScheduleViewer = new DoctorScheduleViewer(appointmentRecords);
        this.doctorLeaveSetter = new DoctorLeaveSetter(appointmentRecords);
        this.appointmentFlagUpdater = new AppointmentFlagUpdater(appointmentRecords);
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
        viewer.adminViewAllRecords();
        saver.saveRecords();

    }

    //DOCTOR
    //view Schedule for 3 days
    public void doctorScheduleViewer() {
        loader.loadInitialAppointments();
        System.out.println("Please enter date in yyyy-mm-dd format");
        String date = sc.next();
        doctorScheduleViewer.viewDoctorScheduleForNextThreeDays(Session.getLoginID(), LocalDate.parse(date));
        saver.saveRecords();
        System.out.println("Press Enter to go back");
        sc.nextLine();
    }

    //view Schedule for day
    public void doctorScheduleViewerByDay() {
        loader.loadInitialAppointments();
        System.out.println("Please enter date in yyyy-mm-dd format");
        String date = sc.next();
        doctorScheduleViewer.viewDoctorScheduleForDate(Session.getLoginID(), LocalDate.parse(date));
        saver.saveRecords();
    }

    //update Personal Schedule
    // set leave for all time slots of a day
    public void doctorSetLeave() {
        loader.loadInitialAppointments();
        System.out.println("Please enter date in yyyy-mm-dd format");
        String date = sc.next();
        doctorLeaveSetter.setLeaveForAllTimeslots(Session.getLoginID(), LocalDate.parse(date));
        saver.saveRecords();

    }

    // cancels leave for all time slots of a day
    public void doctorCancelLeave() {
        loader.loadInitialAppointments();
        System.out.println("Please enter date in yyyy-mm-dd format");
        String date = sc.next();
        doctorLeaveSetter.cancelLeaveForAllTimeslots(Session.getLoginID(), LocalDate.parse(date));
        saver.saveRecords();

    }

    // set leave for a specefic time slot
    public void doctorSetLeaveByTimeslot() {
        loader.loadInitialAppointments();

        //    public void setLeaveForTimeslot(String doctorId, LocalDate date, String timeslot) {
        saver.saveRecords();

    }

    // cancel leave for a specefic time slot
    public void doctorCancelLeaveByTimeslot() {
        loader.loadInitialAppointments();
        //    public void cancelLeaveForTimeslot(String doctorId, LocalDate date, String timeslot) {
        saver.saveRecords();
    }

    public void updateAppointmentFlag() {
        loader.loadInitialAppointments();
        appointmentFlagUpdater.promptUpdateAppointmentFlag(Session.getLoginID());
        saver.saveRecords();
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

}
