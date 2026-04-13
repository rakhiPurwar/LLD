package SnakesAndLadders;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class GamePlay {

    Board board;
    List<Player> players;

    public GamePlay(int playerCount,int hurdles) {
        List<Jump> snakes = new ArrayList<>();
        List<Jump> ladders = new ArrayList<>();
        this.board = new Board(snakes, ladders, hurdles);
        this.players = new ArrayList<>();
        initializePlayers(players, playerCount);

    }

    static void checkHurdles(Player player1, Board board){
        for(Jump snake: board.snakes){
            if(snake.start == player1.currPos){
                System.out.println("snake bite"+player1.playerName +" "+ player1.currPos);
                player1.currPos = snake.end;
            }

        }
        for(Jump ladder: board.ladders){
            if(ladder.start == player1.currPos){
                System.out.println("Ladder up"+player1.playerName +" "+ player1.currPos);
                player1.currPos = ladder.end;
            }
        }


    }

    void startPlaying(){
        int currentPlayerIndex = 0;
        while (true) {
            Player currentPlayer = players.get(currentPlayerIndex);
            System.out.println(currentPlayer.getPlayerName() + "'s turn. Current position: " + currentPlayer.currPos);
            int move = rollDice();
            System.out.println("Rolled a " + move);
            currentPlayer.currPos += move;
            if(currentPlayer.currPos > 100){
                currentPlayer.currPos = 100 - (currentPlayer.currPos - 100);
            }
            checkHurdles(currentPlayer, board);
            if (currentPlayer.currPos == 100) {
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

    public void initialiseGame(){

    }
}
