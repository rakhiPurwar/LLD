package Service;

import Model.Board;
import Model.Piece;
import Model.Player;

import java.util.Random;

public class GamePlay {
    int size;
    Board board;
    Player player1;
    Player player2;

    public GamePlay(int size ) {
        this.size = size;
        initialiseGame(size);
    }

    public void initialiseGame(int size) {
        this.board = new Board(size, new char[3][3]);
        this.player1 = new Player(Piece.X, "Model.Player 1");
        this.player2  = new Player(Piece.O, "Model.Player 2");

    }
    boolean checkWinner(Player player){
        char piece = player.getPiece().getValue();
        // Check rows
        for(int i = 0;i<board.getSize();i++){
            if(board.getBoard()[i][0] == piece && board.getBoard()[i][1] == piece && board.getBoard()[i][2] == piece){
                return true;
            }
        }
        // Check columns
        for(int j = 0;j<board.getSize();j++){
            if(board.getBoard()[0][j] == piece && board.getBoard()[1][j] == piece && board.getBoard()[2][j] == piece){

                return true;
            }
        }
        // Check diagonals
        if(board.getBoard()[0][0] == piece && board.getBoard()[1][1] == piece && board.getBoard()[2][2] == piece){
            return true;
        }
        if(board.getBoard()[0][2] == piece && board.getBoard()[1][1] == piece && board.getBoard()[2][0] == piece){

            return true;
        }

        return false;
    }


    public void startPlaying() {
        Random random = new Random();
        Player currentPlayer = random.nextBoolean() ? player1 : player2;

        while (true) {

            board.printBoard();

            int move, row, col;

            while (true) {
                move = random.nextInt(9);
                row = move / 3;
                col = move % 3;

                if (board.placePiece(currentPlayer, row, col)) {
                    break;
                }
            }

            if (checkWinner(currentPlayer)) {
                board.printBoard();
                System.out.println(currentPlayer.getName() + " won!");
                break;
            }

            if (board.isBoardFull()) {
                board.printBoard();
                System.out.println("Draw!");
                break;
            }

            currentPlayer = (currentPlayer == player1)
                    ? player2
                    : player1;
        }
    }
}


