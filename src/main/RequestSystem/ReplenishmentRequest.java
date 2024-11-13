package RequestSystem;

import enums.Flag;

public class ReplenishmentRequest {
    private String medicationName;
    private int quantity;
    private String pharmacistId;
    private Flag flag; // Field to store the status of the request

    // Constructor to initialize the replenishment request
    public ReplenishmentRequest(String medicationName, int quantity, String pharmacistId) {
        this.medicationName = medicationName;
        this.quantity = quantity;
        this.pharmacistId = pharmacistId;
        this.flag = Flag.PENDING;  // Default flag when a request is created
    }

    // Getter and Setter for flag
    public Flag getFlag() {
        return flag;
    }

    public void setFlag(Flag flag) {
        this.flag = flag;
    }

    // Getter for medicationName
    public String getMedicationName() {
        return medicationName;
    }

    // Getter for quantity
    public int getQuantity() {
        return quantity;
    }

    // Getter for pharmacistId
    public String getPharmacistId() {
        return pharmacistId;
    }
}
