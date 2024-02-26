package services;

import models.User;

import java.sql.SQLException;
import java.util.List;

public interface IService<T> {

    void create(T t) throws SQLException;
    void update(T t) throws SQLException;
    void delete(User user) throws SQLException;
    List<T> read() throws SQLException;

    void delete(int id);
}
