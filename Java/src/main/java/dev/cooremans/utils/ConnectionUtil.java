package dev.cooremans.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {

    public static Connection getConnection() {
        // jdbc:postgresql://host/dbname?user=username&password=userpassword
        try {
            // Best practice to make into enviorment variable rather than hard code
            String dbInfo = System.getenv("DB_CONNECTION");
            // This is the hard code version
            // String dbInfo = "jdbc:postgresql://localhost/?user=postgres&password=revature"; WORKS
            //String dbInfo = "jdbc:postgresql://localhost:5432/?user=postgres&password=revature&currentSchema=foundation_project";
            Connection connection = DriverManager.getConnection(dbInfo);
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}
