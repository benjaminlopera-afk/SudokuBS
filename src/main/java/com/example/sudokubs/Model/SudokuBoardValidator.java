package com.example.sudokubs.Model;

public class SudokuBoardValidator implements SudokuValidator {

    private final SudokuBoard board;
    private static final int SIZE = 6;

    public SudokuBoardValidator(SudokuBoard board) {
        this.board = board;
    }

    @Override
    public boolean isValidMove(int row, int col, int value) {
        return isValidInRow(row, col, value)
                && isValidInCol(row, col, value)
                && isValidInBlock(row, col, value);
    }

    private boolean isValidInRow(int row, int col, int value) {
        for (int c = 0; c < SIZE; c++) {
            if (c != col && board.getValue(row, c) == value) {
                return false;
            }
        }
        return true;
    }

    private boolean isValidInCol(int row, int col, int value) {
        for (int r = 0; r < SIZE; r++) {
            if (r != row && board.getValue(r, col) == value) {
                return false;
            }
        }
        return true;
    }

    private boolean isValidInBlock(int row, int col, int value) {
        int startRow = (row / 2) * 2;
        int startCol = (col / 3) * 3;
        for (int r = startRow; r < startRow + 2; r++) {
            for (int c = startCol; c < startCol + 3; c++) {
                if (r != row && c != col && board.getValue(r, c) == value) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isBoardComplete() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                int value = board.getValue(row, col);
                if (value == 0 || !isValidMove(row, col, value)) {
                    return false;
                }
            }
        }
        return true;
    }
}