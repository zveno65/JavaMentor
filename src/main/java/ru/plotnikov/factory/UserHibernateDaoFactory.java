package ru.plotnikov.factory;

import ru.plotnikov.dao.Dao;
import ru.plotnikov.dao.UserHibernateDao;

public class UserHibernateDaoFactory implements UserDaoFactory {
    @Override
    public Dao createDao() {
        return UserHibernateDao.getInstance();
    }
}
