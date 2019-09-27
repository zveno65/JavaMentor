package ru.plotnikov.factory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class FactoryManager {
    public static UserDaoFactory getFactory() {
        try (InputStream input = FactoryManager.class.getClassLoader().getResourceAsStream("config.properties")) {
            Properties properties = new Properties();

            if (input==null) {
                throw new IOException("Sorry, unable to find config.properties");
            }

            properties.load(input);

            if (properties.getProperty("factory").equals("Hibernate"))
                return new UserHibernateDaoFactory();
            if (properties.getProperty("factory").equals("Jdbc"))
                return new UserJdbcDaoFactory();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
