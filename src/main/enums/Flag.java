package enums;

/**
 * The {@code Flag} enum represents the various states or statuses
 * that can be assigned to an entity in the system, such as appointments,
 * prescriptions, or timeslots. These flags provide a standardized way
 * to manage and track different stages of processing.
 */

public enum Flag {
    /**
     * Indicates that an action or process is pending and has not yet been resolved.
     */
    PENDING,

    /**
     * Indicates that a request, appointment, or process has been rejected.
     */
    REJECTED,

    /**
     * Indicates that an appointment or request has been confirmed and approved.
     */
    CONFIRMED,

    /**
     * Indicates that an appointment or request has been canceled.
     */
    CANCELLED,

    /**
     * Indicates that a process or action has been successfully completed.
     */
    COMPLETED,

    /**
     * Indicates that a prescription has been dispensed to the patient.
     */
    DISPENSED,

    /**
     * Indicates that a timeslot, appointment, or resource is available for use.
     */
    AVAILABLE,

    /**
     * Indicates that a request or action has been reviewed and approved.
     */
    APPROVED,
}
