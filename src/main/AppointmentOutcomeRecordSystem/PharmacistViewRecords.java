package AppointmentOutcomeRecordSystem;

import enums.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PharmacistViewRecords {

    public void viewPendingRecords() throws IOException {
        AppointmentOutcomeRecordService medicalrecordService = new AppointmentOutcomeRecordService();
        @SuppressWarnings("unchecked")
        List<AppointmentOutcomeRecord> medicalRecords = (List<AppointmentOutcomeRecord>) medicalrecordService.load();
        List<AppointmentOutcomeRecord> PendingRecords = new ArrayList<>();
        for (AppointmentOutcomeRecord medicalRecord : medicalRecords) {
            if (medicalRecord.getPrescription().getStatus() == Flag.PENDING) {
                PendingRecords.add(medicalRecord);
            }
        }
        for (AppointmentOutcomeRecord record : PendingRecords) {
            System.out.println(record.getApptId() + " " + record.getPrescription());
        }
    }
}
