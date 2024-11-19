package enums;

/**
 * The {@code Type} enum represents the different categories of actions or
 * events
 * in the system. This enum allows for the classification of items such as
 * appointments
 * or schedules.
 */

public enum Type {
    /**
     * Used to denote a period when a doctor or user is unavailable.
     */
    LEAVE,

    /**
     * Used to classify scheduled meetings between patients and doctors or other
     * medical staff.
     */
    APPOINTMENT,

    /**
     * Represents other types of events or actions that do not fall into the
     * predefined categories.
     */
    OTHERS
}
