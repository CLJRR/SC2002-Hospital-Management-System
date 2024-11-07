import java.util.Scanner;

public class PatientUI {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int option = 0;
        while (option != 9)
        {
            System.out.println("1) View Medical Records");
            System.out.println("2) Update Personal Information");
            System.out.println("3) View Available Appointment Slots");
            System.out.println("4) Schedule an Appointment");
            System.out.println("5) Reschedule an Appointment");
            System.out.println("6) Cancel an Appointment");
            System.out.println("7) View Scheduled Appointments");
            System.out.println("8) View Past Appointment Outcome Records");
            System.out.println("9) Logout");
            option = sc.nextInt();
            switch(option)
            {
                case 1 -> {
                    break;
                }
                case 2 -> {
                    break;
                }
                case 3 -> {
                    break;
                }
                case 4 -> {
                    break;
                }
                case 5 -> {
                    break;
                }
                case 6 -> {
                    break;
                }
                case 7 -> {
                    break;
                }
                case 8 -> {
                    break;
                }
                case 9 -> {
                    break;
                }
            }
        }
    }
}
