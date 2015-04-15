package by.grsu.av.engine;

import by.grsu.av.db.UserRepository;
import by.grsu.av.model.User;

import java.util.Collection;
import java.util.List;

public class UserFacade {

    private static UserFacade instance;

    public static UserFacade getInstance() {
        if(instance == null) {
            instance = new UserFacade();
        }
        return instance;
    }

    public List<User> getUsers() {
        return UserRepository.getUsers();
    }

    public int getUserCount() {
        return getUsers().size();
    }
}
