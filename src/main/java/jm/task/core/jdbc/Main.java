package jm.task.core.jdbc;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        try (Connection con = Util.getConnection()) {
            UserServiceImpl userService = new UserServiceImpl();

            userService.dropUsersTable();
            userService.createUsersTable();

            userService.saveUser("Nick","Perumov",(byte)59);
            userService.saveUser("Boris","Strugatskiy",(byte)79);
            userService.saveUser("Kir", "Bulychev",(byte)78);
            userService.saveUser("Sergey","Lukyanenko",(byte)54);

            List<User> userList = userService.getAllUsers();
            for (User user : userList) {
                System.out.println(user.toString());
            }

            userService.cleanUsersTable();
            userService.dropUsersTable();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
