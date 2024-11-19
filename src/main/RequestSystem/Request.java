
package RequestSystem;

import enums.Flag;
/**
 * The {@code Request} class represents a stock replenishment request for a specific medication.
 * It includes details about the request such as the pharmacist making the request, the medication name,
 * the quantity to increase stock by, any notes, the approval status, and the flag representing the request state.
 */
public class Request {

    private String requestId;
    private String pharmId;
    private String medicationName;
    private int increaseStockBy;
    private String notes;
    private String approvedBy;
    private Flag flag;

    /**
     * Constructs a {@code Request} object with all attributes.
     *
     * @param requestId       the unique ID of the request.
     * @param pharmId         the ID of the pharmacist making the request.
     * @param medicationName  the name of the medication for which stock is to be replenished.
     * @param increaseStockBy the quantity by which stock should be increased.
     * @param notes           any additional notes or comments for the request.
     * @param approvedBy      the ID of the administrator who approved the request (nullable).
     * @param flag            the current status of the request.
     */
    public Request(String requestId, String pharmId, String medicationName, int increaseStockBy, String notes,
                   String approvedBy, Flag flag) {
        this.requestId = requestId;
        this.pharmId = pharmId;
        this.medicationName = medicationName;
        this.increaseStockBy = increaseStockBy;
        this.notes = notes;
        this.approvedBy = approvedBy;
        this.flag = flag;
    }

    /**
     * Constructs a {@code Request} object with default flag set to {@code PENDING}
     * and no approval information.
     *
     * @param requestId       the unique ID of the request.
     * @param pharmId         the ID of the pharmacist making the request.
     * @param medicationName  the name of the medication for which stock is to be replenished.
     * @param increaseStockBy the quantity by which stock should be increased.
     * @param notes           any additional notes or comments for the request.
     */
    public Request(String requestId, String pharmId, String medicationName, int increaseStockBy, String notes) {
        this.requestId = requestId;
        this.pharmId = pharmId;
        this.medicationName = medicationName;
        this.increaseStockBy = increaseStockBy;
        this.notes = notes;
        this.approvedBy = null;
        this.flag = Flag.PENDING;
    }

    /**
     * Gets the unique ID of the request.
     *
     * @return the request ID.
     */
    public String getRequestId() {
        return requestId;
    }

    /**
     * Sets the unique ID of the request.
     *
     * @param requestId the request ID.
     */
    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    /**
     * Gets the ID of the pharmacist making the request.
     *
     * @return the pharmacist ID.
     */
    public String getPharmId() {
        return pharmId;
    }

    /**
     * Sets the ID of the pharmacist making the request.
     *
     * @param pharmId the pharmacist ID.
     */
    public void setPharmId(String pharmId) {
        this.pharmId = pharmId;
    }

    /**
     * Gets the current status flag of the request.
     *
     * @return the status flag.
     */
    public Flag getFlag() {
        return flag;
    }

    /**
     * Sets the status flag of the request.
     *
     * @param flag the status flag.
     */
    public void setFlag(Flag flag) {
        this.flag = flag;
    }

    /**
     * Gets the name of the medication associated with the request.
     *
     * @return the medication name.
     */
    public String getMedicationName() {
        return medicationName;
    }

    /**
     * Sets the name of the medication associated with the request.
     *
     * @param medicationName the medication name.
     */
    public void setMedicationName(String medicationName) {
        this.medicationName = medicationName;
    }

    /**
     * Gets the quantity by which stock is to be increased.
     *
     * @return the quantity to increase stock by.
     */
    public int getIncreaseStockBy() {
        return increaseStockBy;
    }

    /**
     * Sets the quantity by which stock is to be increased.
     *
     * @param increaseStockBy the quantity to increase stock by.
     */
    public void setIncreaseStockBy(int increaseStockBy) {
        this.increaseStockBy = increaseStockBy;
    }

    /**
     * Gets any additional notes associated with the request.
     *
     * @return the notes.
     */
    public String getNotes() {
        return notes;
    }

    /**
     * Sets any additional notes associated with the request.
     *
     * @param notes the notes.
     */
    public void setNotes(String notes) {
        this.notes = notes;
    }

    /**
     * Gets the ID of the administrator who approved the request.
     *
     * @return the approval ID (nullable).
     */
    public String getApprovedBy() {
        return approvedBy;
    }

    /**
     * Sets the ID of the administrator who approved the request.
     *
     * @param approvedBy the approval ID.
     */
    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }

    /**
     * Provides a string representation of the {@code Request} object.
     *
     * @return a string containing all attributes of the request.
     */
    @Override
    public String toString() {
        return "Request [requestId=" + requestId + ", pharmId=" + pharmId + ", medicationName=" + medicationName
                + ", increaseStockBy=" + increaseStockBy + ", notes=" + notes + ", ApprovedBy=" + approvedBy + ", flag=" + flag + "]";
    }
}
