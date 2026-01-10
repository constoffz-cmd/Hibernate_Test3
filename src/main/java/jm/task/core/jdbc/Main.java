package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserDaoHibernateImpl userDaoHibernate = new UserDaoHibernateImpl();
        //userDaoHibernate.createUsersTable();
        //userDaoHibernate.saveUser("Test", "Testov", (byte) 13);
        userDaoHibernate.removeUserById(1);
        List<User> lis = userDaoHibernate.getAllUsers();
        for (User us : lis) {
            System.out.println(us.toString());
        }

        //UserServiceImpl userService = new UserServiceImpl();
        //userService.createUsersTable();
        /*userService.saveUser("2First", "Last", (byte) 14);
        List<User> list = userService.getAllUsers();
        for (User us : list) {
            System.out.println(us.toString());
        }*/
        //System.out.println(Util.establishConnection());
        //UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();

        //userDaoJDBC.createUsersTable();
        //userDaoJDBC.saveUser("second", "LastToo", (byte) 15);
        //List<User> list = userDaoJDBC.getAllUsers();
        //userDaoJDBC.removeUserById(1);
        //userDaoJDBC.dropUsersTable();
        //userDaoJDBC.cleanUsersTable();



    }
}
