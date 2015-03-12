package by.grsu.av.engine;

import by.grsu.av.db.UserRepository;
import by.grsu.av.model.User;

/**
 * Created by ok2ju on 11.03.2015.
 */
public class LoginFacade {

    private static LoginFacade instance;

    public static LoginFacade getInstance() {
        if(instance == null) {
            instance = new LoginFacade();
        }
        return instance;
    }

    public User login(String username) {
        try {
            return UserRepository.find(username) == null ? UserRepository.create(username) : null;
        } catch (Throwable e) {
            return null;
        }
    }
}
