public class Client {
    public static void main(String[] args){
        System.out.println("Welcome to Tic Tac Toe!");

        GamePlay gamePlay = new GamePlay(3);

        GamePlay.startPlaying(gamePlay);

    }
}
