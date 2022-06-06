package dev.cooremans.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil {

    private static ConnectionUtil connectionUtil;

    public static ConnectionUtil getInstance() {

        if (connectionUtil == null) {
            connectionUtil = new ConnectionUtil();
        }
        return connectionUtil;
    }

    static {
        try{
          Class.forName("org.postgresql.Driver");
        } catch (Exception e) {
            System.err.println("Failed to load PostgreSQL Driver");
            throw new RuntimeException(e); // fail fast
        }
    }

    private Properties props = new Properties();

    private ConnectionUtil() {
        try{
            props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("application.properties"));
        } catch (Exception e) {
            System.err.println("Failed to load database credentials from property file.");
            throw new RuntimeException(e); // fail fast for easier debugging
        }
    }

    public Connection getConnection() throws SQLException {

        Connection conn = DriverManager.getConnection(props.getProperty("db-url"),
                props.getProperty("db-username"),
                props.getProperty("db-password"));

        if (conn == null) {
            throw new RuntimeException("Could not establish a connection to the database");
        }
        return conn;
    }

}
