package UserSystem;

import enums.*;
import java.util.*;

public class HospitalStaffController {
    private Map<String, User> staffs;
    private HospitalStaffSaver hospitalStaffSaver;
    private HospitalStaffLoader hospitalStaffLoader;
    private HospitalStaffViewer hospitalStaffViewer;
    private HospitalStaffUpdater hospitalStaffUpdater;
    private HospitalStaffFilter hospitalStaffFilter;

    // Constructor
    public HospitalStaffController() {
        this.staffs = new HashMap<>();
        this.hospitalStaffSaver = new HospitalStaffSaver(this.staffs);
        this.hospitalStaffLoader = new HospitalStaffLoader(this.staffs);
        this.hospitalStaffViewer = new HospitalStaffViewer(this.staffs);
        this.hospitalStaffUpdater = new HospitalStaffUpdater(this.staffs);
        this.hospitalStaffFilter = new HospitalStaffFilter(this.staffs);

        // load initial staff
        hospitalStaffLoader.loadInitialStaff();
    }

    public void saveStaff() {
        hospitalStaffSaver.saveStaff();
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
