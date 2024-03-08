package com.example.game_of_life_lab4;

import java.util.concurrent.ThreadLocalRandom;

public class GameOfLifeCore {
    public static final int SIZE = 50;
    private boolean[][] values;

    public boolean[][] generateInitialValues(Double probability) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                values[i][j] = ThreadLocalRandom.current().nextDouble(0, 1) < probability;
            }
        }
        return values;
    }

    public boolean[][] getValues() {
        return values;
    }

    public boolean[][] newGeneration() {
        boolean[][] newValues = new boolean[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                int alive = amountOfAlive(i, j);
                if (values[i][j]) {
                    if (alive == 2 || alive == 3) {
                        newValues[i][j] = true;
                    } else {
                        newValues[i][j] = false;
                    }
                } else {
                    if (alive == 3) {
                        newValues[i][j] = true;
                    } else
                        newValues[i][j] = false;
                }
            }
        }
        values = newValues;
        return values;
    }

    private int amountOfAlive(int row, int column) {
        int aliveNeighbors = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) continue;
                if (row + i >= 0 && row + i < SIZE && column + j >= 0 && column + j < SIZE) {
                    if (values[row + i][column + j])
                        aliveNeighbors++;
                }
            }
        }
        return aliveNeighbors;
    }

    public GameOfLifeCore() {
        values = new boolean[SIZE][SIZE];
    }

}
