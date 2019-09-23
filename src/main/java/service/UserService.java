package service;

import dao.Dao;
import model.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    void addUser(User user);

    void updateUser(User user);

    void deleteUser(User user);

    User getUserById(long id);

    Dao<User> getUserDAO();
}
