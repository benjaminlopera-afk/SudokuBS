package com.example.sudokubs.Model;

public interface SudokuValidator {

    boolean isValidMove(int row, int col, int value);
}