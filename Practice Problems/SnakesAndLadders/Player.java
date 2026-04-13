package SnakesAndLadders;

public class Player {
    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
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

    String playerName;
    int  currPos;
}
