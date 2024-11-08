package MedicalRecordSystem;

import FIleManager.*;
import enums.*;
import java.io.*;
import java.time.*;
import java.time.format.*;
import java.util.*;

public class MedicalRecordService implements Load, Format, Save, Write, toObject {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public List<?> load(String fileName) throws IOException {
        List<MedicalRecord> data = new ArrayList<>();
        try (Scanner scanner = new Scanner(new FileInputStream(fileName))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                MedicalRecord medicalRecord = (MedicalRecord) toObject(line);
                data.add(medicalRecord);
            }
        }
        return data;
    }

    @Override
    public void save(String filename, List<?> list) throws IOException {
        List<String> data = new ArrayList<>();
        for (Object obj : list) {
            if (obj instanceof MedicalRecord) {
                String formattedString = format(obj); // Use format method
                data.add(formattedString);
            } else {
                throw new IOException("List contains incorrect objects.");
            }
        }
        write(filename, data);

    }

    @Override
    public void write(String fileName, List<String> data) throws IOException {
        try (PrintWriter out = new PrintWriter(new FileWriter(fileName))) {
            for (String line : data) {
                out.println(line);
            }
        }
    }

    @Override
    public String format(Object object) throws IOException {
        if (object instanceof MedicalRecord medicalRecord) {
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
            return new MedicalRecord(apptId, patientId, doctorId, appointmentDate, serviceProvided, diagnoses, prescription);

        } catch (DateTimeParseException e) {
            throw new IOException("Invalid date format in the appointmentDate field.", e);
        } catch (NumberFormatException e) {
            throw new IOException("Invalid number format in the amount field.", e);
        } catch (IllegalArgumentException e) {
            throw new IOException("Invalid enum value in the Flag field.", e);
        }
    }
}
