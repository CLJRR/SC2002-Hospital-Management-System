package AppointmentOutcomeRecordSystem;

import java.io.IOException;
import java.util.Scanner;

public class UpdatePrescriptionStatus {

    static final Scanner sc = new Scanner(System.in);

    public void updatePrescriptionStatus() throws IOException {
        PharmacistViewRecords view = new PharmacistViewRecords();
        UpdateStatus update = new UpdateStatus();

        System.out.println("Pending Records:");
        view.viewPendingRecords();
        System.out.println("1) Update Record");
        System.out.println("2) Go back");
        int option = 0;
        while (option != 2) {
            option = sc.nextInt();
            switch (option) {
                case 1 -> {
                    System.out.println("Please enter Appt Id: ");
                    String apptId = sc.next();
                    update.UpdatePrescriptionStatus(apptId.toUpperCase());
                    view.viewPendingRecords();
                    System.out.println("1) Update Record");
                    System.out.println("2) Go back");
                    break;
                }
                case 2 -> {
                    break;
                }
                default -> {
                    System.err.println("Invalid key");
                }
            }
        }

    }

}
