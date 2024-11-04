package entity;

import enums.Flag;

public class Prescription {

    String medName;
    Flag flag;
    Integer amount;
    String dosage;

    public Prescription(String medName, Integer amount, String dosage) {
        this.medName = medName;
        this.flag = Flag.PENDING;
        this.amount = amount;
        this.dosage = dosage;
    }

    public Prescription(String medName, Flag flag, Integer amount, String dosage) {
        this.medName = medName;
        this.flag = flag;
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

    public Flag getStatus() {
        return flag;
    }

    public void setStatus(Flag flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Prescription{");
        sb.append("medName=").append(medName);
        sb.append(", flag=").append(flag);
        sb.append(", amount=").append(amount);
        sb.append(", dosage=").append(dosage);
        sb.append('}');
        return sb.toString();
    }
}
