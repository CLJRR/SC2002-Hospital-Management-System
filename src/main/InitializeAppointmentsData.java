
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class InitializeAppointmentsData {

    public static void main(String[] args) {
        String fileName = "./data/appointments.txt"; // Path to your file
        String data = """
                A001,P1001,D001,2024-09-22,09:00,APPOINTMENT,CONFIRMED
                A002,P1002,D002,2024-11-05,09:00,APPOINTMENT,COMPLETED
                A003,P1002,D001,2024-11-06,15:00,APPOINTMENT,PENDING
                """;
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            writer.print(data);
            System.out.println("Data successfully written to " + fileName);
        } catch (IOException e) {
            System.err.println("Error writing data to file: " + e.getMessage());
        }
        fileName = "./data/medicalRecords.txt"; // Path to your file
        data = """
                A002,P1002,D002,2024-11-05,General Checkup,fever,Ibuprofen|DISPENSED|5|8mg per day;
                """;
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            writer.print(data);
            System.out.println("Data successfully written to " + fileName);
        } catch (IOException e) {
            System.err.println("Error writing data to file: " + e.getMessage());
        }
        fileName = "./data/medicine_inventory.txt"; // Path to your file
        data = """
                Paracetamol,100,20
                Ibuprofen,50,10
                Amoxicillin,75,15
                """;
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            writer.print(data);
            System.out.println("Data successfully written to " + fileName);
        } catch (IOException e) {
            System.err.println("Error writing data to file: " + e.getMessage());
        }
        fileName = "./data/requests.txt"; // Path to your file
        data = """
                REQ1,P001,Paracetamol,10,normal restock,A001,APPROVED
                """;
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            writer.print(data);
            System.out.println("Data successfully written to " + fileName);
        } catch (IOException e) {
            System.err.println("Error writing data to file: " + e.getMessage());
        }
        fileName = "./data/users.txt"; // Path to your file
        data = """
                P1001,Alice Brown,password,null,1980-05-14,FEMALE,92268689,alice.brown@example.com,A+,PATIENT
                P1002,Bob Stone,password,null,1975-11-22,MALE,96815338,bob.stone@example.com,B+,PATIENT
                P1003,Charlie White,password,null,1990-07-08,MALE,88381439,charlie.white@example.com,O-,PATIENT
                D001,John Smith,password,45,null,MALE,null,null,null,DOCTOR
                D002,Emily Clarke,password,38,null,FEMALE,null,null,null,DOCTOR
                P001,Mark Lee,password,29,null,MALE,null,null,null,PHARMACIST
                A001,Sarah Lee,password,40,null,FEMALE,null,null,null,ADMINISTRATOR
                R001,Emily Tan,password,14,null,Male,null,null,null,RECEPTIONIST
                """;
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            writer.print(data);
            System.out.println("Data successfully written to " + fileName);
        } catch (IOException e) {
            System.err.println("Error writing data to file: " + e.getMessage());
        }
    }
}
