package SnakesAndLadders;



import SnakesAndLadders.Service.GamePlay;

import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of players");
        int playerCount = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter board size");
        int size = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter number of hurdles u want");
        int hurdles = sc.nextInt();
        GamePlay gamePlay = new GamePlay(playerCount,hurdles,size);
        SnakeAndLadderApplication application = new SnakeAndLadderApplication(gamePlay);
        application.startPlaying();

    }
}
