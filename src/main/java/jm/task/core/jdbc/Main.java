package jm.task.core.jdbc;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

public class Main {
    public static void main(String[] args) {

        UserServiceImpl userService = new UserServiceImpl();
        userService.dropUsersTable();
        userService.createUsersTable();

        userService.saveUser("Nick","Perumov",(byte)59);
        userService.saveUser("Boris","Strugatskiy",(byte)79);
        userService.saveUser("Kir", "Bulychev",(byte)78);
        userService.saveUser("Sergey","Lukyanenko",(byte)54);

        userService.getAllUsers();

        userService.cleanUsersTable();
        userService.dropUsersTable();
        Util.closeSessionFactory();
    }
}
