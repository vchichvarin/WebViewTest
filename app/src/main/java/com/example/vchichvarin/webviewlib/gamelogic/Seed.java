package com.example.vchichvarin.webviewlib.gamelogic;

public enum Seed {
    Empty("-"),
    O("O"),
    X("X");

    private final String value;

    private Seed(String value) {
        this.value = value;
    }

    public String toString() {
        return this.value;
    }
}
