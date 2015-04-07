package by.grsu.av.engine;

import by.grsu.av.model.User;
import by.grsu.av.model.UserRole;
import by.grsu.av.model.state.AdminState;
import by.grsu.av.model.state.PlayerState;
import by.grsu.av.model.state.State;

/**
 * Created by ok2ju on 07.04.2015.
 */
public class GameFacade {

    private final int GOOD_NUMBER = 2;
    private boolean isStarted;
    private int matchId;
    private int setId;
    private int gameId;

    private static GameFacade instance;

    public static GameFacade getInstance() {
        if (instance == null)
            instance = new GameFacade();
            return instance;
    }

    private int getMaxSets() {
        return GOOD_NUMBER * Math.round(UserFacade.getInstance().getUserCount() / 2);
    }

    public void startMath() {
        isStarted = true;

        matchId++;
        setId = 0;
        gameId = 0;
    }

    public void stopMath() {
        isStarted = false;
    }

    public void nextSet() {
        if(setId < getMaxSets()) {
            setId++;
        } else {
            stopMath();
        }
    }

    public State getState(User user) {
        State result = null;

        if(isStarted) {
            if(user.getRole().equals(UserRole.Admin)){
                result = new AdminState(matchId, setId, gameId);
            } else if(user.getRole().equals(UserRole.Player)) {
                result = new PlayerState(matchId, setId, gameId);
            }
            // else {throw new NotImplementedException();}
        }

        return result;
    }

    public static void main(String[] args) {
        User player1 = LoginFacade.getInstance().login("player1", UserRole.Player);
        User player2 = LoginFacade.getInstance().login("player2", UserRole.Player);

        getInstance().startMath();

        for (int i = 0; i < 100; i++) {
            getInstance().nextSet();
            System.out.println("p1: " + getInstance().getState(player1));
            System.out.println("p2: " + getInstance().getState(player2));
        }
    }
}
