package service;

import dao.Dao;
import dao.UserJdbcDao;
import model.User;

import java.sql.SQLException;

public class UserJdbcService {

    private static UserJdbcService userService;

    public static UserJdbcService getInstance(){
        if (userService == null) {
            userService = new UserJdbcService();
        }
        return userService;
    }

    private UserJdbcService() {
        try {
            UserJdbcDao.getInstance().createTable();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    Dao<User> getUserDAO() {
        return UserJdbcDao.getInstance();
    }

}
