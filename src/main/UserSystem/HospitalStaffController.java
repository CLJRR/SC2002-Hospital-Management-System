package UserSystem;

import enums.*;
import java.util.*;

public class HospitalStaffController {
    private Map<String, User> staffs;
    private UserSavers hospitalStaffSaver;
    private UserLoader hospitalStaffLoader;
    private HospitalStaffViewer hospitalStaffViewer;
    private HospitalStaffUpdater hospitalStaffUpdater;
    private HospitalStaffFilter hospitalStaffFilter;

    // Constructor
    public HospitalStaffController() {
        this.staffs = new HashMap<>();
        this.hospitalStaffSaver = new UserSavers(this.staffs);
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

    public void updateStaff(User staff) {
        if (staff.getRole() == Role.DOCTOR || staff.getRole() == Role.PHARMACIST) {
            hospitalStaffUpdater.updateStaff(staff.getId());
        }
        else {
            System.out.println("Error updating Staff.");
        }
    }

    public void addStaff(User staff) {
        if (staff.getRole() == Role.DOCTOR || staff.getRole() == Role.PHARMACIST) {
            staffs.put(staff.getId(), staff);
            System.out.println("Staff added.");
        }
        else {
            System.out.println("Error adding Staff.");
        }
    }

    public void removeStaff(User staff) {
        User removed = staffs.remove(staff.getId());
        if (removed == null) {
            System.out.println("Staff not found.");
        }
        else {
            System.out.println("Staff " + removed.getId() + " removed.");
        }
    }

    public void filterStaff(){
        hospitalStaffFilter.filterStaff();
    }

    public void viewStaff() {
        hospitalStaffViewer.viewStaff();
    }

    public Map<String, User> getStaffs() {
        return this.staffs;
    }
}
