package by.grsu.av.engine;

import by.grsu.av.db.UserRepository;
import by.grsu.av.model.User;

import java.util.Collection;

/**
 * Created by ok2ju on 11.03.2015.
 */
public class UserFacade {

    private static UserFacade instance;

    public static UserFacade getInstance() {
        if(instance == null) {
            instance = new UserFacade();
        }
        return instance;
    }

    public Collection<User> getUsers() {
        return UserRepository.getUsers().values();
    }

    public int getUserCount() {
        return getUsers().size();
    }
}
