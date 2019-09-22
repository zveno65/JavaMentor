package service;

import dao.DAO;
import dao.UserDAO_Hibernate;
import model.User;
import org.hibernate.SessionFactory;
import util.DBHelper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserServiceHib {
    private static UserServiceHib carService;

    private SessionFactory sessionFactory;

    private UserServiceHib(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public static UserServiceHib getInstance() {
        if (carService == null) {
            carService = new UserServiceHib(DBHelper.getSessionFactory());
        }
        return carService;
    }

    public List<User> getAllUsers() {
        List users = new ArrayList<>();
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

    private DAO<User> getUserDAO() {
        return new UserDAO_Hibernate(sessionFactory.openSession());
    }
}
