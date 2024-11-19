/**
 * The {@code Role} enum represents the different roles that a user can have in the system.
 * Each role defines the specific permissions and responsibilities assigned to the user.
 */
package enums;

public enum Role {
    /**
     * Administrators have the highest level of access and can manage system settings,
     * users, and data across all modules.
     */
    ADMINISTRATOR,

    /**
     * Pharmacists are responsible for managing prescriptions, dispensing medications,
     * and maintaining inventory records.
     */
    PHARMACIST,

    /**
     * Doctors can manage appointments, provide diagnoses, and prescribe medications.
     */
    DOCTOR,

    /**
     * Patients can schedule, reschedule, and cancel appointments and view their medical records.
     */
    PATIENT
}
