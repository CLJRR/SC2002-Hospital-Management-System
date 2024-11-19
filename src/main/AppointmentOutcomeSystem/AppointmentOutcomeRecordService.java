/**
 * Service class for managing the serialization and deserialization of {@link AppointmentOutcomeRecord} objects.
 * Implements file operations for loading, saving, writing, and formatting data, as well as converting between
 * objects and their string representations.
 */
package AppointmentOutcomeSystem;

import FileManager.*;
import enums.*;
import java.io.*;
import java.time.*;
import java.time.format.*;
import java.util.*;

public class AppointmentOutcomeRecordService implements Load, Format, Save, Write, toObject {

    /**
     * Date formatter used for serializing and deserializing dates in the "yyyy-MM-dd" format.
     */
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * The file path for storing and retrieving appointment outcome records.
     */
    final String fileName = "./data/medicalRecords.txt";

    /**
     * Loads appointment outcome records from the data source file and converts them into {@link AppointmentOutcomeRecord} objects.
     *
     * @return a list of {@link AppointmentOutcomeRecord} objects loaded from the file
     * @throws IOException if an error occurs while reading or parsing the file
     */
    @Override
    public List<?> load() throws IOException {
        List<AppointmentOutcomeRecord> data = new ArrayList<>();
        try (Scanner scanner = new Scanner(new FileInputStream(fileName))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (!line.trim().isEmpty()) { // Ignore empty lines
                    try {
                        AppointmentOutcomeRecord medicalRecord = (AppointmentOutcomeRecord) toObject(line);
                        data.add(medicalRecord);
                    } catch (IOException e) {
                        System.err.println("Skipping invalid record: " + line);
                        e.printStackTrace();
                    }
                }
            }
        }
        return data;
    }

    /**
     * Saves a list of {@link AppointmentOutcomeRecord} objects to the data source file.
     *
     * @param list the list of {@link AppointmentOutcomeRecord} objects to save
     * @throws IOException if an error occurs during the save operation
     */
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

    /**
     * Writes a list of strings to the data source file.
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
     * Formats an {@link AppointmentOutcomeRecord} object into its string representation for storage.
     *
     * @param object the object to format
     * @return a formatted string representation of the object
     * @throws IOException if the object is not of type {@link AppointmentOutcomeRecord}
     */
    @Override
    public String format(Object object) throws IOException {
        if (object instanceof AppointmentOutcomeRecord medicalRecord) {
            StringBuilder sb = new StringBuilder();
            sb.append(medicalRecord.getApptId()).append(",")
                    .append(medicalRecord.getPatientId()).append(",")
                    .append(medicalRecord.getDoctorId()).append(",")
                    .append(medicalRecord.getAppointmentDate().format(DATE_FORMATTER)).append(",")
                    .append(medicalRecord.getServiceProvided()).append(",")
                    .append(String.join(";", medicalRecord.getDiagnoses())).append(",");

            List<Prescription> prescriptions = medicalRecord.getPrescriptions();
            for (int i = 0; i < prescriptions.size(); i++) {
                Prescription prescription = prescriptions.get(i);
                sb.append(prescription.getMedName()).append("|")
                        .append(prescription.getFlag()).append("|")
                        .append(prescription.getAmount()).append("|")
                        .append(prescription.getDosage());
                if (i < prescriptions.size() - 1) {
                    sb.append(";");
                }
            }
            return sb.toString();
        } else {
            throw new IOException("Invalid object type");
        }
    }

    /**
     * Converts a string representation of an {@link AppointmentOutcomeRecord} back into an object.
     *
     * @param string the string representation of the record
     * @return an {@link AppointmentOutcomeRecord} object parsed from the string
     * @throws IOException if the string is not properly formatted
     */
    @Override
    public Object toObject(String string) throws IOException {
        String[] parts = string.split(",", -1); // Use -1 to keep empty fields
        if (parts.length < 6) {
            throw new IOException("Invalid format: " + string);
        }
        try {
            String apptId = parts[0].trim();
            String patientId = parts[1].trim();
            String doctorId = parts[2].trim();
            LocalDate appointmentDate = LocalDate.parse(parts[3].trim(), DATE_FORMATTER);
            String serviceProvided = parts[4].trim();

            // Parse diagnoses
            List<String> diagnoses = new ArrayList<>();
            if (!parts[5].trim().isEmpty()) {
                diagnoses = Arrays.asList(parts[5].trim().split(";"));
            }

            // Parse prescriptions
            List<Prescription> prescriptions = new ArrayList<>();
            if (parts.length > 6 && !parts[6].trim().isEmpty()) {
                String[] prescriptionParts = parts[6].trim().split(";");
                for (String p : prescriptionParts) {
                    String[] pDetails = p.split("\\|", -1);
                    if (pDetails.length == 4) {
                        String medName = pDetails[0].trim();
                        Flag flag = Flag.valueOf(pDetails[1].trim().toUpperCase());
                        int amount = Integer.parseInt(pDetails[2].trim());
                        String dosage = pDetails[3].trim();
                        prescriptions.add(new Prescription(medName, flag, amount, dosage));
                    } else {
                        System.err.println("Skipping invalid prescription format: " + p);
                    }
                }
            }

            return new AppointmentOutcomeRecord(apptId, patientId, doctorId, appointmentDate, serviceProvided,
                    diagnoses, prescriptions);

        } catch (DateTimeParseException e) {
            throw new IOException("Invalid date format in the appointmentDate field.", e);
        } catch (NumberFormatException e) {
            throw new IOException("Invalid number format in the amount field.", e);
        } catch (IllegalArgumentException e) {
            throw new IOException("Invalid enum value in the Flag field.", e);
        }
    }
}
