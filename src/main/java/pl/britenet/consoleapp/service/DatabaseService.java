package pl.britenet.consoleapp.service;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

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

    public<T> Optional<T> performSQL(String sql, ResultParser<T> resultParser) {
        try (Connection connection = this.hikariDataSource.getConnection() ;
             PreparedStatement statement = connection.prepareStatement(sql)) {

            ResultSet resultSet = statement.executeQuery();
            return Optional.of(resultParser.parse(resultSet));
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
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
