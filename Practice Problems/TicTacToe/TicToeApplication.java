import Service.GamePlay;

public class TicToeApplication {
    private  final GamePlay gamePlayService;

    public TicToeApplication(GamePlay gamePlayService) {
        this.gamePlayService = gamePlayService;
    }

    public void startPlaying(){
        gamePlayService.startPlaying();
    }
}
