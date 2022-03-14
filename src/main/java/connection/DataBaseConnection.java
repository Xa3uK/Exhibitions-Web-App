package connection;

import util.AppConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DataBaseConnection {
    private static DataBaseConnection instance;
    private static Connection connection;

    public Connection getConnection() {
        if (connection == null) {
            AppConfig.init();
            String url = AppConfig.getProperty("db.url");
            String login = AppConfig.getProperty("db.login");
            String password = AppConfig.getProperty("db.password");
            String driver = AppConfig.getProperty("db.driver");
            try {
                Class.forName(driver);
                connection = DriverManager.getConnection(url, login, password);
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static DataBaseConnection getInstance() {
        if (instance == null) {
            instance = new DataBaseConnection();
        }
        return instance;
    }
}
