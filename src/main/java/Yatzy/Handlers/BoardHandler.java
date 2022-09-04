package Yatzy.Handlers;

import Yatzy.Entities.GameBoard;
import Yatzy.Phases.Init;
import Yatzy.Utils.GFX;
import java.util.ArrayList;
import java.util.Scanner;

public class BoardHandler {

    static Scanner sc = Init.sc;

    public static void reRollDice(int[] diceRoll, String diceToKeep) {
        // Input and check re-roll dice

        boolean incorrectInput = true;
        while (incorrectInput) {
            incorrectInput = false;

            if (diceToKeep.length() != 5) {
                GFX.printLine();
                System.out.println("You have to input a choice for every die.");
                diceToKeep = sc.nextLine().toUpperCase();
                incorrectInput = true;
            } else {
                for (int i = 0; i < diceToKeep.length(); i++) {
                    if (!(diceToKeep.charAt(i) == 'K' || diceToKeep.charAt(i) == 'R')) {
                        System.out.println("Only input K to keep die or input R to re-roll die.\nExample: 'KKRRK' would keep dice 1-2, re-roll dice 3-4 and keep die 5.");
                        diceToKeep = sc.nextLine().toUpperCase();
                        incorrectInput = true;
                        break;
                    }
                }
            }
        }

        // Re-roll logic
        for (int i = 0; i < diceRoll.length; i++) {
            if (diceToKeep.charAt(i) == 'R') {
                diceRoll[i] = DiceHandler.rollOneDie();
            }
        }

    }

    public static void placeScoreInCell(ArrayList<GameBoard> allGameBoards, int currentPlayer, int[] diceRoll) {
        var applicableCells = DiceHandler.checkApplicableCells(diceRoll);

        String cellSelection;
        boolean notValidSelection = true;

        while (notValidSelection) {
            String cellPossibilities = printApplicableCells(allGameBoards, currentPlayer, applicableCells);

            // Scratch cell if nothing applicable
            boolean haveToScratchCell = cellPossibilities.equals("");

            if (haveToScratchCell) {
                while (haveToScratchCell) {
                    System.out.print("There are no vacant cells for your dice!\n" +
                            "You have to select one cell to scratch from your board: ");

                    StringBuilder replacePossibilities = new StringBuilder();
                    for (int i = 0; i < 7; i++) {
                        if (allGameBoards.get(currentPlayer - 1).getScoreBoard()[i] < 0) {
                            replacePossibilities.append("[").append(i + 1).append("] ");
                        }
                    }
                    replacePossibilities = new StringBuilder(replacePossibilities.toString().replace("[7]", "[F]ull House"));
                    System.out.println(replacePossibilities);

                    cellSelection = sc.nextLine();
                    // check if cellSelection is valid to scratch
                    if (cellSelection.length() == 1 && replacePossibilities.toString().contains(cellSelection.toUpperCase())) {
                        allGameBoards.get(currentPlayer - 1).setScore(cellSelection, 0);
                        notValidSelection = false;
                        haveToScratchCell = false;
                    }
                }
            } else {
                // logic for inputting value for placeValidScore
                cellSelection = sc.nextLine();
                while (!(cellSelection.equals("1") || cellSelection.equals("2") || cellSelection.equals("3")
                        || cellSelection.equals("4") || cellSelection.equals("5") || cellSelection.equals("6")
                        || cellSelection.equalsIgnoreCase("f"))) {
                    printApplicableCells(allGameBoards, currentPlayer, applicableCells);
                    cellSelection = sc.nextLine();
                }

                var isCellOccupied = checkIfCellIsOccupied(allGameBoards, currentPlayer, cellSelection);
                if (isCellOccupied) {
                    GFX.printLine();
                    System.out.println("You have already set a score in that cell!");
                    var applCells = printApplicableCells(allGameBoards, currentPlayer, applicableCells);
                    boolean reCellCheck = !applCells.contains("[" + cellSelection + "]");

                    while (reCellCheck) {
                        cellSelection = sc.nextLine();
                        if (applCells.contains("[" + cellSelection + "]")) {
                            reCellCheck = false;
                        }
                    }
                }
                System.out.println();

                // loop through score of selection
                int score = 0;
                while (score == 0) {
                    for (int die : diceRoll) {
                        try {
                            if (die == Integer.parseInt(cellSelection)) {
                                score += Integer.parseInt(cellSelection);
                            }
                        } catch (NumberFormatException e) {
                            score += die;
                        }
                    }

                    if (score == 0 || !cellPossibilities.contains(cellSelection.toUpperCase())) {
                        printApplicableCells(allGameBoards, currentPlayer, applicableCells);
                        score = 0;
                        cellSelection = sc.nextLine();
                    }
                }


                allGameBoards.get(currentPlayer - 1).setScore(cellSelection, score);
                notValidSelection = false;

            }

        }

    }

    public static String calculateWinner(ArrayList<GameBoard> allGameBoards){

        int[] scores = {0, 0, 0, 0};
        StringBuilder displayWinnerString = new StringBuilder();
        for (int i = 0; i < allGameBoards.size(); i++) {
            for (int j = 0; j < allGameBoards.get(i).getScoreBoard().length; j++) {
                scores[i] += allGameBoards.get(i).getScoreBoard()[j];
            }
            displayWinnerString.append("Player ").append(i + 1).append(": ").append(scores[i]).append("points\n");
        }

        int maxScore = 0;
        int winnerIndex = 0;
        for (int i = 0; i < allGameBoards.size(); i++) {
            if (scores[i]>maxScore){
                maxScore = scores[i];
                winnerIndex = i;
            }
        }

        displayWinnerString.append("\nWinner is player ").append(winnerIndex + 1);

        return displayWinnerString.toString();
    }

    private static String printApplicableCells(ArrayList<GameBoard> allGameBoards, int currentPlayer, int[] applicableCells) {
        System.out.println("Input in which cell you would like to score this round.");
        System.out.print("Available cells are: ");

        StringBuilder cellPossibilities = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            if (applicableCells[i] > 0 && allGameBoards.get(currentPlayer - 1).getScoreBoard()[i] == -1) {
                cellPossibilities.append("[").append(i + 1).append("] ");
            }
        }
        if (applicableCells[6] > 0 && allGameBoards.get(currentPlayer - 1).getScoreBoard()[6] == -1) {
            cellPossibilities.append("[F]ull House");
        }
        System.out.println(cellPossibilities);
        System.out.println();
        return cellPossibilities.toString();
    }

    private static boolean checkIfCellIsOccupied(ArrayList<GameBoard> allGameBoards, int currentPlayer, String cellSelection) {
        int cell;
        try {
            cell = Integer.parseInt(cellSelection) - 1;
        } catch (NumberFormatException e) {
            cell = 6;
        }

        if (cell > 6) {
            return false;
        }

        return allGameBoards.get(currentPlayer - 1).getScoreBoard()[cell] != -1;
    }
}

