package AppointmentOutcomeRecordSystem;

import enums.Flag;
import java.io.IOException;
import java.util.List;

public class UpdateStatus {

    public void UpdatePrescriptionStatus(String apptId) throws IOException {

        AppointmentOutcomeRecordService medicalrecordService = new AppointmentOutcomeRecordService();
        @SuppressWarnings("unchecked")
        List<AppointmentOutcomeRecord> medicalRecords = (List<AppointmentOutcomeRecord>) medicalrecordService.load();
        for (AppointmentOutcomeRecord medicalRecord : medicalRecords) {
            if (apptId.equals(medicalRecord.getApptId()) && medicalRecord.getPrescription().getStatus() == Flag.PENDING) {
                //decrease med inventory
                medicalRecord.setStatus(Flag.DISPENSED);
                System.out.println("prescription for " + medicalRecord.getApptId() + " dispensed");
                medicalrecordService.save(medicalRecords);
                return;
            }
        }
        System.out.println("Cant Find Pending Record");
    }
}
