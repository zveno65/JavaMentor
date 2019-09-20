package dao;

import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    private Connection connection;

    public UserDAO(Connection connection) {
        this.connection = connection;
    }

    public List<User> getAllClient() throws SQLException {
        return new TExecutor().execQuery(connection,
                "SELECT * FROM user",
                result -> {
                    List<User> users = new ArrayList<>();
                    while (result.next()) {
                        long id = result.getLong("id");
                        String name = result.getString("name");
                        int age = result.getInt("age");
                        users.add(new User(id, name, age));
                    }
                    return users;
                });
    }

    public User getUserById(long id) throws SQLException {
        return new TExecutor().execQuery(connection,
                "select * from user where id = '" + id + "'",
                resultSet -> {
                    resultSet.next();
                    User user = new User();
                    user.setId(resultSet.getLong("id"));
                    user.setName(resultSet.getString("name"));
                    user.setAge(resultSet.getInt("age"));
                    return user;
                });
    }

    public void updateUser(User user) throws SQLException{
        try (PreparedStatement preparedStatement = connection.prepareStatement("update user set name = ?, age = ? where id = ?")) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setInt(2,user.getAge());
            preparedStatement.setLong(3, user.getId());
            preparedStatement.executeUpdate();
        }
    }

    public void addUser(User user) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("insert into user(name, age) values (?, ?)")) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setInt(2, user.getAge());
            preparedStatement.execute();
        }
    }

    public void deleteUser(long id) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("delete from user where id = ?")) {
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        }
    }

    public void createTable() throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.execute("create table if not exists user (id bigint auto_increment, name varchar(256), age integer, primary key (id))");
        }
    }

    public void dropTable() throws SQLException {
        try (Statement statement = connection.createStatement()){
            statement.execute("drop if table exists user");
        }
    }

    interface TResultHandler<T> {
        T handle(ResultSet resultSet) throws SQLException;
    }

    static class TExecutor {
        public <T> T execQuery(Connection connection,
                               String query,
                               TResultHandler<T> handler) throws SQLException {
            try (Statement stmt = connection.createStatement()){
                stmt.execute(query);
                ResultSet resultSet = stmt.getResultSet();
                T result = handler.handle(resultSet);
                resultSet.close();
                return result;
            }
        }
    }
}
