package com.example.sudokubs.Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SudokuBoard {

    private final int[][] board;
    private final int[][] solution;
    private final boolean[][] fixed;
    private static final int SIZE = 6;
    private final Random random = new Random();

    public SudokuBoard() {
        board = new int[SIZE][SIZE];
        solution = new int[SIZE][SIZE];
        fixed = new boolean[SIZE][SIZE];
    }

    public int getValue(int row, int col) {
        return board[row][col];
    }

    public void setValue(int row, int col, int value) {
        if (!fixed[row][col]) {
            board[row][col] = value;
        }
    }

    public boolean isFixed(int row, int col) {
        return fixed[row][col];
    }

    public void generateBoard() {
        clearBoard();
        solveBoard();
        saveSolution();
        hideNumbers();
    }

    public void clearBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = 0;
                fixed[i][j] = false;
            }
        }
    }

    private boolean solveBoard() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (board[row][col] == 0) {
                    List<Integer> values = new ArrayList<>();
                    for (int v = 1; v <= SIZE; v++) values.add(v);
                    Collections.shuffle(values, random);
                    for (int value : values) {
                        if (isValidPlacement(row, col, value)) {
                            board[row][col] = value;
                            fixed[row][col] = true;
                            if (solveBoard()) return true;
                            board[row][col] = 0;
                            fixed[row][col] = false;
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private void saveSolution() {
        for (int r = 0; r < SIZE; r++)
            for (int c = 0; c < SIZE; c++)
                solution[r][c] = board[r][c];
    }

    private void hideNumbers() {
        for (int blockRow = 0; blockRow < 3; blockRow++) {
            for (int blockCol = 0; blockCol < 2; blockCol++) {
                int startRow = blockRow * 2;
                int startCol = blockCol * 3;
                int hidden = 0;
                while (hidden < 4) {
                    int row = startRow + random.nextInt(2);
                    int col = startCol + random.nextInt(3);
                    if (board[row][col] != 0) {
                        board[row][col] = 0;
                        fixed[row][col] = false;
                        hidden++;
                    }
                }
            }
        }
    }

    public boolean isValidPlacement(int row, int col, int value) {
        for (int c = 0; c < SIZE; c++)
            if (board[row][c] == value) return false;
        for (int r = 0; r < SIZE; r++)
            if (board[r][col] == value) return false;
        int startRow = (row / 2) * 2;
        int startCol = (col / 3) * 3;
        for (int r = startRow; r < startRow + 2; r++)
            for (int c = startCol; c < startCol + 3; c++)
                if (board[r][c] == value) return false;
        return true;
    }

    public int[] getHint(SudokuBoardValidator validator) {
        List<int[]> emptyCells = new ArrayList<>();
        for (int row = 0; row < SIZE; row++)
            for (int col = 0; col < SIZE; col++)
                if (board[row][col] == 0)
                    emptyCells.add(new int[]{row, col});

        if (emptyCells.size() <= 1) return null;

        Collections.shuffle(emptyCells, random);
        int[] cell = emptyCells.get(0);
        return new int[]{cell[0], cell[1], solution[cell[0]][cell[1]]};
    }
}