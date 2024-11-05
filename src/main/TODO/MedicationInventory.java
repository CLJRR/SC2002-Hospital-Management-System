package TODO;

public class MedicationInventory {

    private String name;
    private int stock;
    private int alertlevel;

    public MedicationInventory(String name, int stock, int alertlevel) {
        this.name = name;
        this.stock = stock;
        this.alertlevel = alertlevel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getAlertlevel() {
        return alertlevel;
    }

    public void setAlertlevel(int alertlevel) {
        this.alertlevel = alertlevel;
    }

}
