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

    public boolean isExist(String name, String password) {
        for (User user1 : getAllUsers())
            if (user1.getPassword().equals(password) && user1.getName().equals(name))
                return true;
        return false;
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

    @Override
    public User getUserByData(String name, String pass) {
        try {
            List<User> list = getUserDAO().findAll();
            for (User user : list) {
                if (user.getName().equals(name) && user.getPassword().equals(pass))
                    return user;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
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
