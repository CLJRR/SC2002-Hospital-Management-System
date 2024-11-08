package MedicalRecordSystem;

import java.io.IOException;
import java.util.List;

public class ViewAppointmentOutcomeRecords {

    final String FILENAME = "./data/medicalRecords.txt";

    public void viewAllMedicalOutcomeRecords() throws IOException {
        AppointmentOutcomeRecordService medicalrecordService = new AppointmentOutcomeRecordService();
        @SuppressWarnings("unchecked")
        List<AppointmentOutcomeRecord> medicalRecords = (List<AppointmentOutcomeRecord>) medicalrecordService.load(FILENAME);
        System.out.println(medicalRecords.size());
        // List<MedicalOutcomeRecord> medicalRecords = new ArrayList<MedicalOutcomeRecord>();
        for (AppointmentOutcomeRecord medicalRecord : medicalRecords) {
            System.out.println(medicalRecord.toString());
        }
        medicalrecordService.save(FILENAME, medicalRecords);
    }

    public void viewMedicalRecord(String ApptId) throws IOException {

    }

    public void viewMedicalRecordByPatient(String ApptId) throws IOException {

    }
}
