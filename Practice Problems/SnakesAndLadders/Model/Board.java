package SnakesAndLadders.Model;

import java.util.ArrayList;
import java.util.List;


public class Board {

    private final int size;
    private final List<Jump> snakes;
    private final List<Jump>ladders;
    private final int hurdles;

    public int getSize() {
        return size;
    }

    public Board(int hurdles, int size) {
        this.snakes =  new ArrayList<>();
        this.ladders = new ArrayList<>();
        this.hurdles = hurdles;
        this.size = size;
        initialiseBoard();
    }


    private void initialiseBoard() {
        System.out.println("initialising board with "+ hurdles + " snakes and "+ hurdles + " ladders");
        for(int i = 0;i<hurdles;i++){
            int min = 2;
            int max = 80;
            int start=Integer.MAX_VALUE, end =0;
            while(start>end  || !valid(snakes,ladders,start,end)) {
                start = (int) (Math.random() * (max - min + 1)) + min;
                end = (int) (Math.random() * (max - min + 1)) + min;
            }
            Jump ladder = new Jump(start, end);
            ladders.add(ladder);
        }
        for(int i = 0;i<hurdles;i++){
            int min = 2;
            int max = 80;
            int start=Integer.MIN_VALUE, end =0;

            while(start<end || !valid(snakes,ladders,start,end) ){
                start = (int) (Math.random() * (max - min + 1)) + min;
                end = (int) (Math.random() * (max - min + 1)) + min;
                //snake end can overlap with ladder end

            }
            Jump snake = new Jump(start, end);
            snakes.add(snake);
        }

        for(Jump snake: snakes){
            System.out.println("Snake from "+ snake.getStart() + " to "+ snake.getEnd());
        }
        for(Jump ladder: ladders) {
            System.out.println("ladder from "+ ladder.getStart()+ " to "+ ladder.getEnd());

        }


    }

    public void checkHurdles(Player player1, Board board){
        for(Jump snake: snakes){
            if(snake.getStart() == player1.currPos){
                System.out.println("snake bite"+player1.getPlayerName() +" "+ player1.currPos);
                player1.currPos = snake.getEnd();
            }

        }
        for(Jump ladder: board.ladders){
            if(ladder.getStart()== player1.currPos){
                System.out.println("Ladder up"+player1.getPlayerName() +" "+ player1.currPos);
                player1.currPos = ladder.getEnd();
            }
        }
    }

    private boolean valid(List<Jump> snakes, List<Jump> ladders, int start, int end) {
        if(start == end ) return false;
        //no same start and end()
        for(Jump snake: snakes){
            if(snake.getStart() == start || snake.getStart() == end|| snake.getEnd() == start || snake.getEnd() == end){
                return false;
            }
        }
        for(Jump ladder: ladders){
            if(ladder.getStart() == start || ladder.getEnd() == end || ladder.getEnd()== end || ladder.getEnd() == start) {
                return false;
            }
        }
        return true;
    }

}
