package service;

import dao.DAO;
import dao.UserDAO_JDBC;
import model.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.DBHelper;

public class UserServiceJDBC extends UserService{

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

    DAO<User> getUserDAO() {
        return new UserDAO_JDBC(connection);
    }

}
