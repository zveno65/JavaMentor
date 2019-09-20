package dao;

import java.sql.SQLException;
import java.util.List;

public interface DAO<T> {
    T find(Long id) throws SQLException;
    List<T> findAll() throws SQLException;
    boolean save(T o) throws SQLException;
    boolean update(T o) throws SQLException;
    boolean delete(T o) throws SQLException;
}
