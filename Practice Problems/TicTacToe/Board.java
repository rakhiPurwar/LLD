public class Board {
    int size;
    char [][] board ;

    public Board(int size, char[][] board) {
        this.size = size;
        this.board = board;
        initialiseBoard();

    }

    public void initialiseBoard(){
        for(int i = 0;i<size;i++){
            for(int j = 0;j<size;j++){
                board[i][j] = '.';
            }
        }
    }

    public void printBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }


    public boolean isBoardFull() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] == '.') {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean placePiece(Player currentPlayer, int row, int col) {
        if(board[row][col] == '.'){
            board[row][col] = currentPlayer.piece.getValue();
            return true;
        }
        return false;


    }
}
