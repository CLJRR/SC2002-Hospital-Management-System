package MedicineInventory;

public class Medicine {
    private String name;

    public Medicine(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    // Override equals and hashCode for use in collections
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Medicine medicine = (Medicine) obj;
        return name.equals(medicine.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
