package entity;

import enums.Flag;

public class Request {

    private String requestId;
    private String pharmId;
    private String MedicationName;
    private int increaseStockBy;
    private Flag flag;
    private String notes;

    public Request(String requestId, String pharmId, String medicationName, int increaseStockBy, Flag flag) {
        this.requestId = requestId;
        this.pharmId = pharmId;
        MedicationName = medicationName;
        this.increaseStockBy = increaseStockBy;
        this.flag = flag;
    }

    public Request(String requestId, String pharmId, String medicationName, int increaseStockBy) {
        this.requestId = requestId;
        this.pharmId = pharmId;
        MedicationName = medicationName;
        this.increaseStockBy = increaseStockBy;
        this.flag = Flag.PENDING;
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
        return MedicationName;
    }

    public void setMedicationName(String MedicationName) {
        this.MedicationName = MedicationName;
    }

    public int getIncreaseStockBy() {
        return increaseStockBy;
    }

    public void setIncreaseStockBy(int increaseStockBy) {
        this.increaseStockBy = increaseStockBy;
    }

}
