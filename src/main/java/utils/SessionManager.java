//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package utils;

import models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public final class SessionManager {
    private static SessionManager instance;
    private static int id;
    private static int CIN;
    private static String UserName;
    private static int Numero;
    private static String Email;
    private static String Adresse;
    private static String roles;
    public static User Current_User;
    private Connection connection;


    public SessionManager(){
        connection = MyDatabase.getInstance().getConnection();
    }
    private SessionManager(int id, int cin, String user_name, int numero, String email, String address, String role) {
        SessionManager.id = id;
        CIN = cin;
        UserName = user_name;
        Numero = numero;
        Email = email;
        Adresse = address;
        roles = role;
    }

    public static SessionManager getInstace(int id, int cin, String user_name, int numero, String email, String address, String role) {
        if (instance == null) {
            instance = new SessionManager(id, cin, user_name, numero, email, address, role);
        }

        return instance;
    }

    public static SessionManager getInstance() {
        return instance;
    }

    public static void setInstance(SessionManager instance) {
        SessionManager.instance = instance;
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        SessionManager.id = id;
    }

    public static int getCIN() {
        return CIN;
    }

    public static void setCIN(int CIN) {
        SessionManager.CIN = CIN;
    }

    public static String getUserName() {
        return UserName;
    }

    public static void setUserName(String UserName) {
        SessionManager.UserName = UserName;
    }

    public static int getNumero() {
        return Numero;
    }

    public static void setNumero(int Numero) {
        SessionManager.Numero = Numero;
    }

    public static String getEmail() {
        return Email;
    }

    public static void setEmail(String Email) {
        SessionManager.Email = Email;
    }

    public static String getAdresse() {
        return Adresse;
    }

    public static void setAdresse(String Adresse) {
        SessionManager.Adresse = Adresse;
    }

    public static String getRoles() {
        return roles;
    }

    public static void setRoles(String roles) {
        SessionManager.roles = roles;
    }

    public static void cleanUserSession() {
        id = 0;
        CIN = 0;
        UserName = "";
        Numero = 0;
        Email = "";
        Adresse = "";
        roles = "";
    }

    public String toString() {
        return "UserSession{userName='" + UserName + '\'' + "email='" + Email + '\'' + "id = '" + id + '\'' + ", privileges=" + roles + '}';
    }
    public static User getCurrent_User() {
        return Current_User;
    }
    static class cleanUserSession {
        public cleanUserSession() {
            SessionManager.id = 0;
            SessionManager.CIN = 0;
            SessionManager.UserName = "";
            SessionManager.Numero = 0;
            SessionManager.Email = "";
            SessionManager.Adresse = "";
            SessionManager.roles = "";
        }
    }




    public User auth(String username, String pwd) throws SQLException {
        String req = "SELECT * FROM user WHERE email=? AND password=?";
        try (PreparedStatement statement = connection.prepareStatement(req)) {
            statement.setString(1, username);
            statement.setString(2, pwd);

            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    User user = new User(rs.getInt("cin"), rs.getString("user_name"), rs.getInt("phone"), rs.getString("email"), rs.getString("adress"), rs.getString("password"));
                    user.setId(rs.getInt("id_user"));
                    user.setCIN(rs.getInt("cin"));
                    user.setUserName(rs.getString("user_name"));
                    user.setPhone(rs.getInt("phone"));
                    user.setEmail(rs.getString("email"));
                    user.setAdresse(rs.getString("adress"));
                    user.setPassword(rs.getString("password"));
                    user.setRole(rs.getString("role"));
                    System.out.println("login successful");
                    return user;
                }
            }
        } catch (SQLException e) {
            // Handle SQL exception appropriately
            e.printStackTrace();
        }
        System.out.println("login not successful");
        return null;
    }
}
