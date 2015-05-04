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
        return !UserRepository.find(username).isPresent() ?
                UserRepository.create(username, role) :
                UserRepository.find(username).orElseGet(null);
    }
}
