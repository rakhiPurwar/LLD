package SnakesAndLadders.Service;

import SnakesAndLadders.Model.Board;
import SnakesAndLadders.Model.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class GamePlay {

    final Board board;
    private final List<Player> players;

    public GamePlay(int playerCount,int hurdles,int size) {
        this.board = new Board(hurdles,size);
        this.players = new ArrayList<>();
        initializePlayers(players, playerCount);

    }

    public void startPlaying(){
        int currentPlayerIndex = 0;
        while (true) {
            Player currentPlayer = players.get(currentPlayerIndex);
            System.out.println(currentPlayer.getPlayerName() + "'s turn. Current position: " + currentPlayer.getCurrPos());
            int move = rollDice();
            System.out.println("Rolled a " + move);
            if((currentPlayer.getCurrPos()+move) > board.getSize()*board.getSize()){
                System.out.println("Move exceeds board size. Try again next turn.");
                currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
                continue;
            }
            currentPlayer.setCurrPos(currentPlayer.getCurrPos()+move);
            board.checkHurdles(currentPlayer, board);
            if (currentPlayer.getCurrPos() == 100) {
                System.out.println(currentPlayer.getPlayerName() + " wins!");
                break;
            }
            currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        }
    }


    private void initializePlayers(List<Player> players, int count) {
        Scanner sc = new Scanner(System.in);
        for(int i = 0;i<count;i++){
            System.out.println("Enter player name");
            String name = sc.nextLine();
            Player player = new Player(name, 0);
            players.add(player);
        }
    }

    private  int rollDice() {

        Random rand = new Random();
        int num = rand.nextInt(6) + 1;
        return num;

    }

}
