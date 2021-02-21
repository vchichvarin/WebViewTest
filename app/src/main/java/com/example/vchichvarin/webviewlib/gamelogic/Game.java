package com.example.vchichvarin.webviewlib.gamelogic;

public class Game {
    private final IBoard board;
    private final IPlayer playerHuman;
    private final IPlayer playerMachine;
    private final IGameOverHandler gameIsOverHandler;
    private IPlayer nextPlayer;

    public Game(IGameOverHandler gameIsOverHandler) {
        this.gameIsOverHandler = gameIsOverHandler;
        this.board = new Board();
        IAI ai = new MiniMax();
        this.playerHuman = new Player(Seed.X, this.board, ai);
        this.playerMachine = new Player(Seed.O, this.board, ai);
        this.nextPlayer = this.playerHuman;
    }

    public IBoard getBoard() {
        return this.board;
    }

    public void doHumanMoveTo(Pos pos) {
        this.checkPlayer(this.playerHuman);
        this.playerHuman.moveTo(pos);
        this.turnToTheOppositePlayer(this.playerMachine);
    }

    public Pos doHumanMoveToAi() {
        this.checkPlayer(this.playerHuman);
        Pos pos = this.playerHuman.moveToAi();
        this.turnToTheOppositePlayer(this.playerMachine);
        return pos;
    }

    public Pos doMachineMove() {
        this.checkPlayer(this.playerMachine);
        Pos pos = this.playerMachine.moveToAi();
        this.turnToTheOppositePlayer(this.playerHuman);
        return pos;
    }

    private void turnToTheOppositePlayer(IPlayer oppositePlayer) {
        GameStatus status = this.board.getGameStatus();
        if (status.isOver()) {
            this.nextPlayer = null;
            if (this.gameIsOverHandler != null) {
                this.gameIsOverHandler.handleGameIsOver(this, status.getWinnerSeed());
            }
        } else {
            this.nextPlayer = oppositePlayer;
        }

    }

    private void checkPlayer(IPlayer player) {
        if (this.nextPlayer != player) {
            throw new GameException("Сейчас не ваш ход!");
        }
    }
}
