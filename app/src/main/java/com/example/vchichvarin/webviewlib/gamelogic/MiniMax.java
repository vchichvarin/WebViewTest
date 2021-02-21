package com.example.vchichvarin.webviewlib.gamelogic;

import java.util.Iterator;

class MiniMax implements IAI {
    private static final MiniMax.Line[] lines = new MiniMax.Line[]{new MiniMax.Line(new Pos(0, 0), new Pos(0, 1), new Pos(0, 2)), new MiniMax.Line(new Pos(1, 0), new Pos(1, 1), new Pos(1, 2)), new MiniMax.Line(new Pos(2, 0), new Pos(2, 1), new Pos(2, 2)), new MiniMax.Line(new Pos(0, 0), new Pos(1, 0), new Pos(2, 0)), new MiniMax.Line(new Pos(0, 1), new Pos(1, 1), new Pos(2, 1)), new MiniMax.Line(new Pos(0, 2), new Pos(1, 2), new Pos(2, 2)), new MiniMax.Line(new Pos(0, 0), new Pos(1, 1), new Pos(2, 2)), new MiniMax.Line(new Pos(0, 2), new Pos(1, 1), new Pos(2, 0))};
    private Seed ourSeed;
    private Seed oppSeed;

    MiniMax() {
    }

    public Pos findOptimalMovement(IBoard board, Seed seed) {
        this.ourSeed = seed;
        this.oppSeed = invertSeed(this.ourSeed);
        Score score = this.miniMax(board, this.ourSeed, 3);
        return score.getPos();
    }

    private static Seed invertSeed(Seed seed) {
        if (seed == Seed.Empty) {
            return seed;
        } else {
            return seed == Seed.O ? Seed.X : Seed.O;
        }
    }

    private Score miniMax(IBoard board, Seed seed, int depth) {
        int bestScore = seed == this.ourSeed ? -2147483648 : 2147483647;
        Pos bestPos = null;
        if (depth != 0 && !board.getGameStatus().isOver()) {
            Iterator var6 = board.getEmptyPositions().iterator();

            while(var6.hasNext()) {
                Pos move = (Pos)var6.next();
                IBoard clonedBoard = board.duplicate();
                clonedBoard.setSeedAtPosition(move, seed);
                int currentScore;
                if (seed == this.ourSeed) {
                    currentScore = this.miniMax(clonedBoard, this.oppSeed, depth - 1).getScorePoints();
                    if (currentScore > bestScore) {
                        bestScore = currentScore;
                        bestPos = move;
                    }
                } else {
                    currentScore = this.miniMax(clonedBoard, this.ourSeed, depth - 1).getScorePoints();
                    if (currentScore < bestScore) {
                        bestScore = currentScore;
                        bestPos = move;
                    }
                }
            }
        } else {
            bestScore = this.evaluate(board);
        }

        return new Score(bestPos, bestScore);
    }

    private int evaluateSimple(IBoard board) {
        GameStatus status = board.getGameStatus();
        if (status.getWinnerSeed() == this.ourSeed) {
            return 100;
        } else {
            return status.getWinnerSeed() == this.oppSeed ? -100 : 0;
        }
    }

    private int evaluate(IBoard board) {
        int score = 0;
        MiniMax.Line[] var3 = lines;
        int var4 = var3.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            MiniMax.Line line = var3[var5];
            score += this.evaluateLine(board, line);
        }

        return score;
    }

    private int evaluateLine(IBoard board, MiniMax.Line line) {
        int score = 0;
        Seed cell1 = board.getSeedAtPosition(line.getPos(0));
        Seed cell2 = board.getSeedAtPosition(line.getPos(1));
        Seed cell3 = board.getSeedAtPosition(line.getPos(2));
        if (cell1 == this.ourSeed) {
            score = 1;
        } else if (cell1 == this.oppSeed) {
            score = -1;
        }

        if (cell2 == this.ourSeed) {
            if (score == 1) {
                score = 10;
            } else {
                if (score == -1) {
                    return 0;
                }

                score = 1;
            }
        } else if (cell2 == this.oppSeed) {
            if (score == -1) {
                score = -10;
            } else {
                if (score == 1) {
                    return 0;
                }

                score = -1;
            }
        }

        if (cell3 == this.ourSeed) {
            if (score > 0) {
                score *= 10;
            } else {
                if (score < 0) {
                    return 0;
                }

                score = 1;
            }
        } else if (cell3 == this.oppSeed) {
            if (score < 0) {
                score *= 10;
            } else {
                if (score > 1) {
                    return 0;
                }

                score = -1;
            }
        }

        return score;
    }

    private static class Line {
        private final Pos[] line = new Pos[3];

        Line(Pos pos1, Pos pos2, Pos pos3) {
            this.line[0] = pos1;
            this.line[1] = pos2;
            this.line[2] = pos3;
        }

        Pos getPos(int index) {
            return this.line[index];
        }
    }
}
