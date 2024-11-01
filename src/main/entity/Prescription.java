package entity;

import enums.Status;

public class Prescription {

    String medName;
    Status status;
    Integer amount;
    String dosage;

    public Prescription(String medName, Integer amount, String dosage) {
        this.medName = medName;
        this.status = Status.PENDING;
        this.amount = amount;
        this.dosage = dosage;
    }

    public Prescription(String medName, Status status, Integer amount, String dosage) {
        this.medName = medName;
        this.status = status;
        this.amount = amount;
        this.dosage = dosage;
    }

    public String getMedName() {
        return medName;
    }

    public void setMedName(String medName) {
        this.medName = medName;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Prescription{");
        sb.append("medName=").append(medName);
        sb.append(", status=").append(status);
        sb.append(", amount=").append(amount);
        sb.append(", dosage=").append(dosage);
        sb.append('}');
        return sb.toString();
    }
}
