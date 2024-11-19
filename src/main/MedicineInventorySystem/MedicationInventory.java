/**
 * The {@code MedicationInventory} class represents a medication item in the inventory
 * with attributes such as name, current stock level, and alert level for low stock warnings.
 */
package MedicineInventorySystem;

public class MedicationInventory {

    private String name;
    private int stock;
    private int alertlevel;

    /**
     * Constructs a {@code MedicationInventory} object with the specified name, stock, and alert level.
     *
     * @param name       the name of the medication.
     * @param stock      the current stock level of the medication.
     * @param alertlevel the alert level at which a low-stock warning is triggered.
     */
    public MedicationInventory(String name, int stock, int alertlevel) {
        this.name = name;
        this.stock = stock;
        this.alertlevel = alertlevel;
    }

    /**
     * Gets the name of the medication.
     *
     * @return the name of the medication.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the medication.
     *
     * @param name the name of the medication.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the current stock level of the medication.
     *
     * @return the current stock level.
     */
    public int getStock() {
        return stock;
    }

    /**
     * Sets the stock level of the medication.
     *
     * @param stock the stock level to set.
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * Gets the alert level for the medication.
     *
     * @return the alert level for low stock warnings.
     */
    public int getAlertlevel() {
        return alertlevel;
    }

    /**
     * Sets the alert level for the medication.
     *
     * @param alertlevel the alert level to set.
     */
    public void setAlertlevel(int alertlevel) {
        this.alertlevel = alertlevel;
    }
}
