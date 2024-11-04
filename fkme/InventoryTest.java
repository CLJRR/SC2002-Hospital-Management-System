
import java.util.List;
import entity.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import service.*;

public class InventoryTest {

    public static void main(String[] args) {
        MedicationInventoryService service = new MedicationInventoryService();

        // Test 1: Clear all records to start with an empty file
        System.out.println("Test 1: Deleting all records...");
        service.deleteAll();
        System.out.println("All records deleted.\n");

        // Test 2: Save a new medication record
        System.out.println("Test 2: Saving a new medication record...");
        MedicationInventory med1 = new MedicationInventory("Aspirin", 100, 20);
        boolean saveResult1 = service.save(med1);
        System.out.println("Save result: " + saveResult1 + "\n");

        // Test 3: Save another new medication record
        System.out.println("Test 3: Saving another new medication record...");
        MedicationInventory med2 = new MedicationInventory("Ibuprofen", 50, 10);
        boolean saveResult2 = service.save(med2);
        System.out.println("Save result: " + saveResult2 + "\n");

        // Test 4: Attempt to save a duplicate medication record
        System.out.println("Test 4: Saving a duplicate medication record (should fail)...");
        MedicationInventory medDuplicate = new MedicationInventory("Aspirin", 200, 30);
        boolean saveResult3 = service.save(medDuplicate);
        System.out.println("Save result for duplicate: " + saveResult3 + "\n");

        // Test 5: Load all medication records
        System.out.println("Test 5: Loading all medication records...");
        List<MedicationInventory> inventoryList = service.loadAll();
        System.out.println("Loaded medications:");
        for (MedicationInventory inventory : inventoryList) {
            System.out.println(inventory.getName() + " - Stock: " + inventory.getStock() + ", Alert Level: " + inventory.getAlertlevel());
        }
        System.out.println();

        // Test 6: Get a medication by name
        System.out.println("Test 6: Getting a medication by name...");
        MedicationInventory aspirin = service.getByName("Aspirin");
        if (aspirin != null) {
            System.out.println("Found medication: " + aspirin.getName() + " - Stock: " + aspirin.getStock() + ", Alert Level: " + aspirin.getAlertlevel());
        } else {
            System.out.println("Medication not found.");
        }
        System.out.println();

        // Test 7: Get a non-existent medication by name
        System.out.println("Test 7: Getting a non-existent medication by name...");
        MedicationInventory nonExistent = service.getByName("Paracetamol");
        if (nonExistent != null) {
            System.out.println("Found medication: " + nonExistent.getName());
        } else {
            System.out.println("Medication not found.");
        }
        System.out.println();

        // Test 8: Delete a medication by name
        System.out.println("Test 8: Deleting a medication by name...");
        boolean deleteResult1 = service.deleteByName("Aspirin");
        System.out.println("Delete result for 'Aspirin': " + deleteResult1);
        System.out.println();

        // Test 9: Attempt to delete a non-existent medication by name
        System.out.println("Test 9: Deleting a non-existent medication by name...");
        boolean deleteResult2 = service.deleteByName("Paracetamol");
        System.out.println("Delete result for 'Paracetamol': " + deleteResult2);
        System.out.println();

        // Test 10: Load all medication records after deletion
        System.out.println("Test 10: Loading all medication records after deletion...");
        inventoryList = service.loadAll();
        System.out.println("Loaded medications:");
        for (MedicationInventory inventory : inventoryList) {
            System.out.println(inventory.getName() + " - Stock: " + inventory.getStock() + ", Alert Level: " + inventory.getAlertlevel());
        }
        System.out.println();
    }
}
