package UserSystem;

import enums.*;
import java.util.*;

public class HospitalStaffController {

    private Map<String, User> staffs;
    private UserSaver hospitalStaffSaver;
    private UserLoader hospitalStaffLoader;
    private HospitalStaffViewer hospitalStaffViewer;
    private HospitalStaffUpdater hospitalStaffUpdater;
    private HospitalStaffFilter hospitalStaffFilter;

    // Constructor
    public HospitalStaffController() {
        this.staffs = new HashMap<>();
        this.hospitalStaffSaver = new UserSaver(this.staffs);
        this.hospitalStaffLoader = new UserLoader(this.staffs);
        this.hospitalStaffViewer = new HospitalStaffViewer(this.staffs);
        this.hospitalStaffUpdater = new HospitalStaffUpdater(this.staffs);
        this.hospitalStaffFilter = new HospitalStaffFilter(this.staffs);

        // load initial users
        hospitalStaffLoader.loadInitialUsers();
    }

    public void saveUsers() {
        hospitalStaffSaver.saveUsers();
    }

    public void loadUsers() {
        hospitalStaffLoader.loadInitialUsers();
    }

    public void updateStaff(User staff) {
        loadUsers();
        if (staff.getRole() != Role.PATIENT) {
            hospitalStaffUpdater.updateStaff(staff.getId());
        } else {
            System.out.println("Error updating Staff.");
        }
        saveUsers();
    }

    public void addStaff(User staff) {
        loadUsers();
        if (staff.getRole() != Role.PATIENT) {
            staffs.put(staff.getId(), staff);
            System.out.println("Staff added.");
        } else {
            System.out.println("Error adding Staff.");
        }
        saveUsers();

    }

    public void removeStaff(User staff) {
        loadUsers();
        User removed = staffs.remove(staff.getId());
        if (removed == null) {
            System.out.println("Staff not found.");
        } else {
            System.out.println("Staff " + removed.getId() + " removed.");
        }
        saveUsers();

    }

    public void filterStaff() {
        loadUsers();
        hospitalStaffFilter.filterStaff();
        saveUsers();

    }

    public void viewStaff() {
        loadUsers();
        hospitalStaffViewer.viewStaff();
        saveUsers();

    }

    public Map<String, User> getStaffs() {
        loadUsers();
        return this.staffs;

    }
}
