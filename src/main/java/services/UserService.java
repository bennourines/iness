package services;


import models.User;
import utils.MyDatabase;
import javafx.collections.FXCollections;
import java.sql.*;
import java.util.ArrayList;

import java.util.List;
import javafx.collections.ObservableList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class UserService {

    private Connection connection;

    public UserService(){
        connection = MyDatabase.getInstance().getConnection();
    }


    public void create(User user) throws SQLException {
        String sql = "INSERT INTO user (cin, user_name, phone, email, adress, password, role) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, user.getCIN());
            statement.setString(2, user.getUserName());
            statement.setInt(3, user.getPhone());
            statement.setString(4, user.getEmail());
            statement.setString(5, user.getAdresse());
            statement.setString(6, user.getPassword());
            statement.setString(7, user.getRole());
            statement.executeUpdate();

        }
    }







    public void update(User user) throws SQLException {
        try {
            String sql = "UPDATE user SET cin = ?, user_name = ?, phone = ?, email = ?, adress = ? WHERE id_user = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, user.getCIN());
            ps.setString(2, user.getUserName());
            ps.setInt(3, user.getPhone());
            ps.setString(4, user.getEmail());
            ps.setString(5, user.getAdresse());
            ps.setInt(6, user.getId());
            ps.executeUpdate();
            System.out.println("Utlisateur est modifié");
        } catch (SQLException var4) {
            System.out.println(var4.getMessage());
        }
    }



    public void delete(User user) throws SQLException {
        String sql = "DELETE FROM user WHERE id_user = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, user.getId());
            statement.executeUpdate();
        }
    }



    public List<User> read() throws SQLException {
        String sql = "SELECT * FROM user";
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        List<User> users = new ArrayList<>();
        while (rs.next()) {
            User user = new User(rs.getInt("cin"), rs.getString("user_name"), rs.getInt("phone"), rs.getString("email"), rs.getString("adress"), rs.getString("password"));
            user.setId(rs.getInt("id"));
            user.setCIN(rs.getInt("cin"));
            user.setUserName(rs.getString("username"));
            user.setPhone(rs.getInt("phone"));
            user.setEmail(rs.getString("email"));
            user.setAdresse(rs.getString("adress"));
            user.setPassword(rs.getString("password"));
            user.setRole(rs.getString("role"));


            users.add(user);
        }
        return users;
    }


    public String getUserRole(int id_user) throws SQLException {
        String sql = "SELECT role from user where id_user='" + id_user + "'";
        Statement statement = connection.createStatement();
        ResultSet res = statement.executeQuery(sql);
        String role = null;

        while (res.next()) {
            role = res.getString("role");

        }

        return role;
    }

    public User authentifier(String username, String pwd) throws SQLException {
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

    public ObservableList<User> getAllUsers() throws SQLException {
        ObservableList<User> userList = FXCollections.observableArrayList();
        String query = "SELECT * FROM user";

        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                User user = new User(
                        resultSet.getInt("id_user"),
                        resultSet.getInt("cin"),
                        resultSet.getString("user_name"),
                        resultSet.getInt("phone"),
                        resultSet.getString("email"),
                        resultSet.getString("adress")
                );
                userList.add(user);
            }
        }

        return userList;
    }


}
