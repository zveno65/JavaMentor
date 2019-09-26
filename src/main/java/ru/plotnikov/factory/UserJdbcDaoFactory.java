package ru.plotnikov.factory;

import ru.plotnikov.dao.Dao;
import ru.plotnikov.dao.UserJdbcDao;

public class UserJdbcDaoFactory implements UserDaoFactory {
    @Override
    public Dao createDao() {
        return UserJdbcDao.getInstance();
    }
}
