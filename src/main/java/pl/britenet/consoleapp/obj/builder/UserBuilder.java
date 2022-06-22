package pl.britenet.consoleapp.obj.builder;

import pl.britenet.consoleapp.Constants;
import pl.britenet.consoleapp.obj.model.User;

public class UserBuilder {

    private final User user;

    public UserBuilder(int id) {
        this.user = new User(id);
    }

    public UserBuilder() {
        this.user = new User(Constants.INVALID_ID);
    }

    public UserBuilder setUsername(String username) {
        this.user.setUsername(username);
        return this;
    }

    public UserBuilder setPassword(String password) {
        this.user.setPassword(password);
        return this;
    }

    public User getUser() {
        return user;
    }
}
