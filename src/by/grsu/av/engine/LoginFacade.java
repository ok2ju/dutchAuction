package by.grsu.av.engine;

import by.grsu.av.db.UserRepository;
import by.grsu.av.model.User;
import by.grsu.av.model.UserRole;

public class LoginFacade {

    private static LoginFacade instance;

    public static LoginFacade getInstance() {
        if(instance == null) {
            instance = new LoginFacade();
        }
        return instance;
    }

    public User login(String username, UserRole role) {
        try {
            return UserRepository.find(username) == null ? UserRepository.create(username, role) : null;
        } catch (Throwable e) {
            return null;
        }
    }
}
