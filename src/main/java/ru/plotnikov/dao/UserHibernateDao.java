package ru.plotnikov.dao;

import ru.plotnikov.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ru.plotnikov.util.DBHelper;

import java.sql.SQLException;
import java.util.List;

public class UserHibernateDao implements Dao<User> {

    private SessionFactory sessionFactory;

    private static UserHibernateDao userHibernateDao;

    public static UserHibernateDao getInstance() {
        if (userHibernateDao == null) {
            userHibernateDao = new UserHibernateDao(DBHelper.getSessionFactory());
        }
        return userHibernateDao;
    }

    private UserHibernateDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<User> findAll() throws SQLException {
        Session session = sessionFactory.openSession();
        List<User> list = session.createQuery("FROM User").list();
        session.close();
        return list;
    }

    @Override
    public User find(Long id) throws SQLException {
        Session session = sessionFactory.openSession();
        String hql = "from User where id = :id";
        Query<User> query = session.createQuery(hql);
        query.setParameter("id", id);
        User user = query.uniqueResult();
        session.close();
        return user;
    }

    @Override
    public boolean save(User user) throws SQLException {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(User user) throws SQLException {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(user);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(User user) throws SQLException {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(user);
        transaction.commit();
        session.close();
        return true;
    }
}
