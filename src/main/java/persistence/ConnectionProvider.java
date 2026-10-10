package persistence;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionProvider {

    private static ConnectionProvider instance = null;

    private String     URL        = null;
    private Connection connection = null;

    private Properties loadProperties() {
        Properties props = new Properties();
        try {
            InputStream input = getClass().getClassLoader()
                    .getResourceAsStream("db.properties");
            props.load(input);
            return props;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private ConnectionProvider() {
        Properties properties = loadProperties();
        this.URL = "jdbc:mariadb://" +
                properties.getProperty("db.host") + ":" + properties.getProperty("db.port")
                + "/" + properties.getProperty("db.catalog");
        try {
            this.connection = DriverManager.getConnection(
                    this.URL,
                    properties.getProperty("db.user"),
                    properties.getProperty("db.password")
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    static ConnectionProvider getInstance() {
        if (ConnectionProvider.instance == null)
            ConnectionProvider.instance = new ConnectionProvider();
        return ConnectionProvider.instance;
    }

    public Connection getConnection() { return this.connection; }
}
