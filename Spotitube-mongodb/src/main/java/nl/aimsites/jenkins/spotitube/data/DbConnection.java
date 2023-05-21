package nl.aimsites.jenkins.spotitube.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbConnection {

    private String url;
    private String user;
    private String password;

    public DbConnection() {
        try {
            Properties properties = new Properties();
            properties.load(getClass().getClassLoader().getResourceAsStream("database.properties"));
            url = properties.getProperty("DB_URL");
            user = properties.getProperty("DB_USER");
            password = properties.getProperty("DB_PASSWORD");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

}
