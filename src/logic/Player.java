package logic;

import logic.interfaces.IPlayer;

import java.util.concurrent.TimeUnit;

public class Player implements IPlayer {

    private static final int MINES_AMOUNT = 2;
    private final Board board;
    private final String name;
    private int hits;
    private int misses;
    private int turns;
    private int score;
    private long time;
    private long turnStartTime;
    private int mines;

    Player(String name, Board board) {
        this.name = name;
        this.board = board;
        hits = 0;
        misses = 0;
        score = 0;
        turns = 0;
        time = 0;
        mines = MINES_AMOUNT;
    }

    //TODO
    @Override
    public int getUserId() {
        return 0;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name + "  -  Score: " + score + "  Turns: " + turns + "  Hits: " + hits + "  Misses: " + misses +
                "  Mines: " + (MINES_AMOUNT - mines) + "/" + MINES_AMOUNT + "  Average move time: " + averageTime();
    }

    // **************************************************** //
    // Increase hits amonut and turns amount by 1 and handles time
    // **************************************************** //
    void increaseHit() {
        hits++;
        updateTime();
    }

    // **************************************************** //
    // Increase misses amonut and turns amount by 1 and handles time
    // **************************************************** //
    void increaseMiss() {
        misses++;
        updateTime();
    }

    // **************************************************** //
    // Decrease mines amount
    // **************************************************** //
    void decreaseMine() {
        mines--;
        updateTime();
    }

    // **************************************************** //
    // Increase player score
    // **************************************************** //
    void increaseScore(int amount) {
        this.score += amount;
    }

    Board getBoard() {
        return board;
    }

    int getTurns() {
        return turns;
    }

    void setTurns(int turns) {
        this.turns = turns;
    }

    void setTurnStartTime(long turnStartTime) {
        this.turnStartTime = turnStartTime;
    }

    int getMines() {
        return mines;
    }

    int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    private void updateTime() {
        time += System.currentTimeMillis() - turnStartTime;
        turns++;
        turnStartTime = System.currentTimeMillis();
    }

    private String averageTime() {
        long avg;
        if (turns != 0) {
            avg = time / turns;
        } else {
            avg = 0;
        }
        return String.format("%02d:%02d",
                             TimeUnit.MILLISECONDS.toMinutes(avg),
                             TimeUnit.MILLISECONDS.toSeconds(avg) -
                                     TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(avg)));
    }
}
