package models;

public class Admin {
    private int id,cin ;

    private String lastname,firstname,email,address,role,password,phone,image;
    private boolean subscribed;


    public Admin() {
    }

    public Admin(int id, int cin, String lastname, String firstname, String email, String address, String role, String password, String phone, String image, boolean subscribed) {
        this.id = id;
        this.cin = cin;
        this.lastname = lastname;
        this.firstname = firstname;
        this.email = email;
        this.address = address;
        this.role = role;
        this.password = password;
        this.phone = phone;
        this.image = image;
        this.subscribed = subscribed;
    }

    public Admin(int cin, String lastname, String firstname, String email, String address, String role, String password, String phone, String image, boolean subscribed) {
        this.cin = cin;
        this.lastname = lastname;
        this.firstname = firstname;
        this.email = email;
        this.address = address;
        this.role = role;
        this.password = password;
        this.phone = phone;
        this.image = image;
        this.subscribed = subscribed;
    }

    public Admin(String lastname, String firstname, String email, String address, String phone){

        this.lastname = lastname;
        this.firstname = firstname;
        this.email = email;
        this.address = address;
        this.phone = phone;



    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCin() {
        return cin;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isSubscribed() {
        return subscribed;
    }

    public void setSubscribed(boolean subscribed) {
        this.subscribed = subscribed;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", cin=" + cin +
                ", lastname='" + lastname + '\'' +
                ", firstname='" + firstname + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", role='" + role + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", image='" + image + '\'' +
                ", subscribed=" + subscribed +
                '}';
    }
}
