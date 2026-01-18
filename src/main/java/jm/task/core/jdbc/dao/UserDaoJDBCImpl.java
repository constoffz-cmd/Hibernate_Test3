package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import java.util.logging.Logger;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private Connection conn;
    private Logger logger = Logger.getLogger(UserDaoJDBCImpl.class.getName());

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Connection connect = Util.getConnection();) {
            PreparedStatement create = connect.prepareStatement("CREATE TABLE IF NOT EXISTS Users(" +
                    "id BIGINT AUTO_INCREMENT PRIMARY KEY," +
                    "name VARCHAR(255) NOT NULL," +
                    "lastName VARCHAR(255) NOT NULL," +
                    "age TINYINT NOT NULL)");

            create.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        logger.info("table created");
        //System.out.println("table created");
    }

    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS users";
        try (Connection connection = Util.getConnection())
        {
            PreparedStatement remove = connection.prepareStatement(sql);
            remove.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        logger.info("table removed");
        //System.out.println("table removed");

    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO users (name, lastname, age) VALUES (?, ?, ?)";
        // Использование try-with-resources для автоматического закрытия соединений
        try (Connection connect = Util.getConnection();
             PreparedStatement pstmt = connect.prepareStatement(sql)) {
            // Установка значений вместо знаков "?"
            pstmt.setString(1, name);
            pstmt.setString(2, lastName);
            pstmt.setInt(3, age);
            // Выполнение запроса
            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                logger.info("Пользователь с именем - " + name + " добавлен в базу данных");
                //System.out.println("Пользователь успешно добавлен!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void removeUserById(long id) {
        String sql = "DELETE from users WHERE id=?";
        // Использование try-with-resources для автоматического закрытия соединений
        try (Connection connect = Util.getConnection();
             PreparedStatement pstmt = connect.prepareStatement(sql)) {
            // Установка значений вместо знаков "?"
            pstmt.setLong(1, id);
            // Выполнение запроса
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        logger.info("Remove id" + id);
        //System.out.println("Remove id" + id);

    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        //PreparedStatement getUs = conn.prepareStatement()
        String SqlSelect = "SELECT name, lastname, age FROM users;";
        try (Connection connect = Util.getConnection();
             //Statement stmt = connect.createStatement();
             PreparedStatement preparedStatement = connect.prepareStatement(SqlSelect);
             ResultSet rs = preparedStatement.executeQuery(SqlSelect)) {
            // Проходим по каждой строке результата
            while (rs.next()) {
                // Извлекаем данные из колонок по их именам в БД
                //Long id = rs.getObject("id", Long.class);
                String name = rs.getString("name");
                String lastName = rs.getString("lastname");
                byte age = rs.getByte("age");
                // Создаем объект и добавляем в список
                userList.add(new User( name, lastName, age));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public void cleanUsersTable() {
        String sql = "TRUNCATE TABLE users;";
        // Использование try-with-resources для автоматического закрытия соединений
        try (Connection connect = Util.getConnection();
             PreparedStatement pstmt = connect.prepareStatement(sql)) {
            // Установка значений вместо знаков "?"
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        logger.info("Table clear");
        //System.out.println("Table clear" );

    }
}
