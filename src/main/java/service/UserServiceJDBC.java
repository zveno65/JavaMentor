package service;

import dao.DAO;
import dao.UserDAO_JDBC;
import model.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.DBHelper;

public class UserServiceJDBC {

    private static UserServiceJDBC userService;

    private Connection connection;

    public static UserServiceJDBC getInstance(){
        if (userService == null) {
            userService = new UserServiceJDBC(DBHelper.getConnection());
        }
        return userService;
    }

    private UserServiceJDBC(Connection connection) {
        try {
            this.connection = connection;
            new UserDAO_JDBC(connection).createTable();
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

//    public void cleanUp() {
//        try {
//            getUserDAO().dropTable();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//    public void createTable() {
//        try {
//            getUserDAO().createTable();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    private DAO<User> getUserDAO() {
        return new UserDAO_JDBC(connection);
    }

}
