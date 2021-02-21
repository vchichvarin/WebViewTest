package com.example.vchichvarin.webviewlib.gamelogic;

interface IPlayer {
    Pos moveToAi();

    void moveTo(Pos var1);

    Seed getSeed();
}
