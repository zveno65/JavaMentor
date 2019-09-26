package ru.plotnikov.service;

import ru.plotnikov.dao.Dao;
import ru.plotnikov.model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    void addUser(User user);

    void updateUser(User user);

    void deleteUser(User user);

    User getUserById(long id);

    Dao<User> getUserDAO();

    boolean isExist(String name, String password);

    User getUserByData(String name, String pass);
}
