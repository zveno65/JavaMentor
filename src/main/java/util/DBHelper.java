package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBHelper {

    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            connection = getMysqlConnection();
        }
        return connection;
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
}
