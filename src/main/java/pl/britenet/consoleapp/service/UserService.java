package pl.britenet.consoleapp.service;

import pl.britenet.consoleapp.obj.builder.UserBuilder;
import pl.britenet.consoleapp.obj.model.User;

import java.sql.SQLException;
import java.util.Optional;

public class UserService {

    private final DatabaseService databaseService;

    public UserService(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    public Optional<User> getUserByCredentials(String username, String password) {
        return this.databaseService.performSQL(
                String.format("SELECT * FROM user WHERE username='%s' AND password='%s'", username, password),
                resultSet -> {
                    try {
                        if (resultSet.next()) {

                            int id = resultSet.getInt("id");

                            return new UserBuilder(id)
                                    .setUsername(username)
                                    .setPassword(password)
                                    .getUser();
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    return null;
                }
        );
    }

}
