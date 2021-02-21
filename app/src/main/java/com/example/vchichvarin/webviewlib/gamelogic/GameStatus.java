package com.example.vchichvarin.webviewlib.gamelogic;

public class GameStatus {
    private final boolean isOver;
    private final Seed winner;

    public GameStatus(boolean isFinished, Seed winnerSeed) {
        this.isOver = isFinished;
        this.winner = winnerSeed;
    }

    public boolean isOver() {
        return this.isOver;
    }

    public Seed getWinnerSeed() {
        return this.winner;
    }
}
