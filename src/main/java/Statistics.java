/**
 * Created by pjoa09 on 9/17/17.
 */
public class Statistics {


    public String getTurn() {
        return turn;
    }

    public void setTurn(String turn) {
        this.turn = turn;
    }

    private static String turn;
    private int blackScore;
    private int whiteScore;

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    private static String mode;

    public String getCurrentWinner() {
        return currentWinner;
    }

    public void setCurrentWinner(String currentWinner) {
        this.currentWinner = currentWinner;
    }

    private String currentWinner;

    public int getEmptyScore() {
        return emptyScore;
    }

    public void setEmptyScore(int emptyScore) {
        this.emptyScore = emptyScore;
    }

    private int emptyScore;

    public int getBlackScore() {
        return blackScore;
    }

    public void setBlackScore(int blackScore) {
        this.blackScore = blackScore;
    }

    public int getWhiteScore() {
        return whiteScore;
    }

    public void setWhiteScore(int whiteScore) {
        this.whiteScore = whiteScore;
    }








}
