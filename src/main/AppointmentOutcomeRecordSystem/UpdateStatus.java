package AppointmentOutcomeRecordSystem;

import MedicineInventorySystem.InventoryManager;
import enums.Flag;
import java.io.IOException;
import java.util.List;

public class UpdateStatus {

    public void UpdatePrescriptionStatus(String apptId) throws IOException {
        InventoryManager inventoryManager = new InventoryManager();
        AppointmentOutcomeRecordService medicalrecordService = new AppointmentOutcomeRecordService();
        @SuppressWarnings("unchecked")
        List<AppointmentOutcomeRecord> medicalRecords = (List<AppointmentOutcomeRecord>) medicalrecordService.load();
        for (AppointmentOutcomeRecord medicalRecord : medicalRecords) {
            if (apptId.equals(medicalRecord.getApptId()) && medicalRecord.getPrescription().getStatus() == Flag.PENDING) {
                if (inventoryManager.decreaseStock(medicalRecord.getPrescription().getMedName(), medicalRecord.getPrescription().getAmount())) {
                    inventoryManager.saveInventory();
                    medicalRecord.setStatus(Flag.DISPENSED);
                    System.out.println("prescription for " + medicalRecord.getApptId() + " dispensed");
                    medicalrecordService.save(medicalRecords);
                    return;

                }
                System.out.println("Error updating status");
            }
        }
        System.out.println("Cant Find Pending Record");
    }
}
