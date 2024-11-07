import java.util.Scanner;

public class AdminUI {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int option = 0;
        while (option != 5)
        {
            System.out.println("1) View and Manage Hospital Staff");
            System.out.println("2) View Appointment details");
            System.out.println("3) View and Manage Medication Inventory");
            System.out.println("4) Approve Replenishment Requests");
            System.out.println("5) Logout");
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
            }
        }
    }
}
