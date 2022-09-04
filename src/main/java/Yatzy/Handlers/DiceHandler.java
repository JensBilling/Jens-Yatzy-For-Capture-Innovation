package Yatzy.Handlers;

import Yatzy.Entities.Dice;

public class DiceHandler {
    private static final int[] diceArray = new int[5];

    public static int[] rollAllDice() {
        for (int i = 0; i < 5; i++) {
            diceArray[i] = Dice.rollDice();
        }
        return diceArray;
    }

    public static int rollOneDie(){
        return (int) (Math.random() * 6) +1;
    }

    public static int[] checkApplicableCells(int[] diceRolls) {
        int[] applicableCells = {0, 0, 0, 0, 0, 0, 0};

        // Check applicable nums
        for (int roll : diceRolls) {
            if (roll == 1) {
                applicableCells[0]++;
            } else if (roll == 2) {
                applicableCells[1]++;
            } else if (roll == 3) {
                applicableCells[2]++;
            } else if (roll == 4) {
                applicableCells[3]++;
            } else if (roll == 5) {
                applicableCells[4]++;
            } else if (roll == 6) {
                applicableCells[5]++;
            }
        }

        // Check for full house
        boolean threeOfAKind = false;
        boolean twoOfAKind = false;
        for (int roll : applicableCells) {
            if (roll == 3){
                threeOfAKind = true;
            }
            if (roll == 2){
                twoOfAKind = true;
            }
        }

        if (threeOfAKind && twoOfAKind) {
            applicableCells[6] = 1;
        }

        return applicableCells;
    }
}
