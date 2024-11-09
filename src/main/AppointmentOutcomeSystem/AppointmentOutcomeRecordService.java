package AppointmentOutcomeSystem;

import FIleManager.*;
import enums.*;
import java.io.*;
import java.time.*;
import java.time.format.*;
import java.util.*;

public class AppointmentOutcomeRecordService implements Load, Format, Save, Write, toObject {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    final String fileName = "./data/medicalRecords.txt";

    @Override
    public List<?> load() throws IOException {
        List<AppointmentOutcomeRecord> data = new ArrayList<>();
        try (Scanner scanner = new Scanner(new FileInputStream(fileName))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                AppointmentOutcomeRecord medicalRecord = (AppointmentOutcomeRecord) toObject(line);
                data.add(medicalRecord);
            }
        }
        return data;
    }

    @Override
    public void save(List<?> list) throws IOException {
        List<String> data = new ArrayList<>();
        for (Object obj : list) {
            if (obj instanceof AppointmentOutcomeRecord) {
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
        if (object instanceof AppointmentOutcomeRecord medicalRecord) {
            StringBuilder sb = new StringBuilder();
            sb.append(medicalRecord.getApptId()).append(",")
                    .append(medicalRecord.getPatientId()).append(",")
                    .append(medicalRecord.getDoctorId()).append(",")
                    .append(medicalRecord.getAppointmentDate().format(DATE_FORMATTER)).append(",")
                    .append(medicalRecord.getServiceProvided()).append(",")
                    .append(medicalRecord.getDiagnoses()).append(",");

            Prescription prescription = medicalRecord.getPrescription();
            sb.append(prescription.getMedName()).append(",")
                    .append(prescription.getStatus()).append(",")
                    .append(prescription.getAmount()).append(",")
                    .append(prescription.getDosage());
            return sb.toString();
        } else {
            throw new IOException("Invalid object type");
        }
    }

    @Override
    public Object toObject(String string) throws IOException {
        String[] parts = string.split(",");

        // Validate the format by checking the number of fields
        if (parts.length != 10) {
            throw new IOException("Invalid format.");
        }
        try {
            String apptId = parts[0];
            String patientId = parts[1];
            String doctorId = parts[2];
            LocalDate appointmentDate = LocalDate.parse(parts[3], DATE_FORMATTER);
            String serviceProvided = parts[4];
            String diagnoses = parts[5];
            String medName = parts[6];
            Flag flag = Flag.valueOf(parts[7].toUpperCase());
            int amount = Integer.parseInt(parts[8]);
            String dosage = parts[9];
            Prescription prescription = new Prescription(medName, flag, amount, dosage);
            return new AppointmentOutcomeRecord(apptId, patientId, doctorId, appointmentDate, serviceProvided, diagnoses, prescription);

        } catch (DateTimeParseException e) {
            throw new IOException("Invalid date format in the appointmentDate field.", e);
        } catch (NumberFormatException e) {
            throw new IOException("Invalid number format in the amount field.", e);
        } catch (IllegalArgumentException e) {
            throw new IOException("Invalid enum value in the Flag field.", e);
        }
    }
}
