package com.example.vchichvarin.webviewlib.gamelogic;

class Player implements IPlayer {
    private final Seed ourSeed;
    private final IBoard board;
    private final IAI ai;

    Player(Seed seed, IBoard board, IAI ai) {
        this.ourSeed = seed;
        this.board = board;
        this.ai = ai;
    }

    public Pos moveToAi() {
        Pos pos = this.ai.findOptimalMovement(this.board, this.getSeed());
        this.board.setSeedAtPosition(pos, this.ourSeed);
        return pos;
    }

    public void moveTo(Pos pos) {
        this.board.setSeedAtPosition(pos, this.getSeed());
    }

    public Seed getSeed() {
        return this.ourSeed;
    }
}
