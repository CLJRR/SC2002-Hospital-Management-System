
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TxtDatabaseService {

    private static final String FILE_PATH = "appointments.txt";

    // Save an appointment to the text file
    public boolean saveAppointment(Appointment appointment) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(appointmentToText(appointment));
            writer.newLine();
            return true;
        } catch (IOException e) {
            System.err.println("Error saving appointment: " + e.getMessage());
            return false;
        }
    }

    // Convert Appointment object to text format
    private String appointmentToText(Appointment appointment) {
        return appointment.getTitle() + "," + appointment.getDate() + "," + appointment.getTimeSlot();
    }

    // Load appointments from the text file
    public List<Appointment> loadAppointments() {
        List<Appointment> appointments = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                appointments.add(textToAppointment(line));
            }
        } catch (IOException e) {
            System.err.println("Error loading appointments: " + e.getMessage());
        }
        return appointments;
    }

    // Get appointments for a specific date
    public List<Appointment> getAppointmentsByDate(String date) {
        List<Appointment> appointmentsOnDate = new ArrayList<>();
        for (Appointment appointment : loadAppointments()) {
            if (appointment.getDate().equals(date)) {
                appointmentsOnDate.add(appointment);
            }
        }
        return appointmentsOnDate;
    }

    // Convert a line of text to an Appointment object
    private Appointment textToAppointment(String line) {
        String[] data = line.split(",");
        return new Appointment(data[0], data[1], data[2]);
    }
}
