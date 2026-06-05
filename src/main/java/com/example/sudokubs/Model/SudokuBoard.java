package com.example.sudokubs.Model;

public class SudokuBoard {

    private final int[][] board;

    public SudokuBoard() {
        board = new int[6][6];
    }

    public int getValue(int row, int col) {
        return board[row][col];
    }

    public void setValue(int row, int col, int value) {
        board[row][col] = value;
    }

    public int[][] getBoard() {
        return board;
    }
}