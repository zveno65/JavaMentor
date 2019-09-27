package ru.plotnikov.service;

import ru.plotnikov.dao.Dao;
import ru.plotnikov.dao.UserJdbcDao;
import ru.plotnikov.factory.FactoryManager;
import ru.plotnikov.model.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserImplService implements UserService{

    private static UserImplService userService;

    public static UserImplService getInstance(){
        if (userService == null) {
            userService = new UserImplService();
        }
        return userService;
    }

    private UserImplService() {
        try {
            UserJdbcDao.getInstance().createTable();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try {
            users = getUserDAO().findAll();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void addUser(User user) {
        try {
            getUserDAO().save(user);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUser(User user) {
        try {
            getUserDAO().update(user);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(User user) {
        try {
            getUserDAO().delete(user);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getUserById(long id) {
        try {
            return getUserDAO().find(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Dao<User> getUserDAO() {
        return FactoryManager.getFactory().createDao();
    };
}
