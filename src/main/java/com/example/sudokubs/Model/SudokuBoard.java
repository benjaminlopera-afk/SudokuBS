package com.example.sudokubs.Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SudokuBoard {

    private final int[][] board;
    private final boolean[][] fixed;
    private static final int SIZE = 6;
    private final Random random = new Random();

    public SudokuBoard() {
        board = new int[SIZE][SIZE];
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
        for (int blockRow = 0; blockRow < 3; blockRow++) {
            for (int blockCol = 0; blockCol < 2; blockCol++) {
                placeNumbersInBlock(blockRow, blockCol);
            }
        }
    }

    public void clearBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = 0;
                fixed[i][j] = false;
            }
        }
    }

    private void placeNumbersInBlock(int blockRow, int blockCol) {
        int startRow = blockRow * 2; // bloques de 2 filas
        int startCol = blockCol * 3; // bloques de 3 columnas
        int placed = 0;
        int maxAttempts = 100;
        int attempts = 0;

        while (placed < 2 && attempts < maxAttempts) {
            int row = startRow + random.nextInt(2);
            int col = startCol + random.nextInt(3);
            int value = random.nextInt(SIZE) + 1;

            if (board[row][col] == 0 && isValidPlacement(row, col, value)) {
                board[row][col] = value;
                fixed[row][col] = true;
                placed++;
            }
            attempts++;
        }
    }


    public boolean isValidPlacement(int row, int col, int value) {
        // Verificar fila
        for (int c = 0; c < SIZE; c++) {
            if (board[row][c] == value) return false;
        }
        // Verificar columna
        for (int r = 0; r < SIZE; r++) {
            if (board[r][col] == value) return false;
        }
        // Verificar bloque 2x3
        int startRow = (row / 2) * 2;
        int startCol = (col / 3) * 3;
        for (int r = startRow; r < startRow + 2; r++) {
            for (int c = startCol; c < startCol + 3; c++) {
                if (board[r][c] == value) return false;
            }
        }
        return true;
    }
}