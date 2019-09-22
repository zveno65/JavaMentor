package service;

import dao.DAO;
import dao.UserDAO_Hibernate;
import model.User;
import org.hibernate.SessionFactory;
import util.DBHelper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserServiceHib extends UserService{
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

    DAO<User> getUserDAO() {
        return new UserDAO_Hibernate(sessionFactory.openSession());
    }
}
