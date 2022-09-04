package Yatzy.Phases;

import Yatzy.Entities.GameBoard;
import Yatzy.Handlers.BoardHandler;
import Yatzy.Handlers.DiceHandler;
import Yatzy.Utils.GFX;

import java.util.ArrayList;
import java.util.Scanner;

public class GameLoop {
    static Scanner sc = Init.sc;

    public static String playGame(ArrayList<GameBoard> allGameBoards) {

        var numberOfPlayers = allGameBoards.size();
        var currentPlayer = 1;
        var roundCounter = 1;

        // Actual game loop
        while (roundCounter <= 7) {
            GFX.showBoard(allGameBoards);

            int playerScore = 0;
            for (int i = 0; i < 7; i++) {
                if (allGameBoards.get(currentPlayer-1).getScoreBoard()[i]==-1){
                    playerScore += 0;
                }else {
                    playerScore += allGameBoards.get(currentPlayer-1).getScoreBoard()[i];
                }
            }

            System.out.println("\nplayer " + currentPlayer + "'s total score is: " + playerScore + "\n");
            var diceRoll = DiceHandler.rollAllDice();

            // Display Dice
            GFX.printDice(diceRoll);

            // re-rolls
            GFX.printLine();
            System.out.println("Input K to keep die, input R to re-roll die.\nExample: 'KKRRK' would keep dice 1-2, re-roll dice 3-4 and keep die 5.");
            String diceToKeep = sc.nextLine().toUpperCase();
            if (!diceToKeep.equals("KKKKK")){
                BoardHandler.reRollDice(diceRoll, diceToKeep);
                GFX.printLine();
                GFX.printDice(diceRoll);

                System.out.println("Input K to keep die, input R to re-roll die.\nExample: 'KKRRK' would keep dice 1-2, re-roll dice 3-4 and keep die 5.");
                diceToKeep = sc.nextLine().toUpperCase();
                BoardHandler.reRollDice(diceRoll, diceToKeep);
                GFX.printLine();
                GFX.printDice(diceRoll);
            }



            // Select board-cell
            BoardHandler.placeScoreInCell(allGameBoards, currentPlayer, diceRoll);

            // Cycle round
            if (currentPlayer < numberOfPlayers) {
                currentPlayer++;
            } else {
                currentPlayer = 1;
                roundCounter++;
            }
        }

        // Calc winner from allGameBoards score compare
        GFX.printLine();
        return BoardHandler.calculateWinner(allGameBoards);
    }

}
