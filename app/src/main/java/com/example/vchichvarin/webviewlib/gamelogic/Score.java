package com.example.vchichvarin.webviewlib.gamelogic;

class Score {
    private final Pos pos;
    private final int scorePoints;

    Pos getPos() {
        return this.pos;
    }

    int getScorePoints() {
        return this.scorePoints;
    }

    Score(Pos pos, int scorePoints) {
        this.pos = pos;
        this.scorePoints = scorePoints;
    }
}
