package com.example.vchichvarin.webviewlib.gamelogic;

public class Pos {
    private final int row;
    private final int col;

    public Pos(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return this.row;
    }

    public int getCol() {
        return this.col;
    }

    public boolean equals(Object o) {
        if (!(o instanceof Pos)) {
            return false;
        } else {
            Pos other = (Pos)o;
            if (other == this) {
                return true;
            } else {
                return other.row == this.row && other.col == this.col;
            }
        }
    }

    public int hashCode() {
        return this.row ^ this.col;
    }

    public String toString() {
        return "(" + this.row + "," + this.col + ")";
    }
}
