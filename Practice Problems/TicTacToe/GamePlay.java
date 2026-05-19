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
        this.player1 = new Player(Piece.X, "Player 1");
        this.player2  = new Player(Piece.O, "Player 2");

    }
    boolean checkWinner(Player player){
        char piece = player.piece.getValue();
        // Check rows
        for(int i = 0;i<board.size;i++){
            if(board.board[i][0] == piece && board.board[i][1] == piece && board.board[i][2] == piece){
                return true;
            }
        }
        // Check columns
        for(int j = 0;j<board.size;j++){
            if(board.board[0][j] == piece && board.board[1][j] == piece && board.board[2][j] == piece){

                return true;
            }
        }
        // Check diagonals
        if(board.board[0][0] == piece && board.board[1][1] == piece && board.board[2][2] == piece){
            return true;
        }
        if(board.board[0][2] == piece && board.board[1][1] == piece && board.board[2][0] == piece){

            return true;
        }

        return false;
    }


    static void startPlaying(GamePlay gamePlay) {
        Random random = new Random();
        Player currentPlayer = random.nextBoolean() ? gamePlay.player1 : gamePlay.player2;

        while (true) {

            gamePlay.board.printBoard();

            int move, row, col;

            while (true) {
                move = random.nextInt(9);
                row = move / 3;
                col = move % 3;

                if (gamePlay.board.placePiece(currentPlayer, row, col)) {
                    break;
                }
            }

            if (gamePlay.checkWinner(currentPlayer)) {
                gamePlay.board.printBoard();
                System.out.println(currentPlayer.name + " won!");
                break;
            }

            if (gamePlay.board.isBoardFull()) {
                gamePlay.board.printBoard();
                System.out.println("Draw!");
                break;
            }

            currentPlayer = (currentPlayer == gamePlay.player1)
                    ? gamePlay.player2
                    : gamePlay.player1;
        }
    }
}


