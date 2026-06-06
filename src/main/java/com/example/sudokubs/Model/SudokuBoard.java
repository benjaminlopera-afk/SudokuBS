package com.example.sudokubs.Model;

import com.example.sudokubs.Model.datastructures.Dequeue;
import com.example.sudokubs.Model.datastructures.IDequeue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SudokuBoard {

    private final IDequeue<IDequeue<Integer>> board;
    private final IDequeue<IDequeue<Integer>> solution;
    private final IDequeue<IDequeue<Boolean>> fixed;
    private static final int SIZE = 6;
    private final Random random = new Random();

    public SudokuBoard() {
        board = new Dequeue<>();
        solution = new Dequeue<>();
        fixed = new Dequeue<>();

        for (int i = 0; i < SIZE; i++) {
            IDequeue<Integer> boardRow = new Dequeue<>();
            IDequeue<Integer> solutionRow = new Dequeue<>();
            IDequeue<Boolean> fixedRow = new Dequeue<>();
            for (int j = 0; j < SIZE; j++) {
                boardRow.addLast(0);
                solutionRow.addLast(0);
                fixedRow.addLast(false);
            }
            board.addLast(boardRow);
            solution.addLast(solutionRow);
            fixed.addLast(fixedRow);
        }
    }

    public int getValue(int row, int col) {
        return board.get(row).get(col);
    }

    public void setValue(int row, int col, int value) {
        if (!fixed.get(row).get(col)) {
            board.get(row).set(col, value);
        }
    }

    public boolean isFixed(int row, int col) {
        return fixed.get(row).get(col);
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
                board.get(i).set(j, 0);
                fixed.get(i).set(j, false);
            }
        }
    }

    private boolean solveBoard() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (board.get(row).get(col) == 0) {
                    List<Integer> values = new ArrayList<>();
                    for (int v = 1; v <= SIZE; v++) values.add(v);
                    Collections.shuffle(values, random);
                    for (int value : values) {
                        if (isValidPlacement(row, col, value)) {
                            board.get(row).set(col, value);
                            fixed.get(row).set(col, true);
                            if (solveBoard()) return true;
                            board.get(row).set(col, 0);
                            fixed.get(row).set(col, false);
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
                solution.get(r).set(c, board.get(r).get(c));
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
                    if (board.get(row).get(col) != 0) {
                        board.get(row).set(col, 0);
                        fixed.get(row).set(col, false);
                        hidden++;
                    }
                }
            }
        }
    }

    public boolean isValidPlacement(int row, int col, int value) {
        for (int c = 0; c < SIZE; c++)
            if (board.get(row).get(c) == value) return false;
        for (int r = 0; r < SIZE; r++)
            if (board.get(r).get(col) == value) return false;
        int startRow = (row / 2) * 2;
        int startCol = (col / 3) * 3;
        for (int r = startRow; r < startRow + 2; r++)
            for (int c = startCol; c < startCol + 3; c++)
                if (board.get(r).get(c) == value) return false;
        return true;
    }

    public int[] getHint(SudokuBoardValidator validator) {
        List<int[]> emptyCells = new ArrayList<>();
        for (int row = 0; row < SIZE; row++)
            for (int col = 0; col < SIZE; col++)
                if (board.get(row).get(col) == 0)
                    emptyCells.add(new int[]{row, col});

        if (emptyCells.size() <= 1) return null;

        Collections.shuffle(emptyCells, random);
        int[] cell = emptyCells.get(0);
        return new int[]{cell[0], cell[1], solution.get(cell[0]).get(cell[1])};
    }
}