package MedicalRecordSystem;

import java.io.IOException;
import java.util.List;

public class ViewMedicalRecord {

    public void viewMedicalRecord() throws IOException {
        // final String FILENAME = "../src/main/data/medicalRecords.txt";
        // final String FILENAME = "src/main/data/medicalRecords.txt";
        final String FILENAME = "./data/medicalRecords.txt";
        MedicalRecordService medicalrecordService = new MedicalRecordService();
        @SuppressWarnings("unchecked")
        List<MedicalRecord> medicalRecords = (List<MedicalRecord>) medicalrecordService.load(FILENAME);
        System.out.println(medicalRecords.size());
        // List<MedicalRecord> medicalRecords = new ArrayList<MedicalRecord>();
        for (MedicalRecord medicalRecord : medicalRecords) {
            System.out.println(medicalRecord.toString());
        }
        medicalrecordService.save(FILENAME, medicalRecords);
    }
}
