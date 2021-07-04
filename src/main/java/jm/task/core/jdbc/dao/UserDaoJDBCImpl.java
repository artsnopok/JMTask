package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String SQL = "CREATE TABLE IF NOT EXISTS users " +
                "(id BIGINT auto_increment, " +
                " name VARCHAR(20) not NULL, " +
                " lastName VARCHAR (20) not NULL, " +
                " age TINYINT (3) not NULL, " +
                " PRIMARY KEY (id))";
        try (Statement statement = Objects.requireNonNull(Util.getNewConnection()).createStatement()) {
            statement.executeUpdate(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        String SQL = "DROP TABLE IF EXISTS users";
        try (Statement statement = Objects.requireNonNull(Util.getNewConnection()).createStatement()) {
            statement.executeUpdate(SQL);
            Util.getNewConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String SQL = "INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)";
        try (PreparedStatement pst = Objects.requireNonNull(Util.getNewConnection()).prepareStatement(SQL)) {
            pst.setString(1, name);
            pst.setString(2, lastName);
            pst.setByte(3, age);
            pst.execute();
            System.out.println("User с именем – " + name + " добавлен в базу данных.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        String SQL = "DELETE FROM users WHERE id=?";
        try (PreparedStatement pst = Objects.requireNonNull(Util.getNewConnection()).prepareStatement(SQL)) {
            pst.setLong(1, id);
            pst.execute();
            System.out.println("User c id - " + id + " удален.");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<User> getAllUsers() {
        String SQL = "SELECT * FROM users";
        List<User> list = new ArrayList<>();
        try (Statement statement = Objects.requireNonNull(Util.getNewConnection()).createStatement();
             ResultSet rs = statement.executeQuery(SQL);) {
            while (rs.next()) {
                User user = new User(rs.getString(2), rs.getString(3), rs.getByte(4));
                list.add(user);
                user.setId(rs.getLong(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void cleanUsersTable() {
        String SQL = "DELETE FROM users";
        try (Statement statement = Objects.requireNonNull(Util.getNewConnection()).createStatement()) {
            statement.executeUpdate(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
