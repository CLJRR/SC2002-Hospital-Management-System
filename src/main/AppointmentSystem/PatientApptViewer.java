package AppointmentSystem;

import java.time.LocalDate;
import java.util.ArrayList;
//import java.time.LocalTime;
import java.util.List;
import java.util.Map;

import enums.Type;

public class PatientApptViewer {
    private Map<String, Appointment> appointmentRecords;

    public PatientApptViewer(Map<String, Appointment> appointmentRecords) {
        this.appointmentRecords = appointmentRecords;

    }

    //viewAvailableAppointmentSlots
    //check how appt timeslots updated with filled
    // Method to view available slots for a specific date
    public List<String> viewAvailableSlots(String patientId, LocalDate date) {
        //List<LocalTime> availableSlots = getAvailableSlots(date);
        List<String> availableSlots = getAvailableSlots(date);
        if (availableSlots.isEmpty()) {
            System.out.println("No available slots for " + date);
            return null; // Return null if no slots are available
        }

        System.out.println("Available slots for " + date + ":");
        for (int i = 0; i < availableSlots.size(); i++) {
            String slot = availableSlots.get(i);
            System.out.println((i + 1) + ") " + slot ); 
        }

        return availableSlots; // Return available slots to be used in scheduling
    }

    // Helper method to get available slots for a specific date
    public List<String> getAvailableSlots(LocalDate date) {
        List<String> allSlots = Timeslot.getTimeslot();
        List<String> bookedSlots = new ArrayList<>();

        for (Appointment appointment : appointmentRecords.values()) {
            if (appointment.getDate().equals(date) && appointment.getType() == Type.APPOINTMENT) {
                bookedSlots.add(appointment.getTimeSlot());
            }
        }

        allSlots.removeAll(bookedSlots);
        return allSlots;
    }
    //viewScheduledApppointments
    //need to test check
    // Method to view all scheduled appointments for the logged-in patient
    public void viewAllScheduledAppointments(String patientId) {
        System.out.println("Your scheduled appointments:");
        boolean hasAppointments = false;

        for (Appointment appointment : appointmentRecords.values()) {
            if (appointment.getPatientId().equals(patientId)) {
                System.out.println("Appointment ID: " + appointment.getAppointmentId()
                        + ", Date: " + appointment.getDate()
                        + ", Time: " + appointment.getTimeSlot()
                        + ", Doctor ID: " + appointment.getDoctorId()
                        + ", Flag: " + appointment.getFlag());
                hasAppointments = true;
            }
        }

        if (!hasAppointments) {
            System.out.println("You have no scheduled appointments.");
        }
    }
}
