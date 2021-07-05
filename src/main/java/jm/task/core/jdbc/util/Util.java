package jm.task.core.jdbc.util;


import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class Util {

    private static final String URL = "jdbc:mysql://localhost:3306/mydb?useSSL=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "e19720q11q";

    public static Connection getNewConnection() {
        Connection connection;
        try {
            return connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Соединение с БД не установлено");
        }
        return null;
    }

    public static SessionFactory getSessionFactory() {
        Properties properties = new Properties();
        properties.setProperty(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
//        properties.setProperty(Environment.HBM2DDL_AUTO,"update");
        properties.setProperty(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
        properties.setProperty(Environment.USER, "root");
        properties.setProperty(Environment.PASS, "e19720q11q");
        properties.setProperty(Environment.URL, "jdbc:mysql://localhost:3306/mydb?useSSL=false&amp");

        SessionFactory sessionFactory = new Configuration()
                .setProperties(properties)
                .addAnnotatedClass(User.class)
                .buildSessionFactory();

        return sessionFactory;
    }
}

