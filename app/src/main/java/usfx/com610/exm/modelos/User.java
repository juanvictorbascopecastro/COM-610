package usfx.com610.exm.modelos;

public class User {
    private String id;
    private String name;
    private String lastName;
    private String phone;

    public User(String id, String name, String lastName, String phone) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
