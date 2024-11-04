package service;

import entity.Appointment;
import enums.Availability;
import enums.Flag;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AppointmentService {

    private static final String FILE_PATH = "./data/appointments.txt";

    // Save an appointment to the text file
    public boolean save(Appointment appointment) {
        // Load existing appointments to check for duplicate ID and doctor availability
        List<Appointment> existingAppointments = loadAll();

        // Check if an appointment with the same apptId already exists
        for (Appointment existingAppointment : existingAppointments) {
            if (existingAppointment.getApptId().equals(appointment.getApptId())) {
                System.out.println("An appointment with the same ID already exists.");
                return false; // Duplicate appointment ID found, do not save
            }
        }

        // Check if the doctor is available at the given date and time slot
        for (Appointment existingAppointment : existingAppointments) {
            if (existingAppointment.getDoctorId().equals(appointment.getDoctorId())
                    && existingAppointment.getDate().equals(appointment.getDate())
                    && existingAppointment.getTimeSlot().equals(appointment.getTimeSlot())) {
                System.out.println("Doctor is not available at the specified time slot.");
                return false; // Doctor is already booked for this time slot
            }
        }

        // If no duplicate ID and doctor is available, proceed to save the appointment
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(format(appointment));
            writer.newLine();
            System.out.println("Appointment saved successfully.");
            return true;
        } catch (IOException e) {
            System.err.println("Error saving appointment: " + e.getMessage());
            return false;
        }
    }

    // Load appointments from the text file
    public List<Appointment> loadAll() {
        List<Appointment> appointments = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String input;
            while ((input = reader.readLine()) != null) {
                Appointment appointment = toObject(input);
                if (appointment != null) {
                    appointments.add(appointment);
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading appointments: " + e.getMessage());
        }
        return appointments;
    }    // Convert a input of text to an Appointment object

    // Get appointments for a specific date
    public List<Appointment> getAppointmentsByDate(LocalDate date) {
        List<Appointment> appointmentsOnDate = new ArrayList<>();
        for (Appointment appointment : loadAll()) {
            if (appointment.getDate().equals(date)) {
                appointmentsOnDate.add(appointment);
            }
        }
        return appointmentsOnDate;
    }

    // Set a doctor's availability status for a particular date and time slot
    public boolean setDoctorAvailability(String doctorId, LocalDate date, String timeSlot, Availability availability) {
        Appointment availabilityEntry = new Appointment(null, null, doctorId, date, timeSlot, availability);
        return save(availabilityEntry);
    }

    // Check if a doctor is available on a specific date and time
    public boolean isDoctorAvailable(String doctorId, LocalDate date, String timeSlot) {
        List<Appointment> appointmentsOnDate = getAppointmentsByDate(date);
        for (Appointment appointment : appointmentsOnDate) {
            if (appointment.getDoctorId().equals(doctorId) && appointment.getTimeSlot().equals(timeSlot)) {
                return appointment.getAvailability() == Availability.AVALIABILE;
            }
        }
        return true; // If no appointment found, assume available
    }

    private Appointment toObject(String input) {
        String[] data = input.split(",");
        if (data.length == 7) {
            LocalDate date = LocalDate.parse(data[3]);
            Availability availability = Availability.valueOf(data[5]);
            Flag flag = Flag.valueOf(data[6]);
            return new Appointment(data[0], data[1], data[2], date, data[4], availability, flag);
        } else {
            System.err.println("Error parsing appointment data: " + input);
            return null;
        }

    }

    // Convert Appointment object to text format
    private String format(Appointment appointment) {
        return appointment.getApptId() + ","
                + appointment.getPatientId() + ","
                + appointment.getDoctorId() + ","
                + appointment.getDate().toString() + ","
                + appointment.getTimeSlot() + ","
                + appointment.getAvailability() + ","
                + appointment.getFlag();
    }
}
