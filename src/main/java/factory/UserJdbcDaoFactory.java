package factory;

import dao.Dao;
import dao.UserJdbcDao;

public class UserJdbcDaoFactory implements UserDaoFactory {
    @Override
    public Dao createDao() {
        return UserJdbcDao.getInstance();
    }
}
