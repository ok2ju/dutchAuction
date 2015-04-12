package by.grsu.av.model.state;

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

    public int getMatchId() {
        return matchId;
    }

    public int getSetId() {
        return setId;
    }

    public int getGameId() {
        return gameId;
    }
}
