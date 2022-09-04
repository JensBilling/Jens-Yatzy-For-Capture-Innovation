package Yatzy.Phases;

import Yatzy.Entities.GameBoard;
import Yatzy.Utils.GFX;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Init {

    public static Scanner sc = new Scanner(System.in);


    public static int initConfig(){
        int numberOfPlayers = 0;

        while (numberOfPlayers < 2 || numberOfPlayers > 4) {
            try {
                numberOfPlayers = sc.nextInt();
                if (numberOfPlayers < 2 || numberOfPlayers > 4){
                    GFX.printLine();
                    System.out.print("Number of players must be more than two, and must not exceed four: ");
                }
            } catch (InputMismatchException e){
                GFX.printLine();
                System.out.print("Please enter numeber of players as a numeric integer (2-4): ");
            } finally {
                sc.nextLine();
            }


        }
       return numberOfPlayers;
    }

    public static ArrayList<GameBoard> gameSetup(int numberOfPlayers){
        ArrayList<GameBoard> allGameBoards = new ArrayList<>() {
            {
                add(new GameBoard());
                add(new GameBoard());
            }
        };

        if (numberOfPlayers >= 3){
            allGameBoards.add(new GameBoard());
        }
        if (numberOfPlayers == 4){
            allGameBoards.add(new GameBoard());
        }
        return allGameBoards;
    }

}
