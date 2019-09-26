package ru.plotnikov.factory;

import ru.plotnikov.dao.Dao;

public interface UserDaoFactory {
    Dao createDao();
}
