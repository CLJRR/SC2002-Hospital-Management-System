/**
 * The {@code AppointmentService} class provides methods for managing appointment records,
 * including loading, saving, formatting, and converting between objects and their string representations.
 * Implements the {@code Load}, {@code Format}, {@code Save}, {@code Write}, and {@code toObject} interfaces.
 */
package AppointmentSystem;

import FileManager.*;
import enums.*;
import java.io.*;
import java.time.*;
import java.time.format.*;
import java.util.*;

public class AppointmentService implements Load, Format, Save, Write, toObject {

    /**
     * Formatter for serializing and deserializing dates in the "yyyy-MM-dd" format.
     */
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * The file path where appointment records are stored.
     */
    final String fileName = "./data/appointments.txt";

    /**
     * Loads appointment records from the file and converts them into {@link Appointment} objects.
     *
     * @return a list of {@link Appointment} objects
     * @throws IOException if an error occurs while reading or parsing the file
     */
    @Override
    public List<?> load() throws IOException {
        List<Appointment> data = new ArrayList<>();
        try (Scanner scanner = new Scanner(new FileInputStream(fileName))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Appointment appointment = (Appointment) toObject(line);
                data.add(appointment);
            }
        }
        return data;
    }

    /**
     * Saves a list of {@link Appointment} objects to the file by formatting them into strings.
     *
     * @param list the list of {@link Appointment} objects to save
     * @throws IOException if an error occurs during the save operation
     */
    @Override
    public void save(List<?> list) throws IOException {
        List<String> data = new ArrayList<>();
        for (Object obj : list) {
            if (obj instanceof Appointment) {
                String formattedString = format(obj); // Use format method
                data.add(formattedString);
            } else {
                throw new IOException("List contains incorrect objects.");
            }
        }
        write(data);
    }

    /**
     * Writes a list of strings to the file.
     *
     * @param data the list of strings to write to the file
     * @throws IOException if an error occurs during the write operation
     */
    @Override
    public void write(List<String> data) throws IOException {
        try (PrintWriter out = new PrintWriter(new FileWriter(fileName))) {
            for (String line : data) {
                out.println(line);
            }
        }
    }

    /**
     * Formats an {@link Appointment} object into its string representation for storage.
     *
     * @param object the {@link Appointment} object to format
     * @return a formatted string representation of the {@link Appointment} object
     * @throws IOException if the object is not of type {@link Appointment}
     */
    @Override
    public String format(Object object) throws IOException {
        if (object instanceof Appointment appointment) {
            StringBuilder sb = new StringBuilder();
            sb.append(appointment.getAppointmentId()).append(",")
                    .append(appointment.getPatientId()).append(",")
                    .append(appointment.getDoctorId()).append(",")
                    .append(appointment.getDate().format(DATE_FORMATTER)).append(",")
                    .append(appointment.getTimeSlot()).append(",")
                    .append(appointment.getType()).append(",")
                    .append(appointment.getFlag());
            return sb.toString();
        } else {
            throw new IOException("Invalid object type");
        }
    }

    /**
     * Converts a string representation of an appointment into an {@link Appointment} object.
     *
     * @param string the string representation of the appointment
     * @return an {@link Appointment} object parsed from the string
     * @throws IOException if the string is improperly formatted or contains invalid values
     */
    @Override
    public Object toObject(String string) throws IOException {
        String[] parts = string.split(",");

        // Validate the format by checking the number of fields
        if (parts.length != 7) {
            throw new IOException("Invalid format.");
        }
        try {
            String appointmentId = parts[0];
            String patientId = parts[1].equalsIgnoreCase("null") ? null : parts[1];
            String doctorId = parts[2].equalsIgnoreCase("null") ? null : parts[2];
            LocalDate date = LocalDate.parse(parts[3], DATE_FORMATTER);
            String timeSlot = parts[4];
            Type type = Type.valueOf(parts[5].toUpperCase());
            Flag flag = Flag.valueOf(parts[6].toUpperCase());

            return new Appointment(appointmentId, patientId, doctorId, date, timeSlot, type, flag);

        } catch (DateTimeParseException e) {
            throw new IOException("Invalid date format in the date field.", e);
        } catch (IllegalArgumentException e) {
            throw new IOException("Invalid enum value in the Type or Flag field.", e);
        }
    }
}
