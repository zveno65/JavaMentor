package dao;

import model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

public class UserDAO_Hibernate implements DAO<User> {
    private Session session;

    public UserDAO_Hibernate(Session session) {
        this.session = session;
    }

    @Override
    public List<User> findAll() throws SQLException {
        List<User> list = session.createQuery("FROM User").list();
        session.close();
        return list;
    }

    @Override
    public User find(Long id) throws SQLException {
        String hql = "from User where id = :id";
        Query<User> query = session.createQuery(hql);
        query.setParameter("id", id);
        User user = query.uniqueResult();
        session.close();
        return user;
    }

    @Override
    public boolean save(User user) throws SQLException {
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(User user) throws SQLException {
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(user);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(User user) throws SQLException {
        Transaction transaction = session.beginTransaction();
        session.delete(user);
        transaction.commit();
        session.close();
        return true;
    }
}
