package Yatzy.Handlers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DiceHandlerTest {

    @Test
    void rollAllDiceShouldReturn5RandomDice() {
        var allDice = DiceHandler.rollAllDice();
        assertEquals(5, allDice.length);
    }

    @Test
    void rollOneDieReturnsOneRandomDie() {
        for (int i = 0; i < 20; i++) {
            assertTrue(DiceHandler.rollOneDie() >= 1 && DiceHandler.rollOneDie() <= 6);
        }
    }

    @Test
    void checkApplicableCellsReturnsFullHouse() {
        int[] fullHouseDice = {3,3,5,5,5};
        var applicableCellsFullHouse = DiceHandler.checkApplicableCells(fullHouseDice);
        assertEquals(0, applicableCellsFullHouse[0]);
        assertEquals(0, applicableCellsFullHouse[1]);
        assertEquals(2, applicableCellsFullHouse[2]);
        assertEquals(0, applicableCellsFullHouse[3]);
        assertEquals(3, applicableCellsFullHouse[4]);
    }

}