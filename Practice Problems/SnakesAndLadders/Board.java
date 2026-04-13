package SnakesAndLadders;

import java.util.List;


public class Board {

    List<Jump> snakes;
    List<Jump>ladders;
    int hurdles;


    public Board( List<Jump> snakes, List<Jump> ladders,int hurdles) {
        this.snakes = snakes;
        this.ladders = ladders;
        this.hurdles = hurdles;
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
            System.out.println("Snake from "+ snake.start + " to "+ snake.end);
        }
        for(Jump ladder: ladders) {
            System.out.println("ladder from "+ ladder.start + " to "+ ladder.end);

        }


    }

    private boolean valid(List<Jump> snakes, List<Jump> ladders, int start, int end) {
        if(start == end) return false;
        //no same start and end
        for(Jump snake: snakes){
            if(snake.start == start || snake.end == end || snake.start ==end || snake.end == start){
                return false;
            }
        }
        for(Jump ladder: ladders){
            if(ladder.start == start || ladder.end == end || ladder.start == end || ladder.end == start) {
                return false;
            }
        }
        return true;
    }



}
