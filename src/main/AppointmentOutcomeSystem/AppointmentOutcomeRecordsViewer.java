package AppointmentOutcomeSystem;

import enums.Flag;
import java.io.IOException;
import java.util.*;

public class AppointmentOutcomeRecordsViewer {

    private Map<String, AppointmentOutcomeRecord> appointmentOutcomeRecords;
    static final Scanner sc = new Scanner(System.in);

    public AppointmentOutcomeRecordsViewer(Map<String, AppointmentOutcomeRecord> appointmentOutcomeRecords) {
        this.appointmentOutcomeRecords = appointmentOutcomeRecords;
    }

    public void viewAllRecords() {
        System.out.println("All Appointment Outcome Records:");
        for (AppointmentOutcomeRecord record : appointmentOutcomeRecords.values()) {
            System.out.println(record.toString());
        }
        System.out.println(); // Adds a new line after the last record
    }

    public void viewPendingRecords() throws IOException {
        for (AppointmentOutcomeRecord record : appointmentOutcomeRecords.values()) {
            for (Prescription prescription : record.getPrescriptions()) {
                if (prescription.getFlag() == Flag.PENDING) {
                    System.out.println(record.getApptId() + " " + prescription);
                }
            }
        }
    }

    public boolean viewRecordsById(String Id) throws IOException {
        List<AppointmentOutcomeRecord> matchingRecords = new ArrayList<>();
        System.out.println("For Id " + Id);

        // Collect matching records
        for (AppointmentOutcomeRecord medicalRecord : appointmentOutcomeRecords.values()) {
            if (Id.equalsIgnoreCase(medicalRecord.getApptId()) || Id.equalsIgnoreCase(medicalRecord.getPatientId())) {
                matchingRecords.add(medicalRecord);
            }
        }

        // Sort records in ascending order by appointment date
        matchingRecords.sort(Comparator.comparing(AppointmentOutcomeRecord::getAppointmentDate));

        // Display sorted records
        if (!matchingRecords.isEmpty()) {
            for (AppointmentOutcomeRecord medicalRecord : matchingRecords) {
                if (Id.equalsIgnoreCase(medicalRecord.getApptId())) {
                    System.out.println(medicalRecord.toString());
                } else if (Id.equalsIgnoreCase(medicalRecord.getPatientId())) {
                    System.out.println("Appointment Id: " + medicalRecord.getApptId());
                    System.out.println("Doctor Id: " + medicalRecord.getDoctorId());
                    System.out.println("Appointment Date: " + medicalRecord.getAppointmentDate());
                    System.out.println("Service Provided: " + medicalRecord.getServiceProvided());
                    System.out.println("Diagnoses: " + String.join(", ", medicalRecord.getDiagnoses()));

                    // Display prescriptions
                    for (Prescription prescription : medicalRecord.getPrescriptions()) {
                        System.out.println("Prescription: " + prescription.getMedName() + ", " +
                                prescription.getAmount() + ", " + prescription.getDosage());
                    }
                    System.out.println("\n");
                }
            }
        } else {
            System.out.println("No Outcome Records found");
            return false;
        }

        System.out.println("Press Enter to go back");
        sc.nextLine();
        return true;
    }
    public boolean viewRecordsByIdnoNewline(String Id) throws IOException {
        List<AppointmentOutcomeRecord> matchingRecords = new ArrayList<>();
        System.out.println("For Id " + Id);

        // Collect matching records
        for (AppointmentOutcomeRecord medicalRecord : appointmentOutcomeRecords.values()) {
            if (Id.equalsIgnoreCase(medicalRecord.getApptId()) || Id.equalsIgnoreCase(medicalRecord.getPatientId())) {
                matchingRecords.add(medicalRecord);
            }
        }

        // Sort records in ascending order by appointment date
        matchingRecords.sort(Comparator.comparing(AppointmentOutcomeRecord::getAppointmentDate));

        // Display sorted records
        if (!matchingRecords.isEmpty()) {
            for (AppointmentOutcomeRecord medicalRecord : matchingRecords) {
                if (Id.equalsIgnoreCase(medicalRecord.getApptId())) {
                    System.out.println(medicalRecord.toString());
                } else if (Id.equalsIgnoreCase(medicalRecord.getPatientId())) {
                    System.out.println("Appointment Id: " + medicalRecord.getApptId());
                    System.out.println("Doctor Id: " + medicalRecord.getDoctorId());
                    System.out.println("Appointment Date: " + medicalRecord.getAppointmentDate());
                    System.out.println("Service Provided: " + medicalRecord.getServiceProvided());
                    System.out.println("Diagnoses: " + String.join(", ", medicalRecord.getDiagnoses()));

                    // Display prescriptions
                    for (Prescription prescription : medicalRecord.getPrescriptions()) {
                        System.out.println("Prescription: " + prescription.getMedName() + ", " +
                                prescription.getAmount() + ", " + prescription.getDosage());
                    }
                    System.out.println("\n");
                }
            }
        } else {
            System.out.println("No Outcome Records found");
            return false;
        }


        return true;
    }
}
