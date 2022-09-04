package Yatzy.Entities;

public class GameBoard {

    private final int[] scoreBoard = new int[7];

    public GameBoard() {
        for (int i = 0; i < 7; i++) {
            scoreBoard[i] = -1;
        }
    }

    public int[] getScoreBoard() {
        return scoreBoard;
    }

    public void setScore(String inputCell, int score) {
        int cell;
        try {
            cell = Integer.parseInt(inputCell)-1;
        } catch (NumberFormatException e) {
            cell = 6;
        }

        scoreBoard[cell] = score;
    }
}
