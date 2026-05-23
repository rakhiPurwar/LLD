import Service.GamePlay;

public class ClientApplication {
    public static void main(String[] args){
        System.out.println("Welcome to Tic Tac Toe!");

        GamePlay gamePlayService = new GamePlay(3);

        TicToeApplication application = new TicToeApplication(gamePlayService);
        application.startPlaying();

    }
}
