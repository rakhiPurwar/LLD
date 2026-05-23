package SnakesAndLadders.Model;

public class Player {

    private final String playerName;
    int currPos;

    public String getPlayerName() {
        return playerName;
    }

    public int getCurrPos() {
        return currPos;
    }

    public void setCurrPos(int currPos) {
        this.currPos = currPos;
    }

    public Player(String playerName, int currPos) {
        this.playerName = playerName;
        this.currPos = currPos;
    }
}
