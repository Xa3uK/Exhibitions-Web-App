package connection;

import util.AppConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ConnectionPool {
    private static ConnectionPool instance;
    private List<Connection> connectionPool = createPool();
    private List<Connection> usedConnections = new ArrayList<>();
    private static int INITIAL_POOL_SIZE = 10;

    private ConnectionPool() {

    }

    public Connection getConnection() {
        Connection connection = connectionPool
                .remove(connectionPool.size() - 1);
        usedConnections.add(connection);
        return connection;
    }

    private List<Connection> createPool() {
        AppConfig.init();
        String url = AppConfig.getProperty("db.url");
        String login = AppConfig.getProperty("db.login");
        String password = AppConfig.getProperty("db.password");
        String driver = AppConfig.getProperty("db.driver");
        List<Connection> pool = new ArrayList<>(INITIAL_POOL_SIZE);

        for (int i = 0; i < INITIAL_POOL_SIZE; i++) {
            try {
                pool.add(createConnection(url, login, password, driver));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return pool;
    }

    public boolean releaseConnection(Connection connection) {
        connectionPool.add(connection);
        return usedConnections.remove(connection);
    }

    private static Connection createConnection(String url, String login, String password, String driver) throws SQLException {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection(url, login, password);
    }

    public static ConnectionPool getInstance() {
        if (instance == null) {
            instance = new ConnectionPool();
        }
        return instance;
    }
}