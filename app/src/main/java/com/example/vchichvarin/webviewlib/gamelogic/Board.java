package com.example.vchichvarin.webviewlib.gamelogic;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

class Board implements IBoard {
    private static final int N = 3;
    private final Seed[][] cells = new Seed[3][3];
    private final Set<Pos> emptyPositions = new HashSet();

    Board() {
        forEachElement((row, col) -> {
            this.cells[row][col] = Seed.Empty;
            this.emptyPositions.add(new Pos(row, col));
        });
    }

    private static void forEachElement(IHandler action) {
        for(int row = 0; row < 3; ++row) {
            for(int col = 0; col < 3; ++col) {
                action.doAction(row, col);
            }
        }

    }

    public Set<Pos> getEmptyPositions() {
        return Collections.unmodifiableSet(this.emptyPositions);
    }

    public Seed getSeedAtPosition(Pos pos) {
        return this.cells[pos.getRow()][pos.getCol()];
    }

    public void setSeedAtPosition(Pos pos, Seed seed) {
        Seed currentSeed = this.cells[pos.getRow()][pos.getCol()];
        if (currentSeed != Seed.Empty) {
            throw new GameException("Позиция " + pos + " уже занята!");
        } else if (currentSeed != seed) {
            this.cells[pos.getRow()][pos.getCol()] = seed;
            this.emptyPositions.remove(pos);
        }
    }

    public GameStatus getGameStatus() {
        int[] rowScores = new int[3];
        int[] colScores = new int[3];
        int[] diag1Score = new int[1];
        int[] diag2Score = new int[1];
        forEachElement((row, col) -> {
            Seed seed = this.cells[row][col];
            int delta = this.getDelta(seed);
            rowScores[row] += delta;
            colScores[col] += delta;
            if (row == col) {
                diag1Score[0] += delta;
            }

            if (row == 3 - col - 1) {
                diag2Score[0] += delta;
            }

        });
        Seed[] var5 = new Seed[]{Seed.O, Seed.X};
        int var6 = var5.length;

        for(int var7 = 0; var7 < var6; ++var7) {
            Seed seed = var5[var7];
            int winPoints = 3 * this.getDelta(seed);

            for(int i = 0; i < 3; ++i) {
                if (rowScores[i] == winPoints || colScores[i] == winPoints) {
                    return new GameStatus(true, seed);
                }
            }

            if (diag1Score[0] == winPoints || diag2Score[0] == winPoints) {
                return new GameStatus(true, seed);
            }
        }

        return new GameStatus(this.getEmptyPositions().isEmpty(), Seed.Empty);
    }

    private int getDelta(Seed seed) {
        if (seed == Seed.X) {
            return 1;
        } else {
            return seed == Seed.O ? -1 : 0;
        }
    }

    public IBoard duplicate() {
        Board board = new Board();
        forEachElement((row, col) -> {
            Pos pos = new Pos(row, col);
            Seed seed = this.getSeedAtPosition(pos);
            board.setSeedAtPosition(pos, seed);
        });
        return board;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("  | 0 | 1 | 2 |\n");
        sb.append("--+---+---+---+\n");
        forEachElement((row, col) -> {
            if (col == 0) {
                sb.append(row);
                sb.append(" | ");
            }

            sb.append(this.cells[row][col]);
            sb.append(" | ");
            if (col == 2) {
                sb.append("\n--+---+---+---+\n");
            }

        });
        return sb.toString();
    }
}