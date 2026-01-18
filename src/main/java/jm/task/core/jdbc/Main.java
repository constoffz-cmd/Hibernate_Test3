package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserService userService = new UserServiceImpl();

        userService.createUsersTable();
        userService.saveUser("second", "LastToo", (byte) 15);
        userService.saveUser("third", "LastThree", (byte) 22);
        userService.saveUser("four", "LastFour", (byte) 56);
        userService.saveUser("five", "LastFive", (byte) 8);
        List<User> list = userService.getAllUsers();
        for (User us : list) {
            System.out.println(us.toString());
        }

        userService.cleanUsersTable();
        userService.dropUsersTable();


    }
}
