package UserSystem;

import enums.*;
import java.util.*;

/**
 * Controller class for managing hospital staff, including doctors and
 * pharmacists.
 * This class provides operations to add, remove, update, view, and filter staff
 * members,
 * as well as saving and loading staff data.
 */

public class HospitalStaffController {
    private final Map<String, User> staffs;
    private final UserSaver hospitalStaffSaver;
    private final UserLoader hospitalStaffLoader;
    private final HospitalStaffViewer hospitalStaffViewer;
    private final HospitalStaffUpdater hospitalStaffUpdater;
    private final HospitalStaffFilter hospitalStaffFilter;

    /**
     * Constructs a HospitalStaffController instance and initialises the components
     * required for managing hospital staff. It loads the initial staff from the
     * storage system into memory.
     */

    public HospitalStaffController() {
        this.staffs = new HashMap<>();
        this.hospitalStaffSaver = new UserSaver(this.staffs);
        this.hospitalStaffLoader = new UserLoader(this.staffs);
        this.hospitalStaffViewer = new HospitalStaffViewer(this.staffs);
        this.hospitalStaffUpdater = new HospitalStaffUpdater(this.staffs);
        this.hospitalStaffFilter = new HospitalStaffFilter(this.staffs);

        // load the initial set of users
        hospitalStaffLoader.loadInitialUsers();
    }

    /**
     * Saves all hospital staff data to the storage system.
     */

    public void saveUsers() {
        hospitalStaffSaver.saveUsers();
    }

    /**
     * Updates the details of a specific staff member.
     * Only staff members with roles of DOCTOR or PHARMACIST can be updated.
     * 
     * @param staff The User object representing the staff member to be updated.
     */

    public void updateStaff(User staff) {
        if (staff.getRole() == Role.DOCTOR || staff.getRole() == Role.PHARMACIST) {
            hospitalStaffUpdater.updateStaff(staff.getId());
        } else {
            System.out.println("Error updating Staff.");
        }
    }

    /**
     * Adds a new staff member to the system.
     * Only staff members with roles of DOCTOR or PHARMACIST can be added.
     * 
     * @param staff The User object representing the staff member to be added.
     */

    public void addStaff(User staff) {
        if (staff.getRole() == Role.DOCTOR || staff.getRole() == Role.PHARMACIST) {
            staffs.put(staff.getId(), staff);
            System.out.println("Staff added.");
        } else {
            System.out.println("Error adding Staff.");
        }
    }

    /**
     * Removes a staff member from the system.
     * 
     * @param staff The User object representing the staff member to be removed.
     */

    public void removeStaff(User staff) {
        User removed = staffs.remove(staff.getId());
        if (removed == null) {
            System.out.println("Staff not found.");
        } else {
            System.out.println("Staff " + removed.getId() + " removed.");
        }
    }

    /**
     * Filters staff based on predefined criteria.
     * The specific criteria are handled by the HospitalStaffFilter component.
     */

    public void filterStaff() {
        hospitalStaffFilter.filterStaff();
    }

    /**
     * Displays all the staff members currently in the system.
     * This includes information about their roles and other details.
     */

    public void viewStaff() {
        hospitalStaffViewer.viewStaff();
    }

    /**
     * Retrieves the map of all staff members managed by this controller.
     * 
     * @return A map containing User objects, keyed by their unique IDs.
     */

    public Map<String, User> getStaffs() {
        return this.staffs;
    }
}
