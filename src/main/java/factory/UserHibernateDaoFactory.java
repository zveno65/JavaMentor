package factory;

import dao.Dao;
import dao.UserHibernateDao;

public class UserHibernateDaoFactory implements UserDaoFactory {
    @Override
    public Dao createDao() {
        return UserHibernateDao.getInstance();
    }
}
