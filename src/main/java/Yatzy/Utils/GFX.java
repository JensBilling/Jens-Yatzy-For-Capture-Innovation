package Yatzy.Utils;

import Yatzy.Entities.GameBoard;

import java.util.ArrayList;
import java.util.HashMap;

public class GFX {

    public static void printDice(int[] diceArray) {
        System.out.println("Your roll is:");
        HashMap<Integer, String[]> diceMap = generateDiceMap();

        System.out.println(" ___   ___   ___   ___   ___ ");
        System.out.println(diceMap.get(diceArray[0])[0] + diceMap.get(diceArray[1])[0] + diceMap.get(diceArray[2])[0] + diceMap.get(diceArray[3])[0] + diceMap.get(diceArray[4])[0]);
        System.out.println(diceMap.get(diceArray[0])[1] + diceMap.get(diceArray[1])[1] + diceMap.get(diceArray[2])[1] + diceMap.get(diceArray[3])[1] + diceMap.get(diceArray[4])[1]);
        System.out.println(diceMap.get(diceArray[0])[2] + diceMap.get(diceArray[1])[2] + diceMap.get(diceArray[2])[2] + diceMap.get(diceArray[3])[2] + diceMap.get(diceArray[4])[2]);
        System.out.println(" ---   ---   ---   ---   --- ");
        System.out.println("\n");
    }

    private static HashMap<Integer, String[]> generateDiceMap() {
        HashMap<Integer, String[]> diceMap = new HashMap<>();
        String[] dieOne = {"|   | ", "| O | ", "|   | "};
        String[] dieTwo = {"|O  | ", "|   | ", "|  O| "};
        String[] dieThree = {"|O  | ", "| O | ", "|  O| "};
        String[] dieFour = {"|O O| ", "|   | ", "|O O| "};
        String[] dieFive = {"|O O| ", "| O | ", "|O O| "};
        String[] dieSix = {"|O O| ", "|O O| ", "|O O| "};
        diceMap.put(1, dieOne);
        diceMap.put(2, dieTwo);
        diceMap.put(3, dieThree);
        diceMap.put(4, dieFour);
        diceMap.put(5, dieFive);
        diceMap.put(6, dieSix);
        return diceMap;
    }

    public static void showBoard(ArrayList<GameBoard> allGameBoards) {
        var player1 = allGameBoards.get(0);
        var player2 = allGameBoards.get(1);
        GameBoard player3 = new GameBoard();
        GameBoard player4 = new GameBoard();
        if (allGameBoards.size() >= 3) {
            player3 = allGameBoards.get(2);
        }
        if (allGameBoards.size() == 4) {
            player4 = allGameBoards.get(3);
        }
        printLine();

        System.out.println("Current standings:");
        System.out.println("  |P1|P2|P3|P4|");
        for (int i = 0; i < 7; i++) {
            int p1 = player1.getScoreBoard()[i];
            int p2 = player2.getScoreBoard()[i];
            int p3 = player3.getScoreBoard()[i];
            int p4 = player4.getScoreBoard()[i];

            String p1Print = "  ";
            String p2Print = "  ";
            String p3Print = "  ";
            String p4Print = "  ";

            if (p1 > -1) {
                p1Print = String.valueOf(p1);
            }
            if (p2 > -1) {
                p2Print = String.valueOf(p2);
            }
            if (p3 > -1) {
                p3Print = String.valueOf(p3);
            }
            if (p4 > -1) {
                p4Print = String.valueOf(p4);
            }

            if (p1Print.length() == 1) {
                p1Print += " ";
            }
            if (p2Print.length() == 1) {
                p2Print += " ";
            }
            if (p3Print.length() == 1) {
                p3Print += " ";
            }
            if (p4Print.length() == 1) {
                p4Print += " ";
            }

            if (i < 6) {
                System.out.println(i + 1 + "s|" + p1Print + "|" + p2Print + "|" + p3Print + "|" + p4Print + "|");
            } else {
                System.out.println("FH|" + p1Print + "|" + p2Print + "|" + p3Print + "|" + p4Print + "|");
            }

        }
    }

    public static void printLine() {
        System.out.println("____________________________________________________________");
    }

}
