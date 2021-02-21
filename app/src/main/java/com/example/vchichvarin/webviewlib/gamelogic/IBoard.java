package com.example.vchichvarin.webviewlib.gamelogic;

import java.util.Set;

public interface IBoard {
    Set<Pos> getEmptyPositions();

    Seed getSeedAtPosition(Pos var1);

    void setSeedAtPosition(Pos var1, Seed var2);

    GameStatus getGameStatus();

    IBoard duplicate();
}
