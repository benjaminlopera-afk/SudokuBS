package com.example.sudokubs.Model;

/**
 * Validador del tablero de Sudoku.
 * Verifica que los números ingresados cumplan con las reglas del juego,
 * comprobando que no se repitan en filas, columnas ni bloques 2x3.
 */
public class SudokuBoardValidator implements SudokuValidator {

    /** Tablero de Sudoku a validar. */
    private final SudokuBoard board;

    /** Tamaño del tablero. */
    private static final int SIZE = 6;

    /**
     * Construye un validador para el tablero indicado.
     *
     * @param board el tablero de Sudoku a validar
     */
    public SudokuBoardValidator(SudokuBoard board) {
        this.board = board;
    }

    /**
     * Verifica si colocar un valor en la posición indicada es un movimiento válido.
     * Un movimiento es válido si el valor no se repite en la fila, columna ni bloque 2x3.
     *
     * @param row   índice de fila (0-5)
     * @param col   índice de columna (0-5)
     * @param value valor a verificar (1-6)
     * @return true si el movimiento es válido, false en caso contrario
     */
    @Override
    public boolean isValidMove(int row, int col, int value) {
        return isValidInRow(row, col, value)
                && isValidInCol(row, col, value)
                && isValidInBlock(row, col, value);
    }

    /**
     * Verifica que el valor no se repita en la fila indicada.
     *
     * @param row   índice de fila
     * @param col   índice de columna de la celda verificada (excluida)
     * @param value valor a verificar
     * @return true si no hay repetición en la fila
     */
    private boolean isValidInRow(int row, int col, int value) {
        for (int c = 0; c < SIZE; c++) {
            if (c != col && board.getValue(row, c) == value) {
                return false;
            }
        }
        return true;
    }

    /**
     * Verifica que el valor no se repita en la columna indicada.
     *
     * @param row   índice de fila de la celda verificada (excluida)
     * @param col   índice de columna
     * @param value valor a verificar
     * @return true si no hay repetición en la columna
     */
    private boolean isValidInCol(int row, int col, int value) {
        for (int r = 0; r < SIZE; r++) {
            if (r != row && board.getValue(r, col) == value) {
                return false;
            }
        }
        return true;
    }

    /**
     * Verifica que el valor no se repita en el bloque 2x3 que contiene la celda.
     *
     * @param row   índice de fila
     * @param col   índice de columna
     * @param value valor a verificar
     * @return true si no hay repetición en el bloque
     */
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

    /**
     * Verifica si el tablero está completo y correctamente resuelto.
     *
     * @return true si todas las celdas están llenas y son válidas
     */
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