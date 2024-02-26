package models;


public class User {
    private int id;
    private int CIN;
    private String UserName;
    private int Phone;
    private String Email;
    private String Adresse;
    private String Password;
    private String role;
    public static User Current_User;

    public User(int cin, String userName, int phone, String email, String adress, String password) {
    }

    public User() {
    }

    public User(int id, int CIN, String userName, int phone, String email, String adresse, String password, String role) {
        this.id = id;
        this.CIN = CIN;
        this.UserName = userName;
        this.Phone = phone;
        this.Email = email;
        this.Adresse = adresse;
        this.Password = password;
        this.role = role;
    }

    public User(int CIN, String userName, int phone, String email, String adresse, String password, String role) {
        this.CIN = CIN;
        this.UserName = userName;
        this.Phone = phone;
        this.Email = email;
        this.Adresse = adresse;
        this.Password = password;
        this.role = role;
    }
    public User(int id, int CIN, String UserName, int Phone, String Email, String Adresse) {
        this.id = id;
        this.CIN = CIN;
        this.UserName = UserName;
        this.Phone =Phone;
        this.Email = Email;
        this.Adresse = Adresse;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCIN() {
        return CIN;
    }

    public void setCIN(int CIN) {
        this.CIN = CIN;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }


    public int getPhone() {
        return Phone;
    }

    public void setPhone(int phone) {
        Phone = phone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getAdresse() {
        return Adresse;
    }

    public void setAdresse(String adresse) {
        Adresse = adresse;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public static User getCurrent_User() {
        return Current_User;
    }

    public static void setCurrent_User(User current_User) {
        Current_User = current_User;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", CIN=" + CIN +
                ", UserName='" + UserName + '\'' +
                ", Phone=" + Phone +
                ", Email='" + Email + '\'' +
                ", Adresse='" + Adresse + '\'' +
                ", Password='" + Password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}


