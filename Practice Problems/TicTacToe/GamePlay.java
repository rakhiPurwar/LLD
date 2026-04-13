import java.util.Random;

public class GamePlay {
    Board board;
    Player player1;
    Player player2;

    public GamePlay(Board board, Player player1, Player player2) {
        this.board = board;
        this.player1 = player1;
        this.player2 = player2;

    }

    private static GamePlay initialiseGame() {
        Board board = new Board(3, new char[3][3]);
        Player player1 = new Player(Piece.X, "Player 1");
        Player player2 = new Player(Piece.O, "Player 2");
        return new GamePlay(board, player1, player2);

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

    public static void main(String args[]){
        System.out.println("Welcome to Tic Tac Toe!");
        GamePlay gamePlay = GamePlay.initialiseGame();

        GamePlay.startPlaying(gamePlay);


    }
}


