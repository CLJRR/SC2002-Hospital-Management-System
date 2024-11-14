package AppointmentSystem;

import FileManager.*;
import enums.*;
import java.io.*;
import java.time.*;
import java.time.format.*;
import java.util.*;

public class AppointmentService implements Load, Format, Save, Write, toObject {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    final String fileName = "./data/appointments.txt";

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

    @Override
    public void write(List<String> data) throws IOException {
        try (PrintWriter out = new PrintWriter(new FileWriter(fileName))) {
            for (String line : data) {
                out.println(line);
            }
        }
    }

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

    @Override
    public Object toObject(String string) throws IOException {
        String[] parts = string.split(",");

        // Validate the format by checking the number of fields
        if (parts.length != 7) {
            throw new IOException("Invalid format.");
        }
        try {
            String appointmentId = parts[0];
            String patientId = parts[1].equals("null") ? null : parts[1];
            String doctorId = parts[2].equals("null") ? null : parts[2];
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
