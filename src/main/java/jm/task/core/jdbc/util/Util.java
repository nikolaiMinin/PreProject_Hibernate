package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    public static Connection getConnection() {

        Connection con = null;

        try {
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/users_database", "minin", "root1234!");

            if (!con.isClosed()) {
                System.out.println("Connection successful");
            }
        } catch (SQLException e) {
            System.out.println("Exception while trying to connect database");
        }

        return con;
    }
}
