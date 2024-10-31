package entity;

public class Pharmacist extends User {

    private String name;
    private String gender;
    private Integer age;

    public Pharmacist(String id, String name, String gender, Integer age) {
        super(id);
        this.name = name;
        this.gender = gender;
        this.age = age;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String var1) {
        this.name = var1;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String var1) {
        this.gender = var1;
    }

    public Integer getAge() {
        return this.age;
    }

    public void setAge(Integer var1) {
        this.age = var1;
    }
}
