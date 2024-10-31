
import entity.*;
import static entity.Staff.Role.*;
import java.util.*;
public class init {
    public static void main(String[] args) {
        StaffService service = new StaffService();
        service.deleteAll();
        service.save(new Staff("D001", "John Smith", "Male", 45, DOCTOR));
        service.save(new Staff("D002", "Emily Clarke", "Female", 38, DOCTOR));
        service.save(new Staff("A001", "Sarah Lee", "Female", 40, ADMINISTRATOR));
        service.save(new Staff("P001", "Mark Lee", "Male", 29, PHARMACIST));
        List<Staff> staffs = new ArrayList<>();
        staffs = service.loadAll();

        //filter by Role
        Iterator<Staff> iterator = staffs.iterator();
        while (iterator.hasNext()) {
            Staff staff = iterator.next();
            if (!staff.getRole().equals(Staff.Role.DOCTOR)) {
                iterator.remove();  // Safely remove the item from the list
            }
        }
        for (Staff staff : staffs) {
            System.out.println(staff.toString());
        }
    }
}
