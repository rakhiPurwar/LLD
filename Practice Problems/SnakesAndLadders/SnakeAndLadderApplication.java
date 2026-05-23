package SnakesAndLadders;

import SnakesAndLadders.Service.GamePlay;

public class SnakeAndLadderApplication {
    GamePlay gamePlay;

    public SnakeAndLadderApplication(GamePlay gamePlay) {
        this.gamePlay = gamePlay;
    }
    public void startPlaying(){
        gamePlay.startPlaying();
    }



}
