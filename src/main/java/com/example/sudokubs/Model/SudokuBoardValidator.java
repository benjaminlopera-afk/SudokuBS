package com.example.sudokubs.Model;

public class SudokuBoardValidator implements SudokuValidator {

    private final SudokuBoard board;

    public SudokuBoardValidator(SudokuBoard board) {
        this.board = board;
    }

    @Override
    public boolean isValidMove(int row, int col, int value) {
        return true;
    }
}