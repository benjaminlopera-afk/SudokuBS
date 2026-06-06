package com.example.sudokubs.Model;

/**
 * Interfaz que define el contrato para la validación de movimientos en el tablero de Sudoku.
 * Cualquier clase que implemente esta interfaz debe proporcionar la lógica
 * para verificar si un movimiento es válido según las reglas del juego.
 */
public interface SudokuValidator {

    /**
     * Verifica si colocar un valor en la posición indicada es un movimiento válido.
     *
     * @param row   índice de fila (0-5)
     * @param col   índice de columna (0-5)
     * @param value valor a verificar (1-6)
     * @return true si el movimiento es válido, false en caso contrario
     */
    boolean isValidMove(int row, int col, int value);
}