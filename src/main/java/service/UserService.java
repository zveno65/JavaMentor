package service;

import dao.UserDAO;
import model.User;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.mysql.jdbc.*;

public class UserService {

    private static UserService userService = new UserService();

    public static UserService getInstance() {
        return userService;
    }

    private UserService() {
        try {
            getUserDAO().createTable();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try {
            users = getUserDAO().getAllUser();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void addUser(User user) {
        try {
            getUserDAO().addUser(user);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUser(User user) {
        try {
            getUserDAO().updateUser(user);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(Long id) {
        try {
            getUserDAO().deleteUser(id);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getUserById(long id) {
        try {
            return getUserDAO().getUserById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void cleanUp() {
        try {
            getUserDAO().dropTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void createTable() {
        try {
            getUserDAO().createTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static Connection getMysqlConnection() {
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());

            StringBuilder url = new StringBuilder();

            url.
                    append("jdbc:mysql://").        //db type
                    append("localhost:").           //host name
                    append("3306/").                //port
                    append("new_schema?").          //db name
                    append("user=denis&").          //login
                    append("password=jesus567&").       //password
                    append("useTimezone=true&").
                    append("serverTimezone=UTC&").
                    append("useSSL=false");

            System.out.println("URL: " + url + "\n");

            Connection connection = DriverManager.getConnection(url.toString());
            return connection;
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }
    }

    private static UserDAO getUserDAO() {
        return new UserDAO(getMysqlConnection());
    }

}
