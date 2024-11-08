package AppointmentOutcomeRecordSystem;

import java.io.IOException;
import java.util.Scanner;

public class UpdatePrescriptionStatus {

    static final Scanner sc = new Scanner(System.in);

    public void updatePrescriptionStatus() throws IOException {

        PharmacistViewRecords view = new PharmacistViewRecords();
        UpdateStatus update = new UpdateStatus();
        view.viewPendingRecords();
        System.out.println("Please enter Appt Id: ");
        String apptId = sc.next();
        update.UpdatePrescriptionStatus(apptId.toUpperCase());
    }

}
