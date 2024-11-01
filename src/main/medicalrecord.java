
import entity.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import service.*;

public class medicalrecord {

    public static void main(String[] args) {
        MedicalRecordService service = new MedicalRecordService();

        // Creating a Prescription object
        Prescription prescription = new Prescription("Ibuprofen", 30, "200mg twice daily");
        // Creating a MedicalRecord object
        // i did not check for dupes, do w care bruv
        MedicalRecord record = new MedicalRecord("A001", "P1001", "D001", LocalDate.of(2024, 10, 10), "General Checkup", prescription);
        MedicalRecord record = new MedicalRecord("A002", "P1001", "D001", LocalDate.of(2024, 10, 10), "General Checkup", prescription);
        // Saving the MedicalRecord
        service.save(record);
        List<MedicalRecord> medicalRecords = new ArrayList<>();
        service.deleteByApptId("A001");
        medicalRecords = service.loadAll();
        for (MedicalRecord medicalRecord : medicalRecords) {
            System.out.println(medicalRecord.toString());
        }
    }
}
