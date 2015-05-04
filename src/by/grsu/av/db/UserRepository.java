package by.grsu.av.db;

import by.grsu.av.engine.ConfigFacade;
import by.grsu.av.model.User;
import by.grsu.av.model.UserRole;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public final class UserRepository {

    private static String adminName = "admin";
    private static List<User> users;

    public static List<User> getUsers() {
        if(users == null) {
            users = new ArrayList<>();
        }
        return users;
    }

    public static User create(String username, UserRole role) {
        if(role.equals(UserRole.Admin)) {
            return new User(username, role);
        }

        User user = new User(username, role, ConfigFacade.getInitialMoney());
        getUsers().add(user);

        return user;
    }

    public static void delete(String username) {
        users = getUsers()
                .stream()
                .filter(user -> !user.getUsername().equals(username))
                .collect(Collectors.toList());
    }

    public static void delete(User user) {
        delete(user.getUsername());
    }

    public static Optional<User> find(String username) {
        return getUsers().stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst();
    }
}
