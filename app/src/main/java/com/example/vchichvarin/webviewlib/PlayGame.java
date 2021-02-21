package com.example.vchichvarin.webviewlib;

import android.app.Activity;
import android.widget.Button;
import android.widget.TextView;

import com.example.vchichvarin.webviewlib.gamelogic.*;

public class PlayGame {
    private static final int N = 3;
    private static Button[][] fieldButtons = new Button[N][N];
    private static Button btnPlayComputer;
    private static Button btnNewGame;
    private static TextView infoLabel;
    private static Game game;
    private static boolean stopGame = false;
    private static Seed winnerSeed;
    private static IBoard board;

    public static void init (Activity activity) {
        btnPlayComputer = activity.findViewById(R.id.button_ai);
        btnNewGame = activity.findViewById(R.id.button_new_game);
        infoLabel = activity.findViewById(R.id.infoLabel);
        fieldButtons[0][0] = activity.findViewById(R.id.button_0_0);
        fieldButtons[0][1] = activity.findViewById(R.id.button_0_1);
        fieldButtons[0][2] = activity.findViewById(R.id.button_0_2);
        fieldButtons[1][0] = activity.findViewById(R.id.button_1_0);
        fieldButtons[1][1] = activity.findViewById(R.id.button_1_1);
        fieldButtons[1][2] = activity.findViewById(R.id.button_1_2);
        fieldButtons[2][0] = activity.findViewById(R.id.button_2_0);
        fieldButtons[2][1] = activity.findViewById(R.id.button_2_1);
        fieldButtons[2][2] = activity.findViewById(R.id.button_2_2);
    }

    public static void newGame() {
        infoLabel.setText("ИДЕТ ИГРА !!!");
        stopGame = false;
        game = new Game((sender, winner) -> {
            stopGame = true;
            winnerSeed = winner;
        });
        board = game.getBoard();
    }

    public static void initFieldButtons() {
        for (int col = 0; col < N; col++) {
            for (int row = 0; row < N; row++) {
                fieldButtons[row][col].setText("-");
                fieldButtons[row][col].setTag(new Pos(row, col));
                // Обработчик нажатия кнопок на игровом поле
                fieldButtons[row][col].setOnClickListener(v -> {
                    onClick((Pos) v.getTag());
                });
            }
        }
    }

    private static void onClick(Pos playerStepPos) {
        fieldButtons[playerStepPos.getRow()][playerStepPos.getCol()].setEnabled(false);
        game.doHumanMoveTo(playerStepPos);
        if (stopGame) {
            refreshUi();
            finishGame(winnerSeed);
            return;
        }
        Pos machineStepPos = game.doMachineMove();
        fieldButtons[machineStepPos.getRow()][machineStepPos.getCol()].setEnabled(false);
        refreshUi();
        if (stopGame) {
            finishGame(winnerSeed);
        }
    }

    private static void clearButtons() {
        for (int col = 0; col < N; col++) {
            for (int row = 0; row < N; row++) {
                Button button = fieldButtons[row][col];
                button.setText("-");
                button.setEnabled(true);
            }
        }
        btnPlayComputer.setEnabled(true);
    }

    private static void refreshUi() {
        for (int col = 0; col < N; col++) {
            for (int row = 0; row < N; row++) {
                fieldButtons[row][col].setText(board.getSeedAtPosition(new Pos(row, col)).toString());
            }
        }
    }

    private static void finishGame(Seed winnerSeed) {
        btnPlayComputer.setEnabled(false);
        for (int col = 0; col < N; col++) {
            for (int row = 0; row < N; row++) {
                fieldButtons[row][col].setEnabled(false);
            }
        }
        String result = "???";
        switch (winnerSeed) {
            case Empty:
                result = "Ничья";
                break;
            case X:
                result = "Выиграл ЧЕЛОВЕК";
                break;
            case O:
                result = "Выиграл КОМПЬЮТЕР";
                break;
        }
        infoLabel.setText(result);
    }

    public static void createControlButtons() {
        btnPlayComputer.setOnClickListener(e -> {
            Pos pos = game.doHumanMoveToAi();
            fieldButtons[pos.getRow()][pos.getCol()].setEnabled(false);
            refreshUi();
            if (stopGame) {
                finishGame(winnerSeed);
                return;
            }
            Pos machineStepPos = game.doMachineMove();
            fieldButtons[machineStepPos.getRow()][machineStepPos.getCol()].setEnabled(false);
            refreshUi();
            if (stopGame) {
                finishGame(winnerSeed);
            }
        });
        btnNewGame.setOnClickListener(e -> {
            newGame();
            clearButtons();
            refreshUi();
        });
    }

}
