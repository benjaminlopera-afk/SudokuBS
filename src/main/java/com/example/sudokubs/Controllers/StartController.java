package com.example.sudokubs.Controllers;

import com.example.sudokubs.Applications.SudokuApplication;
import com.example.sudokubs.Utils.Paths;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * Controlador de la pantalla de inicio del juego.
 * Gestiona la navegación hacia la pantalla del tablero.
 */
public class StartController {

    /** Botón para iniciar el juego. */
    @FXML
    private Button btnJugar;

    /**
     * Maneja el evento del botón "Jugar".
     * Navega a la pantalla del tablero Sudoku.
     *
     * @param event evento de acción generado por el botón
     */
    @FXML
    void inicioJugar(ActionEvent event) {
        SudokuApplication.setScene(Paths.TABLERO);
    }
}