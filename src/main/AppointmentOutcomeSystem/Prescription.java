package AppointmentOutcomeSystem;

import enums.Flag;

/**
 * Represents a prescription associated with an appointment outcome record.
 * Contains details about the medication, dosage, amount, and its current flag
 * status.
 */

public class Prescription {

    /**
     * Name of the medication prescribed.
     */
    private String medName;

    /**
     * Status flag for the prescription, indicating its current state (e.g.,
     * PENDING, DISPENSED, etc.).
     */
    private Flag flag;

    /**
     * Quantity of the medication prescribed.
     */
    private Integer amount;

    /**
     * Dosage instructions for the medication.
     */
    private String dosage;

    /**
     * Constructs a new {@code Prescription} with the specified medication name,
     * amount,
     * and dosage. The flag is set to {@link Flag#PENDING} by default.
     *
     * @param medName the name of the medication
     * @param amount  the quantity of the medication
     * @param dosage  the dosage instructions for the medication
     */
    public Prescription(String medName, Integer amount, String dosage) {
        this.medName = medName;
        this.flag = Flag.PENDING;
        this.amount = amount;
        this.dosage = dosage;
    }

    /**
     * Constructs a new {@code Prescription} with the specified medication name,
     * flag,
     * amount, and dosage.
     *
     * @param medName the name of the medication
     * @param flag    the status flag of the prescription
     * @param amount  the quantity of the medication
     * @param dosage  the dosage instructions for the medication
     */
    public Prescription(String medName, Flag flag, Integer amount, String dosage) {
        this.medName = medName;
        this.flag = flag;
        this.amount = amount;
        this.dosage = dosage;
    }

    /**
     * Gets the name of the medication.
     *
     * @return the name of the medication
     */
    public String getMedName() {
        return medName;
    }

    /**
     * Sets the name of the medication.
     *
     * @param medName the new name of the medication
     */
    public void setMedName(String medName) {
        this.medName = medName;
    }

    /**
     * Gets the quantity of the medication prescribed.
     *
     * @return the quantity of the medication
     */
    public Integer getAmount() {
        return amount;
    }

    /**
     * Sets the quantity of the medication prescribed.
     *
     * @param amount the new quantity of the medication
     */
    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    /**
     * Gets the dosage instructions for the medication.
     *
     * @return the dosage instructions
     */
    public String getDosage() {
        return dosage;
    }

    /**
     * Sets the dosage instructions for the medication.
     *
     * @param dosage the new dosage instructions
     */
    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    /**
     * Gets the current flag status of the prescription.
     *
     * @return the current flag status
     */
    public Flag getFlag() {
        return flag;
    }

    /**
     * Sets the flag status of the prescription.
     *
     * @param flag the new flag status
     */
    public void setFlag(Flag flag) {
        this.flag = flag;
    }

    /**
     * Returns a string representation of the prescription object.
     *
     * @return a string representation of the prescription
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Prescription: ");
        sb.append("Medication =").append(medName);
        sb.append(", dosage =").append(dosage);
        sb.append(", amount =").append(amount);
        sb.append(", flag =").append(flag);
        return sb.toString();
    }
}
