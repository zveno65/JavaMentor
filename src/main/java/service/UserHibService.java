package service;

import dao.Dao;
import dao.UserHibernateDao;
import model.User;

public class UserHibService {
    private static UserHibService userServiceHib;

    private UserHibService() {

    }

    public static UserHibService getInstance() {
        if (userServiceHib == null) {
            userServiceHib = new UserHibService();
        }
        return userServiceHib;
    }

    Dao<User> getUserDAO() {
        return UserHibernateDao.getInstance();
    }
}
