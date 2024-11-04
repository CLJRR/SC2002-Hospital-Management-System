package entity;

import enums.Flag;

public class Request {

    private String requestId;
    private String pharmId;
    private String medicationName;
    private int increaseStockBy;
    private String notes;
    private Flag flag;

    public Request(String requestId, String pharmId, String medicationName, int increaseStockBy, String notes, Flag flag) {
        this.requestId = requestId;
        this.pharmId = pharmId;
        this.medicationName = medicationName;
        this.increaseStockBy = increaseStockBy;
        this.notes = notes;
        this.flag = flag;
    }

    public Request(String requestId, String pharmId, String medicationName, int increaseStockBy, String notes) {
        this.requestId = requestId;
        this.pharmId = pharmId;
        this.medicationName = medicationName;
        this.increaseStockBy = increaseStockBy;
        this.notes = notes;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getPharmId() {
        return pharmId;
    }

    public void setPharmId(String pharmId) {
        this.pharmId = pharmId;
    }

    public Flag getFlag() {
        return flag;
    }

    public void setFlag(Flag flag) {
        this.flag = flag;
    }

    public String getMedicationName() {
        return medicationName;
    }

    public void setMedicationName(String MedicationName) {
        this.medicationName = MedicationName;
    }

    public int getIncreaseStockBy() {
        return increaseStockBy;
    }

    public void setIncreaseStockBy(int increaseStockBy) {
        this.increaseStockBy = increaseStockBy;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "Request [requestId=" + requestId + ", pharmId=" + pharmId + ", medicationName=" + medicationName
                + ", increaseStockBy=" + increaseStockBy + ", notes=" + notes + ", flag=" + flag + "]";
    }

}
