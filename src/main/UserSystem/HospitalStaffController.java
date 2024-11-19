package UserSystem;

import enums.*;
import java.util.*;

/**
 * The {@code HospitalStaffController} class manages hospital staff operations, 
 * including adding, removing, updating, filtering, viewing, and saving staff records. 
 * It ensures that only valid staff (non-patients) can be managed.
 */
public class HospitalStaffController {

    private Map<String, User> staffs;
    private UserSaver hospitalStaffSaver;
    private UserLoader hospitalStaffLoader;
    private HospitalStaffViewer hospitalStaffViewer;
    private HospitalStaffUpdater hospitalStaffUpdater;
    private HospitalStaffFilter hospitalStaffFilter;

    /**
     * Constructs a {@code HospitalStaffController} and initializes components.
     * Loads initial staff data from storage.
     */
    public HospitalStaffController() {
        this.staffs = new HashMap<>();
        this.hospitalStaffSaver = new UserSaver(this.staffs);
        this.hospitalStaffLoader = new UserLoader(this.staffs);
        this.hospitalStaffViewer = new HospitalStaffViewer(this.staffs);
        this.hospitalStaffUpdater = new HospitalStaffUpdater(this.staffs);
        this.hospitalStaffFilter = new HospitalStaffFilter(this.staffs);

        // Load initial staff records
        hospitalStaffLoader.loadInitialUsers();
    }

    /**
     * Saves the current staff data to persistent storage.
     */
    public void saveUsers() {
        hospitalStaffSaver.saveUsers();
    }

    /**
     * Loads staff data from persistent storage into memory.
     */
    public void loadUsers() {
        hospitalStaffLoader.loadInitialUsers();
    }

    /**
     * Updates the details of a specific staff member.
     *
     * @param staff the staff member to update.
     */
    public void updateStaff(User staff) {
        loadUsers();
        if (staff.getRole() != Role.PATIENT) {
            hospitalStaffUpdater.updateStaff(staff.getId());
        } else {
            System.out.println("Error updating Staff.");
        }
        saveUsers();
    }

    /**
     * Adds a new staff member to the system.
     *
     * @param staff the staff member to add.
     */
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

    /**
     * Removes a specific staff member from the system.
     *
     * @param staff the staff member to remove.
     */
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

    /**
     * Filters staff members based on specified criteria.
     */
    public void filterStaff() {
        loadUsers();
        hospitalStaffFilter.filterStaff();
        saveUsers();
    }

    /**
     * Displays the details of all staff members.
     */
    public void viewStaff() {
        loadUsers();
        hospitalStaffViewer.viewStaff();
        saveUsers();
    }

    /**
     * Retrieves all current staff members as a map.
     *
     * @return a map of staff IDs to {@link User} objects.
     */
    public Map<String, User> getStaffs() {
        loadUsers();
        return this.staffs;
    }
}
