package pl.britenet.consoleapp.service;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseService {

    private HikariDataSource hikariDataSource;

    private void configureDataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306/shop");
        config.setUsername("root");
        config.setPassword("");
        this.hikariDataSource = new HikariDataSource(config);
    }

    public DatabaseService() {
        this.configureDataSource();
    }

    public void performDML(String dml) {
        try (Connection connection = this.hikariDataSource.getConnection() ;
             PreparedStatement statement = connection.prepareStatement(dml)) {

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
