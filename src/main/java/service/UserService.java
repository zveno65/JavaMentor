package service;

import dao.UserDAO;
import model.User;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.mysql.jdbc.*;
import util.DBHelper;

public class UserService {

    private static UserService userService = new UserService();

    public static UserService getInstance() {
        return userService;
    }

    private UserService() {
        try {
            getUserDAO().createTable();
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

    public void cleanUp() {
        try {
            getUserDAO().dropTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void createTable() {
        try {
            getUserDAO().createTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static UserDAO getUserDAO() {
        return new UserDAO(DBHelper.getConnection());
    }

}
