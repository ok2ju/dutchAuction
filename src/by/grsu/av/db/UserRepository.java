package by.grsu.av.db;

import by.grsu.av.engine.ConfigFacade;
import by.grsu.av.model.User;
import by.grsu.av.model.UserRole;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public final class UserRepository {

    private static String adminName = "admin";
    private static List<User> users;

    public static List<User> getUsers() {
        if(users == null) {
            users = new ArrayList<User>();
        }
        return users;
    }

    public static User create(String username, UserRole role) {

        if(role.equals(UserRole.Admin)) {
            User admin = new User(username, role);
            return admin;
        }

        User user = new User(username, role, ConfigFacade.getInitialMoney());
        getUsers().add(user);

        return user;
    }

    public static void delete(String username) {
        getUsers().remove(username);
    }

    public static void delete(User user) {
        delete(user.getUsername());
    }

    public static User find(String username) {
        return null;
    }

    //TODO: user update method222
}
