package AppointmentOutcomeRecordSystem;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class ViewAppointmentOutcomeRecords {

    public void viewRecordsById(String Id) throws IOException {
        Scanner sc = new Scanner(System.in);
        AppointmentOutcomeRecordService medicalrecordService = new AppointmentOutcomeRecordService();
        @SuppressWarnings("unchecked")
        List<AppointmentOutcomeRecord> medicalRecords = (List<AppointmentOutcomeRecord>) medicalrecordService.load();

        // List<MedicalOutcomeRecord> medicalRecords = new ArrayList<MedicalOutcomeRecord>();
        System.out.println("----------------------------------------------------------------------------------------------------------------");
        for (AppointmentOutcomeRecord medicalRecord : medicalRecords) {
            if (Id.equalsIgnoreCase((medicalRecord.getApptId()))) {
                System.out.println(medicalRecord.toString());
                return;
            }
            if (Id.equalsIgnoreCase((medicalRecord.getPatientId())) || Id.equalsIgnoreCase((medicalRecord.getDoctorId()))) {
                // returnRecord.add(medicalRecord);
                System.out.println(medicalRecord.toString());
                System.out.println("----------------------------------------------------------------------------------------------------------------");
            }
        }
        System.out.println("Press Enter to go back");
        sc.nextLine();
    }
}
