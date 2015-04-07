package by.grsu.av.model.state;

/**
 * Created by ok2ju on 07.04.2015.
 */
public class State {

    // match -> set -> game (tennis rules)

    private int matchId;
    private int setId;
    private int gameId;

    public State(int matchId, int setId, int gameId) {
        this.matchId = matchId;
        this.setId = setId;
        this.gameId = gameId;
    }

    public String toString() {
        return "State{" +
                "matchId=" + matchId +
                ", setId=" + setId +
                ", gameId=" + gameId +
                '}';
    }
}
