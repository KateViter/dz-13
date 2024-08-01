package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBManager {
    private static final Properties prop = PropertyFileReader.getProperties(
            "src/test/resources/db.properties");
    private static final String URL = prop.get("db.url").toString();
    private static final String USER = prop.get("db.user").toString();
    private static final String PASS = prop.get("db.pass").toString();

    private static Connection connection;

    private DBManager() {
        //private constructor to prevent instantiation
    }

    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(URL, USER, PASS);
            } catch (SQLException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        return connection;
    }

    public static void closeConnection() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

}
