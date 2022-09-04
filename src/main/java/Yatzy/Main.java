package Yatzy;

import Yatzy.Phases.GameLoop;
import Yatzy.Phases.Init;
import Yatzy.Utils.GFX;

public class Main {
    public static void main(String[] args) {
        // Intro/Game config
        GFX.printLine();
        System.out.print("Welcome to Jens's Yatzy for Capture Innovation \nInput number of players (2-4): ");
        var allGameBoards = Init.gameSetup(Init.initConfig());

        // Game loop
        var finalResult = GameLoop.playGame(allGameBoards);

        System.out.println(finalResult);
    }
}
