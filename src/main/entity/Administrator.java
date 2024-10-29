package entity;

public class Administrator extends User {
    //comment
    private String name;
    private String gender;
    private Integer age;

    public Administrator(String id, String name, String gender, Integer age) {
        super(id);
        this.age = age;
        this.gender = gender;
        this.name = name;
    }

    // Getters and setters for the Doctor fields
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
