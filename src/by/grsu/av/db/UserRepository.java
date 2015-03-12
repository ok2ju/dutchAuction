package by.grsu.av.db;

import by.grsu.av.engine.ConfigFacade;
import by.grsu.av.model.User;
import by.grsu.av.model.UserRole;

import java.util.HashMap;

/**
 * Created by ok2ju on 12.03.2015.
 */
public final class UserRepository {

    private static String adminName = "admin";
    private static HashMap<String, User> users;

    public static HashMap<String, User> getUsers() {
        if(users == null) {
            users = new HashMap<String, User>();
        }
        return users;
    }

    public static User create(String username) {
        User user = new User(username, UserRole.Player, ConfigFacade.getInitialMoney());
        getUsers().put(username, user);

        return user;
    }

    public static User find(String username) {
        User searchResult = getUsers().get(username); // TODO: validate username, exception

        return searchResult;
    }

    public static void delete(String username) {
        getUsers().remove(username);
    }

    public static void delete(User user) {
        delete(user.getUsername());
    }

    //TODO: user update method
}
