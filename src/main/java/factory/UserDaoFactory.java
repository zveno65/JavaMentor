package factory;

import dao.Dao;

public interface UserDaoFactory {
    Dao createDao();
}
